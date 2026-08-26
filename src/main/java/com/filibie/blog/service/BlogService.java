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

    public BlogPost findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public List<BlogPost> getPostsSortedByDate() {
        List<BlogPost> posts = blogRepository.findAll();
        return posts.stream().sorted((p1, p2) -> {
            if (p1.getCreatedAt().equals(p2.getCreatedAt())) return 0;
            else return p1.getCreatedAt().isBefore(p2.getCreatedAt()) ? 1 : -1;
        }).toList();
    }
}