package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="regalo_pertenece_categoria")
public class PresentCategory implements Serializable{

	private static final long serialVersionUID = 8953129700885792092L;
	
	@EmbeddedId
	private PresentCategoryId id;
	
	@Column(name = "id_categoria", insertable = false, updatable = false)
	private Long categoryId;
	
	@Column(name = "id_regalo", insertable = false, updatable = false)
	private Long presentId;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "id_regalo", referencedColumnName = "id", insertable = false, updatable = false)
	private Present present;
	
	public PresentCategoryId getId() {
		return id;
	}

	public void setId(PresentCategoryId id) {
		this.id = id;
	}

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Present getPresent() {
		return present;
	}

	public void setPresent(Present present) {
		this.present = present;
	}		
}
