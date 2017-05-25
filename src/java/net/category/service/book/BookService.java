package net.category.service.book;

import net.category.model.Book;
import net.category.model.Genre;

import java.util.List;


public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(long id);

    Book getBookById(long id);

    List<Book> listBooks();

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthor(String author);
}
