package com.filibie.blog.util;

import com.filibie.blog.model.BlogPost;
import com.filibie.blog.service.BlogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private BlogService service;

    public DataLoader(BlogService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        BlogPost post1 = new BlogPost();
        post1.setAuthor("filon");
        post1.setTitle("Hello from my first blog post!! :)");
        post1.setContent("Hello from my first blog post, this is start of a great journey of me writing very "
                + "interesting blog posts for you :)");
        post1.setCreatedAt(LocalDateTime.now());
        service.savePost(post1);

        BlogPost post2 = new BlogPost();
        post2.setAuthor("filon");
        post2.setTitle("Second Post, buckle up");
        post2.setContent("Hello from my first blog post, this is start of a great journey of me writing very "
                + "interesting blog posts for you :)");
        post2.setCreatedAt(LocalDateTime.now());
        service.savePost(post2);
    }

}
