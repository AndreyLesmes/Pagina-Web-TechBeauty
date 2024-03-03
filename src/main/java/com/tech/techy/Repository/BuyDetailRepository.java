package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.BuyDetail;

@Repository
public interface BuyDetailRepository extends JpaRepository<BuyDetail, Integer> {
    @Query(value = "SELECT buyd FROM BuyDetail buyd WHERE buyd.pkId = :pkId")
    public BuyDetail findById(int pkId);
}
