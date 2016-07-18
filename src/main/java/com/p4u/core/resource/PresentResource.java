package com.p4u.core.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
import com.p4u.core.model.UserPreference;

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
	@Qualifier("categoryPreferenceRepository")
	private CategoryPreferenceRepository categoryPreferenceRepository;

	@Autowired
	@Qualifier("presentCategoryRepository")
	private PresentCategoryRepository presentCategoryRepository;

	@Autowired
	@Qualifier("userPreferenceRepository")
	private UserPreferenceRepository userPreferenceRepository;

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
