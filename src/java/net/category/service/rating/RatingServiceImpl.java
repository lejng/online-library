package net.category.service.rating;

import net.category.dao.RatingDao;
import net.category.model.Book;
import net.category.model.Rating;
import net.category.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lenovo on 4/24/2017.
 */
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingDao ratingDao;

    @Override
    public void add(Rating rating) {
        ratingDao.save(rating);
    }

    @Override
    public List<Rating> findByBook(Book book) {
        return ratingDao.findByBook(book);
    }

    @Override
    public Rating findByBookAndUser(Book book, User user) {
        return ratingDao.findByBookAndUser(book,user);
    }
}
