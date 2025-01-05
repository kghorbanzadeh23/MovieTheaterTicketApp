// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the TicketCart class which is used to store information about a user's ticket cart.

package entity;

import database.ControlDatabase;
import java.util.ArrayList;

public class TicketCart {
    private ArrayList<Ticket> tickets_in_cart = new ArrayList<Ticket>();
    private float totalPrice = 0;

    /**
     * ADDS A TICKET TO THE CART AND UPDATES THE TOTAL PRICE.
     * @param ticket THE TICKET TO BE ADDED TO THE CART
     */
    public void addToCart(Ticket ticket) {
        tickets_in_cart.add(ticket);
        totalPrice += ticket.getShowtime().getMovie().getPrice();
    }

    /**
     * REMOVES A TICKET FROM THE CART AND UPDATES THE TOTAL PRICE.
     * @param ticket THE TICKET TO BE REMOVED FROM THE CART
     */
    public void removeFromCart(Ticket ticket) {
        tickets_in_cart.remove(ticket);
        totalPrice -= ticket.getShowtime().getMovie().getPrice();
    }

    /**
     * GETS THE LIST OF TICKETS IN THE CART.
     * @return THE LIST OF TICKETS IN THE CART
     */
    public ArrayList<Ticket> getTicketsInCart() {
        return tickets_in_cart;
    }

    /**
     * GETS THE TOTAL PRICE OF THE TICKETS IN THE CART.
     * @return THE TOTAL PRICE OF THE TICKETS IN THE CART
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * CHECKS OUT THE TICKETS IN THE CART, BOOKS THEM IN THE DATABASE, AND CLEARS THE CART.
     */
    public void checkout() {
        for (Ticket ticket : tickets_in_cart) {
            // Book ticket in database
            ControlDatabase.getInstance().bookTicket(ticket);
        }
    }
    public void clearCart() {
        tickets_in_cart.clear();
        totalPrice = 0;
    }
}