package net.category.service.comment;


import net.category.dao.CommentDao;
import net.category.model.Book;
import net.category.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;



    @Override
    public List<Comment> findCommentsByBook(Book book) {
        return commentDao.findByBook(book);
    }

    @Override
    public void add(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public void edit(Comment comment) {

    }

    @Override
    public Comment findCommentById(long id) {
        return commentDao.findOne(id);
    }

    @Override
    public void update(String comment, long id) {
        Comment comment1 = commentDao.findOne(id);
        comment1.setComment(comment);
        commentDao.save(comment1);
    }
}
