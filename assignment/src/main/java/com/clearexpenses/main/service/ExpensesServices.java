package com.clearexpenses.main.service;

import java.util.List;
import java.util.Map;

import com.clearexpenses.main.dtos.ExpensesRequest;
import com.clearexpenses.main.dtos.ExpensesResponse;
import com.clearexpenses.main.dtos.GroupRequest;
import com.clearexpenses.main.entities.UserItems;
import com.clearexpenses.main.entities.Users;

public interface ExpensesServices {
	
	public  Map<String, Object> createGroup(GroupRequest groupRequest);
	public  Map<String, Object>  addUsersByGroup(GroupRequest groupRequest);
	public  Map<String, Object> addExpensesByUser(ExpensesRequest expensesRequest);
	public  Map<String, Object> calculateExpenses(Long groupId);
	
}
