package com.panzhihua.travel.dto.ret;

import com.panzhihua.travel.entity.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class GoWithDto {
    @Id
    private String id;
    private String hotGoWithId;
    private String refId;
    private String placeOfDeparture;
    private String destination;
    private String title;
    private String plan;
    private String beginDate;
    private String endDate;
    private String picUrl;
    private String number;
    private Integer attentionNum;
    private Integer applicantNum;
    private Long travelTime;
    private Integer travelDay;
    private Long timestamp;
    private User user;
    private Integer count;

    public int calTravelDay() {
        if (this.travelTime <= System.currentTimeMillis()) {
            return 0;
        }

        long diffTime = this.travelTime - System.currentTimeMillis();
        return (int) (diffTime / 86400000l);
    }
}
