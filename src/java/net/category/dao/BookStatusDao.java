package net.category.dao;


import net.category.model.Book;
import net.category.model.BookStatus;
import net.category.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStatusDao extends JpaRepository<BookStatus,Long> {
    BookStatus findByBookAndUser(Book book,User user);

    List<BookStatus> findByUser(User user);
}
