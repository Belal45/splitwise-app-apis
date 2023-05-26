package com.clearexpenses.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearexpenses.main.entities.ExpensesGroup;

public interface GroupRespository extends JpaRepository<ExpensesGroup, Long>{

	public ExpensesGroup findByName(String name);
}
