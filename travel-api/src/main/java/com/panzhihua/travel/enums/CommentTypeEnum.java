package com.panzhihua.travel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentTypeEnum {
    FOOD("food"),
    SCENIC("scenic"),
    HOTEL("hotel"),
    TRAVEL_NOTE("travel_note"),
    GO_WITH("go_with");

    private String code;

    public static CommentTypeEnum findByCode(String code) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if(value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }
}
