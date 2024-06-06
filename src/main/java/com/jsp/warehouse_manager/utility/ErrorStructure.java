package com.jsp.warehouse_manager.utility;

public class ErrorStructure<T> {
    private int statuscode;
	private String errorMessage;
	private T  rootCause;
	public int getStatuscode() {
		return statuscode;
	}
	public ErrorStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
        return this;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
        return this;
	}
	public T getRootCause() {
		return rootCause;
	}
	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
        return this;
	}
	
}
