package com.example.restapimongodb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends MongoRepository<Tv, String> {
}
