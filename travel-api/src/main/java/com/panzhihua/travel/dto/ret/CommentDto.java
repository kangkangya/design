package com.panzhihua.travel.dto.ret;

import com.panzhihua.travel.entity.Picture;
import com.panzhihua.travel.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class CommentDto {
    private String id;
    private String userId;
    private String refId;
    private String type;
    private double starNum;
    private String comment;
    private List<Picture> picUrls;
    private User user;
    private List<ReplyDto> replyList;
    private Long timestamp;
}
