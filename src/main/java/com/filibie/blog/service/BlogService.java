package com.filibie.blog.service;

import com.filibie.blog.model.BlogPost;
import com.filibie.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<BlogPost> findAllPosts() {
        return blogRepository.findAll();
    }

    public BlogPost savePost(BlogPost post) {
        return blogRepository.save(post);
    }
}