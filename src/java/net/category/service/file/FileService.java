package net.category.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface FileService {
    String writeBook(MultipartFile file, String name);
    String writeBookImage(MultipartFile file, String name);
    String writeUserImage(MultipartFile file, String name);
    void deleteFile(String path);
    File getFile(String path);
}
