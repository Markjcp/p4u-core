package com.p4u.core.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p4u.core.beans.PresentBuyOrder;
import com.p4u.core.beans.PresentBuyResult;
import com.p4u.core.beans.Transfer;
import com.p4u.core.dao.CategoryPreferenceRepository;
import com.p4u.core.dao.CategoryRepository;
import com.p4u.core.dao.ItemBoughtRepository;
import com.p4u.core.dao.ItemRepository;
import com.p4u.core.dao.NotificationItemRepository;
import com.p4u.core.dao.PresentCategoryRepository;
import com.p4u.core.dao.PresentRepository;
import com.p4u.core.dao.UserPreferenceRepository;
import com.p4u.core.dao.UserRepository;
import com.p4u.core.model.Category;
import com.p4u.core.model.Item;
import com.p4u.core.model.ItemBought;
import com.p4u.core.model.NotificationItem;
import com.p4u.core.model.Present;
import com.p4u.core.model.PresentCategory;
import com.p4u.core.model.PresentCategoryId;
import com.p4u.core.model.User;
import com.p4u.core.model.UserItemId;
import com.p4u.core.service.PresentSelectorService;
import com.p4u.core.util.SessionIdentifierGenerator;

@Component
@Path("/present")
public class PresentResource {
	
	private static final String IMAGE_NAME_PREFIX = "p4u/image/";

	private static final Integer GENERIC_ERROR = 2;

	private static final String WRONG_STOCK = "Stock incorrecto";

	private static final Integer STOCK_ERROR = 1;

	private static final String NO_STOCK_MSG = "No hay stock suficiente para su P4U";

	private static final String REDEEM_STATUS = "Canjeado";
	
	private static final String NO_REDEEM_STATUS = "Sin canjear";

	private static final Integer OK_RESULT = 0;

	private static final String UKNOWN_ERROR = "Error inesperado";
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private ItemRepository itemRepository;

	@Autowired
	@Qualifier("presentRepository")
	private PresentRepository presentRepository;

	@Autowired
	@Qualifier("categoryPreferenceRepository")
	private CategoryPreferenceRepository categoryPreferenceRepository;

	@Autowired
	@Qualifier("presentCategoryRepository")
	private PresentCategoryRepository presentCategoryRepository;

	@Autowired
	@Qualifier("userPreferenceRepository")
	private UserPreferenceRepository userPreferenceRepository;
	
	@Autowired
	@Qualifier("notificationItemRepository")
	private NotificationItemRepository notificationItemRepository;
	
	@Autowired
	@Qualifier("itemBoughtRepository")
	private ItemBoughtRepository itemBoughtRepository;
	
