package model;

import java.util.Calendar;
import java.util.Date;


/**
 * MovieCredit class hold all the information for a user's credit that
 * they would have from cancelling movie tickets.
 * All the information can be access through getter and setter methods,
 * and displayed with the toString method.
 */
public class MovieCredit
{

    int movieCreditId;
    String creditCode;
    Date expiryDate;
    double amount;
    int userId;

    /**
     * Initializes MovieCredit object
     *
     * @param movieCreditId
     * @param creditCode
     * @param expiryDate
     * @param amount
     * @param userId
     */
    public MovieCredit(int movieCreditId, String creditCode, Date expiryDate,
                       double amount, int userId) {
        this.movieCreditId = movieCreditId;
        this.creditCode = creditCode;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.userId = userId;
    }

    // GETTERS AND SETTERS AND TOSTRING

    public int getMovieCreditId() {
        return movieCreditId;
    }

    public void setMovieCreditId(int movieCreditId) {
        this.movieCreditId = movieCreditId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MovieCredit{" +
                "movieCreditId=" + movieCreditId +
                ", creditCode='" + creditCode + '\'' +
                ", expiryDate=" + expiryDate +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}
