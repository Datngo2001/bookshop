package com.model;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "cart_list")
public class CardList implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")

	public int id;
	public int uid;
	public int price;
	public String name;
	public String author;
	public int amount;
	public CardList() {}
	public CardList(int uid, String name, String author, int amount, int price) {
		super();
		this.uid = uid;
		this.name = name;
		this.author = author;
		this.amount = amount;
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double totalPrice() {
		double total =  this.amount * getPrice();
		return total;
	}
	
}
