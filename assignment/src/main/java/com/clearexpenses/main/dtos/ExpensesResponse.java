package com.clearexpenses.main.dtos;

public class ExpensesResponse {
	private Long id;
	private String name;
	private String mobile;
	private Double needToPay;
	private Double needToReceive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
