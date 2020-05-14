package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.CommentPicture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPictureRepository extends MongoRepository<CommentPicture, String> {
}
