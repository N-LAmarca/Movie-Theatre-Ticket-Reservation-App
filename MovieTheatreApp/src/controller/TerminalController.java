package controller;

import model.User;
import view.*;

import javax.swing.*;
import java.sql.SQLException;

/**
 * TerminalController class is used to control the terminal view.
 * It also connects to the model to pass all required information.
 */
public class TerminalController {
    TerminalView terminalView;
    DatabaseController databaseController;
    User user;

    /**
     * Constructor for a TerminalController object
     * @param user the user
     * @throws SQLException
     */
    public TerminalController(User user) throws SQLException {
        this.user = user;
        terminalView = new TerminalView();
        databaseController = new DatabaseController();
        // Shows the GUI
        terminalView.setVisible(true);
        terminalView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        terminalView.addSearchMovieListener(e -> {
            terminalView.setSearchVisibility(true);
            terminalView.clearDisplay();
            terminalView.addText("Search for a movie and press enter: \n\n");
            terminalView.addText(databaseController.displayAllMovies());
        });

        terminalView.addEnterButtonListener(e -> {
            String search = terminalView.getStringInput();
            terminalView.clearDisplay();
            terminalView.addText(databaseController.displayMovieShowingsByTitle(search));
        });

        terminalView.addBuyMovieTicketListener(e -> {
            SelectMovieController selectMovieController = new SelectMovieController(user, databaseController);
            terminalView.setVisible(false);
        });

        terminalView.addCancelTicketListener(e->{
            try{
            SelectTransactionController selectTransactionController =
                    new SelectTransactionController(user);
                terminalView.setVisible(false);}
            catch(NullPointerException err){
                terminalView.displayErrorMessage("There are no tickets under your email");
            }

        });

        terminalView.addRegisterListener(e->{
            if (user.getRegistered()){
                terminalView.displayErrorMessage("You are already registered");
            }else{
            RegisterController registerController = new RegisterController(user);
            terminalView.setVisible(false);
            }


        });

        terminalView.addViewEmailListener(e->{
            try {
                EmailController email = new EmailController(user, databaseController);
                terminalView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });


    }



















}
