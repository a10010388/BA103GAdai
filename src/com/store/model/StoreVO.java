package com.store.model;

import java.io.Serializable;
import java.sql.Date;

public class StoreVO implements Serializable {
	private String store_no;
	private String mem_ac;
	private String tax_id_no;
	private byte[] win_id_pic;
	private String store_phone;
	private String store_add;
	private String store_add_lat;
	private String store_add_lon;
	private String store_name;
	private String store_cont;
	private byte[] store_pic1;
	private byte[] store_pic2;
	private byte[] store_pic3;
	private Integer store_free_ship;
	private String store_stat;
	private String store_stat_cont;
	private Date store_stat_cdate;
	
	
	public String getStore_no() {
		return store_no;
	}
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	public String getMem_ac() {
		return mem_ac;
	}
	public void setMem_ac(String mem_ac) {
		this.mem_ac = mem_ac;
	}
	public String getTax_id_no() {
		return tax_id_no;
	}
	public void setTax_id_no(String tax_id_no) {
		this.tax_id_no = tax_id_no;
	}
	public byte[] getWin_id_pic() {
		return win_id_pic;
	}
	public void setWin_id_pic(byte[] Win_id_pic) {
		this.win_id_pic = Win_id_pic;
	}
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	public String getStore_add() {
		return store_add;
	}
	public void setStore_add(String store_add) {
		this.store_add = store_add;
	}
	public String getStore_add_lon() {
		return store_add_lon;
	}
	public void setStore_add_lon(String store_add_lon) {
		this.store_add_lon = store_add_lon;
	}
	public String getStore_add_lat() {
		return store_add_lat;
	}
	public void setStore_add_lat(String store_add_lat) {
		this.store_add_lat = store_add_lat;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_cont() {
		return store_cont;
	}
	public void setStore_cont(String store_cont) {
		this.store_cont = store_cont;
	}
	public byte[] getStore_pic1() {
		return store_pic1;
	}
	public void setStore_pic1(byte[] store_pic1) {
		this.store_pic1 = store_pic1;
	}
	public byte[] getStore_pic2() {
		return store_pic2;
	}
	public void setStore_pic2(byte[] store_pic2) {
		this.store_pic2 = store_pic2;
	}
	public byte[] getStore_pic3() {
		return store_pic3;
	}
	public void setStore_pic3(byte[] store_pic3) {
		this.store_pic3 = store_pic3;
	}
	public Integer getStore_free_ship() {
		return store_free_ship;
	}
	public void setStore_free_ship(Integer store_free_ship) {
		this.store_free_ship = store_free_ship;
	}
	public String getStore_stat() {
		return store_stat;
	}
	public void setStore_stat(String store_stat) {
		this.store_stat = store_stat;
	}
	public String getStore_stat_cont() {
		return store_stat_cont;
	}
	public void setStore_stat_cont(String store_stat_cont) {
		this.store_stat_cont = store_stat_cont;
	}
	public Date getStore_stat_cdate() {
		return store_stat_cdate;
	}
	public void setStore_stat_cdate(Date store_stat_cdate) {
		this.store_stat_cdate = store_stat_cdate;
	}
	

}
