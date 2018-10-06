package com.example.webstorecartservice.resource;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webstorecartservice.entity.Cart;
import com.example.webstorecartservice.exception.CartNotFoundException;
import com.example.webstorecartservice.service.CartService;
import com.example.webstorecartservice.vo.CartItemResponseVO;
import com.example.webstorecartservice.vo.CartResponseVO;

@RequestMapping("customers")
@RestController
public class CustomerResource {
	
	@Autowired
	private CartService cartService;

	@GetMapping("{customerId}/cart")
	public CartResponseVO getCartByCustomer(@PathVariable("customerId") Integer customerId) {
		Cart cart = cartService.findCartByCustomer(customerId);
		
		if (cart == null) {
			throw new CartNotFoundException("Carrinho não encontrado.");
		}
		
		return convertToResponseVO(cart);
	}

	private CartResponseVO convertToResponseVO(Cart cart) {
		CartResponseVO cartResponse = new CartResponseVO();
		cartResponse.setCustomerId(cart.getCustomerId());
		cartResponse.setId(cart.getId());
		cartResponse.setItems(new ArrayList<>());
		
		cart.getItems().forEach(it -> {
			CartItemResponseVO cartItemResponse = new CartItemResponseVO();
			cartItemResponse.setId(it.getId());
			cartItemResponse.setProductId(it.getProduct().getId());
			cartItemResponse.setQuantity(it.getQuantity());
			cartResponse.getItems().add(cartItemResponse);
		});
		
		return cartResponse;
	}
	
}