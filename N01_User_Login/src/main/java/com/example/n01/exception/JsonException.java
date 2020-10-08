package com.example.n01.exception;

import java.util.HashMap;
import java.util.Map;

public class JsonException extends Exception {
    private String code;
    private String message;

    public JsonException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Map<String, Object> toMsgMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", this.code);
        map.put("message", this.message);
        return map;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
