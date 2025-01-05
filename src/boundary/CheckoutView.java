// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the CheckoutView class, responsible for handling the checkout process, including payment and order summary, for the movie theatre application.

package boundary;

import javax.swing.*;

import controller.InstanceController;
import entity.Ticket;
import entity.TicketCart;
import entity.UserRegistered;

import java.awt.*;

public class CheckoutView extends JPanel {
    private JTextField cardHolder;
    private JTextField cardNumberField;
    private JTextField expiryField;
    private JTextField cvvField;
    private JTextField emailField;
    private JButton confirmButton;
    private JButton backButton;
    private JFrame parentFrame;

    /**
     * CONSTRUCTOR FOR CheckoutView.
     * Initializes the checkout view with the given parent frame.
     * @param parent The parent JFrame
     */
    public CheckoutView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE CHECKOUT VIEW.
     * Sets up the form fields, labels, and buttons.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("Checkout");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Order Summary
        float orderTotal = InstanceController.getInstance().getTicketCart().getTotalPrice();
        JLabel summaryLabel = new JLabel("Order Total: $" + String.format("%.2f", orderTotal));
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        add(summaryLabel, gbc);

        // Payment Details
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        // ONLY REQUEST CARD DETAILS IF NOT REGISTERED USER
        // AND EMAIL TO SEND RECEIPT AND TICKET
        if (!(InstanceController.getInstance().getUser() instanceof UserRegistered)) {
            // Card Holder
            addField("Card Holder:", cardHolder = new JTextField(20), gbc, 2);
            // Card Number
            addField("Card Number:", cardNumberField = new JTextField(16), gbc, 3);
            
            // Expiry Date
            addField("Expiry (MM/YY):", expiryField = new JTextField(5), gbc, 4);
            
            // CVV
            addField("CVV:", cvvField = new JTextField(3), gbc, 5);

            // Email
            addField("Email:", emailField = new JTextField(20), gbc, 6);
        }

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        backButton = new JButton("Back to Cart");
        confirmButton = new JButton("Confirm Payment");
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        gbc.gridx = 0;
        gbc.gridy = (InstanceController.getInstance().getUser() == null) ? 2 : 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        setupActionListeners();
    }

    /**
     * ADDS A FIELD TO THE FORM.
     * @param labelText The label text for the field
     * @param field The JTextField object
     * @param gbc The GridBagConstraints object
     * @param row The row number for the field
     */
    private void addField(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(field, gbc);
    }

    /**
     * SETS UP ACTION LISTENERS FOR THE BUTTONS.
     * Handles the actions for the back and confirm buttons.
     */
    private void setupActionListeners() {
        backButton.addActionListener(e -> {
            CartView cartView = new CartView(parentFrame);
            parentFrame.setContentPane(cartView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        confirmButton.addActionListener(e -> {
            if (validateFields() || InstanceController.getInstance().getUser() instanceof UserRegistered) {
                TicketCart ticketCart = InstanceController.getInstance().getTicketCart();
                ticketCart.checkout();
                
                // FIND CORRECT EMAIL TO SEND RECEIPT
                String email;
                if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
                    UserRegistered user = (UserRegistered) InstanceController.getInstance().getUser();
                    email = user.getEmail();
                } else {
                    email = emailField.getText();
                }

                // SEND EMAIL RECEIPT AND TICKET

                StringBuilder tickets = new StringBuilder();
                for (Ticket ticket : ticketCart.getTicketsInCart()) {
                    tickets.append(ticket.getTicketID()).append("\n");
                }

                ticketCart.clearCart();

                JOptionPane.showMessageDialog(parentFrame, 
                    "Payment successful! Your tickets have been booked! " + "\n" +
                            "Tickets:" + "\n" +
                            tickets +
                            "A copy of your tickets along with an email receipt have been sent to " + email,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                MainView mainView = new MainView(parentFrame);
                parentFrame.setContentPane(mainView);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    /**
     * VALIDATES THE FORM FIELDS.
     * Checks if all required fields are filled in.
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateFields() {
        // Basic validation
        if (!(InstanceController.getInstance().getUser() instanceof UserRegistered)) {
            if (cardHolder.getText().isEmpty() || 
                cardNumberField.getText().isEmpty() ||
                expiryField.getText().isEmpty() ||
                cvvField.getText().isEmpty()) {
                
                JOptionPane.showMessageDialog(this,
                    "Please fill in all card details",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}