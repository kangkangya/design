package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Scenic;
import com.panzhihua.travel.utils.IDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScenicControllerTest {
    @Autowired
    private ScenicController scenicController;

    @Test
    public void testList() {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setPageSize(15);
        queryCondition.setSearch("19");
        queryCondition.setSortField("timestamp");
        queryCondition.setSortKey("asc");
        RetDto list = scenicController.list(queryCondition);
    }

    @Test
    public void testDelete() {
        scenicController.delete("1");
    }

    @Test
    public void testSave() {
        IntStream.range(0, 20).forEach(i -> mockData(i));
    }

    private void mockData(int i) {
        Scenic scenic = new Scenic();
        String id = IDUtil.id();
        scenic.setId(id);
        scenic.setTitle("spoit title" + i);
        scenic.setPicUrl("spoit-" + i + ".jpeg");
        scenic.setCommentNum(i);
        scenic.setLikeNum(i);
        scenic.setTravelNoteNum(i);
        scenic.setTimestamp(System.currentTimeMillis());
//
//        ScenicDtl scenicDtl = new ScenicDtl();
//        scenicDtl.setId(IDUtil.id());
//        scenicDtl.setScenicId(id);
//        scenicDtl.setTitle("spoit title" + i);
//        scenicDtl.setSummary("spoit summary" + i);
//        scenicDtl.setAddress("攀枝花" + i);
//        scenicDtl.setPicUrls(Arrays.asList("spoit-0.jpeg", "spoit-1.jpeg"));
//
//        List<Comment> comments = new ArrayList<>();
//        Comment comment1 = new Comment();
//        comment1.setId(IDUtil.id());
//        comment1.setUserId(IDUtil.id());
//        comment1.setScenicId(id);
//        comment1.setUsername("tom" + i);
//        comment1.setHeadPic("spoit-1.jpeg");
//        comment1.setStartNum(i);
//        comment1.setComment("comment" + i);
//        comment1.setTimestamp(System.currentTimeMillis());
//        comment1.setPicUrls(Arrays.asList("spoit-0.jpeg", "spoit-1.jpeg"));
//        comments.add(comment1);
//        scenicDtl.setComments(comments);
//
//        scenic.setScenicDtl(scenicDtl);

//        RetDto save = scenicController.save(scenic);
//        System.err.println(save);
    }
}
