// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the AccountDetailsView class, responsible for displaying the account details of a registered user for the movie theatre application.

package boundary;

import javax.swing.*;
import java.awt.*;
import controller.InstanceController;
import entity.UserRegistered;

public class AccountDetailsView extends JPanel {
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JButton backButton;
    private JFrame parentFrame;

    /**
     * CONSTRUCTOR FOR AccountDetailsView.
     * Initializes the account details view with the given parent frame.
     * @param parent The parent JFrame
     */
    public AccountDetailsView(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE ACCOUNT DETAILS VIEW.
     * Sets up the labels and buttons.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Account Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // User Details
        UserRegistered user = (UserRegistered) InstanceController.getInstance().getUser();

        nameLabel = new JLabel("Name: " + user.getName());
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);

        emailLabel = new JLabel("Email: " + user.getEmail());
        gbc.gridy = 2;
        add(emailLabel, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        backButton = new JButton("Back to Main");
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        setupActionListeners();
    }

    /**
     * SETS UP ACTION LISTENERS FOR THE BUTTONS.
     * Handles the action for the back button.
     */
    private void setupActionListeners() {
        backButton.addActionListener(e -> {
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}