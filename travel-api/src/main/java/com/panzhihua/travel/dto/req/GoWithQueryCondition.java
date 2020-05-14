package com.panzhihua.travel.dto.req;

import lombok.Data;

@Data
public class GoWithQueryCondition extends QueryCondition {
    private String hotGoWithId;
    private String destination;
    private String travelTime;
}
