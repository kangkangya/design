package com.panzhihua.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("foodCategory")
public class FoodCategory {
    @Id
    private String id;
    private String code;
    private String name;
}
