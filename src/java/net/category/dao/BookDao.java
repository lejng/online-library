package net.category.dao;


import net.category.model.Book;
import net.category.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Long> {

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthor(String author);
}
