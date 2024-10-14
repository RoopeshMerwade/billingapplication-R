package com.billingapplication.serviceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billingapplication.entity.Customer;
import com.billingapplication.entity.Invoice;
import com.billingapplication.entity.InvoiceProduct;
import com.billingapplication.entity.Product;
import com.billingapplication.exception.RecordNotFoundException;
import com.billingapplication.repository.CustomerRepository;
import com.billingapplication.repository.InvoiceRepository;
import com.billingapplication.repository.ProductRepository;
import com.billingapplication.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
    private InvoiceRepository invoiceRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public Invoice createInvoice(List<Integer> productIds, List<Integer> quantities, LocalDate dueDate, int customerId) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setDueDate(dueDate);

        
        Customer customer = customerRepo.findById(customerId)
            .orElseThrow(() -> new RecordNotFoundException("Customer not found"));

        invoice.setCustomer(customer); // Set customer in invoice
        invoice.setCustomer_name(customer.getName());
        invoice.setCustomer_email(customer.getEmail());
        invoice.setCustomer_city(customer.getCity());
        invoice.setCustomer_mobileno(customer.getMobileNumber());
        
        double totalAmount = 0;
        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepo.findById(productIds.get(i))
                .orElseThrow(() -> new RecordNotFoundException("Product not found"));
            
            InvoiceProduct invoiceProduct = new InvoiceProduct();
            invoiceProduct.setProduct(product); // Set the correct product variable
            invoiceProduct.setInvoice(invoice);
            invoiceProduct.setQuantity(quantities.get(i));
            invoiceProduct.setProductPrice(quantities.get(i)*product.getPrice());
            totalAmount += product.getPrice() * quantities.get(i);
            invoice.getInvoiceProducts().add(invoiceProduct); // Add to the lists
        }

        invoice.setTotalAmount(totalAmount);
        return invoiceRepo.save(invoice);
    }

	@Override
	public Invoice getInvoiceById(int invoiceId) {
		Optional<Invoice> invoiceDb=invoiceRepo.findById(invoiceId);
		if(invoiceDb.isPresent()) {
			Invoice inv=invoiceDb.get();
			return inv;
		}
		else {
			throw new RecordNotFoundException("Invoice with id "+invoiceId+" not found");		
		}
	}

	@Override
	public void deleteInvoice(int invoiceId) {
		Optional<Invoice> invoiceDb=invoiceRepo.findById(invoiceId);
		if(invoiceDb.isPresent()) {
			Invoice inv=invoiceDb.get();
			invoiceRepo.delete(inv);
		}
		else {
			throw new RecordNotFoundException("Invoice with id "+invoiceId+" not found");		
		}
	}
    
}