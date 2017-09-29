package com.ord.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.ord.model.OrdVO;
import com.ord_list.model.Ord_listVO;

public class OrdService {
	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}

	// 查詢某訂單
	public OrdVO getOrdByOrdno(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}

	// 查詢某訂單細目
	public Set<Ord_listVO> getOrd_listByOrd(String ord_no) {
		return dao.getOrd_listByOrd(ord_no);
	}

	public String newAnOrder(OrdVO ordVO, Set<Ord_listVO> ord_listVOs) {
		return dao.insertWithOrd_list(ordVO, ord_listVOs);
	}

	public List<OrdVO> getOrdByMem_ac(String mem_ac) {
		return dao.getOrdByMem_ac(mem_ac);
	}

	// 修改訂單狀態已付款-改已確認付款狀態
	public OrdVO update_payconiform(String ord_no) {
		OrdVO ordVO = dao.findByPrimaryKey(ord_no);
		ordVO.setOrd_stat("已確認付款");
		ordVO.setPay_chk_date(new Date(System.currentTimeMillis()));
		dao.update(ordVO);
		return ordVO;
	}

	// 修改訂單狀態 已確認付款-改已出貨
	public OrdVO update_sendstat(String ord_no, String send_id) {
		OrdVO ordVO = dao.findByPrimaryKey(ord_no);
		ordVO.setOrd_stat("已出貨");
		ordVO.setSend_date(new Date(System.currentTimeMillis()));
		ordVO.setSend_id(send_id);
		dao.update(ordVO);
		return ordVO;
	}

}
