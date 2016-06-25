package com.p4u.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	
	public List<User> findByEmail(String email);

}
