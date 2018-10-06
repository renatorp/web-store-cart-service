package com.example.webstorecartservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webstorecartservice.entity.CartProduct;

@Repository
public interface CartProductDAO extends JpaRepository<CartProduct, Integer> {

	@Query("SELECT p FROM CartItem i JOIN i.product p WHERE i.cart.id = :cartId")
	List<CartProduct> findProductsByCart(@Param("cartId") Integer id);
}
