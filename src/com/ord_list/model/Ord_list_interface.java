package com.ord_list.model;

import java.util.List;

public interface Ord_list_interface {
	public void insert(Ord_listVO ord_listvo);
	public void update(Ord_listVO ord_listvo);
	public void delete(String ord_list_no);
	
	public Ord_listVO findByPrimaryKey(String ord_no);
	public List<Ord_listVO> getAll();

}
