package com.panzhihua.travel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("user")
public class User {
    @Id
    private String id;
    private String picUrl;
    private String backPicUrl;
    private String mood;
    private String nickName;
    private String gender;
    private String summary;
    private String username;
    private String password;
}
