package com.mioms.core.exception;

public class DtoChangeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6878267644800879807L;
	private String errCode;
	private String errMsg;
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	

}
