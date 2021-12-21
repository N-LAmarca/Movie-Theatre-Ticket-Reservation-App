package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

/**
 * The view presented to guest users after the user selects to register an account,
 *  registered users can not enter this page
 */
public class RegisterView extends JFrame {
    private JTextField name;
    private JTextField address;
    private JPasswordField password;
    private JTextField creditCardNumber;
    private JTextField cvv;
    private JSpinner month;
    private JSpinner year;
    private JButton registerButton, cancelButton;
    private JTextField cardHolderName;

    /**
     * Create the panel.
     */
    public RegisterView() {
        setSize(800,650);
        setLayout(new BorderLayout(0, 0));

        JPanel labelPanel = new JPanel();
        add(labelPanel, BorderLayout.NORTH);

        JLabel registerLabel = new JLabel("Register for an account");
        labelPanel.add(registerLabel);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        buttonPanel.add(registerButton);


        JPanel basePanel = new JPanel();
        add(basePanel, BorderLayout.CENTER);
        basePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));

        JLabel noteLabel = new JLabel("To register an account, please provide the required information in the form below: ");
        basePanel.add(noteLabel);

        JPanel panel = new JPanel();
        basePanel.add(panel);

        JLabel nameLabel = new JLabel("Full Name:");
        panel.add(nameLabel);

        name = new JTextField();
        panel.add(name);
        name.setColumns(50);

        JPanel addressPanel = new JPanel();
        basePanel.add(addressPanel);

        JLabel addressLabel = new JLabel("Address");
        addressPanel.add(addressLabel);

        address = new JTextField();
        addressPanel.add(address);
        address.setColumns(50);

        JPanel passwordPanel = new JPanel();
        basePanel.add(passwordPanel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordPanel.add(passwordLabel);

        password = new JPasswordField();
        password.setColumns(40);
        passwordPanel.add(password);




        JLabel cardLabel = new JLabel("Credit Card Number: ");

        creditCardNumber = new JTextField();
        creditCardNumber.setColumns(40);

        JPanel cardPanel = new JPanel();

        JPanel cardPanel1 = new JPanel();
        cardPanel1.add(cardLabel);
        cardPanel1.add(creditCardNumber);

        basePanel.add(cardPanel1);

        JLabel monthLabel = new JLabel("Expiry Month");
        cardPanel.add(monthLabel);

        month = new JSpinner();
        month.setModel(new SpinnerNumberModel(0, 0, 12, 1));
        cardPanel.add(month);

        JLabel yearLabel = new JLabel("Expiry year");
        cardPanel.add(yearLabel);

        year = new JSpinner();
        year.setModel(new SpinnerNumberModel(20, 20, 99, 1));

        cardPanel.add(year);

        JLabel cvvLabel = new JLabel("CVV");
        cardPanel.add(cvvLabel);

        cvv = new JTextField();
        cardPanel.add(cvv);
        cvv.setColumns(3);
        basePanel.add(cardPanel);
        cardHolderName = new JTextField();
        cardHolderName.setColumns(40);
        JPanel holderName = new JPanel();
        holderName.add(new JLabel("Card Holder Name: "));
        holderName.add(cardHolderName);
        basePanel.add(holderName);


        JLabel noteLabel1 = new JLabel("* Please note a $20.00 annual account fee will be deducted upfront upon registration");
        basePanel.add(noteLabel1);

        JLabel noteLabel2 = new JLabel("* The annual account fee will be automatically billed unless cancelled or credit card expires");
        basePanel.add(noteLabel2);

    }

    public void addRegisterButtonListener(ActionListener e) {
        registerButton.addActionListener(e);
    }

    public void addCancelButtonListener(ActionListener e){
        cancelButton.addActionListener(e);
    }

    public String getName(){
        return this.name.getText();
    }

    public String getAddress(){
        return this.address.getText();
    }
    public String getPassword(){
        return String.valueOf(this.password.getPassword());
    }
    public String getCard(){
        return this.creditCardNumber.getText();
    }

    public int getMonth(){
        return (int)this.month.getValue();
    }

    public int getYear(){
        return (int)this.year.getValue();
    }

    public String getCvv(){
        return this.cvv.getText();
    }

    public String getCardHolderName(){
        return this.cardHolderName.getText();
    }

    public void alert(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }





}
