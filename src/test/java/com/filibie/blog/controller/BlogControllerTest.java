//package com.filibie.blog.controller;
//
//import com.filibie.blog.model.BlogPost;
//import com.filibie.blog.service.BlogService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BlogController.class)
//public class BlogControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BlogService blogService;
//
//    @Test
//    void testListPosts() throws Exception {
//        // Arrange
//        BlogPost post = new BlogPost();
//        post.setId(1L);
//        post.setTitle("Test Post");
//        post.setContent("Content");
//        post.setAuthor("User");
//
//        when(blogService.findAllPosts()).thenReturn(java.util.Arrays.asList(post));
//
//        // Act & Assert
//        mockMvc.perform(get("/")
//                        .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(view("index"));
//    }
//
//    @Test
//    void testViewPost() throws Exception {
//        // Arrange
//        BlogPost post = new BlogPost();
//        post.setId(1L);
//        post.setTitle("Detailed Post");
//        post.setContent("Long content.");
//        post.setAuthor("Admin");
//        post.setPublished(true);
//
//        when(blogService.findAllPosts()).thenReturn(java.util.Arrays.asList(post));
//
//        // Act & Assert
//        mockMvc.perform(get("/1")
//                        .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(view("view"));
//    }
//
//    @Test
//    void testCreatePostSuccess() throws Exception {
//        // Arrange
//        BlogPost inputPost = new BlogPost();
//        inputPost.setTitle("New Article");
//        inputPost.setContent("Great content!");
//        inputPost.setAuthor("Jane Doe"); // Explicit author set in form/test
//
//        BlogPost savedPost = new BlogPost();
//        savedPost.setId(2L);
//        savedPost.setTitle("New Article");
//        savedPost.setContent("Great content!");
//        savedPost.setAuthor("Current User"); // Expecting the service layer to modify this if needed, but we mock the save result
//
//        // Mock behavior: When POST /create is hit with postData, it returns savedPost
//        when(blogService.savePost(any(BlogPost.class))).thenReturn(savedPost);
//
//        // Act & Assert
//        mockMvc.perform(post("/create")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("postData.title", "New Article")
//                        .param("postData.content", "Great content!")
//                        .param("postData.author", "Jane Doe")) // Sending data via form parameters matching @ModelAttribute
//                // We expect a redirect to the root path ("/") upon success
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        // Verify that savePost was called exactly once with the correct type of input object
//        verify(blogService, times(1)).savePost(any(BlogPost.class));
//    }
//
//    @Test
//    void testCreatePostFailure_NoContent() throws Exception {
//        // Arrange
//        BlogPost inputPost = new BlogPost();
//        inputPost.setTitle("Incomplete Post");
//        // Intentionally leave content blank
//
//        when(blogService.savePost(any(BlogPost.class))).thenReturn(null); // Simulating a save failure if repository threw exception, but for simple test, we just ensure the path works
//
//        // Act & Assert
//        mockMvc.perform(post("/create")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("postData.title", "Incomplete Post")
//                        .param("postData.content", "")) // Empty content
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        verify(blogService, times(1)).savePost(any(BlogPost.class));
//    }
//}