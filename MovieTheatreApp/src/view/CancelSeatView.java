package view;

import model.Seat;
import model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This view allows users to choose seats which they've purchased for a speicific showing to cancel.
 *  Users can select multiple seats under the transaction and press cancel to complete teh cancellation.
 */
public class CancelSeatView extends JFrame implements ActionListener{
    JButton backButton, cancelButton;
    ArrayList<String> selectedSeats;
    ArrayList<Seat> seats;
    Transaction transaction;


    public CancelSeatView(ArrayList<Seat> seats, Transaction transaction){
        this.transaction=transaction;
        this.seats = seats;
        selectedSeats = new ArrayList<String>();
        setSize(800, 650);
        setTitle("Select a seat to cancel");
        JPanel displayPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel titlePanel = new JPanel();

        displayPanel.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(1,2));
        backButton = new JButton("Back");
        cancelButton = new JButton("Cancel selected seat");
        buttonPanel.add(backButton);
        buttonPanel.add(cancelButton);

        titlePanel.add(new JLabel("Please select a seat to cancel"));
        JPanel display = new JPanel();
//        display = new JPanel(new BorderLayout());
        display = new JPanel(new GridLayout(seats.size(), 1));
        for(Seat i: seats){
            JButton btn = new JButton( i.getRow() + i.getCol() );
            btn.addActionListener(this);
            display.add(btn);
        }

//        JScrollPane sp = new JScrollPane(display);
        displayPanel.add("Center", display);
        displayPanel.add("South", buttonPanel);
        displayPanel.add("North", titlePanel);
        add(displayPanel);
        setVisible(true);


    }
    public void addBackButtonListener(ActionListener e){
        backButton.addActionListener(e);
    }
    public void addCancelButtonListener(ActionListener e){
        cancelButton.addActionListener(e);
    }



    public void actionPerformed(ActionEvent e){
        for(String seat:selectedSeats) {
            if(seat.equals(((JButton)(e.getSource())).getText())){
                selectedSeats.remove(seat);
                ((JButton)e.getSource()).setBackground(null);
                return;
            }

        }
        selectedSeats.add(((JButton)e.getSource()).getText());
        ((JButton)e.getSource()).setBackground(Color.lightGray);
    }

    public ArrayList<Seat> getCancelledSeats(){
        ArrayList<Seat> cancelledSeats = new ArrayList();
        if(selectedSeats.size()==0){
            return null;
        }

        for (String i: selectedSeats){
            for(int j = 0; j<transaction.getPurchasedSeats().size(); j++){
                if(i.equals((
                        transaction.getPurchasedSeats().get(j).getRow() +
                                transaction.getPurchasedSeats().get(j).getCol()))){
                    cancelledSeats.add(transaction.getPurchasedSeats().get(j));

                }
            }
        }
        return cancelledSeats;
    }
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
