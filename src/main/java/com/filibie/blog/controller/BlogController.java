package com.filibie.blog.controller;

import com.filibie.blog.model.BlogPost;
import com.filibie.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found: " + id);
        }
        model.addAttribute("post", post);
        return "post-detail";
    }

    @GetMapping("/posts/create")
    public String createPostPage(Model model) {
        BlogPost blogPost = new BlogPost();
        model.addAttribute("post", blogPost);
        model.addAttribute("pageTitle", "Create New Post");
        return "create-post";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(Model model, @PathVariable Long id) {
        BlogPost post = blogService.findById(id);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found " + id);
        }
        model.addAttribute("post", post);
        return "edit-post";
    }

    /**
     * Handles creation of a new blog post.
     */
    @PostMapping("/posts/submit")
    public String submitPost(@ModelAttribute("post") BlogPost postData, Model model) {
        if (postData.getAuthor() == null || postData.getAuthor().trim().isEmpty()) {
            postData.setAuthor("default");
        }

        BlogPost savedPost = blogService.savePost(postData);
        return "redirect:/posts";
    }

    @PostMapping("/posts/edit")
    public String editPost(@ModelAttribute("post") BlogPost editedPost, Model model) {
        log.debug("id: {}", editedPost.getId());
        if (editedPost.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post ID missing in payload");
        }
        BlogPost existingPost = blogService.findById(editedPost.getId());

        if (existingPost == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found " + editedPost.getId());
        }
        existingPost.setTitle(editedPost.getTitle());
        existingPost.setContent(editedPost.getContent());
        existingPost.setAuthor(editedPost.getAuthor());
        existingPost.setUpdatedAt(LocalDateTime.now());

        blogService.savePost(existingPost);
        return "redirect:/posts/" + existingPost.getId();
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id, Model model) {
        BlogPost post = blogService.findById(id);
        if (post == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found " + id);
        }
        blogService.deletePost(post);
        return "redirect:/posts";
    }

}
