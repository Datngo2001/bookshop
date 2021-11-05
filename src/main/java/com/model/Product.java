package com.model;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	public String codeProduct;
	public String nameAuthor;
	public String description;
	public int price;
	public String pictureUrl;
	public String Sku;
	public String typeBook;
	
	public Product(String nameAuthor, String description, int price, String pictureUrl, String sku) {
		super();
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.Sku = sku;
	}
	public Product() {
		
	}
	
	public Product(String nameAuthor, String description, int price, String type) {
		super();
		this.nameAuthor = nameAuthor;
		this.description = description;
		this.price = price;
		this.typeBook = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeProduct() {
		return codeProduct;
	}
	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}
	public String getNameAuthor() {
		return nameAuthor;
	}
	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
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
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getSku() {
		return Sku;
	}
	public void setSku(String sku) {
		Sku = sku;
	}
	public String getTypeBook() {
		return typeBook;
	}
	public void setTypeBook(String typeBook) {
		this.typeBook = typeBook;
	}
}
