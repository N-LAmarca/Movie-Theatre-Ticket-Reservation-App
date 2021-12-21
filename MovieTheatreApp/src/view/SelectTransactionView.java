package view;

import controller.SelectTransactionController;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the window which lets users to select a transaction to cancel tickets from.
 *  Allows users to cancel selected seats in this transaction by proceeding to the next view
 *  or cancel all seats within this transaction
 */
public class SelectTransactionView extends JFrame{
    JButton backButton, cancelAllButton, cancelOneButton;
    JList display;
    ArrayList<Transaction> transactions;
    public SelectTransactionView(ArrayList<Transaction> transactions){
        this.transactions = transactions;
        setSize(800, 650);
        setTitle("Select a transaction to cancel");

        JPanel displayPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel titlePanel = new JPanel();

        displayPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(1, 2));
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new GridLayout(2,1));
        backButton = new JButton("Back");
        cancelAllButton= (new JButton("Cancel all seats on transaction"));
        cancelOneButton = new JButton("Cancel selected seats on transaction");
        buttonPanel2.add(cancelOneButton);
        buttonPanel2.add(cancelAllButton);
        buttonPanel.add(backButton);
        buttonPanel.add(buttonPanel2);
        titlePanel.add(new JLabel("Please select a transaction to cancel"));

        display = new JList(transactions.toArray());
        display.setVisibleRowCount(20);
        display.setFixedCellHeight(20);
        display.setFixedCellWidth(500);
        JScrollPane sp = new JScrollPane(display);

        displayPanel.add("Center", sp);
        displayPanel.add("South", buttonPanel);
        displayPanel.add("North", titlePanel);
        add(displayPanel);
        setVisible(true);
    }

    public void addProceedButtonListener(ActionListener e){
        cancelOneButton.addActionListener(e);
    }
    public void addCancelAllButtonListener(ActionListener e){
        cancelAllButton.addActionListener(e);
    }
    public void addBackButtonListener(ActionListener e){
        backButton.addActionListener(e);
    }

    public Transaction getSelectedTransaction(){
        if(this.getSelectedIndex()==-1){
            return null;
        }
        return transactions.get(this.getSelectedIndex());
    }
    public int getSelectedIndex(){
        return display.getSelectedIndex();
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }




}
