package controller;

import model.RegisteredUser;
import model.Showing;
import model.Transaction;
import model.User;
import view.SelectSeatView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SelectedSeatController class is used to control the seat view.
 * It also connects to the model to pass all required information.
 */
public class SelectedSeatController {
    /**
     * the user that is using the program
     */
    private User user;
    /**
     * the selected showing
     */
    private Showing showing;
    /**
     * The list of seats to be purchased
     */
    private ArrayList<Integer> purchasedSeatsIndex;

    public SelectedSeatController(User user, Showing showing) {


        this.user = user;
        this.showing = showing;
        this.purchasedSeatsIndex = new ArrayList<Integer>();
        SelectSeatView selectSeatView = new SelectSeatView(showing.getTicketPrice(), showing.getSeats());
        selectSeatView.setVisible(true);
        selectSeatView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // when finished is pressed, proceeds to making transaction by providing payment info, or if the user is a
        // a registered user, then automatically processes the payment
        selectSeatView.addFinishedButtonListener(e -> {
            for(String seat: selectSeatView.getSelectedSeats()){
                purchasedSeatsIndex.add(getSeatIndex(seat));
            }
            if(user.getRegistered()){
                try {
                    TransactionController transactionController = new TransactionController(user, showing, purchasedSeatsIndex, user.getCreditCard());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                TransactionPaymentController transactionPaymentController = new TransactionPaymentController(user, showing, purchasedSeatsIndex);
            }
            selectSeatView.setVisible(false);
        });

    }

    /**
     * Gets the indices of a seat based on its string value
     * @param seat
     * @return the index of the seat
     */
    public int getSeatIndex(String seat){
        return ((seat.charAt(0) - 'A') * 5) + Character.getNumericValue(seat.charAt(1))-1;
    }


}
