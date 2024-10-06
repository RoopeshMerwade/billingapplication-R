package com.billingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billingapplication.entity.Admin;
import com.billingapplication.service.AdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin") 
public class HomeController {
    @Autowired
    private AdminService adminSer;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Welcome to the Admin API");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdminValidate(@RequestBody Admin adminCredentials, HttpSession session) {
        String email = adminCredentials.getEmail();
        String password = adminCredentials.getPassword();
        Admin admin = adminSer.validateAdmin(email, password);

        if (admin != null) {
            session.setAttribute("adminloginsession", admin); 
            return ResponseEntity.ok().body(adminSer.validateAdmin(email, password));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/welcome")
    public ResponseEntity<?> welcomePage(HttpSession session) {
        if (session.getAttribute("adminloginsession") != null) {
            return ResponseEntity.ok("Welcome to the Admin Dashboard!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access. Please log in.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("adminloginsession");
        session.setAttribute("msg", "Logout Successful");
        return ResponseEntity.ok("Logout Successful");
    }
}

