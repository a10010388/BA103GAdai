package com.ord.model;

import java.util.List;
import java.util.Set;

import com.ord_list.model.Ord_listVO;



public interface OrdDAO_interface {
	 public void insert(OrdVO ordVO);
     public void update(OrdVO ordVO);
     public void delete(String ord_no);
     public Set<OrdVO> getAll();
     public OrdVO findByPrimaryKey(String ord_no);
     //查某筆訂單細目
     public Set<Ord_listVO> getOrd_listByOrd(String ord_no);
     public String insertWithOrd_list(OrdVO ordVO, Set<Ord_listVO> ord_listVOs);
     public List<OrdVO> getOrdByMem_ac(String mem_ac);
     
}
