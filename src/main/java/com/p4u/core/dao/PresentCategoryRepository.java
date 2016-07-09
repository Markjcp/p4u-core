package com.p4u.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.PresentCategory;
import com.p4u.core.model.PresentCategoryId;

@Repository
public interface PresentCategoryRepository extends CrudRepository<PresentCategory, PresentCategoryId> {
	
	public List<PresentCategory> findByCategoryId(Long categoryId);
	
	public List<PresentCategory> findByPresentId(Long presentId);
 
}
