package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoryPreferenceId implements Serializable{

	private static final long serialVersionUID = 4686006976960937453L;
	
	@Column(name = "id_categoria")
	private Long categoryId;
	
	@Column(name = "id_preferencia")
	private Long preferenceId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((preferenceId == null) ? 0 : preferenceId.hashCode());
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
		CategoryPreferenceId other = (CategoryPreferenceId) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (preferenceId == null) {
			if (other.preferenceId != null)
				return false;
		} else if (!preferenceId.equals(other.preferenceId))
			return false;
		return true;
	}
}
