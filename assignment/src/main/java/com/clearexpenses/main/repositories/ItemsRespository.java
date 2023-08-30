package com.clearexpenses.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clearexpenses.main.entities.UserItems;

public interface ItemsRespository extends JpaRepository<UserItems, Long>{
	
	@Query(value="SELECT sum(t.item_price),t.user_id,r.user_name,r.user_mobile FROM mylocal.user_items_table t inner join mylocal.users_table r where t.user_group=?1 and t.user_id=r.user_id group by t.user_id;",nativeQuery = true)
	public List<Object[]> findSumOfExpensesByUser(Long groupId);
	
	
	@Query(value="SELECT sum(t.item_price) FROM mylocal.user_items_table t where t.user_group=:groupId",nativeQuery = true)
	public float findTotalExpensesOfAllUsers(@Param("groupId") Long groupId);
	

}
