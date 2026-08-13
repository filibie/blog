package com.filibie.blog.controller;

import com.filibie.blog.model.BlogPost;
import com.filibie.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Displays the list of all published blog posts.
     */
    @GetMapping("/")
    public String listPosts(Model model) {
        List<BlogPost> posts = blogService.findAllPosts();
        model.addAttribute("posts", posts);
        return "index"; // Maps to src/main/resources/templates/index.html
    }

    /**
     * Displays the detailed content of a single blog post.
     */

    /**
     * Handles creation of a new blog post.
     */
    @PostMapping("/create")
    public String createPost(@ModelAttribute("postData") BlogPost postData, Model model) {
        if (postData.getAuthor() == null || postData.getAuthor().trim().isEmpty()) {
            postData.setAuthor("Current User"); 
        }

        BlogPost savedPost = blogService.savePost(postData);
        model.addAttribute("message", "Blog post created successfully!");
        return "redirect:/"; 
    }
}