package controller;

import model.CreditCard;
import model.Showing;
import model.Transaction;
import model.User;
import view.PaymentView;
import view.TransactionConfirmationView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * TransactionController class is used to control the transaction view.
 * It also connects to the model to pass all required information.
 */
public class TransactionController {

    private User user;
    private Showing showing;
    private ArrayList<Integer> purchasedSeatsIndex;
    private CreditCard paymentCard;

    /**
     * Constructor for a TransactionController object.
     * @param user the user
     * @param showing the showing the transaction is for
     * @param purchasedSeatsIndex the indicies of the purchased seats for the showing
     * @param paymentCard the user's payment card
     * @throws SQLException
     */
    public TransactionController(User user, Showing showing, ArrayList<Integer> purchasedSeatsIndex, CreditCard paymentCard) throws SQLException {
        this.user = user;
        this.showing = showing;
        this.purchasedSeatsIndex = purchasedSeatsIndex;
        this.paymentCard = paymentCard;
        this.createTransaction();

        TransactionConfirmationView transactionConfirmationView = new TransactionConfirmationView();
        transactionConfirmationView.setVisible(true);
        transactionConfirmationView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        transactionConfirmationView.addReturnButtonListener(e -> {
            try {
                TerminalController terminalController = new TerminalController(user);
                transactionConfirmationView.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Creates a Transaction based off the selected showing and seats, as well as generates the ticket/receipt email
     * @throws SQLException
     */
    public void createTransaction() throws SQLException {
        double totalCost = showing.getTicketPrice() * purchasedSeatsIndex.size();
        Transaction transaction = new Transaction(user, totalCost, paymentCard, showing.getShowingId());
        for(int index: purchasedSeatsIndex){
            showing.getSeats().get(index).setTransactionID(transaction.getTransactionId());
        }
        showing.setAvailability();
        transaction.setSeats();
        transaction.createReceipt();
        user.retrieveUserTransactions();
    }




}
