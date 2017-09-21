package com.ord.model;

import java.io.Serializable;
import java.sql.Date;

public class OrdVO implements Serializable{
	private String ord_no;
	private String mem_ac;
	private Integer send_fee;
	private Integer total_pay;
	private String ord_name;
	private String ord_phone;
	private String ord_add;
	private String pay_info;
	private String ord_stat;
	private Date ord_date;
	private Date pay_date;
	private Date pay_chk_date;
	private Date send_date;
	private String send_id;
	
	public String getOrd_no() {
		return ord_no;
	}
	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}
	public String getMem_ac() {
		return mem_ac;
	}
	public void setMem_ac(String mem_ac) {
		this.mem_ac = mem_ac;
	}
	public Integer getSend_fee() {
		return send_fee;
	}
	public void setSend_fee(Integer send_fee) {
		this.send_fee = send_fee;
	}
	public Integer getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(Integer total_pay) {
		this.total_pay = total_pay;
	}
	public String getOrd_name() {
		return ord_name;
	}
	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}
	public String getOrd_phone() {
		return ord_phone;
	}
	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}
	public String getOrd_add() {
		return ord_add;
	}
	public void setOrd_add(String ord_add) {
		this.ord_add = ord_add;
	}
	public String getPay_info() {
		return pay_info;
	}
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
	public String getOrd_stat() {
		return ord_stat;
	}
	public void setOrd_stat(String ord_stat) {
		this.ord_stat = ord_stat;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public Date getPay_chk_date() {
		return pay_chk_date;
	}
	public void setPay_chk_date(Date pay_chk_date) {
		this.pay_chk_date = pay_chk_date;
	}
	public Date getSend_date() {
		return send_date;
	}
	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	
	
	

}
