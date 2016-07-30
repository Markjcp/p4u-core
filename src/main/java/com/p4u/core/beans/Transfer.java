package com.p4u.core.beans;

public class Transfer {
	
	private Long itemId;
	
	private Long userFrom;
	
	private Long userTo;
	
	private String msg;
	
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
