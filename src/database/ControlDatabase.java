// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the ControlDatabase class, responsible for saving data to the database for the movie theatre application.

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.*;
import entity.Date;

public class ControlDatabase {
    private static ControlDatabase instance;
    private Map<Integer, UserBankInfo> bankInfoMap;
    private Map<Integer, Movie> moviesMap;
    private Map<Integer, UserRegistered> registeredUsersMap;
    private Map<Integer, ScreeningRoom> screeningRoomsMap;
    private Map<Integer, Receipt> receiptMap;
    private Map<Integer, Showtime> showtimeMap;
    private Map<Integer, Theatre> theatreMap;
    private Map<String, Ticket> ticketMap;

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/movie_theatre_app";  // Updated to match exact database name
    private static final String USER = "admin";      // From SQL file
    private static final String PASSWORD = "admin_pass";  // From SQL file

    // Method to get the connection
    public static Connection getConnection() throws SQLException {
        return new ReadDatabase().getConnection();
    }


    public static ControlDatabase getInstance() {
        if (instance == null) {
            instance = new ControlDatabase();
        }
        return instance;
    }

    public ControlDatabase() {
        this.bankInfoMap = new HashMap<>();
        this.moviesMap = new HashMap<>();
        this.registeredUsersMap = new HashMap<>();
        this.screeningRoomsMap = new HashMap<>();
        this.receiptMap = new HashMap<>();
        this.showtimeMap = new HashMap<>();
        this.theatreMap = new HashMap<>();
        this.ticketMap = new HashMap<>();
    }

    public UserBankInfo getBankInfo(int id) {
        return this.bankInfoMap.getOrDefault(id, null);
    }

    public Movie getMovie(int id) {
        return this.moviesMap.getOrDefault(id, null);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(this.moviesMap.values());
    }

    public UserRegistered getUserRegistered(int id) {
        return this.registeredUsersMap.getOrDefault(id, null);
    }

    public ScreeningRoom getScreeningRoom(int id) {
        return this.screeningRoomsMap.getOrDefault(id, null);
    }

    public Map<Integer, UserRegistered> getAllRegisteredUsers() {
        return this.registeredUsersMap;
    }

    public Receipt getReceipt(int id) {
        return this.receiptMap.getOrDefault(id, null);
    }

    public Theatre getTheatre(int id) {
        return this.theatreMap.getOrDefault(id, null);
    }

    public Set<Integer> getMovieIDs() {
        return this.moviesMap.keySet();
    }

    public Set<Integer> getRegisteredUserIDs() {
        return this.registeredUsersMap.keySet();
    }

    public void addBankInfo(UserBankInfo bankInfo) {
        this.bankInfoMap.put(bankInfo.getBankInfoID(), bankInfo);
    }

    public void addMovie(Movie movie) {
        this.moviesMap.put(movie.getMovieId(), movie);
    }

    public void addRegisteredUser(UserRegistered user) {
        this.registeredUsersMap.put(user.getUserID(), user);
    }

    public void addTheatre(Theatre theatre) {
        this.theatreMap.put(theatre.getTheatreId(), theatre);
    }

    public void addScreeningRoom(ScreeningRoom room) {
        this.screeningRoomsMap.put(room.getRoomId(), room);
    }

    public void updateMovie(int movieId, String movieName, String genre, float duration) {
        Movie movie = getMovie(movieId);
        movie.setName(movieName);
        movie.setGenre(genre);
        movie.setDuration(duration);
    }

    public void removeMovie(int movieId) {
        moviesMap.remove(movieId);
    }

