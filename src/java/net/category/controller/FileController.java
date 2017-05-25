package net.category.controller;

import net.category.service.book.BookService;
import net.category.service.file.FileService;
import net.category.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class FileController {

    @Autowired
    private BookService bookService;

    @Autowired
    private FileService fileManager;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/image/{id}", method = RequestMethod.GET)
    public @ResponseBody
    void imageUser(HttpServletResponse response, @PathVariable("id") long id) {
        String path = userService.findById(id).getImageUrl();
        File file = fileManager.getFile(path);
        sendFile(file,response);
    }

    @RequestMapping(value = "book/download/{id}", method = RequestMethod.GET)
    public @ResponseBody void downloadContent(HttpServletResponse response, @PathVariable("id") int id) {
        String path = bookService.getBookById(id).getContentUrl();
        File file = fileManager.getFile(path);
        sendFile(file,response);
    }

    @RequestMapping(value = "book/image/{id}", method = RequestMethod.GET)
    public @ResponseBody void imageBook(HttpServletResponse response, @PathVariable("id") int id) {
        String path = bookService.getBookById(id).getImageUrl();
        File file = fileManager.getFile(path);
        sendFile(file,response);
    }

    private void sendFile(File file,HttpServletResponse response){
        try {
            InputStream in = new FileInputStream(file);
            response.setContentType(file.getName());
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            FileCopyUtils.copy(in, response.getOutputStream());
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
