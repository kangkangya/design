package com.panzhihua.travel.dto.req;

import lombok.Data;

@Data
public class FoodQueryCondition extends QueryCondition {
    private String foodCode;
    private String regionCode;
}
