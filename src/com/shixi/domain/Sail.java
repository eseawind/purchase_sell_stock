package com.shixi.domain;

public class Sail {
	private Integer id;
	private String customer_name;
	private String operator_name;
	private String customer_pay ;	
	private String trade_time ;
	private String customer_debt ;
	
	
	public void setcustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setcustomer_pay(String customer_pay) {
		this.customer_pay = customer_pay;
	}
	public void settrade_time(String trade_time) {
		this.trade_time = trade_time;
	}

	public Sail() {}
		
	public Sail(  String customer_name,String operator_name,String customer_pay, String trade_time,String customer_debt) {

		this.customer_name =customer_name;
		this.operator_name = operator_name;
		this.customer_pay =customer_pay;
		this.trade_time =trade_time;
		this.customer_debt =customer_debt;
	}

	public Sail(Integer id,  String customer_name,String operator_name, String customer_pay, String trade_time,String customer_debt) {

		this.id = id;
		this.operator_name = operator_name;		
		this.customer_name =customer_name;
		this.customer_pay =customer_pay;
		this.trade_time =trade_time;
		this.customer_debt =customer_debt;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getoperator_name() {
		return operator_name;
	}
	public void setoperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	
	@Override
	public String toString() {
		return "Sail [id=" + id + ", customer_name="+customer_name+",operator_name="+operator_name+",customer_pay="+customer_pay+",trade_time="+trade_time+",customer_debt="+customer_debt+"]";
	}
	
}


