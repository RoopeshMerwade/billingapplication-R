package com.billingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billingapplication.entity.Admin;
import com.billingapplication.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public Admin validateAdmin(String email, String password) {
		Admin admin=adminRepo.findByEmail(email);
		if(admin!=null && admin.getPassword().equals(password)) {
			return admin;
		}
		return null;
	}

}
