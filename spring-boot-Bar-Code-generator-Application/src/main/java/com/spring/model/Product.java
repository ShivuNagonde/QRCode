package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "barcodes")
public class Product {

	@Id
	private String id;
	private String name;
	private String designation;
	private String uom;
	private String hsn;
	private String image;
	private double price;
	private String manufactureDate;
	private String validity;
}
