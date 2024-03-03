package com.tech.techy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tech.techy.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT r FROM Role r WHERE r.pkNumRoles = :pkNumRoles")
    public Role findById(int pkNumRoles);
}
