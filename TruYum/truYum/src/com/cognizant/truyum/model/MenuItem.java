package com.cognizant.truyum.model;

import java.util.Date;

import com.cognizant.truyum.util.DateUtil;

public class MenuItem {
	private Long id;
	private String name;
	private double price;
	private Boolean active;
	private Date dateOfLaunch;
	private String Category;
	private Boolean freeDelivery;

	public MenuItem() {
		super();
	}

	public MenuItem(Long id, String name, double d, Boolean active, Date dateOfLaunch, String category,
			Boolean freeDelivery) {
		super();
		this.id = id;
		this.name = name;
		this.price = d;
		this.active = active;
		this.dateOfLaunch = dateOfLaunch;
		this.Category = category;
		this.freeDelivery = freeDelivery;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}

	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public Boolean getFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(Boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Category == null) ? 0 : Category.hashCode());
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((dateOfLaunch == null) ? 0 : dateOfLaunch.hashCode());
		result = prime * result + ((freeDelivery == null) ? 0 : freeDelivery.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (Category == null) {
			if (other.Category != null)
				return false;
		} else if (!Category.equals(other.Category))
			return false;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (dateOfLaunch == null) {
			if (other.dateOfLaunch != null)
				return false;
		} else if (!dateOfLaunch.equals(other.dateOfLaunch))
			return false;
		if (freeDelivery == null) {
			if (other.freeDelivery != null)
				return false;
		} else if (!freeDelivery.equals(other.freeDelivery))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + "\t" + " name=" + name + "\t" + " price=" + price + "\t" + " active=" + active
				+ "\t" + " dateOfLaunch=" + new DateUtil().convertToString(dateOfLaunch) + "\t" + " Category="
				+ Category + "\t" + " freeDelivery=" + freeDelivery + "]";
	}

}