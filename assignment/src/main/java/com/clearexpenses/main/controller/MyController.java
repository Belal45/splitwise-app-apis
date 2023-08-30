package com.clearexpenses.main.controller;

import java.util.List;
import java.util.Map;

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

	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public ResponseEntity<?> createGroup(@RequestBody GroupRequest groupRequest) {
		Map<String, Object> creationResp = expensesServices.createGroup(groupRequest);
		return ResponseEntity.ok(creationResp);

	}

	/* by using group name we can add any number of users in that group */

	@RequestMapping(value = "/addUsersByGroup", method = RequestMethod.POST)
	public ResponseEntity<?> addUsersByGroup(@RequestBody GroupRequest groupRequest) {
		Map<String, Object> userResp = expensesServices.addUsersByGroup(groupRequest);
		return ResponseEntity.ok(userResp);

	}

	/* users can add all items with their respective prices */

	@RequestMapping(value = "/addExpensesByUser", method = RequestMethod.POST)
	public ResponseEntity<?> addExpensesByUser(@RequestBody ExpensesRequest expensesRequest) {
		Map<String, Object> items = expensesServices.addExpensesByUser(expensesRequest);
		return ResponseEntity.ok(items);

	}

	/*
	 * by perticular group id we can fetch all the users with how much money they
	 * need to receive and how much they need give
	 */

	@GetMapping("/calculateExpeses/{groupId}")
	public ResponseEntity<?> calculateExpeses(@PathVariable("groupId") Long groupId) {
		Map<String, Object> expensesList = expensesServices.calculateExpenses(groupId);
		return ResponseEntity.ok(expensesList);

	}

}
