package com.example.User.repository;

import com.example.User.entity.CorporateUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateUserRepository extends MongoRepository<CorporateUser, String> {
}
