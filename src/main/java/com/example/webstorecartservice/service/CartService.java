package com.example.webstorecartservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webstorecartservice.dao.CartDAO;
import com.example.webstorecartservice.dao.CartItemDAO;
import com.example.webstorecartservice.dao.CartProductDAO;
import com.example.webstorecartservice.entity.Cart;
import com.example.webstorecartservice.entity.CartItem;
import com.example.webstorecartservice.entity.CartProduct;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@Autowired
	private CartProductDAO cartProductDAO;
	
	public Cart createCart(Integer customerId) {
		Cart k = new Cart();
		k.setCustomerId(customerId);
		return cartDAO.save(k);
	}

	public Cart getCart(Integer id) {
		Optional<Cart> opt = cartDAO.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public CartItem addItemToCart(Cart cart, CartProduct product, Integer quantity) {
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setCart(cart);
		item.setQuantity(quantity);
		return cartItemDAO.save(item);
	}

	public void updateCartItem(CartItem item) {
		cartItemDAO.save(item);
	}

	public Cart findCartByCustomer(Integer customerId) {
		return cartDAO.findByCustomerId(customerId);
	}

	public CartProduct updateReferenceCartProduct(CartProduct product) {
		Optional<CartProduct> opt = cartProductDAO.findById(product.getId());
		if (!opt.isPresent()) {
			return cartProductDAO.save(product);
		}
		return opt.get();
	}

	public List<CartProduct> findCartItemsProducts(Integer id) {
		return cartProductDAO.findProductsByCart(id);
	}

	public void clearCart(Cart cart) {
		cart.getItems().forEach(cartItemDAO::delete);
	}


}
