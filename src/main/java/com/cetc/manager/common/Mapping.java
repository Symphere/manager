package com.cetc.manager.common;

import java.util.HashMap;
import java.util.Map;

public class Mapping {

    public static final int SUCCESS = 0;
    public static final int NOT_FOUND = 1;
    public static final int ALREADY_REGISTERED = 2;
    public static final int UNREGISTERED = 3;
    public static final int LOGIN_FAILED = 4;
    public static final int SAVE_FAILURE = 5;

    public static Map<String, Object> map(int status, String msg, Object o) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", msg);
        map.put("data", o);
        return map;
    }

    public static Map<String, Object> map(int status, Object o) {
        switch (status) {
            case SUCCESS:
                return map(SUCCESS, "Success", o);
            case NOT_FOUND:
                return map(NOT_FOUND, "Not Found", o);
            case ALREADY_REGISTERED:
                return map(ALREADY_REGISTERED, "工号已经被注册", o);
            case UNREGISTERED:
                return map(UNREGISTERED, "工号未注册", o);
            case LOGIN_FAILED:
                return map(LOGIN_FAILED, "用户名密码不匹配", o);
            case SAVE_FAILURE:
                return map(SAVE_FAILURE, "存储数据失败", o);
            default:
                return map(-1, "Unknown Error", o);
        }
    }

    public static Map<String, Object> mapSuccess(Object o){
        return map(SUCCESS, o);
    }
}
