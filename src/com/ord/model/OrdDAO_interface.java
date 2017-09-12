package com.ord.model;

import java.util.List;



public interface OrdDAO_interface {
	 public void insert(OrdVO ordVO);
     public void update(OrdVO ordVO);
     public void delete(String ORD_NO);
     public OrdVO findByPrimaryKey(String ORD_NO);
     public List<OrdVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 

}
