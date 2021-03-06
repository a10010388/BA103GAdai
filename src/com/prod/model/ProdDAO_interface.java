package com.prod.model;

import java.util.List;
import java.util.Set;

import com.ord_list.model.Ord_listVO;

public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);
	public void update(ProdVO prodVO);
	public void delete(String prod_no);
	public ProdVO findByPrimaryKey(String prod_no);
	public List<ProdVO> getAll();
	public List<ProdVO> getAllNoImg();
	public List<byte[]> getImageByPK(String prod_no);
	public List <ProdVO> getQueryResult(String bean_contry, String proc, String roast, String prod_name);
	public ProdVO findByPrimaryKeyNoImg(String prod_no);
	//查某商品的全部訂單
	public Set<Ord_listVO> getOrd_listByProd(String prod_no);
}
