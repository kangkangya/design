package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.GoWith;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoWithRepository extends MongoRepository<GoWith, String> {
}
