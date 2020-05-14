package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowRepository extends MongoRepository<Follow,String> {
}
