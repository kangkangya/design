package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.ReplayRepository;
import com.panzhihua.travel.dao.UserRepository;
import com.panzhihua.travel.dto.ret.ReplyDto;
import com.panzhihua.travel.entity.Replay;
import com.panzhihua.travel.entity.User;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReplayService {
    private final ReplayRepository replayRepository;
    private final UserRepository userRepository;

    public Replay save(Replay replay) {
        replay.setId(IDUtil.id());
        replay.setTimestamp(System.currentTimeMillis());
        return replayRepository.save(replay);
    }

    public void delete(String id) {
        replayRepository.deleteById(id);
    }

    public List<ReplyDto> findById(String id) {
        Replay replay = new Replay();
        replay.setRefId(id);
        List<Replay> list = replayRepository.findAll(Example.of(replay), Sort.by(Sort.Order.desc("timestamp")));

        List<ReplyDto> list1 = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
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
