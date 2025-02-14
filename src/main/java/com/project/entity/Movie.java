package com.project.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name = "director")
    private String director;
    
    @Column(name = "release_year")
    private int releaseYear;
    
    @Column(name = "rating")
    private double rating;

    // Constructors
    public Movie() {}

    public Movie(String title, String genre, String director, int releaseYear, double rating) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + 
               ", director=" + director + ", releaseYear=" + releaseYear + ", rating=" + rating + "]";
    }
}
