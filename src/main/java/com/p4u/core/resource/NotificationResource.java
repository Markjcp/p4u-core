package com.p4u.core.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.CategoryPreferenceRepository;
import com.p4u.core.dao.ItemRepository;
import com.p4u.core.dao.NotificationItemRepository;
import com.p4u.core.dao.PresentCategoryRepository;
import com.p4u.core.dao.PresentRepository;
import com.p4u.core.dao.UserPreferenceRepository;
import com.p4u.core.dao.UserRepository;
import com.p4u.core.model.NotificationItem;
import com.p4u.core.model.Present;
import com.p4u.core.model.User;
import com.p4u.core.service.PresentSelectorService;

@Component
@Path("/notification")
public class NotificationResource {
	
	private static final Logger logger = Logger.getLogger(NotificationResource.class.getName());

	@Autowired
	@Qualifier("notificationItemRepository")
	private NotificationItemRepository notificationItemRepository;

	@Autowired
	@Qualifier("presentSelectorService")
	private PresentSelectorService presentSelectorService;

	@Autowired
	@Qualifier("userPreferenceRepository")
	private UserPreferenceRepository userPreferenceRepository;

	@Autowired
	@Qualifier("categoryPreferenceRepository")
	private CategoryPreferenceRepository categoryPreferenceRepository;

	@Autowired
	@Qualifier("presentCategoryRepository")
	private PresentCategoryRepository presentCategoryRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private ItemRepository itemRepository;
	
	@Autowired
	@Qualifier("presentRepository")
	private PresentRepository presentRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NotificationItem> retrieveNotificationItems(@PathParam("userId") Long userId) {
		User user = userRepository.findOne(userId);

		List<NotificationItem> result = new ArrayList<NotificationItem>();
		result = notificationItemRepository.findByUserId(userId);
		for (NotificationItem notificationItem : result) {
			if (notificationItem.getExpiration() != null) {
				notificationItem.setExpired(notificationItem.getExpiration().before(new Date()));
			}
		}
		//YOU SHOULD REMOVE THIS AFTER DEMO
		if (presentSelectorService.shouldMakeAPresent()) {
			logger.log(Level.INFO, "About to create a free present");
			List<Present> selectablePresents = presentSelectorService.choosePresentForUser(userPreferenceRepository,
					userId, categoryPreferenceRepository, presentCategoryRepository);
			if(selectablePresents!=null && !selectablePresents.isEmpty()){
				Present selected = presentSelectorService.selectAPresent(result, selectablePresents);
				if(selected!=null){
					result.add(presentSelectorService.createNotificationItem(user, selected, notificationItemRepository, itemRepository));
					selected.setStock(selected.getStock()-1);
					presentRepository.save(selected);
				}
			}
		}
		return result;
	}
}
