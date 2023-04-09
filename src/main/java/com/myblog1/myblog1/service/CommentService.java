package com.myblog1.myblog1.service;

import com.myblog1.myblog1.entity.Comment;
import com.myblog1.myblog1.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto updateComment(long postId, long id, CommentDto commentDto);

    void deleteCommentId(long postId, long id);
}
