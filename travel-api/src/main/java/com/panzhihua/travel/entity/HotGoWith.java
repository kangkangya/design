package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("HotGoWith")
public class HotGoWith {
    @Id
    private String id;
    private String target;
    private String regionCode;
    private String picUrl;
    private Integer goWithNum;
    private Integer attentionNum;
    private Integer enterNum;
    private Long timestamp;
}
