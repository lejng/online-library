package net.category.dao;


import net.category.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre,Long> {
}
