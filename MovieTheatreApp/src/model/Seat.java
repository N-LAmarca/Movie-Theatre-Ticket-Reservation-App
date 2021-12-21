package model;

import java.sql.SQLException;

/**
 * Seat class can be used to access any information for the seats for any given showing.
 * This class is populated from the database, and can be accessed with getter and setter methods.
 * It can also be displayed with the toString method.
 */
public class Seat {

    int showingId;
    String row;
    int col;
    int transactionID;
    JDBCConnect myJDBC;

    /**
     * initialize seat object
     *
     * @param showingId
     * @param row
     * @param col
     * @param transactionID
     */
    public Seat(int showingId, String row, int col, int transactionID) {
        this.showingId = showingId;
        this.row = row;
        this.col = col;
        this.transactionID = transactionID;
    }

    /**
     * Set seat transactionId and update the DB
     *
     * @param transactionID
     * @throws SQLException
     */
    public void setTransactionID(int transactionID) throws SQLException {
        this.transactionID = transactionID;
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        myJDBC.updateSeatTransactionId(transactionID, showingId, row, col);
    }

    // GETTERS AND SETTERS AND TOSTRING

    public int getShowingId() {
        return showingId;
    }

    public void setShowingId(int showingId) {
        this.showingId = showingId;
    }

    public int getTransactionID() {
        return transactionID;
    }


    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "showingId=" + showingId +
                ", row='" + row + '\'' +
                ", col=" + col +
                ", transactionID=" + transactionID +
                '}' + '\n';
    }
}

