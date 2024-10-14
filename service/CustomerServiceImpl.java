package com.billingapplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billingapplication.entity.Customer;
import com.billingapplication.entity.User;
import com.billingapplication.exception.RecordNotFoundException;
import com.billingapplication.repository.CustomerRepository;
import com.billingapplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public Customer createCustomerAcc(Customer customer) {
		customer.setRole("ROLE_CUSTOMER");
		Customer newCustomer=customerRepo.save(customer);
		return newCustomer;
	}
	public boolean existsEmail(String email) {
		return customerRepo.existsByEmail(email);
	}
	@Override
	public List<Customer> getAllCustomer(Customer customer) {
		return customerRepo.findAll();
	}
	@Override
	public Customer updateCustomer(Customer customer) {
		Optional<Customer> cu=customerRepo.findById(customer.getId());
		if(cu.isPresent()) {
			Customer c=cu.get();
			c.setName(customer.getName());
			c.setEmail(customer.getEmail());
			c.setCity(customer.getCity());
			c.setPassword(customer.getPassword());
			c.setMobileNumber(customer.getMobileNumber());
			c.setRole("ROLE_CUSTOMER");
			return customerRepo.save(c);
		}
		else {
			throw new RecordNotFoundException("Customer with id " + customer.getId() + " not found");
		}
	}
	@Override
	public void deleteCustomer(int id) {
		Optional<Customer> cu=customerRepo.findById(id);
		if(cu.isPresent()) {
			Customer c=cu.get();
			customerRepo.delete(c);
		}
		else {
			throw new RecordNotFoundException("Customer with id " + id + " not found");
		}
		
	}
	@Override
	public String getEmailById(int id) {
		Optional<Customer> c=customerRepo.findById(id);
		if(c.isPresent()) {
			Customer us=c.get();
			return us.getEmail();
		}
		else {
			throw new RecordNotFoundException("Customer with id " + id + " not found");
		}
	}
	@Override
	public List<Customer> searchCustomer(String search) {
		return customerRepo.searchCustomer(search);
	}
	@Override
	public Customer getCustomerById(int id) {
		Optional<Customer> cusDb=customerRepo.findById(id);
		if(cusDb.isPresent()) {
			Customer c=cusDb.get();
			return c;
		}
		else {
			throw new RecordNotFoundException("Customer with id " + id + " not found");
		}
	}
	
}
