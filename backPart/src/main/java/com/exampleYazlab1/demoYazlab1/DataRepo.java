package com.exampleYazlab1.demoYazlab1;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepo extends MongoRepository<Data, ObjectId> {

}
