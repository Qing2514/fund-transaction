package com.fundtrans.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid(){
        String id = UUID.randomUUID().toString().replace("-","");
        return id.substring(0,10);
    }
}
