package com.p4u.core.dao;

import org.springframework.data.repository.CrudRepository;

import com.p4u.core.model.ItemBought;
import com.p4u.core.model.UserItemId;

public interface ItemBoughtRepository extends CrudRepository<ItemBought, UserItemId>{

}
