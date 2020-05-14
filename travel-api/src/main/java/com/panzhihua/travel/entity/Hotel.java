package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("hotel")
public class Hotel {
    @Id
    private String id;
    private String regionCode;
    private String title;
    private String summary;
    private String address;
    private Integer price;
    private String startTime;
    private String endTime;
    private String buildTime;
    private String roomNum;
    private List<String> checkedMainFacilitys;
    private List<String> checkedHotelServices;
    private List<String> checkedHotelFacilitys;
    private List<String> checkedRoomFacilitys;
    private String picUrl;
    private Integer commentNum;
    private Integer likeNum;
    private Long timestamp;
}
