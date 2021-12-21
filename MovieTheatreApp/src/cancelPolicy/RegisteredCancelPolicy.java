package cancelPolicy;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class RegisteredCancelPolicy implements CancelPolicy{



    @Override
    public void cancelTicket(ArrayList<Seat> cancelledSeats, Transaction transaction, User user) {
        JDBCConnect myJDBC = new JDBCConnect();

        myJDBC.createConnection();
        int numberOfTickets = cancelledSeats.size();
        System.out.println(cancelledSeats);
        System.out.println(transaction);

        for(Seat seat:cancelledSeats){
            try {
                myJDBC.updateSeatDB(seat.getShowingId(), seat.getRow(), seat.getCol());
               // transaction.getPurchasedSeats().remove(seat);
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


        double amount = (double)numberOfTickets*transaction.getShowing().getTicketPrice();
        String creditCode = "Refund";

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.add(Calendar.YEAR, 1);
        Date expiryDate = c.getTime();


        try {
            myJDBC.addMovieCreditToDB(
                    creditCode, expiryDate, amount, transaction.getUser().getUserId());
            String subjectLine = "Credit Receipt";
            String message = "Transaction Cancelled: " + transaction.getTransactionId() + "      " + "Refunded Credit: " + amount + "     " + "Credit Code: " + creditCode + "     " + "Credit Expires on: " + expiryDate;
            myJDBC.addMessageToDB(user, message, subjectLine);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
