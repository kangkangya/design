package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("scenic")
public class Scenic {
    @Id
    private String id;
    private String title;
    private String picUrl;
    private String summary;
    private Integer commentNum;
    private Integer likeNum;
    private Integer travelNoteNum;
    private Long timestamp;
}
