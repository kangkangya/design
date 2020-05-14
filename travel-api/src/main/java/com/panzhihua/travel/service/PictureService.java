package com.panzhihua.travel.service;

import com.panzhihua.travel.dao.PictureRepository;
import com.panzhihua.travel.entity.Picture;
import com.panzhihua.travel.enums.PictureEnum;
import com.panzhihua.travel.utils.FileUtil;
import com.panzhihua.travel.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    @Value("${app.img.upload.path}")
    private String filePath;

    public String upload(MultipartFile file, String refId, String code) {
        Picture picture = new Picture();
        String id = IDUtil.id();
        picture.setId(id);
        picture.setRefId(refId);

        PictureEnum pictureEnum = PictureEnum.getByCode(code);
        String fileName = id + FileUtil.getSuffixName(file.getOriginalFilename());

        try {
            file.transferTo(new File(filePath + refId + "\\temp\\" + fileName));
            FileUtil.resizeImage(filePath + refId + "\\temp\\" + fileName,
                    filePath + refId + "\\" + fileName, pictureEnum.getWidth(),
                    pictureEnum.getHeight(), true);

            picture.setPicUrl(fileName);

            pictureRepository.save(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public void delete(String id) {
        Optional<Picture> optionalPicture = pictureRepository.findById(id);

        if (optionalPicture.isPresent()) {
            Picture picture = optionalPicture.get();

            File file = new File(filePath + picture.getPicUrl());

            pictureRepository.deleteById(id);

            if (file.exists()) {
                file.delete();
            }
        }
    }
}
