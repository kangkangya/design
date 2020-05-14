package com.panzhihua.travel.controller;

import com.panzhihua.travel.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/picture")
@AllArgsConstructor
public class PictureController {
    private PictureService pictureService;

    @DeleteMapping("delete")
    public void delete(String id) {
        pictureService.delete(id);
    }

    @PostMapping("upload/{refId}/{code}")
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("refId") String refId,
                         @PathVariable("code") String code) {
        return pictureService.upload(file, refId, code);
    }
}
