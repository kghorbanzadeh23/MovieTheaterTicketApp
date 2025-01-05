// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the InitGUI class, responsible for initializing and displaying the main GUI frame for the movie theatre application.

package boundary;

import javax.swing.*;
import java.awt.*;
import entity.*;

public class InitGUI {
    private JFrame mainFrame;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    /**
     * CONSTRUCTOR FOR InitGUI.
     * Initializes the main GUI frame.
     */
    public InitGUI() {
        initializeFrame();
    }

    /**
     * INITIALIZES THE MAIN FRAME.
     * Sets up the main frame properties and starts with the login view.
     */
    private void initializeFrame() {
        mainFrame = new JFrame("AcmePlex Cinemas Ticket Booking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainFrame.setLocationRelativeTo(null);
        
        // Start with login view
        LoginView loginView = new LoginView(mainFrame);
        mainFrame.setContentPane(loginView);
    }

    /**
     * DISPLAYS THE MAIN FRAME.
     * Makes the main frame visible.
     */
    public void show() {
        mainFrame.setVisible(true);
    }

    /**
     * RETURNS THE MAIN FRAME.
     * @return The main JFrame
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }
}