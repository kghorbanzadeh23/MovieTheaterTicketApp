// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the UserRegistered class which is used to store information about a registered user.
package entity;

import java.util.Vector;

public class UserRegistered extends UserOrdinary {
    private boolean annualFeePaid;
    private UserBankInfo bankInfo;
    private Date dateJoined;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE REGISTERED USER WITH THE GIVEN DETAILS.
     * @param userID THE ID OF THE USER
     * @param name THE NAME OF THE USER
     * @param email THE EMAIL OF THE USER
     * @param password THE PASSWORD OF THE USER
     * @param bankInfo THE BANK INFORMATION OF THE USER
     * @param dateJoined THE DATE THE USER JOINED
     */
    public UserRegistered(int userID, String name, String email, String password, UserBankInfo bankInfo, Date dateJoined) {
        super(userID, name, email, password);
        this.annualFeePaid = false;
        this.bankInfo = bankInfo;
        this.dateJoined = dateJoined;
    }

    /**
     * CHECKS IF THE ANNUAL FEE IS PAID.
     * @return TRUE IF THE ANNUAL FEE IS PAID, FALSE OTHERWISE
     */
    public boolean isAnnualFeePaid() {
        return annualFeePaid;
    }

    /**
     * MARKS THE ANNUAL FEE AS PAID.
     */
    public void payAnnualFee() {
        this.annualFeePaid = true;
    }

    /**
     * GETS THE BANK INFORMATION OF THE USER.
     * @return THE BANK INFORMATION OF THE USER
     */
    public UserBankInfo getBankInfo() {
        return bankInfo;
    }

    /**
     * SETS THE BANK INFORMATION OF THE USER.
     * @param bankInfo THE BANK INFORMATION OF THE USER
     */
    public void setBankInfo(UserBankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    /**
     * GETS THE FIRST NAME OF THE USER.
     * @return THE FIRST NAME OF THE USER
     */
    public String getFirstName() {
        String[] parts = this.getName().split(" ");
        return parts.length > 0 ? parts[0] : "";
    }

    /**
     * GETS THE LAST NAME OF THE USER.
     * @return THE LAST NAME OF THE USER
     */
    public String getLastName() {
        String[] parts = this.getName().split(" ");
        return parts.length > 1 ? parts[1] : "";
    }


    public Date getdateJoined() {
        return dateJoined;
    }

    public void setdateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    
    public int getJoinDay() {
        return dateJoined.getDay();
    }

    public int getJoinMonth() {
        return dateJoined.getMonth();
    }

    public int getJoinYear() {
        return dateJoined.getYear();
    }

    /**
     * SELECTS SPECIAL ACCESS SEATS FOR THE USER.
     */
    public void selectSpecialAccessSeats() {
        System.out.println("Selecting special access seats for registered users.");
    }

    /**
     * SEARCHES FOR MOVIES BASED ON THE GIVEN QUERY.
     * @param query THE SEARCH QUERY
     * @return A VECTOR OF MOVIES THAT MATCH THE QUERY
     */
    @Override
    public Vector<Movie> searchMovie(String query) {
        Vector<Movie> movies = super.searchMovie(query);
        System.out.println("Searching for movies with priority access for registered users.");
        return movies;
    }

    /**
     * MAKES A PAYMENT.
     * @param payment THE PAYMENT TO BE MADE
     */
    @Override
    public void makePayment(Payment payment) {
        if (this.annualFeePaid) {
            // Process payment without admin fee
        } else {
            // Process payment with admin fee
        }
    }

     /**
     * CANCELS A BOOKING.
     * @param bookingId THE ID OF THE BOOKING TO BE CANCELED
     */
    @Override
    public void cancelBooking(int bookingId) {
        System.out.println("Refund 100% for registered users, not 85%.");
    }
}
