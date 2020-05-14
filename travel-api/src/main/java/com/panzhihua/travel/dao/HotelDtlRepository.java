package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.HotelDtl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDtlRepository extends MongoRepository<HotelDtl, String> {
}
