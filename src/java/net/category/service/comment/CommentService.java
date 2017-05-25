package net.category.service.comment;

import net.category.model.Book;
import net.category.model.Comment;

import java.util.List;

/**
 * Service class for {@link net.category.model.Comment}
 */
public interface CommentService {

    List<Comment> findCommentsByBook(Book book);

    void add(Comment comment);

    void delete(Comment comment);

    void edit(Comment comment);

    Comment findCommentById(long id);

    void update(String comment,long id);
}
