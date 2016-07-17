package com.p4u.core.dao;

import org.springframework.data.repository.CrudRepository;

import com.p4u.core.model.CategoryPreference;
import com.p4u.core.model.CategoryPreferenceId;

public interface CategoryPreferenceRepository extends CrudRepository<CategoryPreference, CategoryPreferenceId>{

}
