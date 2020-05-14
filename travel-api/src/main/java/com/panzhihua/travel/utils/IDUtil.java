package com.panzhihua.travel.utils;

import java.util.UUID;

public class IDUtil {

    public static String id() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
