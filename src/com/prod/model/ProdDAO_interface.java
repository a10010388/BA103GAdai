package com.prod.model;

import java.util.List;



public interface ProdDAO_interface {
	public void insert(ProdVO prodVO);
    public void update(ProdVO prodVO);
    public void delete(String PROD_NO);
    public ProdVO findByPrimaryKey(String PROD_NO);
    public List<ProdVO> getAll();

}
