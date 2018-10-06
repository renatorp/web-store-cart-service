package com.example.webstorecartservice.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.webstorecartservice.entity.Cart;
import com.example.webstorecartservice.entity.CartItem;
import com.example.webstorecartservice.entity.CartProduct;
import com.example.webstorecartservice.exception.CartNotFoundException;
import com.example.webstorecartservice.service.CartService;
import com.example.webstorecartservice.serviceproxy.ProductServiceProxy;
import com.example.webstorecartservice.vo.CartItemRequestVO;

@RequestMapping("carts")
@RestController
public class CartResource {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductServiceProxy productProxy;
	
	@PutMapping
	public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
		cart = cartService.createCart(cart.getCustomerId());
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/carts/{cartId}")
				.buildAndExpand(cart.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	public Cart getCart(@PathVariable("id") Integer id) {
		Cart cart = cartService.getCart(id);
		if (cart == null) {
			throw new CartNotFoundException("Cart does not exist");
		}
		return cart;
	}
	
	@PutMapping("{cartId}/items")
	public ResponseEntity<CartItem> addItem(@PathVariable("cartId") Integer cartId, @RequestBody CartItemRequestVO itemRequest) {
		Cart cart = getCart(cartId);
		
		CartProduct product = productProxy.getProduct(itemRequest.getProductId());
		
		product = cartService.updateReferenceCartProduct(product);
		
		CartItem cartItem = cartService.addItemToCart(cart, product, itemRequest.getQuantity());
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/carts/{cartId}/cartitems/{id}")
				.buildAndExpand(cart.getId(), cartItem.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("{cartId}/items/{id}")
	public ResponseEntity<CartItem> updateItem(@PathVariable("cartId") Integer cartId, @PathVariable("id") Integer id, @RequestBody CartItem item) {
		Cart cart = getCart(cartId);
		item.setCart(cart);
		cartService.updateCartItem(item);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}/items/products")
	public List<CartProduct> findCartItemsProducts(@PathVariable("id") Integer id) {
		return cartService.findCartItemsProducts(id);
	}
	
	@DeleteMapping("{id}/items")
	public void clearCart(@PathVariable("id") Integer id) {
		Cart cart = getCart(id);
		cartService.clearCart(cart);
	}
	
}
