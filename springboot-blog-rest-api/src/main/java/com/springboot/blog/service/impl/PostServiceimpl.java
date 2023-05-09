package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceimpl implements PostService {

   private PostRepository postRepository;


    public PostServiceimpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert Dto to Entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newpost = postRepository.save(post);

        //convert Entity to Dto

        PostDto postResponse = new PostDto();
        postResponse.setId(newpost.getId());
        postResponse.setTitle(newpost.getTitle());
        postResponse.setDescription(newpost.getDescription());
        postResponse.setContent(newpost.getContent());

        return postResponse;
    }
}
