package com.store.model;

import java.sql.Date;
import java.util.List;

public class StoreService {
	
	private StoreDAO_interface dao;
	
	public StoreService(){
		dao =new StoreDAO();
	}
	public StoreVO addStore( String MEM_AC, String TAX_ID_NO,
	  byte[] WIN_ID_PIC, String STORE_PHONE, String STORE_ADD, String STORE_ADD_LAT,
	  String STORE_ADD_LON, String STORE_NAME, String STORE_CONT,  byte[] STORE_PIC1,
	  byte[] STORE_PIC2, byte[] STORE_PIC3, Integer STORE_FREE_SHIP, Date STORE_STAT_CDATE,
	  String STORE_STAT){
		StoreVO storeVO = new StoreVO();
		storeVO.setMEM_AC(MEM_AC);
		storeVO.setTAX_ID_NO(TAX_ID_NO);
		storeVO.setWIN_ID_PIC(WIN_ID_PIC);
		storeVO.setSTORE_PHONE(STORE_PHONE);
		storeVO.setSTORE_ADD(STORE_ADD);
		storeVO.setSTORE_ADD_LAT(STORE_ADD_LAT);
		storeVO.setSTORE_ADD_LON(STORE_ADD_LON);
		storeVO.setSTORE_NAME(STORE_NAME);
		storeVO.setSTORE_CONT(STORE_CONT);
		storeVO.setSTORE_PIC1(STORE_PIC1);
		storeVO.setSTORE_PIC2(STORE_PIC2);
		storeVO.setSTORE_PIC3(STORE_PIC3);
		storeVO.setSTORE_FREE_SHIP(STORE_FREE_SHIP);
		storeVO.setSTORE_STAT(STORE_STAT);
		storeVO.setSTORE_STAT_CDATE(STORE_STAT_CDATE);
		dao.insert(storeVO);
		return storeVO;
		
	}
	
	public void addstore(StoreVO storeVO){
		dao.insert(storeVO);
	}
	
	public StoreVO updatesotre(String STORE_NO,String  TAX_ID_NO,  String STORE_PHONE,String STORE_ADD,String STORE_NAME,String STORE_CONT 
			,Integer STORE_FREE_SHIP,String STORE_STAT){
		StoreVO storevo = new StoreVO();
		storevo.setSTORE_NO(STORE_NO);
		storevo.setTAX_ID_NO(TAX_ID_NO);
		storevo.setSTORE_PHONE(STORE_PHONE);
		storevo.setSTORE_ADD(STORE_ADD);
		storevo.setSTORE_NAME(STORE_NAME);
		storevo.setSTORE_CONT(STORE_CONT);
		storevo.setSTORE_FREE_SHIP(STORE_FREE_SHIP);
		storevo.setSTORE_STAT(STORE_STAT);
		dao.update(storevo);
		
		return dao.findByPrimaryKey(STORE_NO);
	}
	public void updatestore(StoreVO storevo){
		dao.update(storevo);
	}
	
	public void deletestore(String STORE_NO){
		dao.delete(STORE_NO);
	}
	
	public List<StoreVO> getAll(){
		return dao.getAll();
	}
	public StoreVO getonestore(String STORE_NO){
		return dao.findByPrimaryKey(STORE_NO);
	}
	
	
	
	
}
