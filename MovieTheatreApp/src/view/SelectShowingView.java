package view;

import model.Showing;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Allows the user to select a desired showing to purchase tickets for, this window is shown after
 *  the user selects a movie
 */
public class SelectShowingView extends JFrame {

    JButton backButton, proceedButton;
    JList display;

    public SelectShowingView(ArrayList<Showing> showings) {

        setSize(800, 650);
        setTitle("Theater Terminal Page");

        JPanel displayPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        displayPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1, 2));
        displayPanel.setLayout(new BorderLayout());

        backButton = new JButton("Back");
        proceedButton = new JButton("Proceed");

        buttonPanel.add(backButton);
        buttonPanel.add(proceedButton);
        JLabel title = new JLabel("Please select a showing");
        inputPanel.add(title);
        display = new JList(showings.toArray());
        display.setVisibleRowCount(20);
        display.setFixedCellHeight(20);
        display.setFixedCellWidth(500);

        JScrollPane sp = new JScrollPane(display);




        displayPanel.add("South", buttonPanel);
        displayPanel.add("Center", display);
        displayPanel.add("North", inputPanel);
        add(displayPanel);

    }

    public int getListIndex() {
        return display.getSelectedIndex();
    }

    // BUTTON LISTENERS
    public void addProceedButtonListener(ActionListener listener) {
        proceedButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener){
        backButton.addActionListener(listener);
    }

}

