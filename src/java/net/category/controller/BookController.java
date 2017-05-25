package net.category.controller;

import net.category.model.Book;
import net.category.model.Comment;
import net.category.model.Rating;
import net.category.model.User;
import net.category.service.book.BookService;
import net.category.service.comment.CommentService;
import net.category.service.file.FileService;
import net.category.service.genre.GenreService;
import net.category.service.rating.RatingService;
import net.category.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller for {@link net.category.model.Book}'s pages.
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private FileService fileManager;

    @RequestMapping(value = {"/","books"}, method = RequestMethod.GET)
    public String listBooks(Model model){
        addAttributesForBooksPage(model);
        model.addAttribute("listBooks", bookService.listBooks());
        return "book/books";
    }

    @RequestMapping(value = "books/genre/{genre}", method = RequestMethod.GET)
    public String listBooksByGenre(Model model,@PathVariable("genre") int genre){
        addAttributesForBooksPage(model);
        model.addAttribute("listBooks", bookService.findByGenre(genreService.findById(genre)));
        return "book/books";
    }

    private void addAttributesForBooksPage(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listGenres", genreService.listGenres());
        model.addAttribute("isAdmin",userService.isAdmin());
        model.addAttribute("isLogin",userService.isLogin());
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, @RequestParam("file_book") MultipartFile fileBook,
                          @RequestParam("file_image") MultipartFile fileImage,
                          @RequestParam("genre_id") Long genre){
        book.setGenre(genreService.findById(genre));
        Book originBook = bookService.getBookById(book.getId());
        if(!fileBook.isEmpty()) {
            if(book.getId() != 0)
                fileManager.deleteFile(originBook.getContentUrl());
            String content = fileManager.writeBook(fileBook, book.getName());
            book.setContentUrl(content);
        }
        if(!fileImage.isEmpty()) {
            if(book.getId() != 0)
                fileManager.deleteFile(originBook.getImageUrl());
            String content = fileManager.writeBookImage(fileImage, book.getName());
            book.setImageUrl(content);
        }
        if(book.getId() == 0){
            bookService.addBook(book);
        }else {
            if(fileBook.isEmpty())
                book.setContentUrl(originBook.getContentUrl());
            if(fileImage.isEmpty())
                book.setImageUrl(originBook.getImageUrl());
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping("book/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        Book book = bookService.getBookById(id);
        try {
            fileManager.deleteFile(book.getContentUrl());
            fileManager.deleteFile(book.getImageUrl());
        }catch (Exception e){

        }
        bookService.removeBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "book/rate/{id}",method = RequestMethod.POST)
    public String rateBook(@PathVariable("id") int id,@RequestParam("rate") int rate,Model model){
        Book book = bookService.getBookById(id);
        User user = userService.getCurrentUser();
        Rating rating = ratingService.findByBookAndUser(book, user);
        if(rating == null){
            rating = new Rating();
            rating.setBook(book);
            rating.setUser(user);
        }
        if(rate < 6 && rate > 0)
            rating.setRating(rate);
        ratingService.add(rating);
        book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book/_rating";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        List<Comment> comments = commentService.findCommentsByBook(bookService.getBookById(id));
        model.addAttribute("comments",comments);
        model.addAttribute("isLogin",userService.isLogin());
        model.addAttribute("isAdmin",userService.isAdmin());
        model.addAttribute("listGenres", genreService.listGenres());
        model.addAttribute("book", bookService.getBookById(id));
        int userRate = 0;
        if(ratingService.findByBookAndUser(bookService.getBookById(id),userService.getCurrentUser()) != null)
            userRate = (int)ratingService.findByBookAndUser(bookService.getBookById(id),userService.getCurrentUser()).getRating();
        model.addAttribute("userRate",userRate);
        return "book/bookdata";
    }

}