package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.DTOs.BusinessDtos.CartDTO;
import com.DTOs.BusinessDtos.LineItemDTO;
import com.data.DAOs.CartDAO;

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
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	private List<LineItem> items = new ArrayList<LineItem>();

	public Cart() {
	}

	public Cart(int id, User user, List<LineItem> items) {
		this.id = id;
		this.user = user;
		this.items = items;
	}

	// BUSINESS LOGIC ----------------------------------------------------
	
	public void UpdateTotalPrice() {
		total = 0;
		for (LineItem lineItem : items) {
			Product product = lineItem.getProduct();
			if (product != null) {
				total += lineItem.getProduct().getPrice() * lineItem.getQuantity();
			}
		}
	}

	public CartDTO getUserCart(int userId) {
		CartDTO cartDTO = new CartDTO();
		Cart cart = new CartDAO().getUserCart(userId);

		// Map to CartDTO
		cartDTO.setId(cart.id);
		for (LineItem item : cart.getItems()) {
			LineItemDTO itemDTO = new LineItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setProductID(item.getProduct().getId());
			itemDTO.setProductName(item.getProduct().getProductName());
			itemDTO.setPrice(item.getProduct().getPrice());

			cartDTO.getItems().add(itemDTO);
		}
		cartDTO.setTotal(cart.getTotal());

		return cartDTO;
	}

	public CartDTO addItem(CartDTO cartDTO, int productId, int quantity) {
		CartDAO cartDao = new CartDAO();
		Cart cart = cartDao.getCart(cartDTO.getId());
		LineItem item = cartDao.addToCart(cart, productId, quantity);

		LineItemDTO lineItemDTO = new LineItemDTO();
		lineItemDTO.setId(item.getId());
		lineItemDTO.setPrice(item.getProduct().getPrice());
		lineItemDTO.setProductName(item.getProduct().getProductName());
		lineItemDTO.setQuantity(item.getQuantity());
		cartDTO.getItems().add(lineItemDTO);
		cartDTO.setTotal(cart.getTotal());

		return cartDTO;
	}

	public CartDTO removeItem(CartDTO cartDTO, int itemId) {
		CartDAO cartDao = new CartDAO();
		Cart cart = cartDao.getCart(cartDTO.getId());

		cartDao.RemoveItem(cart, itemId);
		for (LineItemDTO item : cartDTO.getItems()) {
			if (item.getId() == itemId) {
				cartDTO.getItems().remove(item);
			}
		}

		return cartDTO;
	}

	public CartDTO updateQuantity(CartDTO cartDTO, int itemId, int quantity) {
		CartDAO cartDao = new CartDAO();
		Cart cart = cartDao.getCart(cartDTO.getId());

		cartDao.updateQuantity(cart, itemId, quantity);
		for (LineItemDTO item : cartDTO.getItems()) {
			if (item.getId() == itemId) {
				item.setQuantity(quantity);
			}
		}
		cartDTO.setTotal(cart.getTotal());

		return cartDTO;
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
		return total;
	}
}
