package controller;

import view.*;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WelcomeController class is used to control the welcome view.
 * It also connects to the model to pass all required information.
 */
public class WelcomeController {

    WelcomeView welcomeView;

    /**
     * Constructor for a WelcomeController object.
     * @param theTerminal the instance of the WelcomeView
     */
    public WelcomeController(WelcomeView theTerminal) {

        this.welcomeView = theTerminal;

        this.welcomeView.addLoginButtonListener(e -> {
            LoginController loginController = new LoginController();
            welcomeView.setVisible(false);
        });

        this.welcomeView.addGuestButtonListener(e -> {
            GuestController guestController = new GuestController();
            welcomeView.setVisible(false);
        });




    }

}
