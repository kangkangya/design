package com.panzhihua.travel.enums;

import lombok.Getter;

@Getter
public enum RetEnum {
    SUCCESS(200, "success"),
    INTERNAL_ERROR(500, "internal error!"),
    USER_EXISTS(520, "用户已存在!"),
    USER_NOT_EXISTS(404, "用户名不存在或密码错误!");

    RetEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;
}
