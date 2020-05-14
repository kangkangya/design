package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("banner")
public class Banner {
    @Id
    private String id;
    private String title;
    private String summary;
    private String picUrl;
}
