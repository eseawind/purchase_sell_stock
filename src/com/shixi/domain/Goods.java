package com.shixi.domain;

public class Goods {
	private Integer id;
	private String name;
	private String area;
	private String producter;
	private String date;
	private Integer quality;	//保质期
	private Integer bid;		//进货价格
	private Integer price;		//售价
	
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
	public Integer getQuality() {
		return quality;
	}
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Goods(Integer id, String name, String area, String producter,
			String date, Integer quality, Integer bid, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.producter = producter;
		this.date = date;
		this.quality = quality;
		this.bid = bid;
		this.price = price;
	}
	
	public Goods(String name, String area, String producter,
			String date, Integer quality, Integer bid, Integer price) {
		super();
		this.name = name;
		this.area = area;
		this.producter = producter;
		this.date = date;
		this.quality = quality;
		this.bid = bid;
		this.price = price;
	}
	
	public Goods() {}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", area=" + area
				+ ", producter=" + producter + ", date=" + date + ", quality="
				+ quality + ", bid=" + bid + ", price=" + price + "]";
	}
	
	
}


