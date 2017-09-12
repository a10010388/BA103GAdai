package com.prod.model;

import java.io.Serializable;
import java.sql.Date;

public class ProdVO implements Serializable{
	private String PROD_NO;
	private String STORE_NO;
	private String PROD_NAME;
	private String BEAN_TYPE;
	private String BEAN_GRADE;
	private String BEAN_CONTRY;
	private String BEAN_REGION;
	private String BEAN_FARM;
	private String BEAN_FARMER;
	private Integer BEAN_EL;
	private String PROC;
	private String ROAST;
	private Integer BEAN_ATTR_ACID;
	private Integer BEAN_ATTR_AROMA;
	private Integer BEAN_ATTR_BODY;
	private Integer BEAN_ATTR_AFTER;
	private Integer BEAN_ATTR_BAL;
	private String BEAN_AROMA;
	private Integer PROD_PRICE;
	private Integer PROD_WT;
	private Integer SEND_FEE;
	private Integer PROD_SUP;
	private String PROD_CONT;
	private byte[] PROD_PIC1;
	private byte[] PROD_PIC2;
	private byte[] PROD_PIC3;
	private String PROD_STAT;
	private Date ED_TIME;
	public String getPROD_NO() {
		return PROD_NO;
	}
	public void setPROD_NO(String pROD_NO) {
		PROD_NO = pROD_NO;
	}
	public String getSTORE_NO() {
		return STORE_NO;
	}
	public void setSTORE_NO(String sTORE_NO) {
		STORE_NO = sTORE_NO;
	}
	public String getPROD_NAME() {
		return PROD_NAME;
	}
	public void setPROD_NAME(String pROD_NAME) {
		PROD_NAME = pROD_NAME;
	}
	public String getBEAN_TYPE() {
		return BEAN_TYPE;
	}
	public void setBEAN_TYPE(String bEAN_TYPE) {
		BEAN_TYPE = bEAN_TYPE;
	}
	public String getBEAN_GRADE() {
		return BEAN_GRADE;
	}
	public void setBEAN_GRADE(String bEAN_GRADE) {
		BEAN_GRADE = bEAN_GRADE;
	}
	public String getBEAN_CONTRY() {
		return BEAN_CONTRY;
	}
	public void setBEAN_CONTRY(String bEAN_CONTRY) {
		BEAN_CONTRY = bEAN_CONTRY;
	}
	public String getBEAN_REGION() {
		return BEAN_REGION;
	}
	public void setBEAN_REGION(String bEAN_REGION) {
		BEAN_REGION = bEAN_REGION;
	}
	public String getBEAN_FARM() {
		return BEAN_FARM;
	}
	public void setBEAN_FARM(String bEAN_FARM) {
		BEAN_FARM = bEAN_FARM;
	}
	public String getBEAN_FARMER() {
		return BEAN_FARMER;
	}
	public void setBEAN_FARMER(String bEAN_FARMER) {
		BEAN_FARMER = bEAN_FARMER;
	}
	public Integer getBEAN_EL() {
		return BEAN_EL;
	}
	public void setBEAN_EL(Integer bEAN_EL) {
		BEAN_EL = bEAN_EL;
	}
	public String getPROC() {
		return PROC;
	}
	public void setPROC(String pROC) {
		PROC = pROC;
	}
	public String getROAST() {
		return ROAST;
	}
	public void setROAST(String rOAST) {
		ROAST = rOAST;
	}
	public Integer getBEAN_ATTR_ACID() {
		return BEAN_ATTR_ACID;
	}
	public void setBEAN_ATTR_ACID(Integer bEAN_ATTR_ACID) {
		BEAN_ATTR_ACID = bEAN_ATTR_ACID;
	}
	public Integer getBEAN_ATTR_AROMA() {
		return BEAN_ATTR_AROMA;
	}
	public void setBEAN_ATTR_AROMA(Integer bEAN_ATTR_AROMA) {
		BEAN_ATTR_AROMA = bEAN_ATTR_AROMA;
	}
	public Integer getBEAN_ATTR_BODY() {
		return BEAN_ATTR_BODY;
	}
	public void setBEAN_ATTR_BODY(Integer bEAN_ATTR_BODY) {
		BEAN_ATTR_BODY = bEAN_ATTR_BODY;
	}
	public Integer getBEAN_ATTR_AFTER() {
		return BEAN_ATTR_AFTER;
	}
	public void setBEAN_ATTR_AFTER(Integer bEAN_ATTR_AFTER) {
		BEAN_ATTR_AFTER = bEAN_ATTR_AFTER;
	}
	public Integer getBEAN_ATTR_BAL() {
		return BEAN_ATTR_BAL;
	}
	public void setBEAN_ATTR_BAL(Integer bEAN_ATTR_BAL) {
		BEAN_ATTR_BAL = bEAN_ATTR_BAL;
	}
	public String getBEAN_AROMA() {
		return BEAN_AROMA;
	}
	public void setBEAN_AROMA(String bEAN_AROMA) {
		BEAN_AROMA = bEAN_AROMA;
	}
	public Integer getPROD_PRICE() {
		return PROD_PRICE;
	}
	public void setPROD_PRICE(Integer pROD_PRICE) {
		PROD_PRICE = pROD_PRICE;
	}
	public Integer getPROD_WT() {
		return PROD_WT;
	}
	public void setPROD_WT(Integer pROD_WT) {
		PROD_WT = pROD_WT;
	}
	public Integer getSEND_FEE() {
		return SEND_FEE;
	}
	public void setSEND_FEE(Integer sEND_FEE) {
		SEND_FEE = sEND_FEE;
	}
	public Integer getPROD_SUP() {
		return PROD_SUP;
	}
	public void setPROD_SUP(Integer pROD_SUP) {
		PROD_SUP = pROD_SUP;
	}
	public String getPROD_CONT() {
		return PROD_CONT;
	}
	public void setPROD_CONT(String pROD_CONT) {
		PROD_CONT = pROD_CONT;
	}
	public byte[] getPROD_PIC1() {
		return PROD_PIC1;
	}
	public void setPROD_PIC1(byte[] pROD_PIC1) {
		PROD_PIC1 = pROD_PIC1;
	}
	public byte[] getPROD_PIC2() {
		return PROD_PIC2;
	}
	public void setPROD_PIC2(byte[] pROD_PIC2) {
		PROD_PIC2 = pROD_PIC2;
	}
	public byte[] getPROD_PIC3() {
		return PROD_PIC3;
	}
	public void setPROD_PIC3(byte[] pROD_PIC3) {
		PROD_PIC3 = pROD_PIC3;
	}
	public String getPROD_STAT() {
		return PROD_STAT;
	}
	public void setPROD_STAT(String pROD_STAT) {
		PROD_STAT = pROD_STAT;
	}
	public Date getED_TIME() {
		return ED_TIME;
	}
	public void setED_TIME(Date eD_TIME) {
		ED_TIME = eD_TIME;
	}
	

}
