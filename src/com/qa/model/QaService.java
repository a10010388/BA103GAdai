package com.qa.model;

import java.util.List;


public class QaService {
	
	private QaDAO_interface dao;
	public QaService() {
		dao = new QaDAO();
	}
	
	public List<QaVO> getVOByProd(String prod_no) {
		return dao.getByProd(prod_no);
	}

}
