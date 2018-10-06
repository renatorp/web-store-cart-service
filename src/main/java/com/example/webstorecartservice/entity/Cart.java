package com.example.webstorecartservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Cart {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer customerId;
	
	@OneToMany(mappedBy= "cart")
	@JsonManagedReference
	private List<CartItem> items;
}
