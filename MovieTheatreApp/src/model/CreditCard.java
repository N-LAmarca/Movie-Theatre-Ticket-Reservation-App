package model;

import java.sql.SQLException;

/**
 * CreditCard class is used to store and allow access to the
 * credit card information for each user.
 * Has constructor methods for accessing existing cards, and creating new cards,
 * also has all required getters and setters, and a toString override method.
 */
public class CreditCard {
    int cardId;
    int userId;
    String cardHolderName;
    String cardNumber;
    int expiryMonth;
    int expiryYear;
    int cvv;
    JDBCConnect myJDBC;


    /**
     * Constructor for Existing CreditCard objects (in the Database)
     * @param cardId the Id number of the card
     * @param userId the Id of the user
     * @param name the name of the cardholder
     * @param number the credit card number
     * @param expMonth the expiry month of the card
     * @param expYear the expiry year of the card
     * @param cvv the cvv number of the card
     */
    public CreditCard(int cardId, int userId, String name, String number, int expMonth, int expYear, int cvv)
    {
        setCardId(cardId);
        setUserId(userId);
        setCardHolderName(name);
        setCardNumber(number);
        setExpiryMonth(expMonth);
        setExpiryYear(expYear);
        setCvv(cvv);
    }

    // for new credit cards

    /**
     * Constructor for New CreditCard objects (not currently in the Database)
     * @param userId the Id of the user
     * @param name the name of the cardholder
     * @param number the credit card number
     * @param expMonth the expiry month of the card
     * @param expYear the expiry year of the card
     * @param cvv the cvv number of the card
     * @throws SQLException
     */
    public CreditCard(int userId, String name, String number, int expMonth, int expYear, int cvv) throws SQLException {
        setUserId(userId);
        setCardHolderName(name);
        setCardNumber(number);
        setExpiryMonth(expMonth);
        setExpiryYear(expYear);
        setCvv(cvv);

        myJDBC = new JDBCConnect();
        myJDBC.createConnection();
        this.cardId = myJDBC.addCreditCardToDB(userId, cardHolderName, cardNumber, expiryMonth, expiryYear, cvv);

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    /**
     * toString method for CreditCard objects.
     */
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardNumber=" + cardNumber +
                ", expiryMonth=" + expiryMonth +
                ", expiryYear=" + expiryYear +
                ", cvv=" + cvv +
                '}';
    }
}
