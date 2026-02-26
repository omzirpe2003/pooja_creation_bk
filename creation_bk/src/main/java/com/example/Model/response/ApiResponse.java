	package com.example.Model.response;
	
	public class ApiResponse<T> {
	
	    private boolean success;
	    private String message;
	    private T data;
	    private String errorCode;
	
	    // No-args constructor
	    public ApiResponse() {
	    }
	
	    // All-args constructor
	    public ApiResponse(boolean success, String message, T data, String errorCode) {
	        this.success = success;
	        this.message = message;
	        this.data = data;
	        this.errorCode = errorCode;
	    }
	    public static <T> ApiResponse<T> success(String message, T data) {
	        return new ApiResponse<>(true, message, data, null);
	    }

	    // Static helper for Failure
	    public static <T> ApiResponse<T> error(String message, String errorCode) {
	        return new ApiResponse<>(false, message, null, errorCode);
	    }
	
	    // Getters & Setters
	    public boolean isSuccess() {
	        return success;
	    }
	
	    public void setSuccess(boolean success) {
	        this.success = success;
	    }
	
	    public String getMessage() {
	        return message;
	    }
	 
	    public void setMessage(String message) {
	        this.message = message;
	    }
	
	    public T getData() {
	        return data;
	    }
	 
	    public void setData(T data) {
	        this.data = data;
	    }
	
	    public String getErrorCode() {
	        return errorCode;
	    }
	
	    public void setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	    }
	}
