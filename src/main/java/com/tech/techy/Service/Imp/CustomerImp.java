package com.tech.techy.Service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.techy.Entity.Customer;
import com.tech.techy.Repository.CustomerRepository;
import com.tech.techy.Service.CustomerService;

@Service
public class CustomerImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(int pkId) {
        Customer customer = this.customerRepository.findById(pkId);
        return customer;

    }

    @Override
    public void create(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
