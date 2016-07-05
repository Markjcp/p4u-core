package com.p4u.core.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.beans.LoginResult;
import com.p4u.core.dao.UserRepository;
import com.p4u.core.model.User;

@Component
@Path("/user")
public class UserResource {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> users() {
		return (List<User>) userRepository.findAll();
	}

	@POST
	@Path("register/{email}/{password}/{facebook}/{address}")
	@Produces(MediaType.APPLICATION_JSON)
	public User register(@PathParam("email") String email, @PathParam("password") String password,
			@PathParam("facebook") String facebook, @PathParam("address") String address) {
		User result = new User();
		result.setEmail(email);
		result.setUsername(email);
		result.setPassword(password);
		result.setFacebookUserName(facebook);
		result.setAddress(address);
		return userRepository.save(result);
	}
	
	@POST
	@Path("login/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResult register(@PathParam("email") String email, @PathParam("password") String password){
		List<User> result =  userRepository.findByEmail(email);
		if(result == null || result.isEmpty() || result.size()>1){
			return new LoginResult(false);
		}
		return new LoginResult(result.iterator().next().getPassword().equals(password));
	}
	
	
	@PUT
	@Path("change-password/{user-id}/{new-password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User changePassword(@PathParam("user-id") Long userId, @PathParam("new-password") String newPassword){
		User user = userRepository.findOne(userId);
		if(user!=null){
			user.setPassword(newPassword);
			userRepository.save(user);
		}
		return user;
	}
	
	@GET
	@Path("search-by-email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> search(@PathParam("email") String email){
		return userRepository.findByEmail(email);
	}


}
