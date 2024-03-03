package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT cus FROM Customer cus WHERE cus.pkId = :pkId")
    public Customer findById(int pkId);
}
