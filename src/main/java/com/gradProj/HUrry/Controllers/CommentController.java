package com.gradProj.HUrry.Controllers;

import com.gradProj.HUrry.Dto.CommentDto;
import com.gradProj.HUrry.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
 public class CommentController {
    @Autowired
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public List<CommentDto> getAllComments() {
        return commentService.findAll();
    }

    @GetMapping("get comment/{id}")
    public CommentDto getCommentById(@PathVariable long id) {
        return commentService.findOne(id);
    }

    @PostMapping("/create")
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
    }

    @PostMapping("/update/{id}")
    public void updateComment(@PathVariable long id,@RequestBody CommentDto commentDto) {
        commentService.update(id,commentDto);
    }

    @DeleteMapping("/delete")
    public void deleteComment(@RequestParam long id) {
        commentService.delete(id);
    }


}
