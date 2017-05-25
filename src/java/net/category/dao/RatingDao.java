package net.category.dao;

import net.category.model.Book;
import net.category.model.Rating;
import net.category.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Lenovo on 4/24/2017.
 */
public interface RatingDao extends JpaRepository<Rating,Long> {
    List<Rating> findByBook(Book book);

    Rating findByBookAndUser(Book book, User user);
}
