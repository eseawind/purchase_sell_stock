/**
 * @author whl
 * @date£º2013-7-20 ÏÂÎç3:49:25
 */

package com.shixi.domain;

public class Buy {
	private Integer id;
	private String buy_name;
	private String operator_name;
	private String pay;
	private String time;
	private String debt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getdebt() {
		return debt;
	}
	public void setdebt(String debt) {
		this.debt = debt;
	}
	public String getbuy_name() {
		return buy_name;
	}
	public void setbuy_name(String buy_name) {
		this.buy_name = buy_name;
	}
	public String getoperator_name() {
		return operator_name;
	}
	public void setoperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public String getpay() {
		return pay;
	}

	public void setpay(String pay) {
		this.pay = pay;
	}

	public String gettime() {
		return time;
	}

	public void settime(String time) {
		this.time = time;
	}
	
	public Buy() {}
		
	public Buy(String buy_name,String operator_name, String pay, String time,String debt) {
		this.buy_name=buy_name;
		this.operator_name = operator_name;
		this.pay = pay;
		this.time = time;
		this.debt=debt;
	}



	public Buy(Integer id,String buy_name,String operator_name, String pay, String time,String debt) {
		super();
		this.id = id;
		this.buy_name=buy_name;
		this.operator_name = operator_name;
		this.pay = pay;
		this.time = time;
		this.debt=debt;
	}

	@Override
	public String toString() {
		return "Buy [id=" + id + ",buy_name=" + buy_name + ", operator_name=" + operator_name + ", pay=" + pay
				+ ", time=" + time + ", debt=" + debt + "]";
	}
	
}
