package com.myblog1.myblog1.service.impl;

import com.myblog1.myblog1.entity.Post;
import com.myblog1.myblog1.exception.ResourceNotFound;
import com.myblog1.myblog1.payload.PostDto;
import com.myblog1.myblog1.repository.PostRepository;
import com.myblog1.myblog1.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper =modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post=mapToEntity(postDto);
        Post newpost = postRepository.save(post);

        PostDto newPostDto=mapToDto(newpost);
        return newPostDto;
    }

    @Override
    public List<PostDto> getAllposts(int pageNo, int pageSize, String sortBy) {

        Pageable pageable  = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts=postRepository.findAll(pageable);
        List<Post> contents = posts.getContent();

        return contents.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }

    @Override
   public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow
               (()->new ResourceNotFound("Post", "id",  id));
                return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postdto, long id) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new ResourceNotFound("Post", "id", id));


        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());

       Post updatedPost = postRepository.save(post);return mapToDto(updatedPost);

    }

    @Override
    public void deletePostByid(long id) {
        Post post = postRepository.findById(id).orElseThrow
                (() -> new ResourceNotFound("Post", "id", id));
        postRepository.delete(post);
    }

    Post mapToEntity(PostDto postDto){
       Post post = modelMapper.map(postDto, Post.class);
//        Post post =new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;

    }

    PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post, PostDto.class);
//        PostDto postDto=new PostDto();
//
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

}
