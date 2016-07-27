package com.p4u.core.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.p4u.core.beans.PresentPreferenceScore;
import com.p4u.core.dao.CategoryPreferenceRepository;
import com.p4u.core.dao.CategoryRepository;
import com.p4u.core.dao.PresentCategoryRepository;
import com.p4u.core.dao.PresentRepository;
import com.p4u.core.dao.UserPreferenceRepository;
import com.p4u.core.model.Category;
import com.p4u.core.model.CategoryPreference;
import com.p4u.core.model.Present;
import com.p4u.core.model.PresentCategory;
import com.p4u.core.model.PresentCategoryId;
import com.p4u.core.model.UserPreference;

@Component
@Path("/present")
public class PresentResource {
	
	private static final String IMAGE_NAME_PREFIX = "p4u/image/";

	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;

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

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Present> presents() {
		return (List<Present>) presentRepository.findAll();
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
		Map<Present,Integer> occurrences = countPresentOccurrences(aux);
		List<PresentPreferenceScore> occurrencesScore = retrieveOrderedListForMostOccurrences(occurrences);
		for (PresentPreferenceScore presentPreferenceScore : occurrencesScore) {
			result.add(presentPreferenceScore.getPresent());
		}
		return result;
	}
	
	private List<PresentPreferenceScore> retrieveOrderedListForMostOccurrences(Map<Present,Integer> occurrences){
		List<PresentPreferenceScore> result = new ArrayList<PresentPreferenceScore>();
		for (Present present : occurrences.keySet()) {
			result.add(new PresentPreferenceScore(present,occurrences.get(present)));
		}
		Collections.sort(result);
		return result;
	}
	
	private Map<Present,Integer> countPresentOccurrences(List<Present> presents){
		Map<Present,Integer> result = new HashMap<Present,Integer>();
		for (Present present : presents) {
			if(result.get(present)==null){
				result.put(present, 1);
			}else{
				result.put(present, result.get(present)+1);
			}
		}
		return result;
	}

}
