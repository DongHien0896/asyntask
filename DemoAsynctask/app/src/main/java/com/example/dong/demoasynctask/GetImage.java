package com.example.dong.demoasynctask;

import java.io.File;
import java.util.List;

public class GetImage {

    public void getAllImages(File folder, List<Image> listImages) {
        File[] listFile = folder.listFiles();
        for (File file : listFile) {
            if (file.isDirectory()) getAllImages(file, listImages);
            else addImage(file, listImages);
        }
    }

    public void addImage(File file, List<Image> listImage) {
        if (checkImage(file.getPath()))
            listImage.add(new Image(file.getPath()));
        else return;
    }

    public boolean checkImage(String name) {
        return name.endsWith(Constants.JPEG) ||
                name.endsWith(Constants.JPEGCAP) ||
                name.endsWith(Constants.GIFGCAP) ||
                name.endsWith(Constants.GIF) ||
                name.endsWith(Constants.PNG) ||
                name.endsWith(Constants.PNGCAP) ||
                name.endsWith(Constants.JPG) ||
                name.endsWith(Constants.JPGCAP);
    }
}
