// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the RefundTicketView class, responsible for handling ticket refunds for the movie theatre application.

package boundary;

import controller.InstanceController;
import database.ControlDatabase;
import database.ReadDatabase;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RefundTicketView extends JPanel {
    private JFrame parentFrame;
    private JTextField ticketNumber;

    /**
     * CONSTRUCTOR FOR RefundTicketView.
     * Initializes the refund ticket view with the given parent frame.
     * @param parent The parent JFrame
     */
    public RefundTicketView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new BorderLayout(10, 10));
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE REFUND TICKET VIEW.
     * Sets up the screen label, ticket number input, and control panel.
     */
    private void initializeComponents() {
        // Screen label at top
        JLabel screenLabel = new JLabel("Refund Ticket", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 20));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(screenLabel, BorderLayout.NORTH);

        // Ticket number input
        JPanel ticketNumberPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel ticketNumberLabel = new JLabel("Ticket Number (ex: TKT00000): ");
        ticketNumber = new JTextField(10);
        ticketNumberPanel.add(ticketNumberLabel);
        ticketNumberPanel.add(ticketNumber);
        add(ticketNumberPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirm Refund");
        JButton cancelButton = new JButton("Cancel");

        controlPanel.add(confirmButton);
        controlPanel.add(cancelButton);
        add(controlPanel, BorderLayout.SOUTH);

        confirmButton.addActionListener(e -> {
            String ticketNumberStr = ticketNumber.getText();
            if (!ticketNumberStr.matches("TKT[0-9]+")) {
                JOptionPane.showMessageDialog(parentFrame, "Please enter a valid ticket number.");
                return;
            }

            String ticketId = ticketNumberStr.trim();
            Ticket ticketToRefund = null;

            List<Ticket> tickets = ControlDatabase.getInstance().getAllTickets();
            for (Ticket ticket : tickets) {
                if (ticket.getTicketID().equals(ticketId)) {
                    ticketToRefund = ticket;
                    break;
                }
            }

            if (ticketToRefund != null) {
                // IF MORE THAN 72 HRS
                if (ticketToRefund.canCancel()) {
                    InstanceController.getInstance().getTicketCart().removeFromCart(ticketToRefund);
                    double ticketPrice = ticketToRefund.getMovie().getPrice();
                    double refundAmount = 0.85;
                    InstanceController.getInstance().getUser().cancelBooking(Integer.parseInt(ticketToRefund.getTicketID().substring(3)));
                    if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
                        refundAmount = 1.00;
                    }
                    ControlDatabase.getInstance().removeTicket(ticketToRefund);
                    new ReadDatabase().populateDatabase();
                    JOptionPane.showMessageDialog(parentFrame, "Ticket number: " + ticketNumberStr + " refunded successfully. $" +
                            String.format("%.2f", ticketPrice * refundAmount) + " has been refunded to your account.");
                    goToMain();
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Tickets can only be refunded up to 72 hours prior to the show.", "Refund Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Ticket not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            goToMain();
        });
    }

    /**
     * NAVIGATES BACK TO THE MAIN VIEW.
     * Sets the content pane to MainView and refreshes the frame.
     */
    private void goToMain() {
        MainView mainView = new MainView(parentFrame);
        parentFrame.setContentPane(mainView);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}