package com.ord.model;

import java.sql.Date;
import java.util.Set;

import com.ord_list.model.Ord_listVO;




public class OrdService {
private OrdDAO_interface dao;
	
	public OrdService() {
		dao = new OrdDAO();
	}
	
	//查詢某訂單
	public OrdVO getOrdByOrdno(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}
	//查詢某訂單細目
	public  Set<Ord_listVO> getOrd_listByOrd(String ord_no){
		return dao.getOrd_listByOrd(ord_no);
	}
	
	public String newAnOrder(OrdVO ordVO, Set<Ord_listVO> ord_listVOs){
		return dao.insertWithOrd_list(ordVO, ord_listVOs);
	}
	//修改訂單狀態已付款-改已確認付款狀態
	public OrdVO update_payconiform(OrdVO ordVO){
		dao.update(ordVO);
		return ordVO;
	}
	
	
}
