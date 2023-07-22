package com.example.Search.repository;

import com.example.Search.entity.UserProfile;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Resource
public interface UserProfileRepository extends SolrCrudRepository<UserProfile, String> {
}
