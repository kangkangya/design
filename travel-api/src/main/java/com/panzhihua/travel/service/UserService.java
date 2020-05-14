package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.UserRepository;
import com.panzhihua.travel.dto.req.QueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.User;
import com.panzhihua.travel.utils.IDUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService {
    private final MongoTemplate mongoTemplate;

    private final UserRepository userRepository;

    public RetDto register(User user) {
        List<User> all = userRepository.findAll(Example.of(User.builder().username(user.getUsername()).build()));
        if (!CollectionUtils.isEmpty(all)) {
            return RetDto.userExistsError();
        }

        User save = userRepository.save(user);
        return RetDto.success(save, 0, 0);
    }


    public RetDto edit(User user) {
        List<User> all = userRepository.findAll(Example.of(User.builder().username(user.getUsername()).build()));
        if (CollectionUtils.isEmpty(all)) {
            return RetDto.userNotExistsError();
        }

        if (StringUtils.isEmpty(user.getNickName())) {
            user.setNickName("系统默认用户名");
        }

        if (StringUtils.isEmpty(user.getPicUrl())) {
            user.setPicUrl("default_head_url.jpeg");
        }

        return RetDto.success(userRepository.save(user), 0, 0);
    }

    public RetDto save(User user) {
        user.setId(IDUtil.id());

        List<User> all = userRepository.findAll(Example.of(User
                .builder().username(user.getUsername()).build()));
        if (!CollectionUtils.isEmpty(all)) {
            return RetDto.userExistsError();
        }

        if (StringUtils.isEmpty(user.getNickName())) {
            user.setNickName("系统默认用户名");
        }

        if (StringUtils.isEmpty(user.getPicUrl())) {
            user.setPicUrl("default_head_url.jpeg");
        }

        return RetDto.success(userRepository.save(user), 0, 0);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public RetDto login(String username, String password) {
        Optional<User> one = userRepository.findOne(Example.of(User.builder().username(username).password(password).build()));

        if (one.isPresent()) {
            return RetDto.success(one.get(), 0, 0);
        }

        return RetDto.userNotExistsError();
    }

    public RetDto list(QueryCondition condition) {
        Query query = new Query();

        if (!StringUtils.isEmpty(condition.getSearch())) {
            Pattern pattern = Pattern.compile("^.*"
                    + condition.getSearch() + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("nickName").regex(pattern));
        }

        int count = (int) mongoTemplate.count(query, User.class);

        query.skip((condition.startPage()))
                .limit(condition.getPageSize());

        return RetDto.builder()
                .pageSize(condition.getPageSize())
                .body(mongoTemplate.find(query, User.class))
                .total(count).build();
    }
}
