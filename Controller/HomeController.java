package com.billingapplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.billingapplication.entity.Admin;
import com.billingapplication.repository.AdminRepository;
import com.billingapplication.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private AdminService adminSer;
	@Autowired
	private AdminRepository adminrepo;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String adminLogin(HttpSession session,Model m) {
		String errorMessage=(String) session.getAttribute("errorMess");
		if(errorMessage!=null) {
			m.addAttribute("error",errorMessage);
			session.removeAttribute("errorMess");			
		}
		return "adminlogin";
	}

	@PostMapping("/adminLogin")
	public String loginAdminValidate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {
		Admin admin = adminSer.validateAdmin(email, password);
		if (admin != null) {
			session.setAttribute("adminloginsession", admin);
			return "redirect:/welcome";
		} else {
			session.setAttribute("errorMess", "Invalid email or password");
			return "redirect:/login";
		}
	}

	@GetMapping("/welcome")
	public String welcomePage(HttpSession session, Model m) {
		if (session.getAttribute("adminloginsession") != null) {
			return "welcome";
		} else {
			return "redirect:/login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session , HttpServletRequest request) {
		session.getAttribute("adminloginsession");
		session.removeAttribute("adminloginsession");
		session.setAttribute("msg", "Logout Successful");
		return "redirect:/login";
	}
}
