package com.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "cart_list")
public class CardList implements Serializable {
	@OneToMany
	private List<User> user = new ArrayList<User>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	public String username;
	public int price;
	public String name;
	public String author;
	public int amount;
	public String codeProduct;
	public CardList() {}
	public CardList(String code,String uid, String name, String author, int amount, int price) {
		super();
		this.codeProduct = code;
		this.username = uid;
		this.name = name;
		this.author = author;
		this.amount = amount;
		this.price = price;
	}
	public CardList(List<User> user, int id, String username, int price, String name, String author, int amount,
			String codeProduct) {
		super();
		this.user = user;
		this.id = id;
		this.username = username;
		this.price = price;
		this.name = name;
		this.author = author;
		this.amount = amount;
		this.codeProduct = codeProduct;
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
	public String getUid() {
		return username;
	}
	public void setUid(String uname) {
		this.username = uname;
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
		return this.amount * getPrice();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getbId() {
		return codeProduct;
	}
	public void setbId(String code) {
		this.codeProduct = code;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<User> getUser() {
		return user;
	}
	public String getCodeProduct() {
		return codeProduct;
	}
	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	
}
