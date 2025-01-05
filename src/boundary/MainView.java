// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the MainView class, responsible for displaying the main view for movie selection, ticket booking, and navigation for the movie theatre application.

package boundary;

import javax.swing.*;

import controller.InstanceController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.*;
import database.ControlDatabase;

import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainView extends JPanel {
    private JComboBox<String> theaterSelector; // Add a JComboBox for theater selection
    private JTextField searchedMovie;
    private JComboBox<String> movieSelector;
    private JComboBox<String> showtimeSelector;
    private JSpinner ticketQuantity;
    private JButton viewCartButton;
    private JButton backButton;
    private JLabel priceLabel;
    private JButton selectSeatsButton;  // Add this field
    private JFrame parentFrame;
    private Map<String, Movie> movieMap; // Map to store movie names and corresponding Movie objects
    private Map<String, Theatre> theatreMap;
    private Map<String, Showtime> showtimeMap; 
    private JButton viewAccountDetailsButton;
    private JButton refundTicketButton;

    /**
     * CONSTRUCTOR FOR MainView.
     * Initializes the main view with the given parent frame.
     * @param parent The parent JFrame
     */
    public MainView(JFrame parent) {
        this.parentFrame = parent;
        this.movieMap = new HashMap<>(); // Initialize the map
        this.theatreMap = new HashMap<>();
        this.showtimeMap = new HashMap<>();
        setLayout(new GridBagLayout());
        initializeComponents();
        loadMoviesFromDatabase(); // Fetch movies from the database
        loadTheatersFromDatabase(); // Fetch theaters from the database
        setupActionListeners();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE MAIN VIEW.
     * Sets up the form fields, labels, and buttons.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Movie Selection");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Theater Selection
        JLabel theaterLabel = new JLabel("Select Theater:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(theaterLabel, gbc);

        theaterSelector = new JComboBox<>();
        gbc.gridx = 1;
        add(theaterSelector, gbc);

        // Initially hide movie selection, showtime, and ticket quantity
        JLabel movieSearchLabel = new JLabel("Search for a movie:");
        searchedMovie = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(movieSearchLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        add(searchedMovie, gbc);

        JLabel movieLabel = new JLabel("Select Movie:");
        gbc.gridy = 3;
        gbc.gridx = 0;
        movieSelector = new JComboBox<>();
        movieSelector.setEnabled(false);  // Initially disable movieSelector
        add(movieLabel, gbc);
        gbc.gridx = 1;
        add(movieSelector, gbc);

        JLabel showtimeLabel = new JLabel("Select Showtime:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        showtimeSelector = new JComboBox<>();
        showtimeSelector.setEnabled(false);  // Initially disable showtimeSelector
        add(showtimeLabel, gbc);
        gbc.gridx = 1;
        add(showtimeSelector, gbc);

        // Ticket Quantity
        JLabel quantityLabel = new JLabel("Number of Tickets:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        ticketQuantity = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        ticketQuantity.setEnabled(false);  // Initially disable ticketQuantity
        add(quantityLabel, gbc);
        gbc.gridx = 1;
        add(ticketQuantity, gbc);

        // Add Select Seats button below ticket quantity
        selectSeatsButton = new JButton("Select Seats");
        selectSeatsButton.setEnabled(false); // Initially disabled
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(selectSeatsButton, gbc);

        // Price label moved down one row
        priceLabel = new JLabel("Price per ticket: $12.00");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(priceLabel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
    
        viewCartButton = new JButton("View Cart");
        backButton = new JButton("Return to Login Page");
        viewAccountDetailsButton = new JButton("View Account Details");
        refundTicketButton = new JButton("Refund Ticket");
        buttonPanel.add(viewCartButton);
        buttonPanel.add(backButton);
        buttonPanel.add(refundTicketButton);
        if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
            buttonPanel.add(viewAccountDetailsButton);
        }

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    /**
     * LOADS MOVIES FROM THE DATABASE.
     * Fetches movies from the database and populates the movie selector.
     */
    private void loadMoviesFromDatabase() {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        database.fetchAllMovies(); // Fetch movies from the database
    
        ArrayList<Movie> movies = new ArrayList<>(database.getAllMovies());
        movieSelector.removeAllItems(); // Clear existing items in the dropdown
        movieMap.clear(); // Clear the movie map to avoid stale data
    
        for (Movie movie : movies) {
            movieSelector.addItem(movie.getName()); // Add movie names to the dropdown
            movieMap.put(movie.getName(), movie); // Store the mapping between name and movie object
        }
    
        updatePriceLabel(); // Set the initial price label
    }

    /**
     * LOADS THEATERS FROM THE DATABASE.
     * Fetches theaters from the database and populates the theater selector.
     */
    private void loadTheatersFromDatabase() {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        database.fetchAllTheatres(); // Fetch theatres from the database
    
        ArrayList<Theatre> theatres = new ArrayList<>(database.getAllTheatres());
        theaterSelector.removeAllItems(); // Clear existing items in the dropdown
        theatreMap.clear(); // Clear the map to avoid stale data
    
        for (Theatre theatre : theatres) {
            theaterSelector.addItem(theatre.getLocation()); // Add theatre location to dropdown
            theatreMap.put(theatre.getLocation(), theatre); // Map theatre name to the Theatre object
        }
    }


    // Method to load showtimes from the database
    private void loadShowtimesFromDatabase(Movie movie, Theatre theatre) {
        ControlDatabase database = ControlDatabase.getInstance(); // Get the database instance
        ArrayList<Showtime> showtimes = database.fetchShowtimesForMovieAndTheatre(movie.getMovieId(), theatre.getTheatreId());

        showtimeSelector.removeAllItems(); // Clear existing showtimes
        showtimeMap.clear(); // Clear the map to avoid stale data

        // Populate the showtime selector with available showtimes
        for (Showtime showtime : showtimes) {
            String timeText = showtime.getTime().toString(); // Will return a format like "HH:mm:ss"
            showtimeSelector.addItem(timeText);
            showtimeMap.put(timeText, showtime);
        }
        

        // Enable the showtime selector
        showtimeSelector.setEnabled(true);
    }

    private void setupActionListeners() {
        // Update movie list based on the selected theatre
        theaterSelector.addActionListener(e -> {
            updateMovieSelector();
            showtimeSelector.removeAllItems(); // Clear showtimes when theatre changes
            searchedMovie.setText("");
            showtimeSelector.setEnabled(false); // Disable until movie is selected
            ticketQuantity.setEnabled(false); // Disable until showtime is selected
        });

        searchedMovie.addActionListener(e -> {
            updateMovieSelector();
            String searchedMovieName = searchedMovie.getText();
            if (searchedMovieName != null) {
                for (Movie m : movieMap.values()) {
                    if (!m.getName().toLowerCase().contains(searchedMovieName.toLowerCase())) {
                        movieSelector.removeItem(m.getName());
                    }
                }
            }
        });
    
        // Update price label when a new movie is selected
        movieSelector.addActionListener(e -> {
            updatePriceLabel();
            String selectedMovieName = (String) movieSelector.getSelectedItem();
            String selectedTheatreName = (String) theaterSelector.getSelectedItem();
            
            if (selectedMovieName != null && selectedTheatreName != null) {
                Movie selectedMovie = movieMap.get(selectedMovieName);
                Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
                
                if (selectedMovie != null && selectedTheatre != null) {
                    // Fetch and populate showtimes
                    ArrayList<Showtime> showtimes = ControlDatabase.getInstance()
                        .fetchShowtimesForMovieAndTheatre(selectedMovie.getMovieId(), selectedTheatre.getTheatreId());
                    
                    showtimeSelector.removeAllItems();
                    showtimeMap.clear();
                    
                    for (Showtime showtime : showtimes) {
                        if (showtime != null && showtime.getTime() != null) {
                            Calendar calendar = Calendar.getInstance();
//                            calendar.setTime(showtime.getTime());
//                            String timeStr = String.format("%02d:%02d",
//                                calendar.get(Calendar.HOUR_OF_DAY),
//                                calendar.get(Calendar.MINUTE));
                            String timeStr = showtime.getTime();
                            showtimeSelector.addItem(timeStr);
                            showtimeMap.put(timeStr, showtime);
                        }
                    }
                    
                    showtimeSelector.setEnabled(!showtimes.isEmpty());
                }
            }
        });
        
        showtimeSelector.addActionListener(e -> {
            ticketQuantity.setEnabled(true);
        });

        backButton.addActionListener(e -> {
            if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
                InstanceController.getInstance().logout();
                JOptionPane.showMessageDialog(parentFrame, 
                    "Logged out successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        
            // Navigate to login view
            LoginView loginView = new LoginView(parentFrame);
            parentFrame.setContentPane(loginView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

            // Enable select seats button when quantity is selected
        ticketQuantity.addChangeListener(e -> {
                if ((Integer) ticketQuantity.getValue() > 0) {
                    selectSeatsButton.setEnabled(true);
                } else {
                    selectSeatsButton.setEnabled(false);
                }
            });

            // Select Seats button listener
        selectSeatsButton.addActionListener(e -> {
                String selectedMovieName = (String) movieSelector.getSelectedItem();
                String selectedShowtime = (String) showtimeSelector.getSelectedItem();
                int quantity = (Integer) ticketQuantity.getValue();
                
                if (selectedMovieName != null && selectedShowtime != null) {
                    Showtime selectedShow = showtimeMap.get(selectedShowtime);
                    
                    // Query SHOWS table to get screening room
                    ControlDatabase database = ControlDatabase.getInstance();
                    String query = "SELECT Screening_Room FROM SHOWS WHERE ID_no = ?";
                    
                    try (Connection conn = database.getConnection();
                         PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, selectedShow.getShowtimeId());
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            int screeningRoomId = rs.getInt("Screening_Room");
                            ScreeningRoom room = database.getScreeningRoom(screeningRoomId);
                            
                            if (room != null) {
                                Movie selectedMovie = movieMap.get(selectedMovieName);
                                SeatMapView seatMapView = new SeatMapView(parentFrame, room, quantity, selectedMovie, selectedShow);
                                parentFrame.setContentPane(seatMapView);
                                parentFrame.revalidate();
                                parentFrame.repaint();
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        
            // // Add to Cart button listener
            // addToCartButton.addActionListener(e -> {
            //     CartView cartView = new CartView(parentFrame);
            //     parentFrame.setContentPane(cartView);
            //     parentFrame.revalidate();
            //     parentFrame.repaint();
            // });
        
    
        viewAccountDetailsButton.addActionListener(e -> {
            if (InstanceController.getInstance().getUser() == null ||
                    !(InstanceController.getInstance().getUser() instanceof  UserRegistered)) {
                return;
            }
            AccountDetailsView accountDetailsView = new AccountDetailsView(parentFrame);
            parentFrame.setContentPane(accountDetailsView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        // View cart button functionality
        viewCartButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        // Refund ticket button functionality
        refundTicketButton.addActionListener(e -> {
            RefundTicketView refundTicketView = new RefundTicketView(parentFrame);
            parentFrame.setContentPane(refundTicketView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    private void updateMovieSelector() {
        // Get the selected theatre
        String selectedTheatreName = (String) theaterSelector.getSelectedItem();
        Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
    
        if (selectedTheatre != null) {
            // Fetch movies for the selected theatre from the database
            ControlDatabase database = ControlDatabase.getInstance();
            ArrayList<Movie> moviesForTheatre = new ArrayList<>(database.getMoviesForTheatre(selectedTheatre.getTheatreId()));
    
            movieSelector.removeAllItems(); // Clear existing items in the dropdown
            movieMap.clear(); // Clear the movie map to avoid stale data
    
            // Populate the movie selector with movies associated with the selected theatre
            for (Movie movie : moviesForTheatre) {
                movieSelector.addItem(movie.getName()); // Add movie names to dropdown
                movieMap.put(movie.getName(), movie); // Map movie name to the Movie object
            }
    
            movieSelector.setEnabled(true); // Enable movie selection
        } else {
            movieSelector.setEnabled(false); // Disable if no theatre is selected
        }
    }

    // Method to load showtimes based on selected movie and theatre
    private void updateShowtimeSelector() {
        String selectedMovieName = (String) movieSelector.getSelectedItem();
        String selectedTheatreName = (String) theaterSelector.getSelectedItem();
    
        // Check if a valid movie and theatre are selected
        if (selectedMovieName != null && selectedTheatreName != null && movieMap.containsKey(selectedMovieName)) {
            Movie selectedMovie = movieMap.get(selectedMovieName);
            Theatre selectedTheatre = theatreMap.get(selectedTheatreName);
    
            // Fetch the showtimes for this movie and theatre
            loadShowtimesFromDatabase(selectedMovie, selectedTheatre);
        }
    }

    private void updatePriceLabel() {
        String selectedMovieName = (String) movieSelector.getSelectedItem();
        if (selectedMovieName != null && movieMap.containsKey(selectedMovieName)) {
            Movie selectedMovie = movieMap.get(selectedMovieName);
            priceLabel.setText("Price per ticket: $" + String.format("%.2f", selectedMovie.getPrice()));
        } else {
            priceLabel.setText("Price per ticket: N/A");
        }
    }
}
