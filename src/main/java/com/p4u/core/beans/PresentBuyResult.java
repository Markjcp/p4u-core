package com.p4u.core.beans;

public class PresentBuyResult {
	
	private Integer resultCode;
	
	private String errorDescription;
	
	public PresentBuyResult() {
		super();
	}

	public PresentBuyResult(Integer resultCode, String errorDescription) {
		super();
		this.resultCode = resultCode;
		this.errorDescription = errorDescription;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}
