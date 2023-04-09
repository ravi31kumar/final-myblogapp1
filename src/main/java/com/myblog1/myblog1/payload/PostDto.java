package com.myblog1.myblog1.payload;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;
    @NotEmpty(message="is mandatory")
    @Size(min=2, message= "post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=10, message= "post description should be at least 10 characters")
    private String description;

    @NotEmpty
    @Size(min=10, message= "post content should be at least 10 characters")
    private String content;
}
