package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * User class can be used to access any information for all users, registered and unregistered.
 * This class is populated from the database, and can be accessed with getter and setter methods.
 * It can also be displayed with the toString method.
 */
public class User
{
    protected int userId;
    protected String email;
    protected Boolean isRegistered;
    protected ArrayList<MovieCredit> userCredit;
    protected ArrayList<Transaction> previousPurchases;
    private JDBCConnect myJDBC;

    /**
     * initialize the User object
     *
     * @param userId
     * @param email
     * @param isRegistered
     * @throws SQLException
     */
    public User(int userId, String email, Boolean isRegistered) throws SQLException {
        setUserId(userId);
        setEmail(email);
        this.isRegistered = isRegistered;

    }

    // gets Overridden in RegisteredUser
    public void retrieveCreditCard() throws SQLException {
    }

    /**
     * Retrieves a users previous transactions from the DB using their userId
     *
     * @throws SQLException
     */
    public void retrieveUserTransactions() throws SQLException{
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        this.previousPurchases = myJDBC.transactionsSetStatement(userId);
    }

    /**
     * Retrieves a users credits from the DB using their userId
     *
     * @throws SQLException
     */
    public void retrieveUserCredits() throws SQLException {
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        this.userCredit = myJDBC.creditSetStatement(userId);
    }

    // GETTERS AND SETTERS AND TOSTRING

    public CreditCard getCreditCard() {
        return null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public ArrayList<Transaction> getPreviousPurchases() {
        return previousPurchases;
    }

    public void setPreviousPurchases(ArrayList<Transaction> previousPurchases) {
        this.previousPurchases = previousPurchases;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}' + '\n';
    }


}
