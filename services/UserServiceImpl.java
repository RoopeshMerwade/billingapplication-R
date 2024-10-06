package com.billingapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billingapplication.entity.User;
import com.billingapplication.exception.RecordNotFoundException;
import com.billingapplication.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	@Override
	public User createAccountant(User user) {
		user.setRole("ROLE_ACCOUNTANT");
		User newAcc=userRepo.save(user);
		return newAcc;
	}

	@Override
	public boolean existsEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public User updateAccountant(User user) {
		Optional<User> u=userRepo.findById(user.getId());
		if(u.isPresent()) {
			User us=u.get();
			us.setName(user.getName());
			us.setCity(user.getCity());
			us.setEmail(user.getEmail());
			us.setMobileNumber(user.getMobileNumber());
			us.setRole("ROLE_ACCOUNTANT");
			return userRepo.save(us);
		}
		else {
            throw new RecordNotFoundException("Accountant with id " + user.getId() + " not found");
        }
	}

	@Override
	public List<User> getAllAccountant(User user) {
		return userRepo.findAll();
	}

	@Override
	public void deleteAccountant(int id) {
		Optional<User> u=userRepo.findById(id);
		if(u.isPresent()) {
			User us=u.get();
			userRepo.delete(us);
		}
		else {
			throw new RecordNotFoundException("Accountant with id " + id + " not found");
		}
	}

	@Override
	public User validateAccountant(String email,String password) {
		User u=userRepo.findByEmail(email);
		if(u!=null && u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}

}
