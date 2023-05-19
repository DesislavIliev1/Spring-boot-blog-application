package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "Name should not be empty or null")
    private String name;
    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String email;
    @NotEmpty(message = "Body should not be empty or null")
    @Size(min = 10, message = "Comment body should be minimum 10 characters")
    private String body;
}
