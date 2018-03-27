package com.cetc.manager.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapping {
    public static Map<String, Object> map(int status, String msg, Object o){
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", msg);
        map.put("data", o);
        return map;
    }
}
