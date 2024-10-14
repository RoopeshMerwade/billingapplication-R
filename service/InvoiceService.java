package com.billingapplication.service;
import java.time.LocalDate;
import java.util.List;

import com.billingapplication.entity.Invoice;
import com.billingapplication.entity.Product;


public interface InvoiceService {
	public Invoice createInvoice(List<Integer> productIds, List<Integer> quantities, LocalDate dueDate, int customerId);
	public Invoice getInvoiceById(int invoiceId);
	public void deleteInvoice(int InvoiceId); 
}