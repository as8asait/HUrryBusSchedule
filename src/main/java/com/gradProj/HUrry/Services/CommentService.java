package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Dto.CommentDto;
import com.gradProj.HUrry.Repositories.CommentRepo;
import com.gradProj.HUrry.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;


    public void save(CommentDto commentDto) {
        Comment comment=new Comment();
        comment.setBody(commentDto.getBody());
        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setCreatedByUserId(commentDto.getCreatedByUserId());
        commentRepo.save(comment);
    }
    public List<CommentDto> findAll() {
        return commentRepo.findAll().stream().map(comment -> transferCommentToDto(comment)).toList();
    }
    public CommentDto findOne(Long id) {
        Optional<Comment> comment=commentRepo.findById(id);
        if(comment.isPresent()){
            return transferCommentToDto(comment.get());
        }
        return null;
    }

    public void update (Long id,CommentDto commentDto) {
        Optional<Comment> oldcomment=commentRepo.findById(id);
        if(oldcomment.isPresent()) {
            Comment comment =oldcomment.get();
            comment.setBody(commentDto.getBody());
            comment.setCreatedDate(commentDto.getCreatedDate());
            comment.setCreatedByUserId(commentDto.getCreatedByUserId());
            commentRepo.save(comment);
        }
    }

    public void delete(Long id) {
        commentRepo.deleteById(id);
    }

    public CommentDto transferCommentToDto(Comment comment) {
        CommentDto commentDto=new CommentDto();
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedDate(comment.getCreatedDate());
        commentDto.setCreatedByUserId(comment.getCreatedByUserId());
        return commentDto;
    }
}
