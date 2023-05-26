package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post
    @Operation(
            summary = "Create Post REST API",
            description = "create post API is used to save post to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts
    @Operation(
            summary = "Get all Posts REST API",
            description = "Get all posts API is used to get all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
            @RequestParam(value = "sortDir" , defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy , sortDir);
    }

    // get post by id
    @Operation(
            summary = "Get post by id REST API",
            description = "get post by id API is used to get post by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    //update post by id
    @Operation(
            summary = "Update Post REST API",
            description = "Update post API is used to update post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 UPDATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto , @PathVariable(name = "id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);

    }

    //delete post
    @Operation(
            summary = "Delete Post REST API",
            description = "Delete post API is used to delete post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Deleted"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully" , HttpStatus.OK);
    }

    //get post by category REST API
    @Operation(
            summary = "Get Post by category REST API",
            description = "Get post by category API is used to get post by category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable("id") Long categoryId){
       List<PostDto>posts = postService.getPostsByCategory(categoryId);
       return ResponseEntity.ok(posts);
    }





}
