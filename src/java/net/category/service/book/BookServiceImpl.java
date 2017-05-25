package net.category.service.book;


import net.category.dao.BookDao;
import net.category.model.Book;
import net.category.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    public void addBook(Book book) {
        bookDao.save(book);
    }


    public void updateBook(Book book) {
        bookDao.save(book);
    }


    public void removeBook(long id) {
        bookDao.delete(id);
    }



    public Book getBookById(long id) {
        return bookDao.getOne(id);
    }


    public List<Book> listBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return bookDao.findByGenre(genre);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }
}