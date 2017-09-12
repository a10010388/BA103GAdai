package com.store.model;

import java.io.Serializable;
import java.sql.Date;

public class StoreVO implements Serializable {
	private String STORE_NO;
	private String MEM_AC;
	private String TAX_ID_NO;
	private byte[] WIN_ID_PIC;
	private String STORE_PHONE;
	private String STORE_ADD;
	private String STORE_ADD_LAT;
	private String STORE_ADD_LON;
	private String STORE_NAME;
	private String STORE_CONT;
	private byte[] STORE_PIC1;
	private byte[] STORE_PIC2;
	private byte[] STORE_PIC3;
	private Integer STORE_FREE_SHIP;
	private String STORE_STAT;
	private String STORE_STAT_CONT;
	private Date STORE_STAT_CDATE;
	
	
	public String getSTORE_NO() {
		return STORE_NO;
	}
	public void setSTORE_NO(String STORE_NO) {
		this.STORE_NO = STORE_NO;
	}
	public String getMEM_AC() {
		return MEM_AC;
	}
	public void setMEM_AC(String MEM_AC) {
		this.MEM_AC = MEM_AC;
	}
	public String getTAX_ID_NO() {
		return TAX_ID_NO;
	}
	public void setTAX_ID_NO(String tAX_ID_NO) {
		this.TAX_ID_NO = tAX_ID_NO;
	}
	public byte[] getWIN_ID_PIC() {
		return WIN_ID_PIC;
	}
	public void setWIN_ID_PIC(byte[] WIN_ID_PIC) {
		this.WIN_ID_PIC = WIN_ID_PIC;
	}
	public String getSTORE_PHONE() {
		return STORE_PHONE;
	}
	public void setSTORE_PHONE(String STORE_PHONE) {
		this.STORE_PHONE = STORE_PHONE;
	}
	public String getSTORE_ADD() {
		return STORE_ADD;
	}
	public void setSTORE_ADD(String STORE_ADD) {
		this.STORE_ADD = STORE_ADD;
	}
	public String getSTORE_ADD_LON() {
		return STORE_ADD_LON;
	}
	public void setSTORE_ADD_LON(String STORE_ADD_LON) {
		this.STORE_ADD_LON = STORE_ADD_LON;
	}
	public String getSTORE_ADD_LAT() {
		return STORE_ADD_LAT;
	}
	public void setSTORE_ADD_LAT(String STORE_ADD_LAT) {
		this.STORE_ADD_LAT = STORE_ADD_LAT;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String STORE_NAME) {
		this.STORE_NAME = STORE_NAME;
	}
	public String getSTORE_CONT() {
		return STORE_CONT;
	}
	public void setSTORE_CONT(String STORE_CONT) {
		this.STORE_CONT = STORE_CONT;
	}
	public byte[] getSTORE_PIC1() {
		return STORE_PIC1;
	}
	public void setSTORE_PIC1(byte[] STORE_PIC1) {
		this.STORE_PIC1 = STORE_PIC1;
	}
	public byte[] getSTORE_PIC2() {
		return STORE_PIC2;
	}
	public void setSTORE_PIC2(byte[] STORE_PIC2) {
		this.STORE_PIC2 = STORE_PIC2;
	}
	public byte[] getSTORE_PIC3() {
		return STORE_PIC3;
	}
	public void setSTORE_PIC3(byte[] STORE_PIC3) {
		this.STORE_PIC3 = STORE_PIC3;
	}
	public Integer getSTORE_FREE_SHIP() {
		return STORE_FREE_SHIP;
	}
	public void setSTORE_FREE_SHIP(Integer STORE_FREE_SHIP) {
		this.STORE_FREE_SHIP = STORE_FREE_SHIP;
	}
	public String getSTORE_STAT() {
		return STORE_STAT;
	}
	public void setSTORE_STAT(String STORE_STAT) {
		this.STORE_STAT = STORE_STAT;
	}
	public String getSTORE_STAT_CONT() {
		return STORE_STAT_CONT;
	}
	public void setSTORE_STAT_CONT(String STORE_STAT_CONT) {
		this.STORE_STAT_CONT = STORE_STAT_CONT;
	}
	public Date getSTORE_STAT_CDATE() {
		return STORE_STAT_CDATE;
	}
	public void setSTORE_STAT_CDATE(Date STORE_STAT_CDATE) {
		this.STORE_STAT_CDATE = STORE_STAT_CDATE;
	}
	

}
