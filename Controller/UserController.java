package com.billingapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billingapplication.entity.Admin;
import com.billingapplication.entity.User;
import com.billingapplication.exception.RecordNotFoundException;
import com.billingapplication.repository.UserRepository;
import com.billingapplication.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
	@Autowired
	private UserService userSer;
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/api/admin/createAccountant")
	public ResponseEntity<String> createAccountant(@RequestBody User us, HttpSession session) {
		if (session.getAttribute("adminloginsession") != null) {
			Boolean emailExists = userSer.existsEmail(us.getEmail());
			if (emailExists) {
				return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
			} else {
				User newUser = userSer.createAccountant(us);
				if (newUser != null) {
					return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/api/admin/updateAccountant/{id}")
	public ResponseEntity<?> getUpdateAccountant(@PathVariable("id") int id, HttpSession session,
			@RequestBody User us) {
		if (session.getAttribute("adminloginsession") != null) {
			us.setId(id);
			return ResponseEntity.ok().body(userSer.updateAccountant(us));
		}
		return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/api/admin/displayAcc")
	public ResponseEntity<?> getAllAccountants(HttpSession session) {
		if (session.getAttribute("adminloginsession") != null) {
			List<User> users = userSer.getAllAccountant(new User());
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("/api/admin/deleteAcc/{id}")
	public ResponseEntity<String> deleteAccountant(@PathVariable("id") int id, HttpSession session) {
		if (session.getAttribute("adminloginsession") != null) {
			userSer.deleteAccountant(id);
			return new ResponseEntity<>("Accountant deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/api/users/accLogin")
	public ResponseEntity<String> loginAccountant(@RequestBody User accountantCredentials, HttpSession session) {
		String email=accountantCredentials.getEmail();
		String password=accountantCredentials.getPassword();
		User user=userSer.validateAccountant(email, password);
		if (user != null) {
			session.setAttribute("accountantlogin", user);
			return new ResponseEntity<>("Login successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
		}
	}
}
