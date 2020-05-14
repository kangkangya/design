package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.HotGoWith;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotGoWithRepository extends MongoRepository<HotGoWith, String> {
}
