package cancelPolicy;

import model.Seat;
import model.Transaction;
import model.User;

import java.util.ArrayList;

public interface CancelPolicy {

    public void cancelTicket(ArrayList<Seat> cancelledSeats, Transaction transaction, User user);
}
