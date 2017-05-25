package net.category.service.rating;

import net.category.model.Book;
import net.category.model.Rating;
import net.category.model.User;

import java.util.List;

/**
 * Created by Lenovo on 4/24/2017.
 */
public interface RatingService {
    void add(Rating rating);

    List<Rating> findByBook(Book book);

    Rating findByBookAndUser(Book book, User user);
}
