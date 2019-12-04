package com.customer.database.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "Name", length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal (10,2) NOT NULL DEFAULT 0.0")
	private double sales;
	@Column(name = "Active", nullable=false, columnDefinition="bit")
	private boolean isActive;
	
	public Customer() {}

	public Customer(int id, String name, double sales, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.sales = sales;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name 
				+ ", sales=" + sales + ", isActive=" + isActive + "]";
	}
	
}
