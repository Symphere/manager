package com.cetc.manager.common;

import java.util.UUID;

public class MyUUID {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
