package com.p4u.core.beans;

public class PresentBuyOrder {
	
	private Long userFrom;
	
	private Long userTo;
	
	private Integer quantity;
	
	private Long presentId;
	
	private String text;

	public Long getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Long userFrom) {
		this.userFrom = userFrom;
	}

	public Long getUserTo() {
		return userTo;
	}

	public void setUserTo(Long userTo) {
		this.userTo = userTo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Long getPresentId() {
		return presentId;
	}

	public void setPresentId(Long presentId) {
		this.presentId = presentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
