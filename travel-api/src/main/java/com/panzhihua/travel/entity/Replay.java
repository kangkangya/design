package com.panzhihua.travel.entity;

import lombok.Data;

@Data
public class Replay {
    private String id;
    private String refId;
    private String userId;
    private String comment;
    private String type;
    private Long timestamp;
}
