package com.panzhihua.travel.utils;

import net.coobird.thumbnailator.Thumbnails;
import java.io.File;

public class FileUtil {

    public static boolean resizeImage(String srcPath, String destPath, int newWith, int newHeight, boolean forceSize)  {
        try {
            if (forceSize) {
                Thumbnails.of(srcPath).forceSize(newWith, newHeight).toFile(destPath);
            } else {
                Thumbnails.of(srcPath).width(newWith).height(newHeight).toFile(destPath);
            }

            File file = new File(srcPath);

            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static String getSuffixName(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    public static void main(String[] args) {
        String src = "comment-0.png";
        System.out.println(getSuffixName(src));
    }
}
