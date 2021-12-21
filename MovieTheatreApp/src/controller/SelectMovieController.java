package controller;

import model.Movie;
import model.User;
import view.SelectMovieView;

import javax.swing.*;
import java.sql.SQLException;

/**
 * SelectMovieController class is used to control the select movie view.
 * It also connects to the model to pass all required information.
 */
public class SelectMovieController {
    /**
     * The user using the program
     */
    private User user;
    /**
     * The user selected movie
     */
    private Movie movie;
    /**
     * controller for the database to handle interactions with databse
     */
    DatabaseController databaseController;

    public SelectMovieController(User user, DatabaseController databaseController) {

        this.user = user;

        SelectMovieView selectMovieView = new SelectMovieView(databaseController.getMovieList());
        selectMovieView.setVisible(true);
        selectMovieView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Returns to the termina screen when back button is pressed
        selectMovieView.addBackButtonListener(e -> {
            try {
                TerminalController terminalController = new TerminalController(this.user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            selectMovieView.setVisible(false);
        });
        // Proceeds to selecting a showing when a movie is selected and proceed button is pressed
        selectMovieView.addProceedButtonListener(e -> {
            int index = selectMovieView.getListIndex();
            movie = databaseController.getMovieList().get(index);
            SelectShowingController selectShowingController = new SelectShowingController(this.user, movie);
            selectMovieView.setVisible(false);
        });

    }
}
