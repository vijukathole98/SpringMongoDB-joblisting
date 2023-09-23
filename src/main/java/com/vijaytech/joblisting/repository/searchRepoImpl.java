package com.vijaytech.joblisting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vijaytech.joblisting.model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class searchRepoImpl implements searchRepo{

    @Autowired
    MongoClient Client;

    @Autowired
    MongoConverter converter;


    public List<Post> findByText(String text) {

      final List<Post> posts = new ArrayList<>();



        MongoDatabase database = Client.getDatabase("Vk");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "profile", "desc")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                 new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));
        return posts;
    }


}
