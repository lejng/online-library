package net.category.controller;

import net.category.model.*;
import net.category.service.book.BookService;
import net.category.service.bookStatus.BookStatusService;
import net.category.service.comment.CommentService;
import net.category.service.file.FileService;
import net.category.service.genre.GenreService;
import net.category.service.rating.RatingService;
import net.category.service.user.UserService;
import net.category.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller for {@link net.category.model.Book}'s pages.
 */
@Controller
public class BookController {

    @Autowired
    private BookValidator bookValidator;

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

    @Autowired
    private BookStatusService bookStatusService;

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
        model.addAttribute("isBooks",true);
    }

    @RequestMapping(value = "/books/edit", method = RequestMethod.POST)
    public String editBook(@ModelAttribute("book") Book book, @RequestParam("file_book") MultipartFile fileBook,
                          @RequestParam("file_image") MultipartFile fileImage,BindingResult bindingResult,
                          @RequestParam("genre_id") Long genre){
        bookValidator.validate(book,bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/bookdata/" + book.getId();
        }
        book.setGenre(genreService.findById(genre));
        Book originBook = bookService.getBookById(book.getId());
        book.setRatings(originBook.getRatings());
        if(!fileBook.isEmpty()) {
            fileManager.deleteFile(originBook.getContentUrl());
            String content = fileManager.writeBook(fileBook, book.getName());
            book.setContentUrl(content);
        }
        if(!fileImage.isEmpty()) {
            fileManager.deleteFile(originBook.getImageUrl());
            String content = fileManager.writeBookImage(fileImage, book.getName());
            book.setImageUrl(content);
        }
        if(fileBook.isEmpty())
            book.setContentUrl(originBook.getContentUrl());
        if(fileImage.isEmpty())
            book.setImageUrl(originBook.getImageUrl());
        bookService.updateBook(book);
        return "redirect:/bookdata/" + book.getId();
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, @RequestParam("file_book") MultipartFile fileBook,
                          @RequestParam("file_image") MultipartFile fileImage,BindingResult bindingResult,
                          @RequestParam("genre_id") Long genre,Model model){
        bookValidator.validate(book,bindingResult);
        if (bindingResult.hasErrors() || fileBook.isEmpty()) {
            return "redirect:/books";
        }
        book.setGenre(genreService.findById(genre));
        if(!fileBook.isEmpty()) {
            String content = fileManager.writeBook(fileBook, book.getName());
            book.setContentUrl(content);
        }
        if(!fileImage.isEmpty()) {
            String content = fileManager.writeBookImage(fileImage, book.getName());
            book.setImageUrl(content);
        }
            bookService.addBook(book);
        return "redirect:/bookdata/" + book.getId();
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
        Book book = bookService.getBookById(id);
        List<Comment> comments = commentService.findCommentsByBook(bookService.getBookById(id));
        model.addAttribute("comments",comments);
        model.addAttribute("isLogin",userService.isLogin());
        model.addAttribute("isAdmin",userService.isAdmin());
        model.addAttribute("listGenres", genreService.listGenres());
        model.addAttribute("book",book );
        model.addAttribute("isBookData",true);
        int userRate = 0;
        if(ratingService.findByBookAndUser(bookService.getBookById(id),userService.getCurrentUser()) != null)
            userRate = (int)ratingService.findByBookAndUser(bookService.getBookById(id),userService.getCurrentUser()).getRating();
        model.addAttribute("userRate",userRate);
        BookStatus bookStatus = bookStatusService.ByBookAndUser(book,userService.getCurrentUser());
        if( bookStatus == null)
            model.addAttribute("notRead","active");
        else {
            if (bookStatus.getStatus().equals("Read"))
                model.addAttribute("Read","active");
            if (bookStatus.getStatus().equals("Going to read"))
                model.addAttribute("GoRead","active");
        }
        return "book/bookdata";
    }

}
