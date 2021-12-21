package cancelPolicy;

import model.JDBCConnect;
import model.Seat;
import model.Transaction;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class GuestCancelPolicy implements CancelPolicy{



    @Override
    public void cancelTicket(ArrayList<Seat> cancelledSeats, Transaction transaction, User user) {
        JDBCConnect myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        int numberOfTickets = cancelledSeats.size();

        for(Seat seat:cancelledSeats){
            try {
                myJDBC.updateSeatDB(seat.getShowingId(), seat.getRow(), seat.getCol());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Iterator<Seat> iterator = transaction.getPurchasedSeats().iterator();
        while(iterator.hasNext()){
            Seat seat = iterator.next();
            if(cancelledSeats.contains(seat)){
                iterator.remove();
            }
        }

        double amount = (double)numberOfTickets*transaction.getShowing().getTicketPrice()*0.85;
        String creditCode = "Refund";

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.YEAR, 1);
        Date expiryDate = c.getTime();

        try {
            myJDBC.addMovieCreditToDB(
                    creditCode, expiryDate, amount, transaction.getUser().getUserId());
            String subjectLine = "Credit Receipt";
            String message = "Transaction Cancelled: " + transaction.getTransactionId() + "      " + "Refunded Credit: " + amount + "     " + "Credit Code: " + creditCode + "   " +  "Credit Expires on: " + expiryDate;
            myJDBC.addMessageToDB(user, message, subjectLine);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
