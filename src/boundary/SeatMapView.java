// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the SeatMapView class, responsible for displaying the seat map and handling seat selection for the movie theatre application.

package boundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import database.ControlDatabase;
import entity.*;
import controller.InstanceController;

public class SeatMapView extends JPanel {
    private JFrame parentFrame;
    private ScreeningRoom screeningRoom;
    private int ticketsToSelect;
    private ArrayList<Seat> selectedSeats = new ArrayList<>();
    private JButton[][] seatButtons;
    private Movie selectedMovie;
    private Showtime selectedShowtime;

    static int offset = 1;

    /**
     * CONSTRUCTOR FOR SeatMapView.
     * Initializes the seat map view with the given parameters.
     * @param parent The parent JFrame
     * @param room The screening room
     * @param tickets The number of tickets to select
     * @param movie The selected movie
     * @param showtime The selected showtime
     */
    public SeatMapView(JFrame parent, ScreeningRoom room, int tickets, Movie movie, Showtime showtime) {
        this.parentFrame = parent;
        this.screeningRoom = room;
        this.ticketsToSelect = tickets;
        this.selectedMovie = movie;
        this.selectedShowtime = showtime;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE SEAT MAP VIEW.
     * Sets up the screen label, seat grid, and control panel.
     */
    private void initializeComponents() {
        // Screen label at the top
        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(screenLabel, BorderLayout.NORTH);

        // Seat grid
        JPanel seatsPanel = new JPanel(new GridLayout(screeningRoom.getRows(), screeningRoom.getColumns(), 5, 5));
        seatButtons = new JButton[screeningRoom.getRows()][screeningRoom.getColumns()];

        ArrayList<Ticket> tickets = ControlDatabase.getInstance().getTicketsForShowtime(selectedShowtime);
        for (Ticket ticket : tickets) {
            String[] seatParts = ticket.getSeat().split(" ");
            int row = Integer.parseInt(seatParts[1]);
            int col = Integer.parseInt(seatParts[3]);

            screeningRoom.getSeat(row, col).setUnavailable();
        }
        for (int i = 0; i < screeningRoom.getRows(); i++) {
            for (int j = 0; j < screeningRoom.getColumns(); j++) {
                Seat seat = screeningRoom.getSeat(i + 1, j + 1);

                if (seat == null) {
                    System.out.println("Seat not found at Row " + (i + 1) + ", Column " + (j + 1));
                    JButton placeholder = new JButton("N/A");
                    placeholder.setEnabled(false);
                    seatsPanel.add(placeholder);
                    continue;
                }
                JButton seatButton = createSeatButton(seat);
                seatButtons[i][j] = seatButton;
                seatsPanel.add(seatButton);
            }
        }

        add(seatsPanel, BorderLayout.CENTER);

        // Control panel with Confirm and Cancel buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirm Selection");
        JButton cancelButton = new JButton("Cancel");

        controlPanel.add(confirmButton);
        controlPanel.add(cancelButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Action listener for Confirm button
        confirmButton.addActionListener(e -> {
            if (selectedSeats.size() == ticketsToSelect) {
                // Add selected seats to the cart
                TicketCart cart = InstanceController.getInstance().getTicketCart();
                for (Seat seat : selectedSeats) {
                    cart.addToCart(new Ticket(
                        generateTicketId(),
                        selectedMovie,
                        screeningRoom.getTheatre(),
                        selectedShowtime.getDate(),
                        selectedShowtime,
                        "Row " + ((seat.getSeatId() / screeningRoom.getColumns()) + 1) +
                        " Seat " + ((seat.getSeatId() % screeningRoom.getColumns()) + 1)
                    ));
                }

                JOptionPane.showMessageDialog(parentFrame, "Seats added to cart successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Return to MainView and enable Add to Cart button
                MainView mainView = new MainView(parentFrame);
                // mainView.enableAddToCart(); // Add this method to MainView
                parentFrame.setContentPane(mainView);
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Please select exactly " + ticketsToSelect + " seats.",
                    "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action listener for Cancel button
        cancelButton.addActionListener(e -> {
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    /**
     * CREATES A BUTTON FOR A SEAT.
     * Sets the button's appearance and action listener.
     * @param seat The Seat object
     * @return The JButton representing the seat
     */
    private JButton createSeatButton(Seat seat) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(40, 40));
        
        // Calculate total seats and 10% reserved seats (rounded up)
        int totalSeats = screeningRoom.getRows() * screeningRoom.getColumns();
        int reservedSeatsCount = (int) Math.ceil(totalSeats * 0.1);
        
        // Calculate if this seat is in the reserved section
        // Reserved seats start from the left of the 3rd row
        int row = (seat.getSeatId() / screeningRoom.getColumns()) + 1;
        int col = (seat.getSeatId() % screeningRoom.getColumns()) + 1;
        boolean isReservedSeat = (row == 3 && col <= reservedSeatsCount);
        
        // Check if user is registered
        boolean isRegisteredUser = InstanceController.getInstance().getUser() instanceof UserRegistered;
        
        // Set initial color
        if (isReservedSeat) {
            button.setBackground(Color.RED);
        } else {
            button.setBackground(seat.isAvailable() ? Color.GREEN : Color.RED);
        }
        
        button.setOpaque(true);
        button.setBorderPainted(false);
    
        button.addActionListener(e -> {
            if (seat.isAvailable()) {
                // Check if guest user is trying to select reserved seat
                if (isReservedSeat && !isRegisteredUser) {
                    JOptionPane.showMessageDialog(this,
                        "This seat is reserved for registered users only.",
                        "Reserved Seat",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (!selectedSeats.contains(seat) && selectedSeats.size() < ticketsToSelect) {
                    selectedSeats.add(seat);
                    button.setBackground(Color.BLUE);
                } else if (selectedSeats.contains(seat)) {
                    selectedSeats.remove(seat);
                    button.setBackground(isReservedSeat ? Color.RED : Color.GREEN);
                }
            }
        });
    
        return button;
    }

    /**
     * GENERATES A UNIQUE TICKET ID BASED ON THE CURRENT TIMESTAMP.
     * @return A unique ticket identifier.
     */
    private String generateTicketId() {
        offset += 1;
        return "TKT" + (System.currentTimeMillis() + offset * 100L) % 100000;
    }
}