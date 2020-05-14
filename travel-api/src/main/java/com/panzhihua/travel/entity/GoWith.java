package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("goWith")
public class GoWith {
    @Id
    private String id;
    private String hotGoWithId;
    private String refId;
    private String title;
    private String placeOfDeparture;
    private String destination;
    /*希望人数*/
    private String number;
    private String plan;
    private String picUrl;
    /*开始时间*/
    private String beginDate;
    private String endDate;
    /*关注人数*/
    private Integer attentionNum;
    /*报名人数*/
    private Integer applicantNum;
    private Long travelTime;
    private Long timestamp;
}
