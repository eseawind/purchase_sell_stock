package com.shixi.domain;

public class SailRegister {
	private Integer id;
	private String code;
	private String product_name;
	private String product_price;
	private String purchase_quantity;
	private String tosail;
	
	public String getcode() {
		return code;
	}
	public void setcode(String code) {
		this.code = code;
	}
	public String gettosail() {
		return product_price;
	}
	public void settosail(String tosail) {
		this.tosail = tosail;
	}
	public String getproduct_price() {
		return product_price;
	}

	public void setproduct_price(String product_price) {
		this.product_price = product_price;
	}

	public String getpurchase_quantity() {
		return purchase_quantity;
	}

	public void setpurchase_quantity(String purchase_quantity) {
		this.purchase_quantity = purchase_quantity;
	}
	
	public SailRegister() {}
		
	public SailRegister(String code,String product_name, String product_price, String purchase_quantity,String tosail) {
		this.code = code;
		this.product_name = product_name;
		this.product_price = product_price;
		this.purchase_quantity = purchase_quantity;
		this.tosail=tosail;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getproduct_name() {
		return product_name;
	}
	public void setproduct_name(String product_name) {
		this.product_name = product_name;
	}

	public SailRegister(Integer id, String code,String product_name, String product_price, String purchase_quantity,String tosail) {
		super();
		this.id = id;
		this.code = code;
		this.product_name = product_name;
		this.product_price = product_price;
		this.purchase_quantity = purchase_quantity;
		this.tosail=tosail;
	}

	@Override
	public String toString() {
		return "Sail [id=" + id + ",code=" + code + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", purchase_quantity=" + purchase_quantity + ", tosail=" + tosail + "]";
	}
	
}
