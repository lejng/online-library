package net.category.service.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Lenovo on 5/23/2017.
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final String CONTENT_BOOK_PATH = "C:\\Online_library\\Book";
    private static final String CONTENT_BOOK_IMAGE_PATH = "C:\\Online_library\\Book_image";
    private static final String CONTENT_USER_IMAGE_PATH = "C:\\Online_library\\User_image";

    public String writeBook(MultipartFile file, String name){
        return writeFile(file,name,CONTENT_BOOK_PATH);
    }

    public String writeBookImage(MultipartFile file, String name){
        return writeFile(file,name,CONTENT_BOOK_IMAGE_PATH);
    }

    public String writeUserImage(MultipartFile file, String name){
        return writeFile(file,name,CONTENT_USER_IMAGE_PATH);
    }

    private String writeFile(MultipartFile file, String name,String dirType){
        String path = "";
        String originalName = file.getOriginalFilename();
        FileOutputStream fileOut = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = dirType;
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                path = dir.getAbsolutePath() + File.separator + name + originalName;
                fileOut = new FileOutputStream(path);
                fileOut.write(bytes);
            } catch (Exception e) {
                logger.error("You failed to upload " + name + " => " + e.getMessage());
            }finally {
                try {
                    if (fileOut != null)
                        fileOut.close();
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        } else {
            logger.error("You failed to upload " + name + " because the file was empty.");
        }
        return path;
    }


    public  void deleteFile(String path){
        if(path != null) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public  File getFile(String path){
        File file = new File(path);
        return file;
    }
}