	@Autowired
	@Qualifier("presentSelectorService")
	private PresentSelectorService presentSelectorService;	
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Present create(@Context HttpServletRequest request,  String jsonRequest) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Present present = mapper.readValue(jsonRequest, Present.class);
		Present result = presentRepository.save(present);
		if(result.getImage()!=null){
			result.setImageFileName(IMAGE_NAME_PREFIX+result.getId()+".jpg");
			result = presentRepository.save(present);
		}
		return result;
	}
	
	@POST
	@Path("add-present-to-category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PresentCategory addPresentToCategory(PresentCategoryId id){
		PresentCategory result = new PresentCategory();
		result.setId(id);
		return presentCategoryRepository.save(result);
	}
	
	@POST
	@Path("buy-present")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PresentBuyResult buyPresent(PresentBuyOrder order){
		try {
			User userFrom = userRepository.findOne(order.getUserFrom());
			User userTo = userRepository.findOne(order.getUserTo());
			Present present = presentRepository.findOne(order.getPresentId());
			if(order.getQuantity()== null || order.getQuantity().equals(0)){
				return new PresentBuyResult(GENERIC_ERROR,WRONG_STOCK);
			}
			if(order.getQuantity()>present.getStock()){
				return new PresentBuyResult(STOCK_ERROR,NO_STOCK_MSG);
			}
			Item item = new Item();
			item.setPresentCode(new SessionIdentifierGenerator().nextSessionId().substring(0,6).toUpperCase());
			item.setPresentId(present.getId());
			item.setProductCode("");
			item.setState(NO_REDEEM_STATUS);
			item = itemRepository.save(item);		
			present.setStock(present.getStock()-order.getQuantity());
			present = presentRepository.save(present);
			NotificationItem notificationItem = new NotificationItem();
			UserItemId userItemId = new UserItemId();
			userItemId.setItemId(item.getId());
			userItemId.setUserId(order.getUserTo());
			notificationItem.setDate(new Date());
			notificationItem.setId(userItemId);
			notificationItem.setReceiver(userTo.getLastName() + ", " + userTo.getFirstName());
			notificationItem.setSender(userFrom.getLastName() + ", " + userFrom.getFirstName());
			notificationItem.setMsg(order.getText());
			notificationItem.setEmail(userTo.getEmail());
			notificationItem.setExpired(false);
			notificationItem = notificationItemRepository.save(notificationItem);
			ItemBought itemBought = new ItemBought();
			UserItemId userItemIdFrom = new UserItemId();
			userItemIdFrom.setItemId(item.getId());
			userItemIdFrom.setUserId(order.getUserFrom());
			itemBought.setDate(new Date());
			itemBought.setId(userItemIdFrom);
			itemBoughtRepository.save(itemBought);
			return new PresentBuyResult(OK_RESULT,"");			
		} catch (Exception e) {
			return new PresentBuyResult(GENERIC_ERROR,UKNOWN_ERROR);
		}		
	}
	

	@POST
	@Path("redeem-present/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item redeemItem(@PathParam("itemId") Long itemId){
		Item item = itemRepository.findOne(itemId);
		item.setState(REDEEM_STATUS);
		return itemRepository.save(item);				
	}
	
	@POST
	@Path("transfer-present")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public NotificationItem transferPresent(Transfer transfer) {
		User userTo = userRepository.findOne(transfer.getUserTo());
		UserItemId userItemId = new UserItemId();
		userItemId.setItemId(transfer.getItemId());
		userItemId.setUserId(transfer.getUserFrom());
		NotificationItem notificationItem = notificationItemRepository.findOne(userItemId);
		NotificationItem newNotificationItem = transferItem(notificationItem, transfer.getUserTo(), transfer.getMsg(), userTo.getLastName() + ", " + userTo.getFirstName());
		notificationItemRepository.delete(notificationItem);
		notificationItemRepository.save(newNotificationItem);
		return newNotificationItem;
	}
	
	public NotificationItem transferItem(NotificationItem item, Long userTo, String msg, String receiver){
		NotificationItem result = new NotificationItem();
		UserItemId id = new UserItemId();
		id.setItemId(item.getId().getItemId());
		id.setUserId(userTo);
		result.setId(id);
		result.setDate(item.getDate());
		result.setEmail(item.getEmail());
		result.setExpiration(item.getExpiration());
		result.setExpired(item.isExpired());
		result.setMsg(msg);
		result.setReceiver(receiver);
		result.setSender(item.getReceiver());
		return result;
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presents() {
		return (List<Present>) presentRepository.findAll();
	}
	
	@GET
	@Path("by-company-id/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presentsByCompanyId(@PathParam("companyId") Long companyId) {
		return presentRepository.findByCompanyId(companyId);
	}

	@GET
	@Path("all/by-category/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presentsByCategory(@PathParam("categoryId") Long categoryId) {
		List<Present> result = new ArrayList<Present>();
		List<PresentCategory> presentCategories = presentCategoryRepository.findByCategoryId(categoryId);
		for (PresentCategory presentCategory : presentCategories) {
			result.add(presentCategory.getPresent());
		}
		return result;
	}

	@GET
	@Path("category/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> users() {
		return (List<Category>) categoryRepository.findAll();
	}

	@GET
	@Path("category/by-present-id/{presentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> category(@PathParam("presentId") Long presentId) {
		List<Category> result = new ArrayList<Category>();
		List<PresentCategory> presentCategories = presentCategoryRepository.findByPresentId(presentId);
		for (PresentCategory presentCategory : presentCategories) {
			result.add(presentCategory.getCategory());
		}
		return result;
	}

	@GET
	@Path("by-user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presents(@PathParam("userId") Long userId) {
		return presentSelectorService.choosePresentForUser(userPreferenceRepository, userId,
				categoryPreferenceRepository, presentCategoryRepository);
	}
}
