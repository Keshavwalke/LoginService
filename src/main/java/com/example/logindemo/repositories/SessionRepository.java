package com.example.logindemo.repositories;

import com.example.logindemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}