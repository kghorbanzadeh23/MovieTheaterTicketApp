// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14 (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)
// Date Submitted: 2024-12-01
// Description: This file contains the WriteDatabase class, responsible for saving data to the database for the movie theatre application.

package database;

import controller.InstanceController;
import entity.*;
import entity.Date;

import java.sql.*;

public class WriteDatabase {
    private ControlDatabase controlDatabase;
    private Connection conn;

    /**
     * ESTABLISHES AND RETURNS A DATABASE CONNECTION.
     * @return The database connection
     */
    private Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        controlDatabase = ControlDatabase.getInstance();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_theatre_app?" +
                    "user=admin&password=admin_pass");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
        return conn;
    }

    /**
     * SAVES ALL DATA TO THE DATABASE BY INVOKING SPECIFIC SAVE METHODS.
     */
    public void saveAll() {
        try {   
            saveRegisteredUsers();
            saveTickets();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            conn = null;
        }
    }


    /**
     * SAVES A SINGLE USER BANK INFORMATION RECORD TO THE DATABASE.
     * @param bankInfo The UserBankInfo object to save
     */
    public void saveSingleBankInfo(UserBankInfo bankInfo) throws SQLException {
        String sql = "INSERT INTO bank_info (ID_no, Card_Holder, Card_Number, Expiry_Date, CVV) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Card_Holder=?, Card_Number=?, Expiry_Date=?, CVV=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Insert values
            stmt.setInt(1, bankInfo.getBankInfoID());
            stmt.setString(2, bankInfo.getCardHolder());
            stmt.setString(3, bankInfo.getCardNumber());
            stmt.setString(4, bankInfo.getExpiryDate().getMonth() + "/" + (bankInfo.getExpiryDate().getYear() - 2000));
            stmt.setInt(5, bankInfo.getCvv());

            // Update values for ON DUPLICATE KEY
            stmt.setString(6, bankInfo.getCardHolder());
            stmt.setString(7, bankInfo.getCardNumber());
            stmt.setString(8, bankInfo.getExpiryDate().getMonth() + "/" + (bankInfo.getExpiryDate().getYear() - 2000));
            stmt.setInt(9, bankInfo.getCvv());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SAVES ALL REGISTERED USERS TO THE DATABASE.
     */
    private void saveRegisteredUsers() throws SQLException {
        String sql = "INSERT INTO registered_user (ID_no, User_Password, First_Name, Last_Name, " +
                "User_Email, User_Bank_Info, User_Day, User_Month, User_Year) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE User_Password=?, First_Name=?, Last_Name=?, " +
                "User_Email=?, User_Bank_Info=?, User_Day=?, User_Month=?, User_Year=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            for (UserRegistered user : controlDatabase.getAllRegisteredUsers().values()) {
                // Insert values
                stmt.setInt(1, user.getUserID());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getName().split(" ")[0]); // First name
                stmt.setString(4, user.getName().split(" ")[1]); // Last name
                stmt.setString(5, user.getEmail());
                stmt.setInt(6, user.getBankInfo().getBankInfoID());
                stmt.setInt(7, user.getJoinDay());
                stmt.setInt(8, user.getJoinMonth());
                stmt.setInt(9, user.getJoinYear());

                // Update values for ON DUPLICATE KEY
                stmt.setString(10, user.getPassword());
                stmt.setString(11, user.getName().split(" ")[0]); // First name
                stmt.setString(12, user.getName().split(" ")[1]); // Last name
                stmt.setString(13, user.getEmail());
                stmt.setInt(14, user.getBankInfo().getBankInfoID());
                stmt.setInt(15, user.getJoinDay());
                stmt.setInt(16, user.getJoinMonth());
                stmt.setInt(17, user.getJoinYear());

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SAVES A SINGLE REGISTERED USER TO THE DATABASE.
     * @param user The UserRegistered object to save
     */
    public void saveSingleUser(UserRegistered user) throws SQLException {
        // Logic is identical to saveRegisteredUsers but for a single user
        saveRegisteredUsers();
    }


    /**
     * SAVES TICKETS TO THE DATABASE.
     */
    public void saveTickets() {
        String checkSql = "SELECT COUNT(*) FROM tickets WHERE ID_no = ?";
        String insertSql = "INSERT INTO tickets (ID_no, Show_ID, User_ID, Seat) VALUES (?, ?, ?, ?)";

        try (PreparedStatement checkStmt = getConnection().prepareStatement(checkSql);
             PreparedStatement insertStmt = getConnection().prepareStatement(insertSql)) {
            for (Ticket ticket : controlDatabase.getAllTickets()) {
                checkStmt.setString(1, ticket.getTicketID());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                String seatInfo = ticket.getSeat();
                String[] parts = seatInfo.split("Row | Seat ");
                int row = Integer.parseInt(parts[1].trim());
                int col = Integer.parseInt(parts[2].trim());
                char colLetter = (char) ('A' + (col - 1));
                String seatCode = String.format("%c%d", colLetter, row);

                if (count == 0) {
                    insertStmt.setString(1, ticket.getTicketID());
                    insertStmt.setInt(2, ticket.getShowtime().getShowtimeId());
                    if (InstanceController.getInstance().getUser() instanceof UserRegistered) {
                        insertStmt.setInt(3, (InstanceController.getInstance().getUser()).getUserID());
                    } else {
                        insertStmt.setNull(3, Types.INTEGER);
                    }
                    insertStmt.setString(4, seatCode);
                    insertStmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * REMOVES A SPECIFIC TICKET FROM THE DATABASE.
     * @param ticket The Ticket object to remove
     */
    public void removeTicket(Ticket ticket) {
        String sql = "DELETE FROM tickets WHERE ID_no = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, ticket.getTicketID());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
