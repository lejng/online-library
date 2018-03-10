package net.category.model;

import javax.persistence.*;
import java.util.List;

/**
 * Simple JavaBean domain object that represents a Book.
 */
@Entity
@javax.persistence.Table(name = "books")
public class Book  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;


    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "contentUrl")
    private String contentUrl;

    @Column(name = "imageUrl")
    private String imageUrl;

    @OneToMany
    @JoinColumn(name="book_id")
    private List<Rating> ratings;


    @Transient
    private float rating = - 1;

    public float getRating() {
        if(rating != -1)
            return rating;
        if(ratings.isEmpty()){
            rating = 0;
            return rating;
        }
        rating = 0;
        for(Rating r : ratings)
            rating += r.getRating();
        rating /= ratings.size();
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }


}
