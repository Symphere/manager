package com.cetc.manager.common;

import java.io.Serializable;

public class ResponseEntity implements Serializable {

    private int errCode;

    private String message;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public ResponseEntity() {
    }

    public ResponseEntity(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public ResponseEntity(int errCode, String message, Object o) {
        this.errCode = errCode;
        this.message = message;
        this.data = o;
    }
}
