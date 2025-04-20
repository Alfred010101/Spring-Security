package com.example.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springSecurity.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> 
{

}
