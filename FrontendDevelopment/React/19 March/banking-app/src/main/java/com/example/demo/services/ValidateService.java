package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Credentials;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepo;

@Service
public class ValidateService {

    @Autowired
    private CustomerRepo custRepo; 

    public boolean validateLogin(Credentials cr) {
        Optional<Customer> cc = custRepo.findById(cr.getCustomerId());

        if (cc.isPresent()) {
            Customer customer = cc.get();
            return customer.getPassword().equals(cr.getPassword()); 
        }

        return false;
    }
}
