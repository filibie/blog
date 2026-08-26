package com.filibie.blog.repository;

import com.filibie.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByOrderByCreatedAtDesc();

}