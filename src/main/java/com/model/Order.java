package com.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order implements Serializable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    public int code;
    public String nameItem;
    public String username;
    public double bill;
    public String nameBill;
    public Timestamp createDate;

    public Order(User user, int code, String nameItem, String username, double bill, String nameBill, Timestamp createDate) {
		super();
		this.user = user;
		this.code = code;
		this.nameItem = nameItem;
		this.username = username;
		this.bill = bill;
		this.nameBill = nameBill;
		this.createDate = createDate;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public String getNameBill() {
		return nameBill;
	}

	public void setNameBill(String nameBill) {
		this.nameBill = nameBill;
	}

	public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}