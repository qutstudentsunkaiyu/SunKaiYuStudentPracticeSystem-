package com.exercise.budgetreal.util;


public class ApiError {
	private String msg;
	Integer code;
	
	public String getMsg() {
	
		return msg;
	}
	
	public void setMsg(String msg) {
	
		this.msg = msg;
	}
	
	public Integer getCode() {
	
		return code;
	}
	
	public void setCode(Integer code) {
	
		this.code = code;
	}

	public ApiError(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
}
