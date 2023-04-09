package com.myblog1.myblog1.service;

import com.myblog1.myblog1.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);


    List<PostDto> getAllposts(int pageNo, int pageSize,String sortBy);

   PostDto getPostById(long id);

   PostDto updatePost(PostDto postdto, long id);

    void deletePostByid(long id);
}
