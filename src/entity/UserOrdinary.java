// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the UserOrdinary class which is used to store information about an ordinary user.

package entity;
import java.util.Vector;


public class UserOrdinary {
    private String name;
    private String email;
    private String password;
    private int userID;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE USER WITH THE GIVEN USER ID, NAME, EMAIL, AND PASSWORD.
     * @param userID THE ID OF THE USER
     * @param name THE NAME OF THE USER
     * @param email THE EMAIL OF THE USER
     * @param password THE PASSWORD OF THE USER
     */
    public UserOrdinary(int userID, String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

     /**
     * SEARCHES FOR MOVIES BASED ON THE GIVEN QUERY.
     * @param query THE SEARCH QUERY
     * @return A VECTOR OF MOVIES THAT MATCH THE QUERY
     */
    public Vector<Movie> searchMovie(String query){
      
        Vector<Movie> movies = new Vector<>();
        
        System.out.println("Searching for movies with low access for ordinary users with query: " + query);
        return movies;
    }

    /**
     * SELECTS A MOVIE.
     * @param movie THE MOVIE TO BE SELECTED
     */
    public void selectMovie(Movie movie){
   
        System.out.println("Selected movie: " + movie.getName());
    }

    /**
     * SELECTS A THEATER.
     * @param theater THE THEATER TO BE SELECTED
     */
    public void selectTheater(Theatre theater){
       
        System.out.println("Selected theater: " + theater.getLocation());
    }

    /**
     * VIEWS SHOWTIMES.
     * @return A VECTOR OF SHOWTIMES
     */
    public Vector<Showtime> viewShowtimes(){
       
        Vector<Showtime> showtimes = new Vector<>();
     
        System.out.println("Viewing showtimes.");
        return showtimes;
    }

    /**
     * SELECTS A SHOWTIME.
     * @param showtime THE SHOWTIME TO BE SELECTED
     */
    public void selectShowtime(Showtime showtime){
        
        System.out.println("Selected showtime: " + showtime.getTime());
    }

    /**
     * VIEWS AVAILABLE SEATS.
     * @return A VECTOR OF SEATS
     */
    public Vector<Seat> viewSeats(){
        Vector<Seat> seats = new Vector<>();
        
        System.out.println("Viewing seats.");
        return seats;
    }

    /**
     * SELECTS A SEAT.
     * @param seat THE SEAT TO BE SELECTED
     */
    public void selectSeat(Seat seat){
    
        System.out.println("Selected seat: " + seat.getSeatId());
    }

    /**
     * MAKES A PAYMENT.
     * @param payment THE PAYMENT TO BE MADE
     */
    public void makePayment(Payment payment){
        /// will have to prompt user for bank info and create vaninfo object
        System.out.println("Payment made with amount: " + payment.getAmount());
    }

    /**
     * VIEWS BOOKING DETAILS.
     */
    public void viewBooking(){

        System.out.println("Viewing booking details.");
    }

    /**
     * CANCELS A BOOKING.
     * @param bookingId THE ID OF THE BOOKING TO BE CANCELED
     */
    public void cancelBooking(int bookingId) {
        
        System.out.println("Booking cancelled with 85% refund.");
    }

    /**
     * GETS THE NAME OF THE USER.
     * @return THE NAME OF THE USER
     */
    public String getName() {
        return name;
    }

    /**
     * SETS THE NAME OF THE USER.
     * @param name THE NAME OF THE USER
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GETS THE EMAIL OF THE USER.
     * @return THE EMAIL OF THE USER
     */
    public String getEmail() {
        return email;
    }

    /**
     * SETS THE EMAIL OF THE USER.
     * @param email THE EMAIL OF THE USER
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * GETS THE PASSWORD OF THE USER.
     * @return THE PASSWORD OF THE USER
     */
    public String getPassword() {
        return password;
    }

    /**
     * SETS THE PASSWORD OF THE USER.
     * @param password THE PASSWORD OF THE USER
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * GETS THE ID OF THE USER.
     * @return THE ID OF THE USER
     */
    public int getUserID() {
        return userID;
    }

    /**
     * SETS THE ID OF THE USER.
     * @param userID THE ID OF THE USER
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}