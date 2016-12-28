package com.yijiajiao.server.bean;

public class BaofenUserBean {
	private int id;
	private String name;
	private String area;
	private int age;
	private String contactWay;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	@Override
	public String toString() {
		return "BaofenUserBean [id=" + id + ", name=" + name + ", area=" + area
				+ ", age=" + age + ", contactWay=" + contactWay + "]";
	}
	
}
