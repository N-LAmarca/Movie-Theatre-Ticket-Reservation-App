package controller;

import model.Database;
import model.LoginChecker;
import model.User;
import view.GuestView;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * GuestController  class is used to control the guest sign in view.
 * It also connects to the model to pass all required information.
 */
public class GuestController {

    //the user using the program that is not registered
    private User ordinaryUser;

    public GuestController() {
        GuestView guestView = new GuestView();
        guestView.setVisible(true);
        guestView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        guestView.addEnterListener(e -> {
            String email = guestView.getEmail();
            try {
                ordinaryUser = LoginChecker.AuthenticateOrdinaryUser(email);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if(ordinaryUser == null){
                // CREATE USER HERE
                try {
                    Database database = Database.getDatabase();
                    database.addUser(email);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // ASSIGN NEW USER TO ordinaryUSER
                try {
                    ordinaryUser = LoginChecker.AuthenticateOrdinaryUser(email);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
            try {
                ordinaryUser.retrieveUserTransactions();
                ordinaryUser.retrieveUserCredits();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                TerminalController terminalController = new TerminalController(ordinaryUser);
                guestView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }


}