    // Fetch all movies from the database and add them to the movies map
    public void fetchAllMovies() {
        String query = "SELECT * FROM MOVIE"; // SQL query to fetch all movies
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int movieId = rs.getInt("ID_no");
                String name = rs.getString("Movie_Name");
                String genre = rs.getString("Genre");
                int year = rs.getInt("Release_Year");
                String director = rs.getString("Director");
                float duration = rs.getFloat("Duration");
                float rating = rs.getFloat("Rating");
                String code = rs.getString("Movie_Code");
                float price = rs.getFloat("Movie_Price");
                String description = rs.getString("Movie_Description");

                Movie movie = new Movie(movieId, name, genre, year, director, duration, rating, code, price, description);
                this.moviesMap.put(movieId, movie); // Add movie to the map
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMoviesForTheatre(int theatreId) {
        List<Movie> theatreMovies = new ArrayList<>();
        String query = "SELECT m.ID_no, m.Movie_Name, m.Genre, m.Release_Year, m.Director, " +
                "m.Duration, m.Rating, m.Movie_Code, m.Movie_Price, m.Movie_Description " +
                "FROM MOVIE m " +
                "JOIN THEATRE_MOVIE tm ON m.ID_no = tm.Movie_ID " +
                "WHERE tm.Theatre_ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, theatreId); // Bind the theatre ID

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int movieId = rs.getInt("ID_no");
                    String name = rs.getString("Movie_Name");
                    String genre = rs.getString("Genre");
                    int year = rs.getInt("Release_Year");
                    String director = rs.getString("Director");
                    float duration = rs.getFloat("Duration");
                    float rating = rs.getFloat("Rating");
                    String code = rs.getString("Movie_Code");
                    float price = rs.getFloat("Movie_Price");
                    String description = rs.getString("Movie_Description");

                    // Create and add a Movie object to the list
                    Movie movie = new Movie(movieId, name, genre, year, director, duration, rating, code, price, description);
                    theatreMovies.add(movie);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the SQL error
        }
        return theatreMovies;
    }

    public void fetchAllTheatres() {
        if (this.theatreMap.isEmpty()) { // Only fetch if the map is empty to avoid redundant calls
            String query = "SELECT * FROM THEATRE";
            try (Connection conn = getConnection(); // Get the database connection
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int theatreId = rs.getInt("ID_no");
                    String location = rs.getString("Theatre_Name");
                    List<Movie> movies = getMoviesForTheatre(theatreId); // Fetch movies for the theatre

                    Theatre theatre = new Theatre(theatreId, location, movies); // Create a Theatre object
                    this.theatreMap.put(theatreId, theatre); // Add it to the map
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log SQL errors
            }
        }
    }

    public List<Theatre> getAllTheatres() {
        if (this.theatreMap.isEmpty()) { // Populate map if it's empty
            String query = "SELECT * FROM THEATRE";
            try (Connection conn = getConnection(); // Use your getConnection() method
                 Statement stmt = conn.createStatement(); // Create a statement object
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int theatreId = rs.getInt("ID_no");
                    String location = rs.getString("Theatre_Name");
                    List<Movie> movies = getMoviesForTheatre(theatreId);
                    Theatre theatre = new Theatre(theatreId, location, movies);
                    this.theatreMap.put(theatreId, theatre);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(this.theatreMap.values());
    }

    // Add a showtime to the showtime map
    public void addShowtime(Showtime showtime) {
        showtimeMap.put(showtime.getShowtimeId(), showtime);
    }

    // Update an existing showtime
    public void updateShowtime(Showtime showtime) {
        showtimeMap.put(showtime.getShowtimeId(), showtime);
    }

    // Remove a showtime from the map
    public void removeShowtime(Showtime showtime) {
        showtimeMap.remove(showtime.getShowtimeId());
    }

    public ArrayList<Showtime> fetchShowtimesForMovieAndTheatre(int movieId, int theatreId) {
        ArrayList<Showtime> showtimes = new ArrayList<>();
        try {
            String query = "SELECT s.* FROM SHOWS s " +
                    "JOIN SCREENING_ROOM sr ON s.Screening_Room = sr.ID_no " +
                    "WHERE s.Movie_ID = ? AND sr.Theatre_Number = ?";

            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, movieId);
                stmt.setInt(2, theatreId);

                System.out.println("Fetching showtimes for Movie ID: " + movieId + ", Theatre ID: " + theatreId); // Debug log

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int showtimeId = rs.getInt("ID_no");
                    String time = rs.getString("Showtime");
                    String dateStr = rs.getString("Showdate");

                    Date date = new Date();
                    date.setDay(Integer.parseInt(dateStr.substring(0, 2)));
                    date.setMonth(Integer.parseInt(dateStr.substring(3, 5)));
                    date.setYear(Integer.parseInt(dateStr.substring(6)));
                    Movie movie = getMovie(movieId);
                    Theatre theatre = getTheatre(theatreId);

                    Showtime show = new Showtime(showtimeId, movieId, movie, theatre, date, time);
                    showtimes.add(show);

                    System.out.println("Found showtime: " + time); // Debug log
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showtimes;
    }

    public Showtime getShowtime(int showtimeId) {
        return showtimeMap.getOrDefault(showtimeId, null);
    }

    // Method to fetch a movie by its ID
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            String query = "SELECT * FROM MOVIE WHERE ID_no = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, movieId);

            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("Movie_Name");
                String genre = rs.getString("Genre");
                int releaseYear = rs.getInt("Release_Year");
                String director = rs.getString("Director");
                float duration = rs.getFloat("Duration");
                float rating = rs.getFloat("Rating");
                String movieCode = rs.getString("Movie_Code");
                float price = rs.getFloat("Movie_Price");
                String description = rs.getString("Movie_Description");

                movie = new Movie(movieId, name, genre, releaseYear, director, duration, rating, movieCode, price, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return movie;
    }

    // Method to fetch a theatre by its ID
    public Theatre getTheatreById(int theatreId) {
        Theatre theatre = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            String query = "SELECT * FROM THEATRE WHERE ID_no = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, theatreId);

            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("Theatre_Name");

                theatre = new Theatre(theatreId, name); // Assuming Theatre has a constructor like this
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return theatre;
    }


    // Utility method to convert SQL Date to Entity Date
    public static Date convertSqlDateToEntityDate(java.sql.Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        // Extract year, month, and day from java.sql.Date
        int year = sqlDate.getYear() + 1900; // getYear() returns years since 1900
        int month = sqlDate.getMonth() + 1;  // getMonth() returns months from 0-11
        int day = sqlDate.getDate();

        // Return a new custom Date object
        return new Date(day, month, year);
    }

    // Method to get bank info by ID
    public UserBankInfo getUserBankInfoById(int bankInfoId) {
        UserBankInfo bankInfo = null;
        String query = "SELECT * FROM BANK_INFO WHERE ID_no = ?";  // Adjust the table name if necessary

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bankInfoId);  // Set the bankInfoID parameter

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retrieve data from result set
                String cardNumber = rs.getString("Card_Number");
                String cardHolder = rs.getString("Card_Holder");
                Date expiryDate = new Date();
                String date = rs.getString("Expiry_Date");// Assuming you have a Date object in the database
                expiryDate.setMonth(Integer.valueOf(date.substring(0, 2)));
                expiryDate.setYear(Integer.valueOf(date.substring(3)));
                int cvv = rs.getInt("CVV");

                // Create a new UserBankInfo object using the retrieved data
                bankInfo = new UserBankInfo(bankInfoId, cardNumber, cardHolder, expiryDate, cvv);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL errors
        }
        return bankInfo;
    }

