package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.TravelNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelNoteRepository extends MongoRepository<TravelNote, String> {
}
