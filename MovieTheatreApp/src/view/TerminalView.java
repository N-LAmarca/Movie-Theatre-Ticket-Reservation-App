package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The main termianl screen of the movie system, this screens is presented after a registered user logs in
 *  or when a guest user provides their email.
 *  Allows the user to proceed to purchase tickets, cancel tickets, browse their announcements, or register for
 *  an account.
 */
public class TerminalView extends JFrame implements ActionListener {

    JButton searchButton, buyTicketButton, cancelTicketButton, registerButton, viewEmailButton, QuitButton, enterButton;
    JTextArea display;
    JTextField inputField;
    String flag;

    public TerminalView() {
        flag = "";
        setSize(800, 650);
        setTitle("Theater Terminal Page");

        JPanel displayPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(2, 3));

        searchButton = new JButton("Search for a movie");
        buyTicketButton = new JButton("Buy a movie ticket");
        cancelTicketButton = new JButton("Cancel a movie ticket");
        registerButton = new JButton("Register");
        viewEmailButton = new JButton("View email");
        QuitButton = new JButton("Quit application");

        QuitButton.addActionListener(this);

        buttonPanel.add(searchButton);
        buttonPanel.add(buyTicketButton);
        buttonPanel.add(cancelTicketButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(viewEmailButton);
        buttonPanel.add(QuitButton);

        displayPanel.add("North", buttonPanel);

        display = new JTextArea(30, 60);
        display.setLineWrap(true);

        displayPanel.add("Center", display);

        inputField = new JTextField(20);
        inputPanel.add(inputField);
        inputField.setVisible(false);

        enterButton = new JButton("SEARCH");
        inputPanel.add(enterButton);
        enterButton.setVisible(false);

        displayPanel.add("South", inputPanel);

        add(displayPanel);
    }

    public void setVisible() {
        setVisible(true);
    }

    public void setSearchVisibility(boolean visible){
        inputField.setVisible(visible);
        enterButton.setVisible(visible);
    }

    public int getIntegerInput() {
        int temp = Integer.parseInt(inputField.getText());
        inputField.setText("");
        return temp;
    }

    public String getStringInput() {
        String temp = inputField.getText();
        inputField.setText("");
        return temp;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void clearDisplay() {
        display.setText("");
    }

    public void addText(String string) {
        display.append(string + "");
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    // BUTTON LISTENERS

    public void addEnterButtonListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }

    public void addSearchMovieListener(ActionListener e){
        searchButton.addActionListener(e);
    }

    public void addBuyMovieTicketListener(ActionListener e){
        buyTicketButton.addActionListener(e);// goes to seats vioew
    }

    public void addCancelTicketListener(ActionListener e){
        cancelTicketButton.addActionListener(e);
    }

    public void addRegisterListener(ActionListener e){
        registerButton.addActionListener(e);
    }

    public void addViewEmailListener(ActionListener e){
        viewEmailButton.addActionListener(e);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == QuitButton) {
            System.exit(0);
        }
    }

}
