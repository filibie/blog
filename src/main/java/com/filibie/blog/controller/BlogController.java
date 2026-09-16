package com.filibie.blog.controller;

import com.filibie.blog.model.BlogPost;
import com.filibie.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

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
    public String rootControler() {
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<BlogPost> posts = blogService.getPostsSortedByDate();
        model.addAttribute("posts", posts);
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        BlogPost post = blogService.findById(id);
        if (post == null) {
            throw new RuntimeException("Post not found: " + id);
        }
        model.addAttribute("post", post);
        return "post-detail";
    }

    @GetMapping("/posts/submit")
    public String createPostPage(Model model) {
        BlogPost blogPost = new BlogPost();
        model.addAttribute("post", blogPost);
        model.addAttribute("pageTitle", "Create New Post");
        return "create-post";
    }

    /**
     * Handles creation of a new blog post.
     */
    @PostMapping("/posts/submit")
    public String submitPost(@ModelAttribute("postData") BlogPost postData, Model model) {
        if (postData.getAuthor() == null || postData.getAuthor().trim().isEmpty()) {
            postData.setAuthor("Current User");
        }

        BlogPost savedPost = blogService.savePost(postData);
        model.addAttribute("pageTitle", "Home");
        return "redirect:/posts";
    }

}