package net.category.service.bookStatus;

import net.category.model.Book;
import net.category.model.BookStatus;
import net.category.model.User;

import java.util.List;

/**
 * Created by Lenovo on 26.05.2017.
 */
public interface BookStatusService {
    void add(BookStatus bookStatus);

    void update(BookStatus bookStatus);

    void remove(long id);

    List<BookStatus> findByUser(User user);

    BookStatus ByBookAndUser(Book book,User user);


}
