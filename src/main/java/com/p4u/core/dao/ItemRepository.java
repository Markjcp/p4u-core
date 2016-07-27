package com.p4u.core.dao;

import org.springframework.data.repository.CrudRepository;

import com.p4u.core.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
