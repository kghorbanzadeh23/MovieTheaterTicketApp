// Course: ENSF 480
// Assignment: Term Project
// Instructor: Syed Shah
// Students: L01 - Group 14  (Issy Gaudet, Spiro Douvis, Kamand Ghorbanzadeh, Dylan Wenaas.)  
// Date Submitted: 2024-12-01
// Description: This file contains the Date class which is used to store the day, month, and year of a date.
package entity;

import java.time.LocalDate;

public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * DEFAULT CONSTRUCTOR THAT INITIALIZES THE DATE TO THE CURRENT DATE.
     */
    public Date() {
        LocalDate currentDate = LocalDate.now(); // GET CURRENT DATE
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();
    }

    /**
     * PARAMETERIZED CONSTRUCTOR THAT INITIALIZES THE DATE TO THE GIVEN DAY, MONTH, AND YEAR.
     * @param day THE DAY OF THE MONTH
     * @param month THE MONTH OF THE YEAR
     * @param year THE YEAR
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * GETS THE DAY OF THE MONTH.
     * @return THE DAY OF THE MONTH
     */
    public int getDay() {
        return day;
    }

    /**
     * SETS THE DAY OF THE MONTH.
     * @param day THE DAY OF THE MONTH
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * GETS THE MONTH OF THE YEAR.
     * @return THE MONTH OF THE YEAR
     */
    public int getMonth() {
        return month;
    }

    /**
     * SETS THE MONTH OF THE YEAR.
     * @param month THE MONTH OF THE YEAR
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * GETS THE YEAR.
     * @return THE YEAR
     */
    public int getYear() {
        return year;
    }

    /**
     * SETS THE YEAR.
     * @param year THE YEAR
     */
    public void setYear(int year) {
        this.year = year;
    }
}