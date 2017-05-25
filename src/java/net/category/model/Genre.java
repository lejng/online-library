package net.category.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lenovo on 5/22/2017.
 */
@Entity
@javax.persistence.Table(name = "genres")
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genre")
    private String genre;

    @OneToMany
    @JoinColumn(name="genre_id")
    private List<Book> books;


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
