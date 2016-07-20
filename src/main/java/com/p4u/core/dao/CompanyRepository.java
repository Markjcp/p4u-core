package com.p4u.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.p4u.core.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
	
	List<Company> findByCode(Integer code);

}
