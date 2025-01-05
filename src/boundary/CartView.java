// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the CartView class, responsible for displaying the shopping cart, including items and total price, for the movie theatre application.

package boundary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entity.*;
import controller.InstanceController;

public class CartView extends JPanel {
    private JList<String> cartItems;
    private JLabel totalLabel;
    private JButton checkoutButton;
    private JButton continueShoppingButton;
    private JFrame parentFrame;

    /**
     * CONSTRUCTOR FOR CartView.
     * Initializes the cart view with the given parent frame.
     * @param parent The parent JFrame
     */
    public CartView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE CART VIEW.
     * Sets up the header, cart items panel, and buttons.
     */
    private void initializeComponents() {
        // Header
        JLabel titleLabel = new JLabel("Shopping Cart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Cart Items Panel
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Order Summary Label
        JLabel orderSummaryLabel = new JLabel("Order Summary");
        orderSummaryLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set larger font size
        orderSummaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartItemsPanel.add(orderSummaryLabel);

        // Add a blank line under Order Summary
        cartItemsPanel.add(Box.createVerticalStrut(10));

        // Cart Items
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Ticket> tickets = InstanceController.getInstance().getTicketCart().getTicketsInCart();
        if (tickets.isEmpty()) {
            listModel.addElement("Cart is currently empty.");
        } else {
            for (Ticket ticket : tickets) {
                // Parse the existing seat string to get row and column numbers
                String seatInfo = ticket.getSeat();
                String[] parts = seatInfo.split("Row | Seat ");
                int row = Integer.parseInt(parts[1].trim());
                int col = Integer.parseInt(parts[2].trim());
                
                // Convert column number to letter (1=A, 2=B, etc)
                char colLetter = (char)('A' + (col - 1));
                // Create seat code (e.g., "B5")
                String seatCode = String.format("%c%d", colLetter, row);
                
                String itemText = String.format("%s - %s - %s - Seat %s    $%.2f",
                        ticket.getShowtime().getMovie().getName(),
                        ticket.getShowtime().getTheatre().getLocation(),
                        ticket.getShowtime().getTime(),
                        seatCode,
                        ticket.getShowtime().getMovie().getPrice());
                listModel.addElement(itemText);
            }
        }
        
        cartItems = new JList<>(listModel);
        cartItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(cartItems);
        cartItemsPanel.add(scrollPane);
        add(cartItemsPanel, BorderLayout.CENTER);

        // South Panel (Total + Buttons)
        JPanel southPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        double total = InstanceController.getInstance().getTicketCart().getTotalPrice();
        totalLabel = new JLabel("Total: $" + String.format("%.2f", total), SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        southPanel.add(totalLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        continueShoppingButton = new JButton("Continue Shopping");
        checkoutButton = new JButton("Proceed to Checkout");
        buttonPanel.add(continueShoppingButton);
        buttonPanel.add(checkoutButton);
        southPanel.add(buttonPanel);

        add(southPanel, BorderLayout.SOUTH);

        setupActionListeners();
    }

    /**
     * SETS UP ACTION LISTENERS FOR THE BUTTONS.
     * Handles the actions for the checkout and continue shopping buttons.
     */
    private void setupActionListeners() {
        checkoutButton.addActionListener(e -> {
            CheckoutView checkoutView = new CheckoutView(parentFrame);
            parentFrame.setContentPane(checkoutView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        continueShoppingButton.addActionListener(e -> {
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}