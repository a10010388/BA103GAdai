package com.store.model;

import java.util.List;



public interface StoreDAO_interface {
	 public void insert(StoreVO storeVO);
     public void update(StoreVO storeVO);
     public void delete(String STORE_NO);
     public StoreVO findByPrimaryKey(String STORE_NO);
     public List<StoreVO> getAll();

}
