// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the ControlGUI class, responsible for initializing and controlling the GUI for the movie theatre application.

package controller;

import boundary.InitGUI;
import database.ReadDatabase;
import javax.swing.SwingUtilities;

public class ControlGUI {
    private InitGUI gui;
    private static ControlGUI instance = null;

    /**
     * CONSTRUCTOR FOR ControlGUI.
     * Initializes the GUI and populates the database on startup.
     */
    private ControlGUI() {
        gui = new InitGUI();
        initializeDatabase(); // Initialize and populate the database on startup
    }

    /**
     * RETURNS THE SINGLETON INSTANCE OF ControlGUI.
     * @return The singleton instance of ControlGUI
     */
    public static ControlGUI getInstance() {
        if (instance == null) {
            instance = new ControlGUI();
        }
        return instance;
    }

    /**
     * STARTS THE GUI.
     * Uses SwingUtilities to invoke the GUI on the Event Dispatch Thread.
     */
    public void startGUI() {
        SwingUtilities.invokeLater(() -> {
            gui.show();
        });
    }

    /**
     * INITIALIZES AND POPULATES THE DATABASE.
     * Creates an instance of ReadDatabase and calls the populateDatabase method.
     */
    private void initializeDatabase() {
        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.populateDatabase();
        System.out.println("Database initialized and populated.");
    }

    /**
     * RETURNS THE GUI INSTANCE.
     * @return The InitGUI instance
     */
    public InitGUI getGUI() {
        return gui;
    }

    /**
     * MAIN METHOD TO START THE APPLICATION.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        ControlGUI controller = ControlGUI.getInstance();
        controller.startGUI();
    }
}