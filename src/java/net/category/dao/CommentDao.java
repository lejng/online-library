package net.category.dao;


import net.category.model.Book;
import net.category.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findByBook(Book book);
}
