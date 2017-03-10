package com.dodoca.dataMagic.wxrrd.model;

public class ShopDetail {
	
	private String name;
	private Object money[];
	private Object date[];
	
	public ShopDetail() {
		super();
	}
	public ShopDetail(String name, Object[] money, Object[] date) {
		super();
		this.name = name;
		this.money = money;
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object[] getMoney() {
		return money;
	}
	public void setMoney(Object[] objects) {
		this.money = objects;
	}
	public Object[] getDate() {
		return date;
	}
	public void setDate(Object[] date) {
		this.date = date;
	}
	
	
}
