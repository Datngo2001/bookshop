package com.model;
import java.io.Serializable;
import java.util.ArrayList;

public class cartProduct implements Serializable {
	private ArrayList<LineItem> items;
	public cartProduct() {
		items = new ArrayList<LineItem>();
	}
	public int getCount() {
		return items.size();
	}
	public void addToCart(LineItem lineItem) {
		
	}
}
