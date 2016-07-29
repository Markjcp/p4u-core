package com.p4u.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="item_notifiacion_usuario")
public class NotificationItem implements Serializable {

	private static final long serialVersionUID = 3749958676467345125L;
	
	@EmbeddedId
	private UserItemId id;
	
	@Column(name="fecha_envio_mail")
	private Date date;
	
	@Column(name="email")
	private String email;
	
	@Column(name="remitente")
	private String sender;
	
	@Column(name="destinatario")
	private String receiver;
	
	@Column(name="vencimiento")
	private Date expiration;
	
	@Column(name="mensaje")
	private String msg;
	
	@Transient
	private boolean expired;
	
	@ManyToOne
	@JoinColumn(name = "id_item", referencedColumnName = "id", insertable = false, updatable = false)
	private Item item;
	
	@Column(name="id_usuario", insertable = false, updatable = false)
	private Long userId;
	
	public UserItemId getId() {
		return id;
	}

	public void setId(UserItemId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		NotificationItem other = (NotificationItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
