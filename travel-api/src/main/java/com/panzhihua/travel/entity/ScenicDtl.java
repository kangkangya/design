package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("scenicDtl")
public class ScenicDtl {
    @Id
    private String id;
    private String regionCode;
    private String title;
    private String summary;
    private String phone;
    private String traffic;
    private String ticket;
    private String openTime;
    private String address;
    private List<String> headerPicUrl;
    private String picUrl;

}
