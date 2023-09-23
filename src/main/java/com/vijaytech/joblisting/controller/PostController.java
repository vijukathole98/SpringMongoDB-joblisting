package com.vijaytech.joblisting.controller;


import com.vijaytech.joblisting.repository.PostRepo;
import com.vijaytech.joblisting.model.Post;
import com.vijaytech.joblisting.repository.searchRepo;
import com.vijaytech.joblisting.repository.searchRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class PostController {

    @Autowired
    PostRepo repo;
    @Autowired
    searchRepo sreRepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {

        response.sendRedirect("/swagger-ui.html");

    }

    @GetMapping("/allPosts")
    @CrossOrigin
    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post> search(@PathVariable String text)
    {
        return sreRepo.findByText(text);
    }

    @PostMapping("/posts")
    @CrossOrigin
    public Post addPost(@RequestBody Post post)
    {

        return repo.save(post);

    }


}
