package com.model;

public class Product {
	public int id;
	public String name;
	public String description;
	public int price;
	public double discount;
	public int getId() {
		return id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Product(String name, String description, int price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public Product(int id, String name, String description, int price, double discount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
	}
	
}
