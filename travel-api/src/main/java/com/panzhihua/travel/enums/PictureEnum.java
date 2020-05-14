package com.panzhihua.travel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PictureEnum {
    SCENIC_LIST("scenic_list", 400, 400),
    SCENIC_DTL("scenic_dtl", 400, 400),
    HOTEL_LIST("hotel_list", 400, 400),
    HOTEL_DTL("hotel_dtl", 400, 400),
    FOOD_LIST("food",400,400),
    COMMENT("comment", 200, 120);

    private String code;
    private int width;
    private int height;

    public static PictureEnum getByCode(String code) {
        for (PictureEnum value : PictureEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }
}
