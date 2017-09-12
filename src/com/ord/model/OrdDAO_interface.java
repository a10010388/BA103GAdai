package com.ord.model;

import java.util.List;



public interface OrdDAO_interface {
	 public void insert(OrdVO ordVO);
     public void update(OrdVO ordVO);
     public void delete(String ORD_NO);
     public OrdVO findByPrimaryKey(String ORD_NO);
     public List<OrdVO> getAll();
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 

}
