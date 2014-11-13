/**
 * @author whl
 * @date：2013-7-20 下午3:49:25
 */

package com.shixi.domain;

public class Storage {
	private Integer id;
	private String goodsid;
	private String name;
	private String area;
	private String producter;
	private String date;
	private String quality;	//保质期
	private String bid;		//进货价格
	private String price;		//售价
	private Integer storage_quantity;
	private String operator_name;
	private String storage_time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getgoodsid() {
		return goodsid;
	}
	public void setgoodsid(String goodsid) {
		this.goodsid = goodsid;
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
	public String getProducter() {
		return producter;
	}
	public void setProducter(String producter) {
		this.producter = producter;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer  getstorage_quantity() {
		return storage_quantity;
	}
	public void setstorage_quantity(Integer storage_quantity) {
		this.storage_quantity = storage_quantity;
	}
	public String getoperator_name() {
		return operator_name;
	}
	public void setoperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public String getstorage_time() {
		return storage_time;
	}
	public void setstorage_time(String storage_time) {
		this.storage_time = storage_time;
	}
	
	public Storage(Integer id, String goodsid,String name, String area, String producter,
			String date, String quality, String bid, String price,Integer storage_quantity,
			String operator_name,String storage_time) {
		super();
		this.id = id;
		this.goodsid = goodsid;
		this.name = name;
		this.area = area;
		this.producter = producter;
		this.date = date;
		this.quality = quality;
		this.bid = bid;
		this.price = price;
		this.storage_quantity =storage_quantity;
		this.operator_name = operator_name;
		this.storage_time = storage_time;
	}
	
	public Storage(String goodsid,String name, String area, String producter,
			String date, String quality, String bid, String price,Integer storage_quantity,
			String operator_name,String storage_time) {
		super();
		this.goodsid = goodsid;
		this.name = name;
		this.area = area;
		this.producter = producter;
		this.date = date;
		this.quality = quality;
		this.bid = bid;
		this.price = price;
		this.storage_quantity =storage_quantity;
		this.operator_name = operator_name;
		this.storage_time = storage_time;
	}
	
	public Storage() {}
	@Override
	public String toString() {
		return "Goods [id=" + id + ",goodsid=" + goodsid + ", name=" + name + ", area=" + area
				+ ", producter=" + producter + ", date=" + date + ", quality="
				+ quality + ", bid=" + bid + ", price=" + price + ", storage_quantity=" + storage_quantity +",operator_name=" + operator_name + ", storage_time=" + storage_time + "]"; 
						
	}
	
	
}


