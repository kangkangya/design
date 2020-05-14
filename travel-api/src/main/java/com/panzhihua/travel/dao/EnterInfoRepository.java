package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.EnterInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnterInfoRepository extends MongoRepository<EnterInfo,String> {
}
