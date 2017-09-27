package com.ord.model;

import java.util.List;
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
	
}
