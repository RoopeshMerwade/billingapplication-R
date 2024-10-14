package com.billingapplication.service;

import java.util.List;
import com.billingapplication.entity.Customer;

public interface CustomerService {
	public Customer createCustomerAcc(Customer customer);
	public List<Customer> getAllCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(int id);
	public boolean existsEmail(String email);
	public String getEmailById(int id);
	public List<Customer> searchCustomer(String search);
	public Customer getCustomerById(int id);
}
