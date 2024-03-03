package com.tech.techy.Service;

import java.util.List;

import com.tech.techy.Entity.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(int pkId);

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
