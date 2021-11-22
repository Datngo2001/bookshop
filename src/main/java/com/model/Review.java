package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String date;
    @Type(type = "text")
    private String content;
    private int stars;

    // Relation
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

    public Review() {

    }

    public Review(int id, String date, String content, User user, Product product, int stars) {
        super();
        this.id = id;
        this.date = date;
        this.stars = stars;
        this.content = content;
        this.user = user;
        this.product = product;
    }

    public Review(String date, String content, User user, Product product, int stars) {
        super();
        this.date = date;
        this.stars = stars;
        this.content = content;
        this.user = user;
        this.product = product;
    }

    public int getId() {
        return this.id;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return this.stars;
    }

    public String getUserName() {
        return this.user.getUsername();
    }

    public int getProductId() {
        return this.product.getId();
    }

    public String getContent() {
        return this.content;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
