package com.vijaytech.joblisting.repository;

import com.vijaytech.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String>
{


}
