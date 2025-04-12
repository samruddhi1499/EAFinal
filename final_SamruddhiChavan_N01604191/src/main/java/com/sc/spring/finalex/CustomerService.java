package com.sc.spring.finalex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	 @Autowired
	    private CustomerRepository customerRepository;

	    public Customer add(Customer customer) {
	       
	        return customerRepository.save(customer);
	    }

	    public Customer update(String id, String newAddress) {
	        Customer existing = customerRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Customer not found."));
	        existing.setAddress(newAddress);
	        return customerRepository.save(existing);
	    }
}
