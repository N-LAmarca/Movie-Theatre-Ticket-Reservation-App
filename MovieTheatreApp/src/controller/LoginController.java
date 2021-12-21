package controller;

import view.*;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

/**
 * LoginController class is used to control the login view.
 * It also connects to the model to pass all required information.
 */
public class LoginController {
    /**
     * The login screen
     */
    private LoginView loginView;
    /**
     * the user using the program
     */
    private User currentUser;

    public LoginController(){
        // create login view
        loginView = new LoginView();
        // Shows the login GUI
        loginView.setVisible(true);
        loginView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /**
         * takes the account and password the user has entered and authenticates the user, if successful, the
         *  program will proceed to the terminal screen
         */
        this.loginView.addAuthenticationListener(e -> {
            String emailInput = loginView.getUsername();
            String passwordInput = loginView.getPassword();

            try {
                currentUser = LoginChecker.AuthenticateRegisteredUser(emailInput, passwordInput);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if(currentUser == null) {
                loginView.setErrorLabel("User not found.");
            }
            else {
                try {
                    currentUser.retrieveCreditCard();
                    currentUser.retrieveUserTransactions();
                    currentUser.retrieveUserCredits();
                    TerminalController terminalController = new TerminalController(currentUser);
                    loginView.setVisible(false);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }


        });
    }
}
