package com.clearexpenses.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearexpenses.main.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
