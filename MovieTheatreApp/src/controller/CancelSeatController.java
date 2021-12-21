
package controller;

import cancelPolicy.CancelPolicy;
import cancelPolicy.GuestCancelPolicy;
import cancelPolicy.RegisteredCancelPolicy;
import model.Seat;
import model.Transaction;
import model.User;
import view.CancelSeatView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CancelSeatController class is used to control the cancel seat view.
 * It also connects to the model to pass all required information.
 */
public class CancelSeatController {
    /**
     * the user of the program
     */
    private User user;
    /**
     * The user selected transaction where the seats are to be cancelled from
     */
    Transaction transaction;
    /**
     * The cancelPolicy utilized when cancelling seats, dependent on whether the user is registered
     */
    CancelPolicy cancelPolicy;

    public CancelSeatController(User user, Transaction transaction){
        this.user= user;
        CancelSeatView cancelSeatView =
                new CancelSeatView(transaction.getPurchasedSeats(), transaction) ;
        cancelSeatView.setVisible(true);

        cancelSeatView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Adds a listener to the cancel button which takes user selected seats in the transaction and cancels them
        cancelSeatView.addCancelButtonListener(e->{
            ArrayList<Seat> cancelledSeats = cancelSeatView.getCancelledSeats();
            if (cancelledSeats==null){
                cancelSeatView.displayErrorMessage("Please select seats to cancel");
                return;
            }
            if (user.getRegistered()){
                cancelPolicy = new RegisteredCancelPolicy();
            }else{
                cancelPolicy = new GuestCancelPolicy();
            }
            cancelPolicy.cancelTicket(cancelledSeats, transaction, user);
            cancelSeatView.setVisible(false);

            CancelSuccessController  cancelSuccessController= new CancelSuccessController(user);


        });

        // Adds an listener for the back button, brings user back to the TerminalView.
        cancelSeatView.addBackButtonListener(e->{
            try {
                TerminalController terminalController = new TerminalController(user);
                cancelSeatView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });





    }



}


