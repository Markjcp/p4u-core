package com.p4u.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.p4u.core.model.Present;

@Repository
public interface PresentRepository extends CrudRepository<Present, Long> {

}
