package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="categoria_agrupa_preferencias")
public class CategoryPreference implements Serializable{

	private static final long serialVersionUID = -6020601563247772130L;
	
	@EmbeddedId
	private CategoryPreferenceId id;
	
	@Column(name = "id_categoria", updatable = false, insertable = false)
	private Long categoryId;
	
	@Column(name = "id_preferencia", updatable = false, insertable = false)
	private Long prefernceId;

	public CategoryPreferenceId getId() {
		return id;
	}

	public void setId(CategoryPreferenceId id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getPrefernceId() {
		return prefernceId;
	}

	public void setPrefernceId(Long prefernceId) {
		this.prefernceId = prefernceId;
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
		CategoryPreference other = (CategoryPreference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
