//package pos;
import javax.swing.*;
import java.awt.event.*;

public class POS {
    JFrame f;
    JButton b1, b2, b3, b4;
    public static void main(String[] args) {
        POS p = new POS();
        p.launch();
    }
    
    public void launch() {
        f = new JFrame("BUS TICKET POS");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
       
        b1 = new JButton("Sell Ticket");
        b1.setSize(150, 30);
        b1.setLocation(30, 30);
        b1.addActionListener(new SellHandler());
        f.getContentPane().add(b1);
       
        b2 = new JButton("Add Bus Schedule");
        b2.setSize(150, 30);
        b2.setLocation(30, 80);
        b2.addActionListener(new BusHandler());
        f.getContentPane().add(b2);
        
        b3 = new JButton("Add District");
        b3.setSize(150, 30);
        b3.setLocation(30, 130);
        b3.addActionListener(new DistrictHandler());
        f.getContentPane().add(b3);
        
        b4 = new JButton("Delete Booking Info");
        b4.setSize(150, 30);
        b4.setLocation(30, 180);
        b4.addActionListener(new DeleteHandler());
        f.getContentPane().add(b4);
        
        
        f.setSize(230, 300);
        f.setVisible(true);


       
    }
    
    class SellHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Booking b = new Booking();
            
        }
        
    }
    
    class BusHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bus b = new Bus();
        }
        
    }
    
    class DistrictHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            District d = new District();
        }
        
    }
    
    class DeleteHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Deletion d = new Deletion();
        }
        
    }
}
