package com.example.webstorecartservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webstorecartservice.entity.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer>{

	Cart findByCustomerId(Integer customerId);

}
