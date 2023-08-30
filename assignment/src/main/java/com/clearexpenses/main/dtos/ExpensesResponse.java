package com.clearexpenses.main.dtos;

public class ExpensesResponse {
	private String id;
	private String name;
	private String mobile;
	private Double needToPay;
	private Double needToReceive;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getNeedToPay() {
		return needToPay;
	}
	public void setNeedToPay(Double needToPay) {
		this.needToPay = needToPay;
	}
	public Double getNeedToReceive() {
		return needToReceive;
	}
	public void setNeedToReceive(Double needToReceive) {
		this.needToReceive = needToReceive;
	}
	

	
	
	

}
