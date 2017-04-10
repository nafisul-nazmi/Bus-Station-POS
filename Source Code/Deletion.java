
//package pos;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Deletion {
    Connection conn;
    Statement st;
    JFrame f;
    JButton sun, mon, tue, wed, thur, fri, sat;
    String table, query;
    
    public Deletion() {
        f = new JFrame("Delete");
        f.setLayout(null);

        
        sun = new JButton("Sunday");
        sun.setSize(150, 30);
        sun.setLocation(30, 30);
        sun.addActionListener(new Handler());
        f.getContentPane().add(sun);
        
        mon = new JButton("Monday");
        mon.setSize(150, 30);
        mon.setLocation(30, 80);
        mon.addActionListener(new Handler());
        f.getContentPane().add(mon);
        
        tue = new JButton("Tuesday");
        tue.setSize(150, 30);
        tue.setLocation(30, 130);
        tue.addActionListener(new Handler());
        f.getContentPane().add(tue);
        
        wed = new JButton("Wednesday");
        wed.setSize(150, 30);
        wed.setLocation(30, 180);
        wed.addActionListener(new Handler());
        f.getContentPane().add(wed);
        
        thur = new JButton("Thursday");
        thur.setSize(150, 30);
        thur.setLocation(30, 230);
        thur.addActionListener(new Handler());
        f.getContentPane().add(thur);
        
        fri = new JButton("Friday");
        fri.setSize(150, 30);
        fri.setLocation(30, 280);
        fri.addActionListener(new Handler());
        f.getContentPane().add(fri);
        
        sat = new JButton("Saturday");
        sat.setSize(150, 30);
        sat.setLocation(30, 330);
        sat.addActionListener(new Handler());
        f.getContentPane().add(sat);
        
        f.setSize(230, 450);
        f.setVisible(true);
        
    }
    
    class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(f, "Confirm Deletion?");
            if(reply == JOptionPane.YES_OPTION) {
                table = (String)e.getActionCommand() + "Booking";
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    query = "truncate table " + table;
                    st.executeQuery(query);
                    f.dispose();
                } catch(SQLException ex) {}
            }
        }
        
    }
    
}
