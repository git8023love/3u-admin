package com._3u.domain;

import java.io.Serializable;

public class Dog implements Serializable {
	private Integer id;
	private String name;
	private String image;
	private Double price;
	private String owner;
	
	public Dog() {
		
		// TODO Auto-generated constructor stub
	}
	public Dog(Integer id, String name, String image, Double price, String owner) {
		
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.owner = owner;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	

}
