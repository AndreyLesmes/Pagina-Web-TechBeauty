package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.Buy;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Integer> {
    @Query(value = "SELECT b FROM Buy b WHERE b.pkId = :pkId")
    public Buy findById(int pkId);
}
