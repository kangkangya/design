package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("food")
public class    Food {
    @Id
    private String id;
    private String foodCode;
    private String regionCode;
    private String title;
    private String summary;
    private String address;
    private String picUrl;
    private double score;
    private int commentNum;
    private String newComment;
    private Long timestamp;
}
