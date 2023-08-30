package com.clearexpenses.main.service.serviceImplementation;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clearexpenses.main.dtos.ExpensesRequest;
import com.clearexpenses.main.dtos.ExpensesResponse;
import com.clearexpenses.main.dtos.GroupRequest;
import com.clearexpenses.main.dtos.ItemList;
import com.clearexpenses.main.entities.ExpensesGroup;
import com.clearexpenses.main.entities.UserItems;
import com.clearexpenses.main.entities.Users;
import com.clearexpenses.main.repositories.GroupRespository;
import com.clearexpenses.main.repositories.ItemsRespository;
import com.clearexpenses.main.repositories.UserRepository;
import com.clearexpenses.main.service.ExpensesServices;

@Service
public class ExpensesServiceImplementation implements ExpensesServices {
	
	@Autowired
	private  GroupRespository groupRespository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemsRespository itemsRespository;
	
	@Override
	public Map<String, Object> createGroup(GroupRequest groupRequest) {
		Map<String, Object> responseJson = new HashMap<>();
		ExpensesGroup groupResponse = groupRespository.findByName(groupRequest.getGroup_name());		
		if(groupResponse==null) {
			ExpensesGroup res = null;
			try {
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY mm:hh:ss");
				ExpensesGroup expensesGroup = new ExpensesGroup();
				expensesGroup.setName(groupRequest.getGroup_name());
				expensesGroup.setStatus(true);
				expensesGroup.setCreateDate(dateFormat.format(currentDate));
				expensesGroup.setUpdateDate(dateFormat.format(currentDate));
				expensesGroup.setUsers(null);
				res = groupRespository.save(expensesGroup);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (res.getId() != 0) {
				responseJson.put("statusCode", "200");
				responseJson.put("statusMessage", "group successfully created.");

			} else {
				responseJson.put("statusCode", "400");
				responseJson.put("statusMessage", "group creation got failed.");

			}
		}else {
			responseJson.put("statusCode", "404");
			responseJson.put("statusMessage", "this group is already created.");

		}
	

	
		return responseJson;

	}
	
	
	@Override
	public List<Users> addUsersByGroup(GroupRequest groupRequest) {
		List<Users> userList = new ArrayList<Users>();

		ExpensesGroup groupObject = groupRespository.findByName(groupRequest.getGroup_name());

		System.out.println("not found :" + groupObject);
		try {
			if (groupObject != null) {
				for (int i = 0; i < groupRequest.getUsers().size(); i++) {
					Users user = new Users();
					user.setName(groupRequest.getUsers().get(i).getUser_name());
					user.setMobile(groupRequest.getUsers().get(i).getMobile_number());
					user.setAddress(groupRequest.getUsers().get(i).getAddress());
					user.setExpensesGroup(groupObject);
					userList.add(user);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return userRepository.saveAll(userList);

	}

	@Override
	public List<UserItems> addExpensesByUser(ExpensesRequest expensesRequest) {

		List<UserItems> list = new ArrayList<UserItems>();
		LocalDate current = LocalDate.now();
		List<UserItems> resp = null;
		try {
			Optional<Users> userExist = userRepository.findById(expensesRequest.getUser_id());

			if (userExist.isPresent()) {

				for (ItemList itr : expensesRequest.getItemsList()) {
					UserItems items = new UserItems();
					items.setName(itr.getItem_name());
					items.setPrice(itr.getItem_price());
					items.setUserid(userExist.get().getId());
					items.setDate(current);
					items.setGroupId(userExist.get().getExpensesGroup().getId());
					list.add(items);
				}

				resp = itemsRespository.saveAll(list);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public List<ExpensesResponse> calculateExpenses(Long groupId) {
		List<ExpensesResponse> response = new ArrayList<ExpensesResponse>();
		try {
			Optional<ExpensesGroup> groupExist = groupRespository.findById(groupId);
			if (groupExist.isPresent()) {
				List<Object[]> allItems = itemsRespository.findSumOfExpensesByUser(groupExist.get().getId());
				float total = itemsRespository.findTotalExpensesOfAllUsers(groupExist.get().getId());

				if (allItems.size() > 0) {

					int numberOfUsers = allItems.size();
					float avarageExpeses = total / numberOfUsers;

					for (Object[] itr : allItems) {
						ExpensesResponse expensesResponse = new ExpensesResponse();
						expensesResponse.setId((Long) itr[1]);
						expensesResponse.setName((String) itr[2]);
						expensesResponse.setMobile((String) itr[3]);
						Double extraMoney = avarageExpeses - (Double) itr[0];
						if (extraMoney >= 0) {
							expensesResponse.setNeedToPay(extraMoney);
							expensesResponse.setNeedToReceive(0.0);
						} else {
							expensesResponse.setNeedToPay(0.0);
							expensesResponse.setNeedToReceive(Math.abs(extraMoney));
						}
						response.add(expensesResponse);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
