package com.bjb.bankmanagement.forextransaction.constant;

public enum ResponseStatus {

	OK(200),
	NOT_FOUND(404)
	;

	private Integer status;

	private ResponseStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

}
