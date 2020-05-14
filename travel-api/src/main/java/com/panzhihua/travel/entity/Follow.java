package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("follow")
public class Follow {
    @Id
    private String id;
    private String userId;
    private String refId;
    /*
    *   类型包括：
    *   go-with
    *   travl-note
    *
    * */
    private String type;

}
