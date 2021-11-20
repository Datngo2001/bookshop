package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {
    private Product product = new Product();
    private User user = new User();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int stars;
    private Date date;

    public Rating() {

    }

    public Rating(int id, int stars, Date date, String username, String email, int productId) {
        super();
        this.id = id;
        this.stars = stars;
        this.date = date;
        this.user = new User(username, email);
        product = Product.find(productId);
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStars() {
        return this.stars;
    }

    public Date getDate() {
        return this.date;
    }

    public Product getRatingProduct() {
        return this.product;
    }

    public User getRatingUser() {
        return this.user;
    }

    public int getId() {
        return this.id;
    }

}
