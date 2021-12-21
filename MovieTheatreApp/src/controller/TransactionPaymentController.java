package controller;

import model.CreditCard;
import model.Showing;
import model.User;
import view.PaymentView;
import view.TransactionConfirmationView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * TransactionPaymentController  class is used to control the transaction payment view.
 * It also connects to the model to pass all required information.
 */
public class TransactionPaymentController {

    private User user;
    private Showing showing;
    private ArrayList<Integer> purchasedSeatsIndex;
    private CreditCard paymentCard;

    /**
     * Constructor for TransactionPaymentController object
     * @param user the user
     * @param showing the showing the transaction is for
     * @param purchasedSeatsIndex the indicies of the purchased seats for the showing
     */
    public TransactionPaymentController(User user, Showing showing, ArrayList<Integer> purchasedSeatsIndex) {
        this.user = user;
        this.showing = showing;
        this.purchasedSeatsIndex = purchasedSeatsIndex;

        PaymentView paymentView = new PaymentView();
        paymentView.setVisible(true);
        paymentView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paymentView.addPurchaseButtonListener(e -> {
            try {
                paymentCard = new CreditCard(user.getUserId(),
                        paymentView.getCardHolderName(),
                        paymentView.getCardNumber(),
                        paymentView.getCardExpiryMonth(),
                        paymentView.getCardExpiryYear(),
                        paymentView.getCardCVV());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                TransactionController transactionController = new TransactionController(this.user, this.showing, this.purchasedSeatsIndex, this.paymentCard);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            paymentView.setVisible(false);

            });
    }
}
