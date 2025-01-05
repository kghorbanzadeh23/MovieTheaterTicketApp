// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the LoginView class, responsible for handling user login and navigation to registration or guest access for the movie theatre application.

package boundary;

import javax.swing.*;
import java.awt.*;
import entity.*;
import controller.InstanceController;
import database.ControlDatabase;

public class LoginView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public JButton submitLoginButton;
    private JButton registerButton;
    private InstanceController instance;
    private JFrame parentFrame;

    /**
     * CONSTRUCTOR FOR LoginView.
     * Initializes the login view with the given parent frame.
     * @param parent The parent JFrame
     */
    public LoginView(JFrame parent) {
        this.parentFrame = parent;
        this.instance = InstanceController.getInstance();
        setLayout(new GridBagLayout());
        initializeComponents();
    }

    /**
     * INITIALIZES THE COMPONENTS OF THE LOGIN VIEW.
     * Sets up the form fields, labels, and buttons.
     */
    private void initializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("AcmePlex Cinemas Ticket Booking"); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 40, 20);
        add(titleLabel, gbc);

        // Username (Email)
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        submitLoginButton = new JButton("Login");
        submitLoginButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(submitLoginButton);

        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(registerButton);

        JButton guestButton = new JButton("Continue as Guest");
        guestButton.setPreferredSize(new Dimension(150, 30));
        buttonPanel.add(guestButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 5, 5, 5);
        add(buttonPanel, gbc);

        // Add action listeners
        submitLoginButton.addActionListener(e -> {
            String email = getUsername();  // Get email from usernameField
            String password = getPassword();  // Get password from passwordField

            if (validateLogin(email, password)) {
                // Login successful, navigate to MainView
                MainView mainView = new MainView(parentFrame);
                parentFrame.setContentPane(mainView);
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                // Show error if login fails
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            // Navigate to RegistrationView for new users
            RegistrationView registrationView = new RegistrationView(parentFrame);
            parentFrame.setContentPane(registrationView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        guestButton.addActionListener(e -> {
            // Create guest user and set in InstanceController
            UserOrdinary guestUser = new UserOrdinary(0, "Guest", "guest@example.com", "");
            instance.setUser(guestUser);

            // Navigate to MainView
            MainView mainView = new MainView(parentFrame);
            parentFrame.setContentPane(mainView);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }

    /**
     * VALIDATES THE LOGIN CREDENTIALS.
     * Checks if the provided email and password match a registered user.
     * @param email The email entered by the user
     * @param password The password entered by the user
     * @return true if the credentials are valid, false otherwise
     */
    private boolean validateLogin(String email, String password) {
        System.out.println("Validating login for email: " + email);  // Log the email
        UserRegistered user = ControlDatabase.getInstance().getUserRegisteredByEmail(email);

        if (user != null) {
            System.out.println("Found user with email: " + user.getEmail());  // Log found user info
            System.out.println("Password from DB: " + user.getPassword());  // Log stored password
            System.out.println("Entered password: " + password);  // Log entered password

            // Compare the entered password with the one stored in the database
            if (user.getPassword().equals(password)) {
                // Login successful
                instance.setUser(user);
                return true;
            }
        }
        // Login failed
        return false;
    }

    /**
     * RETURNS THE USERNAME ENTERED BY THE USER.
     * @return The username (email) entered by the user
     */
    public String getUsername() {
        return usernameField.getText();  // Return email from usernameField
    }

    /**
     * RETURNS THE PASSWORD ENTERED BY THE USER.
     * @return The password entered by the user
     */
    public String getPassword() {
        return new String(passwordField.getPassword());  // Return password from passwordField
    }

    /**
     * CLEARS THE LOGIN FIELDS.
     * Resets the username and password fields to empty.
     */
    public void clearFields() {
        usernameField.setText("");  // Clear username field
        passwordField.setText("");  // Clear password field
    }
}
