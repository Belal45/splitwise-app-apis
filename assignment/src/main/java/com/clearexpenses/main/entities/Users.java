package com.clearexpenses.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users_table")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	@Column(name="user_name")
	private String name;
	@Column(name="user_mobile")
	private String mobile;
	@Column(name="user_address")
	private String address;
	@ManyToOne
	@JoinColumn(name="group_id")
	private ExpensesGroup expensesGroup;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ExpensesGroup getExpensesGroup() {
		return expensesGroup;
	}
	public void setExpensesGroup(ExpensesGroup expensesGroup) {
		this.expensesGroup = expensesGroup;
	}
	
	


}
