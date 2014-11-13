/**
 * @author whl
 * @date£º2013-7-20 ÏÂÎç3:49:25
 */

package com.shixi.domain;

public class Customer {
	private Integer id;
	private String name;
	private String phone;
	private String address;
	private String postcode;
	private String cmail;
	private String company;
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCmail() {
		return cmail;
	}

	public void setCmail(String cmail) {
		this.cmail = cmail;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Customer() {}
		
	public Customer(String name, String phone, String address, String postcode, String cmail, String company) {

		this.name = name;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.cmail = cmail;
		this.company = company;
	}

	public void setName(String name) {
		this.name = name;
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
	

	public Customer(Integer id, String name, String phone, String address,
			String postcode, String cmail, String company) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.cmail = cmail;
		this.company = company;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", postcode=" + postcode
				+ ", cmail=" + cmail + ", company=" + company + "]";
	}
	
}
