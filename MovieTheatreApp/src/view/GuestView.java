package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view prompting a guest user to provide an email
 */
public class GuestView extends JFrame {

    private JTextField inputField;
    private JButton enterButton;
    private JLabel guestLabel;

    public GuestView() {
        setSize(400, 300);
        setTitle("Theatre Guest Page");

        JPanel displayPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new GridLayout(3, 1));

        guestLabel = new JLabel("Email");

        inputPanel.add(guestLabel);

        inputField = new JTextField(25);

        inputPanel.add(inputField);

        displayPanel.add("North", buttonPanel);

        enterButton = new JButton("ENTER");
        inputPanel.add(enterButton);

        displayPanel.add("South", inputPanel);

        add(displayPanel);

    }

    public void addEnterListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }

    public String getEmail(){
        return inputField.getText();
    }

}
