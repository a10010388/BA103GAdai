package com.ord.model;

import java.util.List;




public class OrdService {
private OrdDAO_interface dao;
	
	public OrdService() {
		dao = new OrdDAO();
	}
	
	public OrdVO getOrdByOrdno(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}
}
