package com.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order order;

    @OneToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    public int amount;

    public Item() {

    }

    public Item(int amount) {
        this.amount = amount;
    }

    public Item(Order order, Product product, int id, int amount) {
        this.order = order;
        this.product = product;
        this.id = id;
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
