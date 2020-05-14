package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Replay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplayRepository extends MongoRepository<Replay, String> {
}
