package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.FollowRepository;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Follow;
import com.panzhihua.travel.utils.IDUtil;
import com.sun.prism.impl.paint.PaintUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FollowService {
    private FollowRepository followRepository;

    public RetDto save(Follow follow){
        follow.setId(IDUtil.id());
        return RetDto.success(followRepository.save(follow),0,0);
    }

    public RetDto findOne(Follow follow){
        Optional one = followRepository.findOne(Example.of(follow));
        if (one.isPresent()){
            return RetDto.success(one.get(),0,0);
        }else {
            return RetDto.fail("未关注");
        }
    }

    public void delete(String id){
        followRepository.deleteById(id);
    }

}
