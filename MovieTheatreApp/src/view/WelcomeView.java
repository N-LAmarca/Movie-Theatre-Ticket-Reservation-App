package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The first page the user
 */
public class WelcomeView extends JFrame implements ActionListener {
    private JButton continueAsGuestButton;
    private JButton loginButton;

    public WelcomeView() {
        setSize(400, 300);
        setTitle("Theatre Welcome Page");

        JPanel buttonPanel = new JPanel();
        JPanel displayPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(2, 1));

        continueAsGuestButton = new JButton("Continue As Guest");
        loginButton = new JButton("Login");

        buttonPanel.add(continueAsGuestButton);
        buttonPanel.add(loginButton);

        displayPanel.add("Center", buttonPanel);

        add(displayPanel);

    }

    public void addLoginButtonListener(ActionListener listenerForLoginButton) {
        loginButton.addActionListener(listenerForLoginButton);
    }

    public void addGuestButtonListener(ActionListener listenerForGuestButton) {
        continueAsGuestButton.addActionListener(listenerForGuestButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
