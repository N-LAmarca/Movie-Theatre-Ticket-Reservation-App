package model;

import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Showing class can be used to access any information for the showings for each movie.
 * This class is populated from the database, and can be accessed with getter and setter methods.
 * It can also be displayed with the toString method.
 */
public class Showing {

    private int showingId;
    private int movieId;
    private Date showTime;
    private double ticketPrice;
    private int theatreId;
    private ArrayList<Seat> seats;
    private boolean availableToPublic;
    private JDBCConnect myJDBC;

    /**
     * initialize showing object and connect to the DB
     *
     * @param showingId
     * @param movieId
     * @param showTime
     * @param ticketPrice
     * @param theatreId
     * @throws SQLException
     */
    public Showing(int showingId, int movieId, Date showTime, double ticketPrice, int theatreId) throws SQLException {
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        this.showingId = showingId;
        this.movieId = movieId;
        this.showTime = showTime;
        this.ticketPrice = ticketPrice;
        this.theatreId = theatreId;
        initializeSeats();
        setAvailability();
    }

    /**
     * pull all seats that correspond to this showing
     *
     * @throws SQLException
     */
    public void initializeSeats() throws SQLException {
        seats = myJDBC.seatSetStatement(showingId);
    }

    /**
     * Determine if the showing is available to the public or just to Registered Users
     * If a showing is more than 28 days away and less tahtn 10% of seats have been purchased it is
     * only available to RU.
     *
     */
    public void setAvailability() {
        int count = 0;
        for(Seat seat: seats){
            if(seat.getTransactionID() != 0){
                count++;
            }
        }
        Date now = new Date();
        long timeUntilShow = (showTime.getTime() - now.getTime()) / 1000;

        // if show time is less than 4 weeks away or has more than 0.1 of tickets perchansed make
        // it available to public
        this.availableToPublic = timeUntilShow < 2419200 || ((double)count / 25.0) > 0.1;
    }

    // GETTERS AND SETTERS AND TOSTRING

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isAvailableToPublic() {
        return availableToPublic;
    }

    public void setAvailableToPublic(boolean availableToPublic) {
        this.availableToPublic = availableToPublic;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return this.showingId + ". " + this.showTime + "            $" + this.ticketPrice;
    }


}
