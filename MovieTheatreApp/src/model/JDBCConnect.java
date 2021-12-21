package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * JDBC class is the main JDBC connection to the MySQL database. Using SQL queries we
 * can receive, update, add, and delete information from the database.
 */
public class JDBCConnect {

    private Connection dbConnect;

    /**
     *  Creates a connection to the database.
     */
    public void createConnection() {
        try {
            //You to enter your own SQL  username and password below to make this work!!

            //dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/MOVIESYSTEM", "root", "Katana123!");
            // dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/MOVIESYSTEM", "root", "kou19980126");
            //dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/MOVIESYSTEM", "root", "Hydrogen97!");
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/MOVIESYSTEM", "root", "Teck5Taillight!");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all movies from the database and returns them in an ArrayList.
     * @return movieList the list of all movies present
     * @throws SQLException
     */
    public ArrayList<Movie> movieSetStatement() throws SQLException {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM MOVIE;");

            while (results.next()) {
                int movieId = results.getInt("MovieID");
                String userName = results.getString("Title");
                String password = results.getString("Synopsis");

                Movie myMovie = new Movie(movieId, userName, password);
                movieList.add(myMovie);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    /**
     * Gets all showings from the database for a given movie and returns them in an ArrayList.
     * @param  movieId the Id of the movie for which the showings are to be found
     * @return showingList the list of all showings present for a given movie
     * @throws SQLException
     */
    public ArrayList<Showing> showingSetStatement(int movieId) throws SQLException {
        ArrayList<Showing> showingList = new ArrayList<Showing>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM SHOWING WHERE MovieID = \"" + movieId + "\";");

            while (results.next()) {
                int showingId = results.getInt("ShowingID");
                Date showTime = results.getDate("ShowTime");
                double ticketPrice = results.getDouble("TicketPrice");
                int theatreId = results.getInt("TheatreID");

                Showing myShowing = new Showing(showingId, movieId, showTime, ticketPrice, theatreId);
                showingList.add(myShowing);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showingList;
    }

    /**
     * Gets all showings from the database and returns them in an ArrayList.
     * @return showingList the list of all showings present
     * @throws SQLException
     */
    public ArrayList<Showing> allShowingsSetStatement() throws SQLException {
        ArrayList<Showing> showingList = new ArrayList<Showing>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM SHOWING;");

            while (results.next()) {
                int movieId = results.getInt("MovieID");
                int showingId = results.getInt("ShowingID");
                Date showTime = results.getDate("ShowTime");
                double ticketPrice = results.getDouble("TicketPrice");
                int theatreId = results.getInt("TheatreID");

                Showing myShowing = new Showing(showingId, movieId, showTime, ticketPrice, theatreId);
                showingList.add(myShowing);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showingList;
    }

    /**
     * Gets all users from the database and returns them in an ArrayList.
     * @return userList the list of all users present
     * @throws SQLException
     */
    public ArrayList<User> userSetStatement() throws SQLException {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM USER");

            while (results.next()) {
                int userId = results.getInt("UserId");
                String email = results.getString("Email");
                Boolean isRegistered = results.getBoolean("IsRegistered");
                User myUser = new User(userId, email, isRegistered);
                userList.add(myUser);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Gets all registered users from the database and returns them in an ArrayList.
     * @return registeredUserList the list of all registered users present
     * @throws SQLException
     */
    public ArrayList<RegisteredUser> registeredUserSetStatement() throws SQLException {
        ArrayList<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM USER");

            while (results.next()) {
                int userId = results.getInt("UserId");
                String email = results.getString("Email");
                Boolean isRegistered = results.getBoolean("IsRegistered");
                String name = results.getString("Name");
                String address = results.getString("Address");
                String password = results.getString("Password");
                Boolean activeStatus = results.getBoolean("ActiveStatus");
                Date lastPaymentDate = results.getDate("LastPaymentDate");

                RegisteredUser myUser = new RegisteredUser(userId, email, isRegistered, name,
                        address, password,
                        activeStatus, lastPaymentDate);
                registeredUserList.add(myUser);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredUserList;
    }

    /**
     * Gets all seats for a particular showing from the database and returns them in an ArrayList.
     * @param showingId the id of the desired showing
     * @return seatList the list of all seats for the given showing
     * @throws SQLException
     */
    public ArrayList<Seat> seatSetStatement(int showingId) throws SQLException {
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM SEATS WHERE ShowingID = \"" + showingId + "\";");

            while (results.next()) {
                String rownum = results.getString("rownum");
                int colnum = results.getInt("colnum");
                int transactionID = results.getInt("TransactionID");

                Seat mySeat = new Seat(showingId, rownum, colnum, transactionID);
                seatList.add(mySeat);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

    /**
     * Gets all seats from the database and returns them in an ArrayList.
     * @return seatList the list of all seats present
     * @throws SQLException
     */
    public ArrayList<Seat> allSeatsSetStatement() throws SQLException {
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM SEATS;");

            while (results.next()) {
                int showingId = results.getInt("ShowingID");
                String rownum = results.getString("rownum");
                int colnum = results.getInt("colnum");
                int transactionID = results.getInt("TransactionID");

                Seat mySeat = new Seat(showingId, rownum, colnum, transactionID);
                seatList.add(mySeat);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatList;
    }

    /**
     * Gets all movie credits for a particular user from the database and returns them in an ArrayList.
     * @param userId the id of the given user
     * @return creditList the list of all movie credits for the given user
     * @throws SQLException
     */
    public ArrayList<MovieCredit> creditSetStatement(int userId) throws SQLException {
        ArrayList<MovieCredit> creditList = new ArrayList<MovieCredit>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM MovieCredits WHERE UserID = \"" + userId + "\";");

            while (results.next()) {
                int movieCreditId = results.getInt("CreditID");
                String creditCode = results.getString("CreditCode");
                Date expiryDate = results.getDate("ExpiryDate");
                double amount = results.getDouble("Amount");

                MovieCredit myMovieCredit = new MovieCredit(movieCreditId, creditCode, expiryDate,
                amount, userId);
                creditList.add(myMovieCredit);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditList;
    }

    /**
     /**
     * Gets all transactions for a particular user from the database and returns them in an ArrayList.
     * @param id the id of the given user
     * @return transactionList the list of all transactions for the given user
     * @throws SQLException
     */
    public ArrayList<Transaction> transactionsSetStatement(int id) throws SQLException {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Transaction WHERE UserID = \"" + id + "\";");

            while (results.next()) {
                int transactionId = results.getInt("TransactionID");
                int userId = results.getInt("UserID");
                double cost = results.getDouble("Cost");
                Date purchaseDate = results.getDate("PurchaseDate");
                int cardId = results.getInt("CardID");
                int showingId = results.getInt("ShowingID");

                Transaction myTransaction = new Transaction(transactionId, userId, cost, purchaseDate, cardId, showingId);
                transactionList.add(myTransaction);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    /**
     *  Adds a user to the database.
     * @param email the user's email address
     * @param isRegistered if the user is registerd or not
     * @param name the name of the user
     * @param address the user's address
     * @param password the user's password
     * @param activeStatus if the registerd account is active
     * @param lastPaymentDate the last payment made for the account fee
     * @throws SQLException
     */
    public void addUserToDB(String email, Boolean isRegistered,
                            String name, String address, String password,
                            Boolean activeStatus, Date lastPaymentDate) throws SQLException {
        String query = "INSERT INTO USER " +
                "(IsRegistered, Name, Address, Email, Password, ActiveStatus, LastPaymentDate) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
        preparedStmt.setBoolean(1, isRegistered);
        preparedStmt.setString (2, name);
        preparedStmt.setString (3, address);
        preparedStmt.setString (4, email);
        preparedStmt.setString (5, password);
        preparedStmt.setBoolean (6, activeStatus);
        preparedStmt.setDate (7, (java.sql.Date) lastPaymentDate);

        // execute the prepared statement
        preparedStmt.execute();
        preparedStmt.close();
    }

    /**
     * Updates a registered user in the database.
     * @param userId the id of the user account
     * @param isRegistered if the user is registerd or not
     * @param name the name of the user
     * @param address the user's address
     * @param password the user's password
     * @param activeStatus if the registerd account is active
     * @param lastPaymentDate the last payment made for the account fee
     * @throws SQLException
     */
    public void updateRegUserInDB(int userId, Boolean isRegistered,
                            String name, String address, String password,
                            Boolean activeStatus, Date lastPaymentDate) throws SQLException {
        String query = "UPDATE USER SET IsRegistered = ?, Name = ?, Address = ?, Password = ?, ActiveStatus = ?, LastPaymentDate = ? WHERE UserID = ?";
        PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
        preparedStmt.setBoolean(1, isRegistered);
        preparedStmt.setString (2, name);
        preparedStmt.setString (3, address);
        preparedStmt.setString (4, password);
        preparedStmt.setBoolean (5, activeStatus);
        preparedStmt.setDate (6, (java.sql.Date) lastPaymentDate);
        preparedStmt.setInt (7, userId);

        // execute the prepared statement
        preparedStmt.execute();
        preparedStmt.close();
    }

    /**
     *  Adds a transaction to the database
     * @param user the user who made the transaction
     * @param totalCost the total cost of the transaction
     * @param creditCard the card used on the transaction
     * @param showingId the id of the showing the transaction was for
     * @throws SQLException
     */
    public int addTransactionToDB(User user, double totalCost, CreditCard creditCard, int showingId) throws SQLException {
        String query = "INSERT INTO TRANSACTION " +
                "(UserID, Cost, PurchaseDate, CardID, ShowingID) " +
                "values (?, ?, ?, ?, ?)";

        //SQl Date
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        PreparedStatement preparedStmt = dbConnect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, user.getUserId());
        preparedStmt.setFloat (2, (float)totalCost);
        preparedStmt.setDate (3, sqlDate);
        preparedStmt.setInt (4, creditCard.getCardId());
        preparedStmt.setInt (5, showingId);

        // execute the prepared statement
        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating transaction failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int toReturn = generatedKeys.getInt(1);
                preparedStmt.close();
                return toReturn;
            }
            else {
                throw new SQLException("Creating transaction failed, no ID obtained.");
            }
        }
    }

    /**
     *  Adds a CreditCard to the database.
     * @param userId the user of the card
     * @param cardHolderName the name of the cardholder
     * @param cardNumber the card number
     * @param expiryMonth the expiration month of the card
     * @param expiryYear the expiration year of the card
     * @param cvv the cvv number of the card
     * @throws SQLException
     */
    public int addCreditCardToDB(int userId, String cardHolderName, String cardNumber, int expiryMonth, int expiryYear, int cvv) throws SQLException {
        String query = "INSERT INTO CREDITCARD " +
                "(UserID, CardHolderName, cardNumber, expiryMonth, expiryYear, cvv) " +
                "values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = dbConnect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, userId);
        preparedStmt.setString(2, cardHolderName);
        preparedStmt.setString(3, cardNumber);
        preparedStmt.setInt(4, expiryMonth);
        preparedStmt.setInt(5, expiryYear);
        preparedStmt.setInt(6, cvv);

        // execute the prepared statement
        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating creditCard failed, no rows affected.");
        }


        try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int toReturn = generatedKeys.getInt(1);
                preparedStmt.close();
                return toReturn;
            }
            else {
                throw new SQLException("Creating creditCard failed, no ID obtained.");
            }
        }

    }

    /**
     * Returns the CreditCard associated wit hthe given userId.
     * @param userId the id of the user
     * @return creditCard the associated CreditCard
     * @throws SQLException
     */
    public CreditCard getCreditCardByUserId(int userId) throws SQLException {
        CreditCard creditCard = null;
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM CREDITCARD WHERE UserID = \"" + userId + "\";");

            while (results.next()) {
                int cardId = results.getInt("CardID");
                String cardHolderName = results.getString("CardHolderName");
                String cardNumber = results.getString("cardNumber");
                int expiryMonth = results.getInt("expiryMonth");
                int expiryYear = results.getInt("expiryYear");
                int cvv = results.getInt("cvv");

                creditCard = new CreditCard(cardId, userId, cardHolderName, cardNumber, expiryMonth, expiryYear, cvv);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCard;
    }


    /**
     * Gets all of the Transactions in the database and returns them in an ArrayList.
     * @return transactionList the list of all Transactions present
     * @throws SQLException
     */
    public ArrayList<Transaction> transactionSetStatement() throws SQLException {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            Statement myStmt = dbConnect.createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM TRANSACTION;");

            while (results.next()) {

                int transactionId  = results.getInt("TransactionID");
                int userID = results.getInt("UserID");
                double totalCost = results.getDouble("Cost");
                Date purchaseDate = results.getDate("PurchaseDate");
                int cardID = results.getInt("CardID");
                int showingId = results.getInt("ShowingId");

                Transaction myTransaction = new Transaction(transactionId, userID,
                        totalCost, purchaseDate, cardID, showingId);
                transactionList.add(myTransaction);
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    /**
     * Updates a Seat object in the database.
     * @param showingId the id of the showing the seat is for
     * @param row the seat row
     * @param col the seat column
     * @throws SQLException
     */
    public void updateSeatDB(int showingId, String row, int col) throws SQLException {
        String query = "UPDATE SEATS SET TransactionID = ? WHERE ShowingID = \"" + showingId + "\" AND rownum = \"" + row + "\"AND colnum = \"" + col + "\";";
        PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
        preparedStmt.setObject(1, null);
        preparedStmt.execute();
        preparedStmt.close();
    }

    /**
     * Updates the transactionId of a Seat object in the database.
     * @param transactionId the new transaction id value
     * @param showingId the id of the showing the seat is for
     * @param row the seat row
     * @param col the seat column
     * @throws SQLException
     */
    public void updateSeatTransactionId(int transactionId, int showingId, String row, int col) throws SQLException {
        String query = "UPDATE SEATS SET TransactionID = ? WHERE ShowingID = ? AND rownum = ? AND colnum = ?";
        PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
        preparedStmt.setInt(1, transactionId);
        preparedStmt.setInt (2, showingId);
        preparedStmt.setString (3, row);
        preparedStmt.setInt (4, col);
        // execute the prepared statement
        preparedStmt.execute();
        preparedStmt.close();
    }


    /**
     *  Adds a Message to the database.
     * @param user the user who the message is for
     * @param message the message content
     * @param subjectLine the subject of the message
     * @throws SQLException
     */
    public void addMessageToDB(User user, String message, String subjectLine) throws SQLException {
        String query = "INSERT INTO MESSAGE " +
                "(userID, Message, SubjectLine, SentDate, ReadStatus) " +
                "values (?, ?, ?, ?, ?)";

        //SQl Date
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        PreparedStatement preparedStmt = dbConnect.prepareStatement(query);
        preparedStmt.setInt(1, user.getUserId());
        preparedStmt.setString (2, message);
        preparedStmt.setString (3, subjectLine);
        preparedStmt.setDate (4, sqlDate);
        preparedStmt.setBoolean (5, false);

        // execute the prepared statement
        preparedStmt.execute();
        preparedStmt.close();

    }

    /**
     * Adds a Movie Credit to the database.
     * @param creditCode the code for the MovieCredit
     * @param expiryDate the expiration date of the MovieCredit
     * @param amount the amount of the MovieCredit
     * @param userId the id of the user receiving the MovieCredit
     * @throws SQLException
     */
    public int addMovieCreditToDB(String creditCode, Date expiryDate,
                                  double amount, int userId) throws SQLException {
        String query = "INSERT INTO MovieCredits (CreditCode, ExpiryDate, Amount, UserID) " +
                "values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = dbConnect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, creditCode);
        preparedStmt.setTimestamp(2, (new java.sql.Timestamp(expiryDate.getTime())));
        preparedStmt.setDouble(3, amount);
        preparedStmt.setInt(4, userId);

        // execute the prepared statement
        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Adding movie credit to database failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int toReturn = generatedKeys.getInt(1);
                preparedStmt.close();
                return toReturn;
            } else {
                throw new SQLException("Adding movie credit to database failed, no ID obtained.");
            }
        }
    }

    /**
     * Gets all Messages for a specific user from the database.
     * @param user the user who's messages are to be retrieved
     * @return userMessageList the list of the user's messages
     * @throws SQLException
     */
    public ArrayList<Message> userMessageSetStatement(User user) throws SQLException {
        ArrayList<Message> userMessageList = new ArrayList<Message>();
        int id = user.getUserId();
        try {
           String query = "SELECT * FROM MESSAGE WHERE UserID = ?";
            PreparedStatement myStmt = this.dbConnect.prepareStatement(query);
            myStmt.setInt(1, id);
            ResultSet results = myStmt.executeQuery();
            while (results.next()) {
                if (results.getInt("UserID") == id) {
                    Message foundMessage = new Message();
                    foundMessage.setUserID(id);
                    foundMessage.setMessageID(results.getInt("MessageID"));
                    foundMessage.setMessage(results.getString("Message"));
                    foundMessage.setSubjectLine(results.getString("SubjectLine"));
                    foundMessage.setSentDate(results.getDate("SentDate"));
                    foundMessage.setReadStatus(results.getBoolean("ReadStatus"));
                    userMessageList.add(foundMessage);
                }
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMessageList;
    }

    /**
     * Updates a message in the database as ;read;.
     * @param messageId the id of the message to be updated
     * @throws SQLException
     */
    public void updateMessage(int messageId) throws SQLException {

        String query = "UPDATE MESSAGE SET ReadStatus = ? WHERE MessageID = ?";
        PreparedStatement myStmt = dbConnect.prepareStatement(query);
        myStmt.setBoolean(1, true);
        myStmt.setInt(2, messageId);

        // execute the prepared statement
        myStmt.execute();
        myStmt.close();

    }

}

