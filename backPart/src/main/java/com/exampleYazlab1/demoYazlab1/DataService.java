package com.exampleYazlab1.demoYazlab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataRepo dataRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Data findLastText() {
        Criteria criteria = new Criteria();
        Sort sort = Sort.by(Sort.Direction.DESC, "_id");
        Query query = new Query(criteria).with(sort).limit(1);
        List<Data> texts = mongoTemplate.find(query, Data.class);
        return texts.isEmpty() ? null : texts.get(texts.size()-1);
    }

    public List<Data> findAllData(){
      return dataRepo.findAll();
    }
}
