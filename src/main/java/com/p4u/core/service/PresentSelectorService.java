package com.p4u.core.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.p4u.core.beans.PresentPreferenceScore;
import com.p4u.core.dao.CategoryPreferenceRepository;
import com.p4u.core.dao.ItemRepository;
import com.p4u.core.dao.NotificationItemRepository;
import com.p4u.core.dao.PresentCategoryRepository;
import com.p4u.core.dao.UserPreferenceRepository;
import com.p4u.core.model.CategoryPreference;
import com.p4u.core.model.Item;
import com.p4u.core.model.NotificationItem;
import com.p4u.core.model.Present;
import com.p4u.core.model.PresentCategory;
import com.p4u.core.model.User;
import com.p4u.core.model.UserItemId;
import com.p4u.core.model.UserPreference;
import com.p4u.core.util.SessionIdentifierGenerator;

public class PresentSelectorService {
	
	private static final Logger logger = Logger.getLogger(PresentSelectorService.class.getName());

	private static final String PRESENT_MSG = " y P4U quieren estar presentes para vos con este regalo.";
	
	private static final String NO_REDEEM_STATUS = "Sin canjear";

	public List<Present> choosePresentForUser(UserPreferenceRepository userPreferenceRepository, Long userId,
			CategoryPreferenceRepository categoryPreferenceRepository,
			PresentCategoryRepository presentCategoryRepository) {
		List<UserPreference> userPreferences = userPreferenceRepository.findByUserId(userId);
		List<Present> aux = new ArrayList<Present>();
		List<Present> result = new ArrayList<Present>();
		for (UserPreference userPreference : userPreferences) {
			List<CategoryPreference> catPrefs = categoryPreferenceRepository
					.findByPreferenceId(userPreference.getId().getPreferenceId());
			for (CategoryPreference categoryPreference : catPrefs) {
				List<PresentCategory> presentCategories = presentCategoryRepository
						.findByCategoryId(categoryPreference.getCategoryId());
				for (PresentCategory presentCategory : presentCategories) {
					aux.add(presentCategory.getPresent());
				}
			}
		}
		Map<Present, Integer> occurrences = countPresentOccurrences(aux);
		List<PresentPreferenceScore> occurrencesScore = retrieveOrderedListForMostOccurrences(occurrences);
		for (PresentPreferenceScore presentPreferenceScore : occurrencesScore) {
			result.add(presentPreferenceScore.getPresent());
		}
		return result;
	}
	
	public boolean shouldMakeAPresent(){
		double aux = Math.random()*10;
		logger.info("Random value: " + aux);
		return aux>6.0;
	}
	
	public Present selectAPresent(List<NotificationItem> alreadyGiven, List<Present> selectable) {
		for (Present present : selectable) {
			boolean found = false;
			for (NotificationItem notificationItem : alreadyGiven) {
				if (notificationItem.getItem().getPresentId().equals(present.getId())) {
					found = true;
				}
			}
			if (!found) {
				return present;
			}
		}
		return null;
	}

	private List<PresentPreferenceScore> retrieveOrderedListForMostOccurrences(Map<Present, Integer> occurrences) {
		List<PresentPreferenceScore> result = new ArrayList<PresentPreferenceScore>();
		for (Present present : occurrences.keySet()) {
			result.add(new PresentPreferenceScore(present, occurrences.get(present)));
		}
		Collections.sort(result);
		return result;
	}

	private Map<Present, Integer> countPresentOccurrences(List<Present> presents) {
		Map<Present, Integer> result = new HashMap<Present, Integer>();
		for (Present present : presents) {
			if (result.get(present) == null) {
				result.put(present, 1);
			} else {
				result.put(present, result.get(present) + 1);
			}
		}
		return result;
	}

	public NotificationItem createNotificationItem(User user, Present selected,
			NotificationItemRepository notificationItemRepository, ItemRepository itemRepository) {
		Item item = new Item();
		item.setPresentCode(new SessionIdentifierGenerator().nextSessionId().substring(0,6).toUpperCase());
		item.setPresentId(selected.getId());
		item.setProductCode("");
		item.setState(NO_REDEEM_STATUS);
		item = itemRepository.save(item);		

		NotificationItem notificationItem = new NotificationItem();
		UserItemId userItemId = new UserItemId();
		userItemId.setItemId(item.getId());
		userItemId.setUserId(user.getId());
		notificationItem.setDate(new Date());
		notificationItem.setId(userItemId);
		notificationItem.setReceiver(user.getLastName() + ", " + user.getFirstName());
		notificationItem.setSender(selected.getCompany().getName());
		notificationItem.setMsg(selected.getCompany().getName()+PRESENT_MSG);
		notificationItem.setEmail(user.getEmail());
		notificationItem.setExpired(false);
		if(selected.getExpirationMinutes()!=null){
			notificationItem.setExpiration(addMinutes(new Date(), selected.getExpirationMinutes()));			
		}
		notificationItem = notificationItemRepository.save(notificationItem);
		return notificationItem;
	}
	
	private Date addMinutes(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

}
