package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Accounts;
import com.example.demo.entities.Customer;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Integer> {
    List<Accounts> findByCustomer(Customer customer);
}

