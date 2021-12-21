package view;

        import model.Seat;

        import javax.swing.*;
        import javax.swing.border.Border;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

/**
 * This view is presented to the user after the user has selected a showing to purchase tickets for
 *  Allows users to purchase multiple tickets, seats which are already sold will not be available for
 *  selection and appear red.
 *
 */
public class SelectSeatView extends JFrame implements ActionListener {

    private ArrayList<JButton> seatButtonList;
    private ArrayList<String> selectedSeats;

    private JButton doneButton;

    private JLabel titleText, screen, blankLabel;

    private JTextArea display, showPrice;

    private double price;

    public SelectSeatView(double price, ArrayList<Seat> seats) {

        selectedSeats = new ArrayList<String>();
        this.price = price;

        setSize(400, 600);
        setTitle("Theatre Seating Page");

        seatButtonList = new ArrayList<JButton>();
        for(int i=0; i < 25; i++){
            JButton btn = new JButton(String.valueOf((char)((i / 5) + 65)) + ((i % 5) + 1));
            if(seats.get(i).getTransactionID()!=0){
                btn.setBackground(Color.red);
            }else{
                btn.addActionListener(this);

            }
            seatButtonList.add(btn);
        }


        JPanel displayPanel = new JPanel();
        JPanel seatPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        titleText = new JLabel("Please select your seat\n\n");
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(titleText);
        screen = new JLabel("MOVIE SCREEN");
        screen.setSize(60, 5);
        Border border = BorderFactory.createLineBorder(Color.black, 3);
        screen.setBorder(border);
        textPanel.add(screen);


        seatPanel.setLayout(new GridLayout(6, 6));

        seatPanel.add(new JLabel(""));
        seatPanel.add(new JLabel("Seat 1"));
        seatPanel.add(new JLabel("Seat 2"));
        seatPanel.add(new JLabel("Seat 3"));
        seatPanel.add(new JLabel("Seat 4"));
        seatPanel.add(new JLabel("Seat 5"));

        for(int i=0; i < 25; i++){
            if(i % 5 == 0){
                seatPanel.add(new JLabel("Row " + String.valueOf((char)((i / 5) + 65))));
            }
            seatPanel.add(seatButtonList.get(i));
        }

        bottomPanel.setLayout(new GridLayout(4, 1));
        display = new JTextArea(1, 15);
        display.setLineWrap(true);
        display.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        bottomPanel.add(display);

        blankLabel = new JLabel(" ");
        bottomPanel.add(blankLabel);

        showPrice = new JTextArea(1, 8);
        showPrice.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        bottomPanel.add(showPrice);

        doneButton = new JButton("Purchase");
        bottomPanel.add(doneButton);

        displayPanel.add("North", textPanel);
        displayPanel.add("Center", seatPanel);
        displayPanel.add("South", bottomPanel);

        add(displayPanel);
    }

    public void addFinishedButtonListener (ActionListener listenerForFinish) {
        doneButton.addActionListener(listenerForFinish);
    }

    public ArrayList<String> getSelectedSeats(){
        return selectedSeats;
    }

    public String getSeat(String seatNumber) {
        String ticket = seatNumber;
//        addText(seatNumber);
        return ticket;
    }

    public void updateText() {
        String seatString = "";

        for(String seat: selectedSeats){
            seatString += seat + ", ";
        }

        display.setText(seatString);
        showPrice.setText("$ " + (this.price * selectedSeats.size()));
    }

    public void actionPerformed(ActionEvent e) {
        for(String seat: selectedSeats){
            if(seat.equals(((JButton)e.getSource()).getText())){
                selectedSeats.remove(seat);
                ((JButton) e.getSource()).setBackground(null);
                this.updateText();
                return;
            }
        }

        selectedSeats.add(((JButton)e.getSource()).getText());
        ((JButton) e.getSource()).setBackground(Color.lightGray);

        this.updateText();
    }
}
