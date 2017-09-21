package com.store.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.prod.model.ProdVO;

public class StoreService {
	
	private StoreDAO_interface dao;
	
	public StoreService(){
		dao =new StoreDAO();
	}
	public StoreVO addStore( String mem_ac, String tax_id_no,
	  byte[] win_id_pic, String store_phone, String store_add, String store_add_lat,
	  String store_add_lon, String store_name, String store_cont,  byte[] store_pic1,
	  byte[] store_pic2, byte[] store_pic3, Integer store_free_ship){
		StoreVO storeVO = new StoreVO();
		storeVO.setMem_ac(mem_ac);
		storeVO.setTax_id_no(tax_id_no);
		storeVO.setWin_id_pic(win_id_pic);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_add(store_add);
		storeVO.setStore_add_lat(store_add_lat);
		storeVO.setStore_add_lon(store_add_lon);
		storeVO.setStore_name(store_name);
		storeVO.setStore_cont(store_cont);
		storeVO.setStore_pic1(store_pic1);
		storeVO.setStore_pic2(store_pic2);
		storeVO.setStore_pic3(store_pic3);
		storeVO.setStore_free_ship(store_free_ship);
		
		dao.insert(storeVO);
		return storeVO;
		
	}
	
	public void addstore(StoreVO storeVO){
		dao.insert(storeVO);
	}
	
	public StoreVO updatesotre(String tax_id_no, byte[] win_id_pic,String store_phone,String store_add,String store_add_lat 
			,String store_add_lon,String store_name,String store_cont,byte[] store_pic1,byte[] store_pic2 ,byte[] store_pic3,Integer store_free_ship,String store_stat,String store_stat_cont,Date store_stat_cdate,String store_no){
		StoreVO storevo = new StoreVO();
		storevo.setStore_no(store_no);
		storevo.setStore_phone(store_phone);
		storevo.setStore_add(store_add);
		storevo.setStore_name(store_name);
		storevo.setStore_cont(store_cont);
		storevo.setStore_free_ship(store_free_ship);
		storevo.setStore_stat(store_stat);
		storevo.setTax_id_no(tax_id_no);
		storevo.setWin_id_pic(win_id_pic);
		storevo.setStore_add_lat(store_add_lat);
		storevo.setStore_add_lon(store_add_lon);
		storevo.setStore_pic1(store_pic1);
		storevo.setStore_pic2(store_pic2);
		storevo.setStore_pic3(store_pic3);
		storevo.setStore_stat_cdate(store_stat_cdate);
		storevo.setStore_stat_cont(store_stat_cont);
		dao.update(storevo);
		
		return dao.findByPrimaryKey(store_no);
	}
	public StoreVO update_stat(String store_stat,Date store_stat_cdate,String store_no,String store_stat_cont){
		StoreVO storevo =new StoreVO();
		storevo.setStore_stat(store_stat);
		storevo.setStore_stat_cdate(store_stat_cdate);
		storevo.setStore_no(store_no);
		storevo.setStore_stat_cont(store_stat_cont);
		dao.update_stat(storevo);
		return dao.findByPrimaryKey(store_no);
	}
	
	public void updatestore(StoreVO storevo){
		dao.update(storevo);
	}
	
	public void deletestore(String store_no){
		dao.delete(store_no);
	}
	
	public List<StoreVO> getAll(){
		return dao.getAll();
	}
	public StoreVO getonestore(String store_no){
		return dao.findByPrimaryKey(store_no);
	}
	public List<StoreVO> getstatstr(String store_stat){
		return dao.getAll_stat(store_stat);
	}
	public Set<ProdVO> getProdsByStore(String store_no){
		return dao.getProdsByStore_no(store_no);
	}
	
	
	
	
}
