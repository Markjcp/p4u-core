package com.p4u.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.UserPreference;
import com.p4u.core.model.UserPreferenceId;

@Repository
public interface UserPreferenceRepository extends CrudRepository<UserPreference, UserPreferenceId>{
	
	public List<UserPreference> findByUserId(Long userId);

}
