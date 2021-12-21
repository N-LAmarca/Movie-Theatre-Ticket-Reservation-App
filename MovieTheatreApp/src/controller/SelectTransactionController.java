package controller;

import cancelPolicy.CancelPolicy;
import cancelPolicy.GuestCancelPolicy;
import cancelPolicy.RegisteredCancelPolicy;
import model.Database;
import model.Transaction;
import model.User;
import view.CancelSuccessView;
import view.SelectTransactionView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * SelectTransactionController class is used to control the transaction view.
 * It also connects to the model to pass all required information.
 */
public class SelectTransactionController {
    private User user;
    Transaction selectedTransaction;
    CancelPolicy cancelPolicy;
    SelectTransactionView selectTransactionView;

    /**
     * Constructor for a SelectTransactionController
     * @param user the user
     */
    public SelectTransactionController(User user){
        this.user = user;

        ArrayList<Transaction> cancelable = new ArrayList<>();
        for(Transaction transaction:user.getPreviousPurchases()){
            Calendar c = Calendar.getInstance();
            c.setTime(transaction.getShowing().getShowTime());
            c.add(Calendar.HOUR, -72);
            Date latestCancelTime = c.getTime();
            Date date = new Date(System.currentTimeMillis());

            if (date.before(latestCancelTime)){
                cancelable.add(transaction);
            }

        }
        selectTransactionView = new SelectTransactionView(cancelable);
        selectTransactionView.setVisible(true);
        selectTransactionView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        selectTransactionView.addProceedButtonListener(e->{

            selectedTransaction = selectTransactionView.getSelectedTransaction();
            if(selectedTransaction == null){
                selectTransactionView.displayErrorMessage("Please select a transaction");
                return;
            }
            if(selectedTransaction.getPurchasedSeats().size()==0){
                selectTransactionView.displayErrorMessage("All seats in this transaction have been cancelled");
                return;
            }
            selectTransactionView.setVisible(false);
            CancelSeatController cancelSeatController =
                    new CancelSeatController(user, selectedTransaction);
        });

        selectTransactionView.addCancelAllButtonListener(e->{
            selectedTransaction = selectTransactionView.getSelectedTransaction();
            if(selectedTransaction == null){
                selectTransactionView.displayErrorMessage("Please select a transaction");
                return;
            }

            if(selectedTransaction.getPurchasedSeats().size()==0){
                selectTransactionView.displayErrorMessage("All seats in this transaction have been cancelled");
                return;
            }
            if (user.getRegistered()){
                cancelPolicy = new RegisteredCancelPolicy();
            }else{
                cancelPolicy = new GuestCancelPolicy();
            }
            cancelPolicy.cancelTicket(selectedTransaction.getPurchasedSeats(), selectedTransaction, user);
            selectTransactionView.setVisible(false);
            CancelSuccessController cancelSuccessController = new CancelSuccessController(user);


        });

        selectTransactionView.addBackButtonListener(e->{
            try {
                selectTransactionView.setVisible(false);
                TerminalController terminalController = new TerminalController(user);
                selectTransactionView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });


    }
}
