package net.category.controller;

import net.category.model.Book;
import net.category.model.BookStatus;
import net.category.model.User;
import net.category.service.book.BookService;
import net.category.service.bookStatus.BookStatusService;
import net.category.service.file.FileService;
import net.category.service.user.UserService;
import net.category.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for {@link net.category.model.User}'s pages.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private FileService fileService;

    @Autowired
    private BookStatusService bookStatusService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "user/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model, @RequestParam("file_image") MultipartFile fileImage) {
        String path = fileService.writeUserImage(fileImage,userForm.getEmail());
        userForm.setImageUrl(path);
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        userService.save(userForm);
        return "success_registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "user/login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model)
    {
        User user = userService.getCurrentUser();
        if( user == null)
          return   "redirect:/books";
        else{
            model.addAttribute("listBooks",bookStatusService.findByUser(userService.getCurrentUser()));
            model.addAttribute("user",user);
            model.addAttribute("isAdmin",userService.isAdmin());
            model.addAttribute("isLogin",userService.isLogin());
            return "user/user";
        }
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("user") User user,
                          @RequestParam("file_image") MultipartFile fileImage,Model model){
        User currentUser = userService.getCurrentUser();
        if( currentUser == null )
            return   "redirect:/books";
        if (currentUser.getId() != user.getId())
            return   "redirect:/books";
        User originUser = userService.getCurrentUser();
        user.setPassword(originUser.getPassword());
        if(!fileImage.isEmpty()) {
            fileService.deleteFile(originUser.getImageUrl());
            String content = fileService.writeUserImage(fileImage, originUser.getEmail());
            user.setImageUrl(content);
        }
        if(fileImage.isEmpty())
            user.setImageUrl(originUser.getImageUrl());
        user.setEmail(originUser.getEmail());
        user.setRole(originUser.getRole());
        userService.update(user);
        model.addAttribute("user",user);
        return "user/_user";
    }

    @RequestMapping(value = "user/book/read/{id}",method = RequestMethod.GET)
    public String addReadBook(@PathVariable("id") int id){
        BookStatus bookStatus = findByUserAndBook(id);
        createOrUpdateBookStatus(bookStatus,"Read",id);
        return "ok";
    }

    @RequestMapping(value = "user/book/going_read/{id}",method = RequestMethod.GET)
    public String addGoingReadBook(@PathVariable("id") int id){
        BookStatus bookStatus = findByUserAndBook(id);
        createOrUpdateBookStatus(bookStatus,"Going to read",id);
        return "ok";
    }

    @RequestMapping(value = "user/book/not_read/{id}",method = RequestMethod.GET)
    public String notReadBook(@PathVariable("id") int id){
        BookStatus bookStatus = findByUserAndBook(id);
        if(bookStatus != null)
            bookStatusService.remove(bookStatus.getId());
        return "ok";
    }

    private void createOrUpdateBookStatus(BookStatus bookStatus,String status,int bookId){
        if(bookStatus != null){
            bookStatus.setStatus(status);
            bookStatusService.update(bookStatus);
        }else{
            bookStatus = createBookStatus(bookId,status);
            bookStatusService.add(bookStatus);
        }
    }

    private BookStatus createBookStatus(int bookId,String status){
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBook(bookService.getBookById(bookId));
        bookStatus.setUser(userService.getCurrentUser());
        bookStatus.setStatus(status);
        return bookStatus;
    }

    private BookStatus findByUserAndBook(int bookId){
        Book book = bookService.getBookById(bookId);
        return bookStatusService.ByBookAndUser(book,userService.getCurrentUser());
    }
}
