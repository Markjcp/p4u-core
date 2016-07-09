package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PresentCategoryId implements Serializable{

	private static final long serialVersionUID = -2015371556146086371L;
	
	@Column(name = "id_categoria")
	private Long categoryId;
	
	@Column(name = "id_regalo")
	private Long presentId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getPresentId() {
		return presentId;
	}

	public void setPresentId(Long presentId) {
		this.presentId = presentId;
	}
}
