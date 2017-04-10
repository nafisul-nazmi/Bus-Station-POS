
//package pos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bus {
    JFrame f;
    JLabel busIDL, dayL, typeL, from_dL, to_dL, timeL, fareL;
    JTextField busID, day, type, time, fare;
    JComboBox from_d, to_d, am_pm;
    JButton submit;
    
    Connection conn;
    Statement st;
    ResultSet rs;
    String query;
    
    public Bus() {
        f = new JFrame("Add new Bus Schedule");
        f.setLayout(null);
        
        busIDL = new JLabel("Bus ID:");
        busIDL.setLocation(30, 30);
        busIDL.setSize(100, 30);
        f.getContentPane().add(busIDL);
        
        dayL = new JLabel("Day:");
        dayL.setLocation(30, 80);
        dayL.setSize(100, 30);
        f.getContentPane().add(dayL);
        
        typeL = new JLabel("Type:");
        typeL.setLocation(30, 130);
        typeL.setSize(100, 30);
        f.getContentPane().add(typeL);
        
        from_dL = new JLabel("From:");
        from_dL.setLocation(30, 180);
        from_dL.setSize(100, 30);
        f.getContentPane().add(from_dL);
        
        to_dL = new JLabel("To:");
        to_dL.setLocation(30, 230);
        to_dL.setSize(100, 30);
        f.getContentPane().add(to_dL);
        
        timeL = new JLabel("Time:");
        timeL.setLocation(30, 280);
        timeL.setSize(100, 30);
        f.getContentPane().add(timeL);
        
        fareL = new JLabel("Fare:");
        fareL.setLocation(30, 330);
        fareL.setSize(100, 30);
        f.getContentPane().add(fareL);
        
        busID = new JTextField();
        busID.setSize(200, 30);
        busID.setLocation(100, 30);
        busID.setBackground(Color.white);
        f.getContentPane().add(busID);
        
        day = new JTextField();
        day.setSize(200, 30);
        day.setLocation(100, 80);
        day.setBackground(Color.white);
        f.getContentPane().add(day);
        
        type = new JTextField();
        type.setSize(200, 30);
        type.setLocation(100, 130);
        type.setBackground(Color.white);
        f.getContentPane().add(type);
        
        time = new JTextField();
        time.setSize(100, 30);
        time.setLocation(100, 280);
        time.setBackground(Color.white);
        f.getContentPane().add(time);
        
        fare = new JTextField();
        fare.setSize(200, 30);
        fare.setLocation(100, 330);
        fare.setBackground(Color.white);
        f.getContentPane().add(fare);
        
        from_d = new JComboBox();
        from_d.setSize(200, 30);
        from_d.setLocation(100, 180);
        f.getContentPane().add(from_d);
        
        to_d = new JComboBox();
        to_d.setSize(200, 30);
        to_d.setLocation(100, 230);
        f.getContentPane().add(to_d);
        
        am_pm = new JComboBox();
        am_pm.setSize(80, 30);
        am_pm.setLocation(220, 280);
        am_pm.addItem("AM");
        am_pm.addItem("PM");
        f.getContentPane().add(am_pm);
        
        conn = DB.getConn();
        try {
            st = conn.createStatement();
            query = "select name from districts";
            rs = st.executeQuery(query);
            while(rs.next()) {
                from_d.addItem(rs.getString("name"));
                to_d.addItem(rs.getString("name"));
            }
           
        } catch (SQLException ex) {}
        
        submit = new JButton("Submit");
        submit.setSize(80, 30);
        submit.setLocation(30, 380);
        submit.addActionListener(new Handler());
        f.getContentPane().add(submit);
       
        f.setVisible(true);
        f.setSize(350, 500);
        
        
        
    }
    
    class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(f, "Confirm?");
            if(reply == JOptionPane.YES_OPTION) {
                query = "insert into bus values ('" + busID.getText() + "' , '" + 
                day.getText() + "' , '" + type.getText() + "' , '" + 
                (String)from_d.getSelectedItem() + "' , '" + 
                (String)to_d.getSelectedItem() + "' , '" + time.getText() + 
                " " + (String)am_pm.getSelectedItem() + "' , " + 
                fare.getText() + ")";
                try {
                    conn = DB.getConn();
                    st = conn.createStatement();
                    st.executeQuery(query);
                    f.dispose();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(f, "Please check all information again");
                }
            }
        }
        
    }
}
