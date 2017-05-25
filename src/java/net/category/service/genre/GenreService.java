package net.category.service.genre;

import net.category.model.Genre;

import java.util.List;

/**
 * Created by Lenovo on 5/22/2017.
 */
public interface GenreService {
    List<Genre> listGenres();
    Genre findById(long id);
}
