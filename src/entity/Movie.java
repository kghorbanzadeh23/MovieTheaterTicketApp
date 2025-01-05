// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Movie class which is used to store information about a movie.

package entity;

public class Movie {
    private int movieId;
    private String name;
    private String genre;
    private int year;
    private String director;
    private float duration;
    private float rating;
    private String code;
    private float price;
    private String description;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE MOVIE WITH THE GIVEN ID, NAME, GENRE, AND DURATION.
     * @param movieId THE ID OF THE MOVIE
     * @param name THE NAME OF THE MOVIE
     * @param genre THE GENRE OF THE MOVIE
     * @param duration THE DURATION OF THE MOVIE
     */
    public Movie(int movieId, String name, String genre, float duration) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * CONSTRUCTOR THAT INITIALIZES THE MOVIE WITH THE GIVEN DETAILS.
     * @param movieId THE ID OF THE MOVIE
     * @param name THE NAME OF THE MOVIE
     * @param genre THE GENRE OF THE MOVIE
     * @param year THE YEAR OF THE MOVIE
     * @param director THE DIRECTOR OF THE MOVIE
     * @param duration THE DURATION OF THE MOVIE
     * @param rating THE RATING OF THE MOVIE
     * @param code THE CODE OF THE MOVIE
     * @param price THE PRICE OF THE MOVIE
     * @param description THE DESCRIPTION OF THE MOVIE
     */
    public Movie(int movieId, String name, String genre, int year, String director, float duration, float rating, String code, float price, String description) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.code = code;
        this.price = price;
        this.description = description;
    }

    /**
     * GETS THE ID OF THE MOVIE.
     * @return THE ID OF THE MOVIE
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * SETS THE ID OF THE MOVIE.
     * @param movieId THE ID OF THE MOVIE
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    /**
     * GETS THE NAME OF THE MOVIE.
     * @return THE NAME OF THE MOVIE
     */
    public String getName() {
        return name;
    }

    /**
     * SETS THE NAME OF THE MOVIE.
     * @param name THE NAME OF THE MOVIE
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GETS THE GENRE OF THE MOVIE.
     * @return THE GENRE OF THE MOVIE
     */
    public String getGenre() {
        return genre;
    }

    /**
     * SETS THE GENRE OF THE MOVIE.
     * @param genre THE GENRE OF THE MOVIE
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * GETS THE YEAR OF THE MOVIE.
     * @return THE YEAR OF THE MOVIE
     */
    public int getYear() {
        return year;
    }

    /**
     * SETS THE YEAR OF THE MOVIE.
     * @param year THE YEAR OF THE MOVIE
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * GETS THE DIRECTOR OF THE MOVIE.
     * @return THE DIRECTOR OF THE MOVIE
     */
    public String getDirector() {
        return director;
    }

    /**
     * SETS THE DIRECTOR OF THE MOVIE.
     * @param director THE DIRECTOR OF THE MOVIE
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * GETS THE DURATION OF THE MOVIE.
     * @return THE DURATION OF THE MOVIE
     */
    public float getDuration() {
        return duration;
    }

    /**
     * SETS THE DURATION OF THE MOVIE.
     * @param duration THE DURATION OF THE MOVIE
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * GETS THE RATING OF THE MOVIE.
     * @return THE RATING OF THE MOVIE
     */
    public float getRating() {
        return rating;
    }

    /**
     * SETS THE RATING OF THE MOVIE.
     * @param rating THE RATING OF THE MOVIE
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * GETS THE CODE OF THE MOVIE.
     * @return THE CODE OF THE MOVIE
     */
    public String getCode() {
        return code;
    }

    /**
     * SETS THE CODE OF THE MOVIE.
     * @param code THE CODE OF THE MOVIE
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * GETS THE PRICE OF THE MOVIE.
     * @return THE PRICE OF THE MOVIE
     */
    public float getPrice() {
        return price;
    }

    /**
     * SETS THE PRICE OF THE MOVIE.
     * @param price THE PRICE OF THE MOVIE
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * GETS THE DESCRIPTION OF THE MOVIE.
     * @return THE DESCRIPTION OF THE MOVIE
     */
    public String getDescription() {
        return description;
    }

    /**
     * SETS THE DESCRIPTION OF THE MOVIE.
     * @param description THE DESCRIPTION OF THE MOVIE
     */
    public void setDescription(String description) {
        this.description = description;
    }
}