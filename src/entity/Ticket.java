// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Ticket class which is used to store information about a ticket.

package entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Ticket {
    private String ticketID;
    private Showtime showtime;
    private String seat;
    private Movie movie;
    private Theatre theatre;
    private Date date;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE TICKET WITH THE GIVEN DETAILS.
     * @param ticketID THE ID OF THE TICKET
     * @param movie THE MOVIE OBJECT
     * @param theatre THE THEATRE OBJECT
     * @param date THE DATE OF THE TICKET
     * @param showtime THE SHOWTIME OBJECT
     * @param seat THE SEAT OF THE TICKET
     */
    public Ticket(String ticketID, Movie movie, Theatre theatre, Date date, Showtime showtime, String seat) {
        this.ticketID = ticketID;
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.showtime = showtime;
        this.seat = seat;
    }

    /**
     * CONSTRUCTOR THAT INITIALIZES THE TICKET WITH THE GIVEN DETAILS.
     * @param movie THE MOVIE OBJECT
     * @param theatre THE THEATRE OBJECT
     * @param date THE DATE OF THE TICKET
     * @param showtime THE SHOWTIME OBJECT
     * @param seat THE SEAT OF THE TICKET
     */
    public Ticket(Movie movie, Theatre theatre, Date date, Showtime showtime, String seat) {
        this.ticketID = new Random().nextInt(5) + "";
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.showtime = showtime;
        this.seat = seat;
    }

    /**
     * GETS THE ID OF THE TICKET.
     * @return THE ID OF THE TICKET
     */
    public String getTicketID() {
        return ticketID;
    }

    /**
     * SETS THE ID OF THE TICKET.
     * @param ticketID THE ID OF THE TICKET
     */
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * GETS THE SHOWTIME OBJECT.
     * @return THE SHOWTIME OBJECT
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * SETS THE SHOWTIME OBJECT.
     * @param showtime THE SHOWTIME OBJECT
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    /**
     * GETS THE SEAT OF THE TICKET.
     * @return THE SEAT OF THE TICKET
     */
    public String getSeat() {
        return seat;
    }

    /**
     * SETS THE SEAT OF THE TICKET.
     * @param seat THE SEAT OF THE TICKET
     */
    public void setSeat(String seat) {
        this.seat = seat;
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
     * GETS THE DATE OF THE TICKET.
     * @return THE DATE OF THE TICKET
     */
    public Date getDate() {
        return date;
    }

    /**
     * SETS THE DATE OF THE TICKET.
     * @param date THE DATE OF THE TICKET
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * CHECKS IF THE TICKET CAN BE CANCELED.
     * @return TRUE IF THE TICKET CAN BE CANCELED, FALSE OTHERWISE
     */
    public boolean canCancel() {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
        Date date1 = showtime.getDate();
        String dayString = date1.getDay() < 10 ? "0" + date1.getDay() : date1.getDay() + "";
        String dateTime = date1.getYear() + "-" + date1.getMonth() + "-" + dayString + ":" + showtime.getTime();
        LocalDateTime showtimeLocalDateTime = LocalDateTime.parse(dateTime, formatter);
        ZonedDateTime showtimeDateTime = showtimeLocalDateTime.atZone(ZoneId.systemDefault());
        Duration duration = Duration.between(currentDateTime, showtimeDateTime);
        return duration.toHours() >= 72;
    }
}