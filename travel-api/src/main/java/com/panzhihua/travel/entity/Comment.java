package com.panzhihua.travel.entity;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String userId;
    private String refId;
    private String type;
    private double starNum;
    private String comment;
    private Long timestamp;
}
