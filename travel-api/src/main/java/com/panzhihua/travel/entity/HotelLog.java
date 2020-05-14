package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("hotelLog")
public class HotelLog {
    @Id
    private String id;
    private String hotelId;
    private Long startTime;
    private Long endTime;
    private Integer personNum;
    private String roomId;
    private Long timestamp;
}
