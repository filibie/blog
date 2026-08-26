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
                + "interesting blog posts for you :)" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum.");
        post1.setCreatedAt(LocalDateTime.now());
        service.savePost(post1);

        Thread.sleep(2000);

        BlogPost post2 = new BlogPost();
        post2.setAuthor("filon");
        post2.setTitle("Second Post, buckle up");
        post2.setContent("This is my second blog post, buckle up for a ride with more of those stinky blog posts!! :D" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum."
                );
        post2.setCreatedAt(LocalDateTime.now());
        service.savePost(post2);
    }

}
