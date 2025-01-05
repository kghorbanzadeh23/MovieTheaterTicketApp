// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Payment class which is used to store information about a payment.

package entity;

public class Payment {
    private int userId;
    private float amount;
    private Date date;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE PAYMENT WITH THE GIVEN USER ID, AMOUNT, AND DATE.
     * @param userId THE ID OF THE USER
     * @param amount THE AMOUNT OF THE PAYMENT
     * @param date THE DATE OF THE PAYMENT
     */
    public Payment(int userId, float amount, Date date) {
        this.userId = userId;
        this.amount = amount;
        this.date = date;
    }

    /**
     * GETS THE ID OF THE USER.
     * @return THE ID OF THE USER
     */
    public int getUserId() {
        return userId;
    }

    /**
     * SETS THE ID OF THE USER.
     * @param userId THE ID OF THE USER
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * GETS THE AMOUNT OF THE PAYMENT.
     * @return THE AMOUNT OF THE PAYMENT
     */
    public double getAmount() {
        return amount;
    }

    /**
     * SETS THE AMOUNT OF THE PAYMENT.
     * @param amount THE AMOUNT OF THE PAYMENT
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * GETS THE DATE OF THE PAYMENT.
     * @return THE DATE OF THE PAYMENT
     */
    public Date getDate() {
        return date;
    }

    /**
     * SETS THE DATE OF THE PAYMENT.
     * @param date THE DATE OF THE PAYMENT
     */
    public void setDate(Date date) {
        this.date = date;
    }
}