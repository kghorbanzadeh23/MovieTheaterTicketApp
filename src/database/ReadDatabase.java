// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the ReadDatabase class, responsible for reading data from the database 
//              and populating the ControlDatabase for the movie theatre application.

package database;

import entity.*;
import entity.Date;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadDatabase {
    private ControlDatabase controlDatabase;
    private Connection conn;

    /**
     * ESTABLISHES AND RETURNS A DATABASE CONNECTION.
     * @return The database connection
     */
    public Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        controlDatabase = ControlDatabase.getInstance();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_theatre_app?" +
            "user=admin&password=admin_pass");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
        return conn;
    }

    /**
     * POPULATES THE CONTROL DATABASE WITH DATA FROM THE DATABASE.
     */
    public void populateDatabase() {
        try {
            getMovies();
            getAnnouncements();
            getBankInfo();
            getRegisteredUsers();
            getTheatres();
            getScreeningRooms();
            getShows();
            getTickets();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
    }

    /**
     * RETRIEVES MOVIES FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getMovies() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.movie;");
        while (resultSet.next()) {
            int movieId = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String genre = resultSet.getString(3);
            int year = resultSet.getInt(4);
            String director = resultSet.getString(5);
            float duration = resultSet.getFloat(6);
            float rating = resultSet.getFloat(7);
            String code = resultSet.getString(8);
            float price = resultSet.getFloat(9);
            String description = resultSet.getString(10);
            Movie movie = new Movie(movieId, title, genre, year, director, duration, rating, code, price, description);
            ControlDatabase.getInstance().addMovie(movie);
        }
    }

    /**
     * RETRIEVES ANNOUNCEMENTS FROM THE DATABASE.
     * NOTE: Currently, the Announcement class is missing and needs to be added.
     */
    private void getAnnouncements() throws SQLException {
        this.controlDatabase = ControlDatabase.getInstance();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.announcements;");
        while (resultSet.next()) {
            int announcementID = resultSet.getInt(1);
            int privateDay = resultSet.getInt(2);
            int privateMonth = resultSet.getInt(3);
            int privateYear = resultSet.getInt(4);
            int publicDay = resultSet.getInt(5);
            int publicMonth = resultSet.getInt(6);
            int publicYear = resultSet.getInt(7);
            int movieID = resultSet.getInt(8);
            // TODO: Add logic for handling Announcement objects once the class is implemented.
        }
    }

    /**
     * RETRIEVES BANK INFORMATION FROM THE DATABASE AND ADDS IT TO THE CONTROL DATABASE.
     */
    private void getBankInfo() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.bank_info;");
        while (resultSet.next()) {
            int bankInfoID = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String cardNumber = resultSet.getString(4);
            UserBankInfo info = new UserBankInfo(bankInfoID, cardNumber,
                    firstName + " " + lastName, new Date(0, 6, 2028), 123);
            ControlDatabase.getInstance().addBankInfo(info);
        }
    }

    /**
     * RETRIEVES REGISTERED USERS FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getRegisteredUsers() throws SQLException {
        this.controlDatabase = ControlDatabase.getInstance();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.registered_user;");
        while (resultSet.next()) {
            int userID = resultSet.getInt(1);
            String password = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String email = resultSet.getString(5);
            int bankID = resultSet.getInt(6);
            int day = resultSet.getInt(7);
            int month = resultSet.getInt(8);
            int year = resultSet.getInt(9);
            String name = firstName + " " + lastName;
            Date registrationDate = new Date(day, month, year);
            UserRegistered registered = new UserRegistered(userID, name, email, password, controlDatabase.getBankInfo(bankID), registrationDate);
            controlDatabase.addRegisteredUser(registered);
        }
    }

    /**
     * RETRIEVES THEATRES FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getTheatres() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.theatre;");
        while (resultSet.next()) {
            int theatreId = resultSet.getInt(1);
            String location = resultSet.getString(2);
            Theatre theatre = new Theatre(theatreId, location, new ArrayList<Movie>());
            controlDatabase.addTheatre(theatre);
        }
    }

    /**
     * RETRIEVES SCREENING ROOMS FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getScreeningRooms() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.screening_room;");
        while (resultSet.next()) {
            int roomID = resultSet.getInt(1);
            int rows = resultSet.getInt(2);
            int columns = resultSet.getInt(3);
            int theatreID = resultSet.getInt(4);
            ScreeningRoom room = new ScreeningRoom(roomID, rows, columns);
            room.setTheatre(controlDatabase.getTheatre(theatreID)); // Associate room with the correct theater
            controlDatabase.addScreeningRoom(room);
        }
    }

    /**
     * RETRIEVES SHOWTIMES FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getShows() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.shows;");
        while (resultSet.next()) {
            int showID = resultSet.getInt(1);
            int movieID = resultSet.getInt(2);
            Movie movie = controlDatabase.getMovie(movieID);
            int roomID = resultSet.getInt(3);

            String showtime = resultSet.getString(4);
            String dateStr = resultSet.getString("Showdate");

            Date date = new Date();
            date.setDay(Integer.parseInt(dateStr.substring(0, 2)));
            date.setMonth(Integer.parseInt(dateStr.substring(3, 5)));
            date.setYear(Integer.parseInt(dateStr.substring(6)));

            Showtime show = new Showtime(
                showID,
                movieID,
                movie,
                controlDatabase.getScreeningRoom(roomID).getTheatre(),
                date,
                showtime
            );

            ControlDatabase.getInstance().addShowtime(show);
        }
    }

    /**
     * RETRIEVES TICKETS FROM THE DATABASE AND ADDS THEM TO THE CONTROL DATABASE.
     */
    private void getTickets() throws SQLException {
        conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_theatre_app.tickets;");
        while (resultSet.next()) {
            String ticketID = resultSet.getString(1);
            int showID = resultSet.getInt(2);
            int userID = resultSet.getInt(3);
            String seatcode = resultSet.getString(4);
            Showtime showtime = controlDatabase.getShowtime(showID);

            String[] seatParts = seatcode.split("(?<=\\D)(?=\\d)");
            int row = Integer.parseInt(seatParts[1]);
            char colLetter = seatParts[0].charAt(0);
            int col = colLetter - 'A' + 1;
            String seat = String.format("Row %d Seat %d", row, col);

            Ticket ticket = new Ticket(ticketID, showtime.getMovie(), showtime.getTheatre(), showtime.getDate(), showtime, seat);
            ControlDatabase.getInstance().addTicket(ticket);
        }
    }
}
    
