package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query(value = "SELECT i FROM Inventory i WHERE i.pkId = :pkId")
    public Inventory findById(int pkId);
}
