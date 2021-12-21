package model;

import javax.xml.crypto.Data;
import java.net.IDN;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Database class implements a singleton method.
 * From this class all calls to the MySQL database can be made
 * and the database populates the java object arrays here.
 * These arrays can then be used in other java classes
 * to access the objects.
 */
public class Database {

    private static Database database;
    private ArrayList<Movie> movieDB;
    private ArrayList<Seat> seatDB;
    private ArrayList<User> userDB;
    private ArrayList<RegisteredUser> registeredUserDB;
    private ArrayList<Transaction> transactionDB;
    private ArrayList<Showing> showingDB;
    private JDBCConnect myJDBC;
    private ArrayList<MovieCredit> creditList;

    /**
     * Constructor for a Database Object.
     * @throws SQLException
     */
    private Database() throws SQLException {
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
    }

    /**
     * Makes the Database a Singleton class ( only one shared instance allowed).
     * @return database the single instance of the database
     * @throws SQLException
     */
    public static Database getDatabase() throws SQLException {
        if(database == null){
            database = new Database();
        }
        return database;
    }

    /**
     * Fills the ArrayList of seats (seatDB) with all seats in the database.
     * @throws SQLException
     */
    private void initializeSeats() throws SQLException {
        seatDB = myJDBC.allSeatsSetStatement();
    }

    /**
     * Fills the ArrayList of transactions (transactionDB) with all transactions in the database.
     * @throws SQLException
     */
    private void initializeTransactions() throws SQLException {
        transactionDB = myJDBC.transactionSetStatement();
    }

    /**
     * Fills the ArrayList of movies (movieDB) with all movies in the database.
     * @throws SQLException
     */
    private void initializeMovies() throws SQLException {
        movieDB = myJDBC.movieSetStatement();
    }

    /**
     * Fills the ArrayList of users (userDB) with all users in the database.
     * @throws SQLException
     */
    private void initializeUsers() throws SQLException {
        userDB = myJDBC.userSetStatement();
    }

    /**
     * Fills the ArrayList of registered users (registereduserDB) with all transactions in the database.
     * @throws SQLException
     */
    private void initializeRegisteredUsers() throws SQLException {
        registeredUserDB = myJDBC.registeredUserSetStatement();
    }

    /**
     * Fills the ArrayList of showings (showingDB) with all showings in the database.
     * @throws SQLException
     */
    private void initializeShowings() throws SQLException {
        showingDB = myJDBC.allShowingsSetStatement();
    }

    /**
     *  Gets the movieDB.
     * @return movieDB the ArrayList of movies
     * @throws SQLException
     */
    public ArrayList<Movie> getMovieDB() throws SQLException {
        initializeMovies();
        return movieDB;
    }

    /**
     *  Gets the transactionDB.
     * @return transactionDB the ArrayList of transactions
     * @throws SQLException
     */
    public ArrayList<Transaction> getTransactionDB() throws SQLException {
        initializeTransactions();
        return transactionDB;
    }

    /**
     *  Gets the seatDB.
     * @return seatDB the ArrayList of seats
     * @throws SQLException
     */
    public ArrayList<Seat> getSeatDB() throws SQLException {
        initializeSeats();
        return seatDB;
    }

    /**
     *  Gets the userDB.
     * @return userDB the ArrayList of users
     * @throws SQLException
     */
    public ArrayList<User> getUserDB() throws SQLException {
        initializeUsers();
        return userDB;
    }
    /**
     *  Gets the registeredUserDB.
     * @return registeredUserDB the ArrayList of registered users
     * @throws SQLException
     */
    public ArrayList<RegisteredUser> getRegisteredUserDB() throws SQLException {;
        initializeRegisteredUsers();
        return registeredUserDB;
    }

    /**
     *  Gets the showingDB.
     * @return showingDB the ArrayList of showings
     * @throws SQLException
     */
    public ArrayList<Showing> getShowingDB() throws SQLException {
        initializeShowings();
        return showingDB;
    }

    /**
     *  Adds a User to the database.
     * @param email the email of the user
     * @throws SQLException
     */
    public void addUser(String email) throws SQLException {
        Boolean isRegistered = false;
        String name = "";
        String address = "";
        String password = "";
        Boolean activeStatus = false;
        Date lastPaymentDate = null;
        myJDBC.addUserToDB(email, isRegistered,
                name, address, password,
                activeStatus, lastPaymentDate);
        userDB = myJDBC.userSetStatement();
    }

    /**
     * Registers a user and updates in the database.
     * @param userId the id of the user
     * @param name the name of the user
     * @param address the address of the user
     * @param password the user's password
     * @param lastPaymentDate the last date of payment of the registration fee
     * @throws SQLException
     */
    public void registerUser(int userId, String name, String address, String password, Date lastPaymentDate)
            throws SQLException {
        myJDBC.updateRegUserInDB(userId, true,
                name, address, password,
                true, lastPaymentDate);
        initializeRegisteredUsers();
    }

    /**
     * Returns the list of messages (emails) that are for a given user
     * @param user the user who's emails want to be accessed
     * @return userMessages the list of the user's emails
     * @throws SQLException
     */
    public ArrayList<Message> getUserMessages (User user) throws SQLException
    {
        ArrayList<Message> userMessages = myJDBC.userMessageSetStatement(user);
        return  userMessages;
    }

    /**
     * Marks a message(email) as read in the database.
     * @param m the message that has been read
     * @throws SQLException
     */
    public void markAsRead(Message m) throws SQLException {
        int id = m.getMessageID();
        myJDBC.updateMessage(id);
    }

    /**
     * Adds a message to the database for a given user.
     * @param user the user receiving the message
     * @param message the message
     * @param subjectLine the type of message
     * @throws SQLException
     */
    public void addMessage(User user, String message, String subjectLine) throws SQLException {
        myJDBC.addMessageToDB(user, message, subjectLine);
    }
}
