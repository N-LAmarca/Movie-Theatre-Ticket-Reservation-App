package controller;

import model.*;
import view.RegisterView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.sql.Date;

/**
 * RegisterController class is used to control the register view.
 * It also connects to the model to pass all required information.
 */
public class RegisterController {
    /**
     * the user using the program
     */
    private User user;
    /**
     * The registered user after the guest user becomes registered
     */
    private RegisteredUser regUser;

    public RegisterController(User user){
        this.user = user;

        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
        registerView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        registerView.addRegisterButtonListener(e->{
            boolean errCheck = true;

            String address ="";
            String name="";
            String password="";
            String cardholderName="";
            int year=0;
            int month = 0;
            int cvv=0;
            String card="";
            //checks to ensure the address, name, password, cardholder fields are not empty
            try{
                if((registerView.getAddress().strip().equals(""))||
                        (registerView.getName().strip().equals(""))||
                        (registerView.getPassword().strip().equals(""))||
                        (registerView.getCardHolderName().strip()).equals("")){
                    throw(new IllegalArgumentException());
                }
                address = registerView.getAddress();
                name = registerView.getName();
                password = registerView.getPassword();
                cardholderName = registerView.getCardHolderName();
            }catch(IllegalArgumentException err){
                registerView.alert("Please enter an address, name, and password");
                errCheck = false;
            }
            //checks to ensure the card provided is valid
            try{
                if(registerView.getCard().length()!=16){
                    throw(new NumberFormatException());
                }
                for(int i =0; i <registerView.getCard().length(); i++){
                    int z =Integer.parseInt(registerView.getCard().substring(i,i+1));
                }
                card = registerView.getCard();
            }catch(NumberFormatException err){
                errCheck = false;
                registerView.alert("Please enter a valid credit card number");
            }
            //checks to makesure the card expiry year and month  and cvv are valid
            try{
                if(registerView.getMonth()==0 || registerView.getYear()<=21) throw(new NumberFormatException());

                month = registerView.getMonth();
                year = registerView.getYear();
            }catch(NumberFormatException err){
                errCheck = false;
                registerView.alert("Please enter a valid credit card expiry month and year");
            }

            try{
                cvv = Integer.parseInt(registerView.getCvv());
            }catch(NumberFormatException err){
                errCheck = false;
                registerView.alert("Please enter a cvv number");
            }
            //if successful then creates a card object and user objects then adds it to the database.
            if(errCheck==true) {
                try {
                    CreditCard creditCard = new CreditCard(user.getUserId(),
                            cardholderName,card, month, year, cvv);
                    user.retrieveCreditCard();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Date lastPaymentDate = new Date(System.currentTimeMillis());
                System.out.println(new Date(System.currentTimeMillis()));
                try {
                    Database database = Database.getDatabase();
                    database.registerUser(user.getUserId(), name, address, password, lastPaymentDate);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    regUser = LoginChecker.AuthenticateRegisteredUser(user.getEmail(), password);
                    regUser.retrieveCreditCard();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    TerminalController terminalController = new TerminalController(regUser);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                registerView.setVisible(false);
            }
        });
        // when the back button is pressed, returns to the previous screen
        registerView.addCancelButtonListener(e->{
            try{
                TerminalController terminalController = new TerminalController(this.user);
                registerView.setVisible(false);
            } catch(SQLException err){
                err.printStackTrace();
            }


        });


    }



}
