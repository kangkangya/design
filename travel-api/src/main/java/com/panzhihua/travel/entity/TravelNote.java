package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("travelNote")
public class TravelNote {
    @Id
    private String id;
    private String refId;
    private String title;
    private String summary;
    private String regionCode;
    private String picUrl;
    private int browseNum;
    private Long timestamp;
}
