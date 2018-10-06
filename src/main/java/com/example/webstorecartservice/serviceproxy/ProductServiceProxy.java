package com.example.webstorecartservice.serviceproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webstorecartservice.entity.CartProduct;

@RibbonClient
@FeignClient(name = "web-store-product-service")
@RequestMapping("products")
public interface ProductServiceProxy {

	@GetMapping("{id}")
	public CartProduct getProduct(@PathVariable("id") Integer id);
}
