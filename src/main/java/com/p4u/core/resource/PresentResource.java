package com.p4u.core.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.CategoryRepository;
import com.p4u.core.model.Category;

@Component
@Path("/present")
public class PresentResource {
	
	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;
	
	@GET
	@Path("category/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> users() {
		return (List<Category>) categoryRepository.findAll();
	}

}
