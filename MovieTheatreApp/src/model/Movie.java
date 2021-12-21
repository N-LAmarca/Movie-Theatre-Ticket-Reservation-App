package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Movie class is used to store and allow access to the
 * individual movie information from the database.
 * Has a constructor method  as well as all required getters and setters,
 * and a toString override method.
 */
public class Movie {

    int movieId;
    String title;
    String description;
    ArrayList<Showing> showings;

    private JDBCConnect myJDBC;

    /**
     * Constructor that connects to JDBC and pulls its corresponding Showings
     *
     * @param movieId
     * @param title
     * @param synopsis
     * @throws SQLException
     */
    public Movie(int movieId, String title, String synopsis) throws SQLException {
        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        setMovieId(movieId);
        setTitle(title);
        setDescription(synopsis);
        initializeShowings();
    }

    /**
     * Pulls Showings of move based on movieId
     * @throws SQLException
     */
    public void initializeShowings() throws SQLException {
        showings = myJDBC.showingSetStatement(movieId);
    }

    /**
     * add a showing to the Movies showings
     *
     * @param theShowing
     */
    public void addShowing(Showing theShowing)
    {
        showings.add(theShowing);
    }

    // GETTERS AND SETTERS AND TOSTRING

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Showing> getShowings() {
        return showings;
    }

    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

}
