package com.clearexpenses.main.dtos;

import java.util.List;

public class ExpensesRequest {
	private  Long user_id;
	private List<ItemList> itemsList;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public List<ItemList> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemList> itemsList) {
		this.itemsList = itemsList;
	}
	
 
}
