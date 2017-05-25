package net.category.controller;

import net.category.model.Comment;
import net.category.service.book.BookServiceImpl;
import net.category.service.comment.CommentService;
import net.category.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for {@link net.category.model.Comment}'s pages.
 */
@Controller
public class CommentController {


    @Autowired
    CommentService commentService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/comment/add/{id}", method = RequestMethod.POST)
    public String commentAdd(@PathVariable("id") long bookId, @RequestParam("comment") String commentText,Model model){
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setBook(bookService.getBookById(bookId));
        comment.setUser(userService.getCurrentUser());
        commentService.add(comment);
        model.addAttribute("comment",comment);
        return "book/_comment";
    }

}
