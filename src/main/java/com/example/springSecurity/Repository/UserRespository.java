package com.example.springSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springSecurity.model.User;

public interface UserRespository extends JpaRepository<User, Integer> 
{
    Optional<User> findByUsername(String username);
}