    public UserRegistered getUserRegisteredByEmail(String email) {
        UserRegistered user = null;
        String query = "SELECT * FROM REGISTERED_USER WHERE User_Email = ?";  // SQL query

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);  // Set the email parameter

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retrieve data from result set
                int userId = rs.getInt("ID_no");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                String userEmail = rs.getString("User_Email");
                String password = rs.getString("User_Password");

                // Retrieve bank info ID and create a dummy UserBankInfo object (assuming bank info is stored separately)
                int bankInfoId = rs.getInt("User_Bank_Info");
                UserBankInfo bankInfo = getUserBankInfoById(bankInfoId); // Assuming method to fetch bank info from the database

                // Create a Date object from the User's join date info
                Date dateJoined = new Date(rs.getInt("User_Day"), rs.getInt("User_Month"), rs.getInt("User_Year"));

                // Now create the UserRegistered object with the new constructor
                user = new UserRegistered(userId, firstName + " " + lastName, userEmail, password, bankInfo, dateJoined);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());; // Handle SQL errors
        }
        return user;
    }

    public void addTicket(Ticket ticket) {
        ticketMap.put(ticket.getTicketID(), ticket);

    }

    public void bookTicket(Ticket ticket) {
        ticketMap.put(ticket.getTicketID(), ticket);
        System.out.println(ticket.getTicketID() + " has been booked (Seat: "
                + ticket.getSeat() + "), for "
                + ticket.getShowtime().getMovie().getName()
                + " at " + ticket.getShowtime().getTime()
                + " at " + ticket.getShowtime().getTheatre().getLocation());
        new WriteDatabase().saveTickets();
    }

    public ArrayList<Ticket> getTicketsForShowtime(Showtime showtime) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (ticketMap.isEmpty()) {
            new ReadDatabase().populateDatabase();
        }
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getShowtime().getShowtimeId() == showtime.getShowtimeId()) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public ArrayList<Ticket> getAllTickets() {
        return new ArrayList<>(ticketMap.values());
    }

    public void removeTicket(Ticket ticket) {
        ticketMap.remove(ticket.getTicketID());
        new WriteDatabase().removeTicket(ticket);
    }
}