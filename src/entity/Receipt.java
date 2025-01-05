// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Receipt class which is used to store information about a receipt.

package entity;

public class Receipt {
    private int receiptId;
    private float amount;
    private Date date;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE RECEIPT WITH THE GIVEN RECEIPT ID, AMOUNT, AND DATE.
     * @param receiptId THE ID OF THE RECEIPT
     * @param amount THE AMOUNT OF THE RECEIPT
     * @param date THE DATE OF THE RECEIPT
     */
    public Receipt(int receiptId, float amount, Date date) {
        this.receiptId = receiptId;
        this.amount = amount;
        this.date = date;
    }

    /**
     * GETS THE ID OF THE RECEIPT.
     * @return THE ID OF THE RECEIPT
     */
    public int getReceiptId() {
        return receiptId;
    }

    /**
     * SETS THE ID OF THE RECEIPT.
     * @param receiptId THE ID OF THE RECEIPT
     */
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * GETS THE AMOUNT OF THE RECEIPT.
     * @return THE AMOUNT OF THE RECEIPT
     */
    public float getAmount() {
        return amount;
    }

    /**
     * SETS THE AMOUNT OF THE RECEIPT.
     * @param amount THE AMOUNT OF THE RECEIPT
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * GETS THE DATE OF THE RECEIPT.
     * @return THE DATE OF THE RECEIPT
     */
    public Date getDate() {
        return date;
    }

    /**
     * SETS THE DATE OF THE RECEIPT.
     * @param date THE DATE OF THE RECEIPT
     */
    public void setDate(Date date) {
        this.date = date;
    }
}