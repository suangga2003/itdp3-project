package com.bjb.bankmanagement.forextransaction.constant;

public enum ResponseCode {

	SUCCESS("0000"),
	GENERAL_ERROR("0005")
	;

	private String code;

	private ResponseCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
