package com.panzhihua.travel.controller;

import com.panzhihua.travel.service.CommentPictureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/comment-picture")
@AllArgsConstructor
public class CommentPictureController {
    private CommentPictureService commentPictureService;

    @DeleteMapping("delete")
    public void delete(String id) {
        commentPictureService.delete(id);
    }

    @PostMapping("upload/{commentId}")
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("commentId") String commentId) {
        return commentPictureService.save(file, commentId);
    }
}
