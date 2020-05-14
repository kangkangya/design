package com.panzhihua.travel.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("admin")
public class Admin {

    @Id
    private String id;
    private String username;
    private String password;
}
