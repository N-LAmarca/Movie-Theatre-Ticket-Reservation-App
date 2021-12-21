package view;

import model.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The success screen after a purchase ticket transaction is successful and complete.
 */
public class TransactionConfirmationView extends JFrame {
    JLabel thanksLabel, infoLabel;
    JButton returnButton;

    public TransactionConfirmationView() {

        setSize(800, 650);
        setTitle("Theater Terminal Page");

        JPanel displayPanel = new JPanel();
        JPanel textPanel = new JPanel();

        thanksLabel = new JLabel("Thanks for your Purchase!", SwingConstants.CENTER);
        infoLabel = new JLabel("Your transaction details have been emailed to you!", SwingConstants.CENTER);
        returnButton = new JButton("Return to main");

        displayPanel.setLayout(new GridLayout(3,1));
        displayPanel.add(thanksLabel);
        displayPanel.add(infoLabel);
        displayPanel.add(returnButton);
        add(displayPanel);
    }

    public void addReturnButtonListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }

}
