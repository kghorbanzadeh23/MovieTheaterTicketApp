// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Theatre class which is used to store information about a theatre.

package entity;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int theatreId;
    private String location;
    private List<Movie> movies;  // List to hold movies for this theatre

    /**
     * CONSTRUCTOR THAT INITIALIZES THE THEATRE WITH THE GIVEN ID, LOCATION, AND MOVIES.
     * @param theatreId THE ID OF THE THEATRE
     * @param location THE LOCATION OF THE THEATRE
     * @param movies THE LIST OF MOVIES FOR THE THEATRE
     */
    public Theatre(int theatreId, String location, List<Movie> movies) {
        this.theatreId = theatreId;
        this.location = location;
        this.movies = movies;
    }

    /**
     * CONSTRUCTOR THAT INITIALIZES THE THEATRE WITH THE GIVEN ID AND LOCATION.
     * @param theatreId THE ID OF THE THEATRE
     * @param location THE LOCATION OF THE THEATRE
     */
    public Theatre(int theatreId, String location) {
        this.theatreId = theatreId;
        this.location = location;
        this.movies = new ArrayList<>(); // Initialize an empty list if no movies are provided
    }

    /**
     * GETS THE ID OF THE THEATRE.
     * @return THE ID OF THE THEATRE
     */
    public int getTheatreId() {
        return theatreId;
    }

    /**
     * SETS THE ID OF THE THEATRE.
     * @param theatreId THE ID OF THE THEATRE
     */
    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    /**
     * GETS THE LOCATION OF THE THEATRE.
     * @return THE LOCATION OF THE THEATRE
     */
    public String getLocation() {
        return location;
    }

    /**
     * SETS THE LOCATION OF THE THEATRE.
     * @param location THE LOCATION OF THE THEATRE
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * GETS THE LIST OF MOVIES FOR THE THEATRE.
     * @return THE LIST OF MOVIES FOR THE THEATRE
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * SETS THE LIST OF MOVIES FOR THE THEATRE.
     * @param movies THE LIST OF MOVIES FOR THE THEATRE
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}