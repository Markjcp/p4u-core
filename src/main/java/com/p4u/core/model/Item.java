package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item implements Serializable{

	private static final long serialVersionUID = -5519152475601478666L;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="id_regalo")
	private Long presentId;
	
	@Column(name="codigo_producto")
	private String productCode;
	
	@Column(name="estado")
	private String state;
	
	@Column(name="codigo_regalo")
	private String presentCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPresentId() {
		return presentId;
	}

	public void setPresentId(Long presentId) {
		this.presentId = presentId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPresentCode() {
		return presentCode;
	}

	public void setPresentCode(String presentCode) {
		this.presentCode = presentCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
