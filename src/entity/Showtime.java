// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Showtime class which is used to store information about a showtime.

package entity;

public class Showtime {
    private int showtimeId;
    private int movieId;
    private Movie movie;
    private String time;  
    private Theatre theatre;
    private Date date;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE SHOWTIME WITH THE GIVEN DETAILS.
     * @param showtimeId THE ID OF THE SHOWTIME
     * @param movieId THE ID OF THE MOVIE
     * @param movie THE MOVIE OBJECT
     * @param theatre THE THEATRE OBJECT
     * @param date THE DATE OF THE SHOWTIME
     * @param time THE TIME OF THE SHOWTIME
     */
    public Showtime(int showtimeId, int movieId, Movie movie, Theatre theatre, Date date, String time) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.time = time;
    }

    /**
     * GETS THE ID OF THE SHOWTIME.
     * @return THE ID OF THE SHOWTIME
     */
    public int getShowtimeId() {
        return showtimeId;
    }

    /**
     * SETS THE ID OF THE SHOWTIME.
     * @param showtimeId THE ID OF THE SHOWTIME
     */
    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
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
     * GETS THE MOVIE OBJECT.
     * @return THE MOVIE OBJECT
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * SETS THE MOVIE OBJECT.
     * @param movie THE MOVIE OBJECT
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * GETS THE THEATRE OBJECT.
     * @return THE THEATRE OBJECT
     */
    public Theatre getTheatre() {
        return theatre;
    }

    /**
     * SETS THE THEATRE OBJECT.
     * @param theatre THE THEATRE OBJECT
     */
    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    /**
     * GETS THE DATE OF THE SHOWTIME.
     * @return THE DATE OF THE SHOWTIME
     */
    public Date getDate() {
        return date;
    }

    /**
     * SETS THE DATE OF THE SHOWTIME.
     * @param date THE DATE OF THE SHOWTIME
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * GETS THE TIME OF THE SHOWTIME.
     * @return THE TIME OF THE SHOWTIME
     */
    public String getTime() {
        return time;
    }

    /**
     * SETS THE TIME OF THE SHOWTIME.
     * @param time THE TIME OF THE SHOWTIME
     */
    public void setTime(String time) {
        this.time = time;
    }
}