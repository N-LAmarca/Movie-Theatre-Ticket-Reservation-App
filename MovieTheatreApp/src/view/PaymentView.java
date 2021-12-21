package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaymentView extends JFrame {
    JLabel holderName, number, expiryMonth, expiryYear, cvv;
    JTextArea holderNameInput, numberInput, expiryMonthInput, expiryYearInput, cvvInput;
    JButton purchaseButton;

    /**
     * The view presented to a guest user after the user has selected seats of a showing to purchase
     */
    public PaymentView() {

        setSize(800, 650);
        setTitle("Theater Terminal Page");
        getContentPane().setLayout(new BorderLayout(0,0 ));

        JPanel displayPanel= new JPanel ();
        displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 100));
        JPanel label = new JPanel();
        getContentPane().add(label, BorderLayout.NORTH);

        label.add(new JLabel("Please provide payment information"));

        JPanel purchasePanel = new JPanel();
        getContentPane().add(purchasePanel, BorderLayout.SOUTH);
        purchaseButton = new JButton("Purchase");
        purchasePanel.add(purchaseButton);


        JLabel cardLabel = new JLabel("Card Number: ");

        numberInput = new JTextArea();
        numberInput.setColumns(40);
        numberInput.setRows(1);
        numberInput.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel cardPanel = new JPanel();

        JPanel cardPanel1 = new JPanel();
        cardPanel1.add(cardLabel);
        cardPanel1.add(numberInput);
        displayPanel.add(cardPanel1);

        JLabel monthLabel = new JLabel("Expiry Month");
        cardPanel.add(monthLabel);

        expiryMonthInput = new JTextArea(1,2);
        cardPanel.add(expiryMonthInput);
        expiryMonthInput.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JLabel yearLabel = new JLabel("Expiry year");
        cardPanel.add(yearLabel);

        expiryYearInput = new JTextArea(1,2);
        expiryYearInput.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        cardPanel.add(expiryYearInput);

        JLabel cvvLabel = new JLabel("CVV");
        cardPanel.add(cvvLabel);

        cvvInput = new JTextArea(1,3);
        cvvInput.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        cardPanel.add(cvvInput);
        displayPanel.add(cardPanel);
        holderNameInput = new JTextArea(1, 40);
        holderNameInput.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        JPanel holderName = new JPanel();
        holderName.add(new JLabel("Card Holder Name: "));
        holderName.add(holderNameInput);
        displayPanel.add(holderName);
        add(displayPanel);
    }

    public void addPurchaseButtonListener(ActionListener listener) {
        purchaseButton.addActionListener(listener);
    }

    public String getCardHolderName(){ return holderNameInput.getText(); }

    public String getCardNumber(){ return numberInput.getText(); }

    public int getCardExpiryMonth(){ return Integer.parseInt(expiryMonthInput.getText()); }

    public int getCardExpiryYear(){ return Integer.parseInt(expiryYearInput.getText()); }

    public int getCardCVV(){ return Integer.parseInt(cvvInput.getText()); }


}
