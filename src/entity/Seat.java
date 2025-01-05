// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Seat class which is used to store information about a seat.

package entity;

public class Seat {
    private int seatId;
    private int roomId;
    private boolean isAvailable;

    /**
     * CONSTRUCTOR THAT INITIALIZES THE SEAT WITH THE GIVEN SEAT ID, ROOM ID, AND AVAILABILITY.
     * @param seatId THE ID OF THE SEAT
     * @param roomId THE ID OF THE ROOM
     * @param isAvailable THE AVAILABILITY OF THE SEAT
     */
    public Seat(int seatId, int roomId, boolean isAvailable) {
        this.seatId = seatId;
        this.roomId = roomId;
        this.isAvailable = isAvailable;
    }

    /**
     * GETS THE ID OF THE SEAT.
     * @return THE ID OF THE SEAT
     */
    public int getSeatId() {
        return seatId;
    }

    /**
     * SETS THE ID OF THE SEAT.
     * @param seatId THE ID OF THE SEAT
     */
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    /**
     * GETS THE ID OF THE ROOM.
     * @return THE ID OF THE ROOM
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * SETS THE ID OF THE ROOM.
     * @param roomId THE ID OF THE ROOM
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * CHECKS IF THE SEAT IS AVAILABLE.
     * @return TRUE IF THE SEAT IS AVAILABLE, FALSE OTHERWISE
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * SETS THE SEAT AS UNAVAILABLE.
     */
    public void setUnavailable() {
        this.isAvailable = false;
    }
}