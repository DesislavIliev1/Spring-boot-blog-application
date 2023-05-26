package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment REST API",
            description = "create comment API is used to save comment to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@PathVariable(value = "postId") long postId,
                                                   @Valid @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get comments by post id REST API",
            description = "Get comments by post id API is used to get comments by post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);

    }

    @Operation(
            summary = "Get comments by id REST API",
            description = "Get comments by id API is used to get comments by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable(value = "postId") Long postId ,
                                                    @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.findCommentById(postId, commentId);
        return  new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    @Operation(
            summary = "Update comment REST API",
            description = "Update comment API is used to update comment from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable(value ="postId" ) Long postId,
                                                   @PathVariable (value = "id") Long commentId,
                                                   @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId , commentDto);
        return new ResponseEntity<>(updatedComment , HttpStatus.OK);
    }

    @Operation(
            summary = "Delete comment  REST API",
            description = "Delete comment API is used to delete comment from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 OK"
    )
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deteleComment(@PathVariable(value = "postId") Long postId,
                                               @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);


    }

}
