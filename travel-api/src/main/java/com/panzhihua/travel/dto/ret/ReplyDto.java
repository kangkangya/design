package com.panzhihua.travel.dto.ret;

import com.panzhihua.travel.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ReplyDto {
    private String id;
    private String refId;
    private String userId;
    private String comment;
    private String type;
    private User user;
    private Long timestamp;
}
