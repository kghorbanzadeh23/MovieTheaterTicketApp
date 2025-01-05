package entity;

import java.util.ArrayList;

public class ScreeningRoom {
    private int roomId;
    private int rows; 
    private int columns;
    private Theatre theatre;
    private ArrayList<Seat> seats = new ArrayList<>();

    public ScreeningRoom(int roomId, int rows, int columns) {
        this.roomId = roomId;
        this.rows = rows;
        this.columns = columns;
        initializeSeats();
    }

    private void initializeSeats() {
        int totalSeats = rows * columns;
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new Seat(i, roomId, true));
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCapacity() {
        return rows * columns;
    }

    // 

    public Seat getSeat(int row, int column) {
        int coloff = (row - 1) * columns;
        return seats.get(coloff + (column - 1));
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }


}