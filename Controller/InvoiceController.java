package com.billingapplication.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.billingapplication.entity.Invoice;
import com.billingapplication.entity.Product;
import com.billingapplication.service.InvoiceService;

@RestController
public class InvoiceController {
	@Autowired
	InvoiceService invSer;

	@PostMapping("/api/accountant/createInvoice")
	public ResponseEntity<Invoice> createInvoice(@RequestParam int customerId, @RequestParam List<Integer> productIds, @RequestParam List<Integer> quantities,@RequestParam LocalDate dueDate) {

		Invoice invoice = invSer.createInvoice(productIds, quantities, dueDate, customerId);
		return ResponseEntity.ok(invoice); 
	}
	@GetMapping("/api/accountant/viewInvoice/{invoiceId}")
	public ResponseEntity<Invoice> viewInvoiceById(@PathVariable("invoiceId") int invoiceId){
		Invoice inv=invSer.getInvoiceById(invoiceId);
		return new ResponseEntity<Invoice>(inv, HttpStatus.OK);
	}
	@DeleteMapping("/api/accountant/deleteInvoice/{invoiceId}")
	public ResponseEntity<String> deleteInvoiceById(@PathVariable("invoiceId") int invoiceId){
		invSer.deleteInvoice(invoiceId);
		return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
	}
}