package com.p4u.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario_tiene_preferencia")
public class UserPreference implements Serializable{

	private static final long serialVersionUID = 5469839768416343694L;
	
	@EmbeddedId
	private UserPreferenceId id;
	
	@Column(name = "id_usuario", insertable  = false, updatable = false)
	private Long userId;
	
	@ManyToOne
	@JoinColumn(name = "id_preferencia", referencedColumnName = "id", insertable = false, updatable = false)
	private Preference preference;

	public UserPreferenceId getId() {
		return id;
	}

	public void setId(UserPreferenceId id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
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
		UserPreference other = (UserPreference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	 
	

}
