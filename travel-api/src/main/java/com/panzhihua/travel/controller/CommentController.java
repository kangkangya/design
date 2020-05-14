package com.panzhihua.travel.controller;

import com.panzhihua.travel.dto.req.CommentQueryCondition;
import com.panzhihua.travel.dto.ret.RetDto;
import com.panzhihua.travel.entity.Comment;
import com.panzhihua.travel.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("list")
    public RetDto list(CommentQueryCondition queryCondition) {
        return commentService.find(queryCondition);
    }

    @DeleteMapping("delete")
    public void delete(String id) {
        commentService.delete(id);
    }

    @PostMapping("save")
    public Comment save(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    @PutMapping("edit")
    public Comment edit(Comment comment) {
        return commentService.save(comment);
    }
}
