package net.category.service.genre;

import net.category.dao.GenreDao;
import net.category.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lenovo on 5/22/2017.
 */
@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Genre> listGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre findById(long id) {
        return genreDao.findOne(id);
    }
}
