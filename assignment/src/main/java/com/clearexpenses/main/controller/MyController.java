package com.clearexpenses.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearexpenses.main.dtos.ExpensesRequest;
import com.clearexpenses.main.dtos.ExpensesResponse;
import com.clearexpenses.main.dtos.GroupRequest;
import com.clearexpenses.main.entities.UserItems;
import com.clearexpenses.main.entities.Users;
import com.clearexpenses.main.service.ExpensesServices;

@RestController
@RequestMapping("/API")
public class MyController {
	
	@Autowired
	private ExpensesServices expensesServices;
	
	
	/* controller to create the new expenses group */
	
	@RequestMapping(value="/createGroup" ,method = RequestMethod.POST)
	public ResponseEntity<String> createGroup(@RequestBody GroupRequest groupRequest) {
		 Boolean status = expensesServices.createGroup(groupRequest);
		 if(status)
			 return ResponseEntity.status(HttpStatus.OK).body("group successfully created.");
		 else 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("group successfully created.");

	}
	
	
	/* by using group name we can add any number of users in that group */
	
	@RequestMapping(value="/addUsersByGroup" ,method = RequestMethod.POST)
	public ResponseEntity<String> addUsersByGroup(@RequestBody GroupRequest groupRequest) {
		  List<Users> status = expensesServices.addUsersByGroup(groupRequest);
		 if(status.size()>0)
			 return ResponseEntity.status(HttpStatus.OK).body("user added successfully.");
		 else 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user creation get failed.");

	}
	
	
	/* users can add all items with their respective prices */
	
	@RequestMapping(value="/addExpensesByUser" ,method = RequestMethod.POST)
	public ResponseEntity<?> addExpensesByUser(@RequestBody ExpensesRequest expensesRequest) {
		  List<UserItems> items = expensesServices.addExpensesByUser(expensesRequest);
		 if(items.size()>0)
			 return ResponseEntity.status(HttpStatus.OK).body(items);
		 else 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("getting of items got failed.");

	}
	
	
	/*
	 * by perticular group id we can fetch all the users with how much money they
	 * need to receive and how much they need give
	 */

	@GetMapping("/calculateExpeses/{groupId}")
	public ResponseEntity<?> calculateExpeses(@PathVariable("groupId") Long groupId) {
		List<ExpensesResponse> expensesList = expensesServices.calculateExpenses(groupId);
		 if(expensesList.size()>0)
			 return ResponseEntity.status(HttpStatus.OK).body(expensesList);
		 else 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("calculations of expenses failed.");

	}

}
