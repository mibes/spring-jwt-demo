package com.mibesco.spring.jwt.api;

public class RestResult {

    private String message;
    private Boolean success;

    public RestResult() {
        success = false;
        message = "";
    }

    public RestResult(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
