package com.clearexpenses.main.service;

import java.util.List;

import com.clearexpenses.main.dtos.ExpensesRequest;
import com.clearexpenses.main.dtos.ExpensesResponse;
import com.clearexpenses.main.dtos.GroupRequest;
import com.clearexpenses.main.entities.UserItems;
import com.clearexpenses.main.entities.Users;

public interface ExpensesServices {
	
	public  Boolean createGroup(GroupRequest groupRequest);
	public  List<Users> addUsersByGroup(GroupRequest groupRequest);
	public  List<UserItems> addExpensesByUser(ExpensesRequest expensesRequest);
	public  List<ExpensesResponse> calculateExpenses(Long groupId);
	
	
	
	

}
