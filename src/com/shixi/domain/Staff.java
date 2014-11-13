package com.shixi.domain;

public class Staff {
	private Integer id;
	private String name;
	private String usrgroup;
	private String password;
	private Integer question;
	private String answer;
	private String phone;
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
	public String getUsrgroup() {
		return usrgroup;
	}
	public void setUsrgroup(String usrgroup) {
		this.usrgroup = usrgroup;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getQuestion() {
		return question;
	}
	public void setQuestion(Integer question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public Staff(){}
	
	public Staff(Integer id, String name, String usrgroup, String password,
			Integer question, String answer, String phone, String postcode,
			String mail) {
		super();
		this.id = id;
		this.name = name;
		this.usrgroup = usrgroup;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.phone = phone;
		this.postcode = postcode;
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", usrgroup=" + usrgroup
				+ ", password=" + password + ", question=" + question
				+ ", answer=" + answer + ", phone=" + phone + ", postcode="
				+ postcode + ", mail=" + mail + "]";
	}
	
	
}
