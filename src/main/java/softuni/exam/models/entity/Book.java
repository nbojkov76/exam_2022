package softuni.exam.models.entity;

import javax.persistence.*;
import java.io.StringReader;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    private String title;

    private String author;

    private String description;

    private boolean available;

    private Genre genre;

    private double rating;

    private Set<BorrowingRecord> records;

    public Book() {
    }

    @Column(unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Column(nullable = false)
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
@OneToMany(mappedBy = "book")
    public Set<BorrowingRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<BorrowingRecord> records) {
        this.records = records;
    }
}


