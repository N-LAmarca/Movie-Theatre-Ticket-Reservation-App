package controller;

import model.User;
import view.CancelSuccessView;

import javax.swing.*;
import java.sql.SQLException;

/**
 * CancelSuccessController class is used to control the cancel success view.
 * It also connects to the model to pass all required information.
 */
public class CancelSuccessController {
    User user;

    public CancelSuccessController(User user){
        this.user = user;
        CancelSuccessView cancelSuccessView = new CancelSuccessView();
        cancelSuccessView.setVisible(true);
        cancelSuccessView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Adds an action listener that allows the user to return to the terminal view after back button is pressed.
        cancelSuccessView.addReturnButtonListener(e->{
            try {
                TerminalController terminalController = new TerminalController(user);
                cancelSuccessView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


    }
}
