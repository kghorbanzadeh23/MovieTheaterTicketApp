// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the InstanceController class, responsible for managing user sessions, seat selections, and ticket operations for the movie theatre application.

package controller;

import java.sql.SQLException;

import database.ControlDatabase;
import database.WriteDatabase;
import entity.*;

public class InstanceController {
    private Seat selectedSeat;
    private int selectedShowtimeId;
    private UserOrdinary user;
    private TicketCart ticketCart;

    private static InstanceController instance = null;

    /**
     * CONSTRUCTOR FOR InstanceController.
     * Initializes the ticket cart.
     */
    private InstanceController() {
        ticketCart = new TicketCart();
    }

    /**
     * RETURNS THE SINGLETON INSTANCE OF InstanceController.
     * @return The singleton instance of InstanceController
     */
    public static InstanceController getInstance() {
        if (instance == null) {
            instance = new InstanceController();
        }
        return instance;
    }

    /**
     * SETS THE CURRENT USER.
     * @param user The UserOrdinary object representing the current user
     */
    public void setUser(UserOrdinary user) {
        this.user = user;
        if (user instanceof UserRegistered) {
            ((UserRegistered) user).selectSpecialAccessSeats();
        }
    }

    /**
     * RETURNS THE CURRENT USER.
     * @return The UserOrdinary object representing the current user
     */
    public UserOrdinary getUser() {
        return user;
    }

    /**
     * FETCHES A REGISTERED USER FROM THE DATABASE.
     * @param userId The ID of the user to fetch
     * @return The UserRegistered object representing the fetched user
     */
    public UserRegistered fetchUser(int userId) {
        UserRegistered registeredUser = ControlDatabase.getInstance().getUserRegistered(userId);
        setUser(registeredUser);
        return registeredUser;
    }

    /**
     * SAVES THE CURRENT USER TO THE DATABASE.
     */
    public void saveUser() {
        if (user instanceof UserRegistered) {
            WriteDatabase writeDatabase = new WriteDatabase();
            try {
                writeDatabase.saveSingleUser((UserRegistered) user);
                System.out.println("User saved to database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * SETS THE SELECTED SEAT.
     * @param seat The Seat object representing the selected seat
     */
    public void setSelectedSeat(Seat seat) {
        this.selectedSeat = seat;
    }

    /**
     * RETURNS THE SELECTED SEAT.
     * @return The Seat object representing the selected seat
     */
    public Seat getSelectedSeat() {
        return selectedSeat;
    }

    /**
     * RETURNS THE TICKET CART.
     * @return The TicketCart object representing the ticket cart
     */
    public TicketCart getTicketCart() {
        return ticketCart;
    }

    /**
     * LOGS OUT THE CURRENT USER.
     * Resets the user, selected seat, selected showtime ID, and ticket cart.
     */
    public void logout() {
        this.user = null;
        this.selectedSeat = null;
        this.selectedShowtimeId = 0;
        this.ticketCart = new TicketCart(); // Reset the ticket cart
    }
}