package com.store.model;

import java.util.List;
import java.util.Map;




public interface StoreDAO_interface {
	 public void insert(StoreVO storeVO);
     public void update(StoreVO storeVO);
     public void update_stat(StoreVO storeVO);
     public void delete(String store_no);
     public StoreVO findByPrimaryKey(String store_no);
     public List<StoreVO> getAll();
     public List<StoreVO> getAll_stat(String store_stat);//後端審核
     
    

}
