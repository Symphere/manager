package com.cetc.manager.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    private HashMap<String, Object> data = new HashMap<String, Object>();

    public ResponseEntity() {
    }

    public ResponseEntity(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public ResponseEntity(int errCode, String message, Map<String, Object> map) {
        this.errCode = errCode;
        this.message = message;
        data.putAll(map);
    }

    public ResponseEntity put(String key, Object value) {
        data.put(key, value);
        return this;
    }
}
