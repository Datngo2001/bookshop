package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

//@SuppressWarnings("serial")
@Entity
public class Cart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Transient
	private float total;

	// Relation
	@OneToOne
	private User user;
	@OneToMany(mappedBy = "cart")
	private List<LineItem> items = new ArrayList<LineItem>();

	public Cart() {
	}

	public Cart(int id, User user, List<LineItem> items) {
		this.id = id;
		this.user = user;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}

	public float getTotal() {
		total = 0;
		for (LineItem lineItem : items) {
			Product product = lineItem.getProduct();
			if (product != null) {
				total += lineItem.getProduct().price;
			}
		}
		return total;
	}
}
