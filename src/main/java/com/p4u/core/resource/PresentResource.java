package com.p4u.core.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.p4u.core.dao.CategoryRepository;
import com.p4u.core.dao.PresentCategoryRepository;
import com.p4u.core.dao.PresentRepository;
import com.p4u.core.model.Category;
import com.p4u.core.model.Present;
import com.p4u.core.model.PresentCategory;

@Component
@Path("/present")
public class PresentResource {
	
	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;
	
	@Autowired
	@Qualifier("presentRepository")
	private PresentRepository presentRepository;
	
	@Autowired
	@Qualifier("presentCategoryRepository")
	private PresentCategoryRepository presentCategoryRepository;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presents() {
		return (List<Present>) presentRepository.findAll();
	}
	
	@GET
	@Path("all/by-category/{categoryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presentsByCategory(@PathParam("categoryId")Long categoryId) {
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
	public List<Category> category(@PathParam("presentId")Long presentId) {
		List<Category> result = new ArrayList<Category>();
		List<PresentCategory> presentCategories = presentCategoryRepository.findByPresentId(presentId);
		for (PresentCategory presentCategory : presentCategories) {
			result.add(presentCategory.getCategory());
		}
		return result;
	}

}
