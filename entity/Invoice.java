package com.billingapplication.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Invoice {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String customer_name;
    private String customer_email;
    private String customer_city;
    private Long customer_mobileno;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceProduct> invoiceProducts = new ArrayList<>(); // Initialize here

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_city() {
		return customer_city;
	}

	public void setCustomer_city(String customer_city) {
		this.customer_city = customer_city;
	}

	public Long getCustomer_mobileno() {
		return customer_mobileno;
	}

	public void setCustomer_mobileno(Long customer_mobileno) {
		this.customer_mobileno = customer_mobileno;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<InvoiceProduct> getInvoiceProducts() {
		return invoiceProducts;
	}

	public void setInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customer=" + customer + ", customer_name=" + customer_name + ", customer_email="
				+ customer_email + ", customer_city=" + customer_city + ", customer_mobileno=" + customer_mobileno
				+ ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", totalAmount=" + totalAmount
				+ ", invoiceProducts=" + invoiceProducts + "]";
	}

	
	
    

}