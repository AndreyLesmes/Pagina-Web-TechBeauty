package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.OrderSupplier;

@Repository
public interface OrderSupplierRepository extends JpaRepository<OrderSupplier, Integer> {
    @Query(value = "SELECT os FROM OrderSupplier os WHERE os.pkId = :pkId")
    public OrderSupplier findById(int pkId);
}
