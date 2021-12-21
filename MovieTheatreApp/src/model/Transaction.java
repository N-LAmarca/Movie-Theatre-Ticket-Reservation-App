package model;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Transaction class can be used to access any information for the transactions.
 * This class is populated from the database, and can be accessed with getter and setter methods.
 * It can also be displayed with the toString method.
 */
public class Transaction
{
    int transactionId;
    int userId;
    double totalCost;
    Date purchaseDate;
    int creditCardId;
    int showingId;
    User user;
    CreditCard paymentCreditCard;
    ArrayList<Seat> purchasedSeats = new ArrayList<Seat>();
    Showing showing;
    JDBCConnect myJDBC;
    Database database;

    /**
     * Constructor for an initializing an existing Transaction
     * @param userId
     * @param totalCost
     * @param purchaseDate
     * @param paymentCreditCard
     * @param showingId
     * @throws SQLException
     */
    public Transaction(int transactionId, int userId, double totalCost,
                       Date purchaseDate, int paymentCreditCard, int showingId) throws SQLException {
        this.transactionId = transactionId;
        this.userId = userId;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
        this.creditCardId = paymentCreditCard;
        this.showingId = showingId;
        database = Database.getDatabase();
        setUser(userId);
        setSeats();
        setShowing(showingId);
    }

    /**
     * Constructor for initializing a new Transaction and inserting it into the DB
     *
     * @param user
     * @param totalCost
     * @param paymentCreditCard
     * @param showingId
     * @throws SQLException
     */
    public Transaction(User user, double totalCost, CreditCard paymentCreditCard, int showingId) throws SQLException {
        this.user = user;
        this.totalCost = totalCost;
        this.paymentCreditCard = paymentCreditCard;
        database = Database.getDatabase();
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        this.transactionId = myJDBC.addTransactionToDB(user, totalCost, paymentCreditCard, showingId);
        setShowing(showingId);
        setPurchaseDate(new Date());
    }

    /**
     * initializes user of transaction from DB using the userId
     *
     * @param userId
     * @throws SQLException
     */
    public void setUser(int userId) throws SQLException {

        for (User u: database.getUserDB()) {
            if (userId == u.getUserId()) {
                this.user = u;
            }
        }
    }

    /**
     * initializes seats in transaction from DB using the transactionId
     *
     * @throws SQLException
     */
    public void setSeats() throws SQLException {
        for (Seat s: database.getSeatDB()) {
            if(transactionId == s.getTransactionID()){
                this.purchasedSeats.add(s);
            }
        }
    }

    /**
     * initializes showing of transaction from DB using the showingId
     *
     * @param showingId
     * @throws SQLException
     */
    public void setShowing(int showingId) throws SQLException {
        for (Showing s: database.getShowingDB()) {
            if(showingId == s.getShowingId()){
                this.showing = s;
            }
        }
    }

    /**
     * creates a receipt for a transaction and inserts it into the DB
     *
     * @throws SQLException
     */
    public void createReceipt() throws SQLException {
        String subjectLine = "Receipt";
        String message = this.toString();
        myJDBC.addMessageToDB(user, message, subjectLine);
    }

    /**
     * Formats To String to contain TransactionId, Total Cost, Purchase Date, Movie Title, Showing Time and Seats purchased
     *
     * @return
     */
    @Override
    public String toString() {

        String seats = " ";

        for (Seat s: purchasedSeats)
        {
            seats += s.getRow()+s.getCol() + "  ";
        }
        try {
            return "Transaction #: " + transactionId +"      " +
                    "Total Cost: " + totalCost +"      " +
                    "Purchase Date: " + purchaseDate + "      " +
                    "Movie Title: " + getMovieTitle() + "      " +
                    "Showing Time: " + showing.getShowTime() + "      " +
                    "Seats: " + seats;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets movie title for toString
     * @return String movieTitle
     * @throws SQLException
     */
    public String getMovieTitle() throws SQLException {
        String title = " ";
        ArrayList<Movie> movies = database.getMovieDB();
        for (Movie m: movies)
        {
            if (m.getMovieId() == showing.getMovieId())
            {
                title = m.getTitle();
            }
        }
        return title;
    }

    // GETTERS AND SETTERS

    public Showing getShowing(){
        return showing;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Seat> getPurchasedSeats() {
        return purchasedSeats;
    }

    public void setPurchasedSeats(ArrayList<Seat> purchasedSeats) {
        this.purchasedSeats = purchasedSeats;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public CreditCard getPaymentCard() {
        return paymentCreditCard;
    }

    public void setPaymentCard(CreditCard paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
    }




}
