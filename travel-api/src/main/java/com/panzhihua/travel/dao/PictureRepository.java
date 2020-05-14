package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends MongoRepository<Picture, String> {

    List<Picture> findByRefId(String refId);
}
