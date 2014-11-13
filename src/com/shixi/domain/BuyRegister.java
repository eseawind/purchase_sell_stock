/**
 * @author whl
 * @date£º2013-7-20 ÏÂÎç3:49:25
 */

package com.shixi.domain;

public class BuyRegister {
	private Integer id;
	private String goods_id;
	private String product_name;
	private String product_price;
	private String purchase_quantity;
	private String tobuy;
	
	public String getgoods_id() {
		return goods_id;
	}
	public void setgoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String gettobuy() {
		return product_price;
	}
	public void settobuy(String tobuy) {
		this.tobuy = tobuy;
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
	
	public BuyRegister() {}
		
	public BuyRegister(String goods_id,String product_name, String product_price, String purchase_quantity,String tobuy) {
		this.goods_id=goods_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.purchase_quantity = purchase_quantity;
		this.tobuy=tobuy;
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

	public BuyRegister(Integer id,String goods_id,String product_name, String product_price, String purchase_quantity,String tobuy) {
		super();
		this.id = id;
		this.goods_id=goods_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.purchase_quantity = purchase_quantity;
		this.tobuy=tobuy;
	}

	@Override
	public String toString() {
		return "Buy [id=" + id + ",goods_id=" + goods_id + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", purchase_quantity=" + purchase_quantity + ", tobuy=" + tobuy + "]";
	}
	
}
