package com.billingapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billingapplication.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public boolean existsByEmail(String email);
	@Query("SELECT c FROM Customer c WHERE c.name LIKE %:search% OR c.email LIKE %:search%")
	public List<Customer> searchCustomer(@Param("search")String search);
}
