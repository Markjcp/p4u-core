package com.p4u.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.Present;

@Repository
public interface PresentRepository extends CrudRepository<Present, Long> {
	
	public List<Present> findByCompanyId(Long companyId);

}
