package com.shixi.domain;

public class Provider {
	private Integer id;
	private String name;
	private String phone;
	private String address;
	private String postcode;
	private String mail;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Provider(Integer id, String name, String phone, String address,
			String postcode, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.mail = mail;
	}
	
	public Provider(String name, String phone, String address,
			String postcode, String mail) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.mail = mail;
	}
	
	public Provider() {}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", postcode=" + postcode + ", mail="
				+ mail + "]";
	}
	
}
