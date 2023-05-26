package com.clearexpenses.main.dtos;

import java.util.List;

public class GroupRequest {
	
	private String group_name;
	private List<UsersDto> users;
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public List<UsersDto> getUsers() {
		return users;
	}
	public void setUsers(List<UsersDto> users) {
		this.users = users;
	}

}
