package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("enterInfo")
public class EnterInfo {
    @Id
    private String id;
    private String userId;
    private String goWithId;
    private String phone;
    private String sex;
    private Integer manNum;
    private Integer womanNum;
    /*同行名单*/
    private String order;
    private String remarks;
    private Long timeStamp;
}
