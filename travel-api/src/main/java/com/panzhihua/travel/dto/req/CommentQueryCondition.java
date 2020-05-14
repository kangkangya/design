package com.panzhihua.travel.dto.req;

import lombok.Data;

@Data
public class CommentQueryCondition extends QueryCondition {
    private String refId;
    private String type;
}
