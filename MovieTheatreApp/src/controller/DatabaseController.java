package controller;

import model.*;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CancelSuccessController class is used to control information moving in and out of the database
 * It also connects to the model to pass all required information.
 */
public class DatabaseController {
    /**
     * list of movies
     */
    ArrayList<Movie> movieList;
    /**
     * The database object
     */
    Database database;

    /**
     * populates the database
     * @throws SQLException
     */
    public DatabaseController() throws SQLException {
        // PULL ALL MOVIES INTO MOVIE LIST
        database = Database.getDatabase();
        movieList = database.getMovieDB();
    }

    /**
     * converts the list of movies into a string
     * @return a string containing the name and description of the movies
     */
    public String displayAllMovies(){
        StringBuilder movies = new StringBuilder();
        for(Movie movie: movieList){
            movies.append(movie.getTitle()).append("\n").append(movie.getDescription()).append("\n\n");
        }
        return movies.toString();
    }


    /**
     * Searches for a movie based on its title
     * @param search the movie title to search
     * @return movie the movie object found
     */
    public Movie getMovieByTitle(String search) {
        for(Movie movie: movieList){
            if(search.equals(movie.getTitle())){
                return movie;
            }
        }
        return null;
    }

    /**
     * Gets the showings belonging to a movie
     * @param movie the movie to search showings for
     * @return the movie's showings
     */
    public ArrayList<Showing> getMovieShowingsByTitle(Movie movie) {
        return movie.getShowings();
    }

    /**
     * displays a movie and its showings by searching with movie title
     * @param search the movie's title
     * @return string containing movie title and showing information
     */
    public String displayMovieShowingsByTitle(String search){

        Movie movie = getMovieByTitle(search);
        if(movie == null){
            return "Movie not found!";
        }
        ArrayList<Showing> showingList = getMovieShowingsByTitle(movie);
        StringBuilder showings = new StringBuilder();

        showings.append(movie.getTitle()).append("\n").append(movie.getDescription()).append("\n\n");

        for(Showing show: showingList){
            showings.append("Show ID: ").append(show.getShowingId()).append("\n")
                    .append("Show Time: ").append(show.getShowTime()).append("\n")
                    .append("Ticket Price: ").append(show.getTicketPrice()).append("\n\n");
        }
        return showings.toString();

    }

    /**
     * gets the list of all movies
     * @return movieList the list of all movies
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    /**
     * Gets the list of emails for a user
     * @param user the user to get emails for
     * @return userMail the list of emails belonging to the user
     * @throws SQLException
     */
    public ArrayList<Message> getUserMail(User user) throws SQLException {
        ArrayList<Message> userMail = database.getUserMessages(user);
        return  userMail;
    }

    /**
     * updates a message's status as read if the user have read it
     * @param m the message
     * @throws SQLException
     */
    public void updateReadStatus(Message m) throws SQLException {
        database.markAsRead(m);
    }

    /**
     * adds a message to the database
     * @param user
     * @param message
     * @param subjectLine
     * @throws SQLException
     */
    public void addMessageToDB (User user, String message, String subjectLine) throws SQLException {
        database.addMessage(user, message, subjectLine);

    }

}
