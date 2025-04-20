package com.example.springSecurity.service;

import org.springframework.stereotype.Service;

import com.example.springSecurity.model.User;
import com.example.springSecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService 
{

    private final UserRepository userRepository;

    public void createUser(User user)
    {
        userRepository.save(user);
    }
}