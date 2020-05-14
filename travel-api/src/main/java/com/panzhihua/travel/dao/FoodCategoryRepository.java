package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.FoodCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends MongoRepository<FoodCategory, String> {
}
