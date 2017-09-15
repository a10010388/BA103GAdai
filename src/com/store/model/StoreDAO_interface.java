package com.store.model;

import java.util.List;



public interface StoreDAO_interface {
	 public void insert(StoreVO storeVO);
     public void update(StoreVO storeVO);
     public void update_stat(StoreVO storeVO);
     public void delete(String store_no);
     public StoreVO findByPrimaryKey(String store_no);
     public List<StoreVO> getAll();
    

}
