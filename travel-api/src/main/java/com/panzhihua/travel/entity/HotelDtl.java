package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document("hotelDtl")
public class HotelDtl {
    @Id
    private String id;
    private String hotelId;
    private String title;
    private String address;
    private List<String> picUrls;
    private Integer commentNum;
    private Integer likeNum;
    private List<RoomInfo> roomInfos;
    private BasicInfo basicInfo;
    private List<String> mainFacility;
    private List<String> hotelService;
    private List<String> hotelFacility;
    private List<String> roomFacility;
    private String strategy;
    private List<Comment> comments;
    private Long timestamp;

    @Data
    private static class BasicInfo {
        private Long startTime;
        private Long endTime;
        private String buildTime;
        private Integer roomNum;
    }
    @Data
    private static class RoomInfo {
        private String id;
        private String title;
        private Integer price;
        private Integer personNum;
        private List<String> picUrl;
    }
}
