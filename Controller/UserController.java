package com.billingapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.billingapplication.entity.User;
import com.billingapplication.repository.UserRepository;
import com.billingapplication.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class UserController {
	@Autowired
	private UserService userSer;
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/createAccountant")
	public String createAccountant(@ModelAttribute User us, HttpSession session) {
		if (session.getAttribute("adminloginsession") != null) {
			Boolean b = userSer.existsEmail(us.getEmail());
			if (b) {
				session.setAttribute("emailexists", "Email already exists");
			} else {
				User newUser = userSer.createAccountant(us);
				if (newUser != null) {
					session.setAttribute("mess", "Account created successfully");
				} else {
					session.setAttribute("error", "Something went wrong");
				}
			}
		}
		return "redirect:/welcome";
	}
	@GetMapping("/updateAccountant/{id}")
	public String updateAccountant(HttpSession session, @PathVariable("id") int id, Model m, @ModelAttribute User us) {
		if (session.getAttribute("adminloginsession") != null) {
			Optional<User> u = userRepo.findById(id);
			if (u.isPresent()) {
				m.addAttribute("update", u.get());
				return "updateAccountant";
			}
			session.setAttribute("error", "User not found");
			return "redirect:/welcome";
		}
		return "adminlogin";
	}

	@PostMapping("/updateAccountant")
	public String updateAccountant(@ModelAttribute User us, HttpSession session) {
		if (session.getAttribute("adminloginsession") != null) {
			User updateAcc = userSer.updateAccountant(us);
			if (updateAcc != null) {
				session.setAttribute("mess", "Accountant Details updated successfully");
			} else {
				session.setAttribute("error", "Not Updated");
			}
			return "updateAccountant";
		}
		return "adminlogin";
	}
	@GetMapping("/displayAcc")
	public String getAllAcc(HttpSession session,Model m,User user) {
		if(session.getAttribute("adminloginsession")!=null) {
			List<User> list_user=userSer.getAllAccountant(user);
			m.addAttribute("user", list_user);
			return "displayAcc";			
		}
		return "adminlogin";
	}
	@GetMapping("/deleteAcc/{id}")
	public String deleteAcc(HttpSession session,@PathVariable("id") int id) {
		if(session.getAttribute("adminloginsession")!=null) {
			userSer.deleteAccountant(id);
			return "welcome";			
		}
		return "adminlogin";
	}
}
