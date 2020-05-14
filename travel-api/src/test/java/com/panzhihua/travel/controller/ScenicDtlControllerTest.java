package com.panzhihua.travel.controller;

import com.panzhihua.travel.dao.ScenicRepository;
import com.panzhihua.travel.entity.Comment;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.entity.ScenicDtl;
import com.panzhihua.travel.enums.CommentTypeEnum;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenicDtlControllerTest {
    @Autowired
    private ScenicDtlController scenicDtlController;

    @Autowired
    private ScenicRepository scenicRepository;

    @Autowired
    private CommentController commentController;

    @Test
    public void testSave() {
        List<Scenic> all = scenicRepository.findAll();

        all.forEach(a -> buildDtl(a));
    }

    private void buildDtl(Scenic scenic) {
        ScenicDtl scenicDtl = new ScenicDtl();
        String id = IDUtil.id();
        scenicDtl.setId(id);
        scenicDtl.setTitle(scenic.getTitle());

        scenicDtl.setPhone("123123123");
        scenicDtl.setTicket("30RMB");
        scenicDtl.setTraffic("在攀枝花有直达米易的班车,全程80公里,价格12.00元,从 6:00--17:00 40分钟");
        scenicDtl.setOpenTime("16:00-18:00");
        scenicDtl.setAddress("攀枝花有直攀枝花有直攀枝花有直攀枝花有直");

        List<String> l1 = new ArrayList<>();
        l1.add("header-0.jpeg");
        l1.add("header-1.jpeg");
        l1.add("header-2.jpeg");
        scenicDtl.setHeaderPicUrl(l1);

//        List<String> l2 = new ArrayList<>();
//        IntStream.range(0, 11).forEach(l -> l2.add("list-" + l + ".jpeg"));
//        scenicDtl.setPicUrls(l2);

        scenicDtlController.save(scenicDtl);

        buildComment(scenic.getId());
    }

    private void buildComment(String dtlId) {
        IntStream.range(0, 2).forEach(l -> {
            Comment comment = new Comment();
            String id = IDUtil.id();
            comment.setId(id);
            comment.setUserId("5e9acdd113bb0b5a75197c55");
            comment.setRefId(dtlId);
            comment.setType(CommentTypeEnum.SCENIC.getCode());
            comment.setStarNum(3.5);
            comment.setComment("哈啊啊啊啊啊啊啊啊啊啊啊哈啊啊啊啊啊啊啊啊啊啊啊哈啊啊啊啊啊啊啊啊啊啊啊哈啊啊啊啊啊啊啊啊啊啊啊");

            List<String> picUrls = new ArrayList<>();
            picUrls.add("comment-0.png");
            picUrls.add("comment-1.png");
            picUrls.add("comment-2.png");
            picUrls.add("comment-3.png");
            comment.setTimestamp(System.currentTimeMillis());

//            commentController.save(comment);
        });
    }
}
