package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Genre;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookSeedDto {
@Expose
    private String author;
@Expose
    private boolean available;
@Expose
    private String description;
@Expose
    private String genre;
@Expose
    private String title;
@Expose
    private double rating;
@Size(min = 3,max = 40)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
@Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
@Size(min = 3,max = 40)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
@Positive
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
