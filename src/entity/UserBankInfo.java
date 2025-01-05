// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the UserBankInfo class which is used to store information about a user's bank information.

package entity;

public class UserBankInfo {
    private int bankInfoID;
    private String cardNumber;
    private String cardHolder;
    private Date expiryDate;
    private int cvv;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE USER BANK INFO WITH THE GIVEN DETAILS.
     * @param bankInfoID THE ID OF THE BANK INFO
     * @param cardNumber THE CARD NUMBER
     * @param cardHolder THE CARD HOLDER
     * @param expiryDate THE EXPIRY DATE OF THE CARD
     * @param cvv THE CVV OF THE CARD
     */
    public UserBankInfo(int bankInfoID, String cardNumber, String cardHolder, Date expiryDate, int cvv) {
        this.bankInfoID = bankInfoID;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    /**
     * GETS THE ID OF THE BANK INFO.
     * @return THE ID OF THE BANK INFO
     */
    public int getBankInfoID() {
        return bankInfoID;
    }

    /**
     * SETS THE ID OF THE BANK INFO.
     * @param bankInfoID THE ID OF THE BANK INFO
     */
    public void setBankInfoID(int bankInfoID) {
        this.bankInfoID = bankInfoID;
    }

    /**
     * GETS THE CARD NUMBER.
     * @return THE CARD NUMBER
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * SETS THE CARD NUMBER.
     * @param cardNumber THE CARD NUMBER
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * GETS THE CARD HOLDER.
     * @return THE CARD HOLDER
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * SETS THE CARD HOLDER.
     * @param cardHolder THE CARD HOLDER
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * GETS THE EXPIRY DATE OF THE CARD.
     * @return THE EXPIRY DATE OF THE CARD
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * SETS THE EXPIRY DATE OF THE CARD.
     * @param expiryDate THE EXPIRY DATE OF THE CARD
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * GETS THE CVV OF THE CARD.
     * @return THE CVV OF THE CARD
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * SETS THE CVV OF THE CARD.
     * @param cvv THE CVV OF THE CARD
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}