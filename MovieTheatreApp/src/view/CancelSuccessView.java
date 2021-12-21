package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This view is shown after a ticket cancellation is complete and successful
 */
public class CancelSuccessView extends JFrame {
    JLabel confirmLabel;
    JButton returnButton;

    public CancelSuccessView() {

        setSize(800, 650);
        setTitle("Cancellation success");

        JPanel displayPanel = new JPanel();
        JPanel textPanel = new JPanel();

        confirmLabel = new JLabel("Cancellation successful!", SwingConstants.CENTER);

        returnButton = new JButton("Return to main");

        displayPanel.setLayout(new GridLayout(2,1));
        displayPanel.add(confirmLabel);
        displayPanel.add(returnButton);
        add(displayPanel);
    }

    public void addReturnButtonListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }

}