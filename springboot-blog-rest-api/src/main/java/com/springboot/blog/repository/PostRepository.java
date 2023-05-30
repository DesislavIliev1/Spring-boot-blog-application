package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);


    @Query(value = "SELECT * FROM posts p WHERE "+
    " p.title LIKE CONCAT ('%', :query, '%')"+
    "Or p.description LIKE CONCAT ('%', :query, '%')", nativeQuery = true)
    List<Post>searchPosts(String query);
}
