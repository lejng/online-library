package net.category.service.bookStatus;

import net.category.dao.BookStatusDao;
import net.category.model.Book;
import net.category.model.BookStatus;
import net.category.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lenovo on 26.05.2017.
 */

@Service
public class BookStatusServiceImpl implements BookStatusService {
    @Autowired
    private BookStatusDao bookStatusDao;

    @Override
    public void add(BookStatus bookStatus) {
        bookStatusDao.save(bookStatus);
    }

    @Override
    public void update(BookStatus bookStatus) {
        bookStatusDao.save(bookStatus);
    }

    @Override
    public void remove(long id) {
        bookStatusDao.delete(id);
    }

    @Override
    public List<BookStatus> findByUser(User user) {
        return bookStatusDao.findByUser(user);
    }

    @Override
    public BookStatus ByBookAndUser(Book book, User user) {
        return bookStatusDao.findByBookAndUser(book,user);
    }
}
