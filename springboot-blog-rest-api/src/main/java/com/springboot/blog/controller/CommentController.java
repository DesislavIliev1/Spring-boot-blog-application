package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createComment(@PathVariable(value = "postId") long postId,
                                                   @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);

    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable(value = "postId") Long postId ,
                                                    @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.findCommentById(postId, commentId);
        return  new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable(value ="postId" ) Long postId,
                                                   @PathVariable (value = "id") Long commentId,
                                                   @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId , commentDto);
        return new ResponseEntity<>(updatedComment , HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deteleComment(@PathVariable(value = "postId") Long postId,
                                               @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);


    }

}
