package com.example.logindemo.repositories;

import com.example.logindemo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByIdIn(List<Long> roleIds);
}
