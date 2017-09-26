package com.ord_list.model;



public class Ord_listService {
	private Ord_list_interface dao;
	
	public Ord_listService(){
		dao = new Ord_listDAO();
	}
	

}
