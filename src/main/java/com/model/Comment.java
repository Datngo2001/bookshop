package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    private Product product = new Product();
    private User user = new User();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private Date date;
    private String content;

    public Comment() {

    }

    public Comment(int id, Date date, String content, String username, String email, int productId) {
        super();
        this.id = id;
        this.date = date;
        this.content = content;
        this.user = new User(username, email);
        this.product = Product.find(productId);
    }

    // public void set() {}
    // public String get(){}
    public String getUserName() {
        return this.user.getUsername();
    }

    public String getContent() {
        return this.content;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
