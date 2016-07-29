package com.p4u.core.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.NotificationItemRepository;
import com.p4u.core.model.NotificationItem;

@Component
@Path("/notification")
public class NotificationResource {
	
	@Autowired
	@Qualifier("notificationItemRepository")
	private NotificationItemRepository notificationItemRepository;
	
	@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NotificationItem> retrieveNotificationItems(@PathParam("userId")Long userId){
		List<NotificationItem> result = new ArrayList<NotificationItem>();
		result = notificationItemRepository.findByUserId(userId);
		for (NotificationItem notificationItem : result) {
			if(notificationItem.getExpiration()!=null){
				notificationItem.setExpired(notificationItem.getExpiration().before(new Date()));
			}
		}
		return result;
	}
	
	

}
