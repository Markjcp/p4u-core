package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Company implements Serializable{

	private static final long serialVersionUID = 2247789974894853177L;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "codigo_compania")
	private Integer code;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "posicion")
	private String location;
	
	@Column(name = "domicilio")
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
