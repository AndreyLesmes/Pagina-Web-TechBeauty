package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query(value = "SELECT st FROM Status st WHERE st.pkId = :pkId")
    public Status findById(int pkId);
}
