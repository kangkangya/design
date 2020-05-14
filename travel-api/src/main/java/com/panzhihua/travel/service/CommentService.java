package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.CommentRepository;
import com.panzhihua.travel.dao.PictureRepository;
import com.panzhihua.travel.dao.ReplayRepository;
import com.panzhihua.travel.dao.UserRepository;
import com.panzhihua.travel.dto.req.CommentQueryCondition;
import com.panzhihua.travel.dto.ret.CommentDto;
import com.panzhihua.travel.dto.ret.ReplyDto;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Comment;
import com.panzhihua.travel.entity.Replay;
import com.panzhihua.travel.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final MongoTemplate mongoTemplate;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final ReplayRepository replayRepository;

    private final PictureRepository pictureRepository;

    public Comment save(Comment comment) {
        comment.setTimestamp(System.currentTimeMillis());
        return commentRepository.save(comment);
    }

    public void delete(String id) {
        commentRepository.deleteById(id);
    }

    public RetDto find(CommentQueryCondition queryCondition) {
        Query query = new Query();

        query.with(Sort.by(Sort.Order.desc("timestamp")));

        query.addCriteria(Criteria.where("refId").in(queryCondition.getRefId()));
        query.addCriteria(Criteria.where("type").in(queryCondition.getType()));

        int count = (int) mongoTemplate.count(query, Comment.class);

        query.skip((queryCondition.startPage()))
                .limit(queryCondition.getPageSize());

        List<Comment> comments = mongoTemplate.find(query, Comment.class);

        List<CommentDto> dtos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(comments)) {
            comments.forEach(c -> {
                CommentDto d = new CommentDto();
                BeanUtils.copyProperties(c, d);

                User user = userRepository.findById(c.getUserId()).get();
                user.setPicUrl(StringUtils.isEmpty(user.getPicUrl()) ? "default_head_url.jpeg" : user.getPicUrl());
                user.setNickName(StringUtils.isEmpty(user.getNickName()) ? "系统默认用户" : user.getNickName());
                d.setUser(user);

                d.setReplyList(findById(d.getId()));

                d.setPicUrls(pictureRepository.findByRefId(d.getId()));

                dtos.add(d);
            });
        }

        return RetDto.builder()
                .pageSize(queryCondition.getPageSize())
                .body(dtos)
                .total(count).build();

    }

    public List<ReplyDto> findById(String id) {
        Replay replay = new Replay();
        replay.setRefId(id);
        List<Replay> list = replayRepository.findAll(Example.of(replay), Sort.by(Sort.Order.asc("timestamp")));

        List<ReplyDto> list1 = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(l -> {
                ReplyDto dto = new ReplyDto();
                BeanUtils.copyProperties(l, dto);

                User user = userRepository.findById(l.getUserId()).get();
                user.setPicUrl(StringUtils.isEmpty(user.getPicUrl()) ? "default_head_url.jpeg" : user.getPicUrl());
                user.setNickName(StringUtils.isEmpty(user.getNickName()) ? "系统默认用户" : user.getNickName());
                dto.setUser(user);

                list1.add(dto);
            });
        }

        return list1;
    }
}
