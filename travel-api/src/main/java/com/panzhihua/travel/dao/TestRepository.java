package com.panzhihua.travel.dao;

import com.panzhihua.travel.entity.TestA;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//将DAO类声明为bean
@Repository
public interface TestRepository extends MongoRepository<TestA, String> {

}
