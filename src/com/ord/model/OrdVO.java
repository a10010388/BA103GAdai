package com.ord.model;

import java.io.Serializable;
import java.sql.Date;

public class OrdVO implements Serializable{
	private String ORD_NO;
	private String MEM_AC;
	private Integer SEND_FEE;
	private Integer TOTAL_PAY;
	private String ORD_NAME;
	private String ORD_PHONE;
	private String ORD_ADD;
	private String PAY_INFO;
	private String ORD_STAT;
	private Date ORD_DATE;
	private Date PAY_DATE;
	private Date PAY_CHK_DATE;
	private Date SEND_DATE;
	private String SEND_ID;
	
	public String getORD_NO() {
		return ORD_NO;
	}
	public void setORD_NO(String oRD_NO) {
		ORD_NO = oRD_NO;
	}
	public String getMEM_AC() {
		return MEM_AC;
	}
	public void setMEM_AC(String mEM_AC) {
		MEM_AC = mEM_AC;
	}
	public Integer getSEND_FEE() {
		return SEND_FEE;
	}
	public void setSEND_FEE(Integer sEND_FEE) {
		SEND_FEE = sEND_FEE;
	}
	public Integer getTOTAL_PAY() {
		return TOTAL_PAY;
	}
	public void setTOTAL_PAY(Integer tOTAL_PAY) {
		TOTAL_PAY = tOTAL_PAY;
	}
	public String getORD_NAME() {
		return ORD_NAME;
	}
	public void setORD_NAME(String oRD_NAME) {
		ORD_NAME = oRD_NAME;
	}
	public String getORD_PHONE() {
		return ORD_PHONE;
	}
	public void setORD_PHONE(String oRD_PHONE) {
		ORD_PHONE = oRD_PHONE;
	}
	public String getORD_ADD() {
		return ORD_ADD;
	}
	public void setORD_ADD(String oRD_ADD) {
		ORD_ADD = oRD_ADD;
	}
	public String getPAY_INFO() {
		return PAY_INFO;
	}
	public void setPAY_INFO(String pAY_INFO) {
		PAY_INFO = pAY_INFO;
	}
	public String getORD_STAT() {
		return ORD_STAT;
	}
	public void setORD_STAT(String oRD_STAT) {
		ORD_STAT = oRD_STAT;
	}
	public Date getORD_DATE() {
		return ORD_DATE;
	}
	public void setORD_DATE(Date oRD_DATE) {
		ORD_DATE = oRD_DATE;
	}
	public Date getPAY_DATE() {
		return PAY_DATE;
	}
	public void setPAY_DATE(Date pAY_DATE) {
		PAY_DATE = pAY_DATE;
	}
	public Date getPAY_CHK_DATE() {
		return PAY_CHK_DATE;
	}
	public void setPAY_CHK_DATE(Date pAY_CHK_DATE) {
		PAY_CHK_DATE = pAY_CHK_DATE;
	}
	public Date getSEND_DATE() {
		return SEND_DATE;
	}
	public void setSEND_DATE(Date sEND_DATE) {
		SEND_DATE = sEND_DATE;
	}
	public String getSEND_ID() {
		return SEND_ID;
	}
	public void setSEND_ID(String sEND_ID) {
		SEND_ID = sEND_ID;
	}
	
	
	

}
