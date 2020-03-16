package com.bit.phoneservlet.vo;

public class PhoneBookVO {

	private Long id;
	private String name;
	private String hp;
	private String tel;
	
	public PhoneBookVO(Long id, String name, String hp, String tel) {
		super();
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}

	public PhoneBookVO(String name, String hp, String tel) {
		super();
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "PhoneBookVO [id=" + id + ", name=" + name + ", hp=" + hp + ", tel=" + tel + "]";
	}
	
	
}
