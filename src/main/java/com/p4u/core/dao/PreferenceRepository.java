package com.p4u.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.Preference;

@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Long> {

}
