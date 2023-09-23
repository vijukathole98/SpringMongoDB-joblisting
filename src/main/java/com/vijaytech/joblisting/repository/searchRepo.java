package com.vijaytech.joblisting.repository;

import com.vijaytech.joblisting.model.Post;

import java.util.List;
public interface searchRepo {

    List<Post> findByText(String text);
}
