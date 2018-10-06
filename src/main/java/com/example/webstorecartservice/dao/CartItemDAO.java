package com.example.webstorecartservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webstorecartservice.entity.CartItem;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Integer> {

}
