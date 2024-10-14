package com.billingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billingapplication.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

}