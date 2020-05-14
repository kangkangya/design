package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.CommentPictureRepository;
import com.panzhihua.travel.entity.CommentPicture;
import com.panzhihua.travel.utils.FileUtil;
import com.panzhihua.travel.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class CommentPictureService {

    @Autowired
    private CommentPictureRepository commentPictureRepository;

//    @Value("${app.img.upload.path}")

    private String filePath = "E:\\travel-static\\img\\";

    public String save(MultipartFile file, String commentId) {
        CommentPicture commentPicture = new CommentPicture();
        String id = IDUtil.id();
        commentPicture.setId(id);
        commentPicture.setCommentId(commentId);

        String destFilePath = "comment\\";
        String tempFilePath = "comment\\temp\\";
        String fileName = id + FileUtil.getSuffixName(file.getOriginalFilename());

        try {
            file.transferTo(new File(filePath + tempFilePath + fileName));
            FileUtil.resizeImage(filePath + tempFilePath + fileName,
                    filePath + destFilePath + fileName, 200, 120, true);

            commentPicture.setPicUrl(fileName);
            commentPictureRepository.save(commentPicture);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public void delete(String id) {
        commentPictureRepository.deleteById(id);
    }
}
