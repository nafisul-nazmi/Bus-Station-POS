
//package pos;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class District {
    JFrame f;
    JTextField name;
    JLabel nameL;
    JButton submit;
    
    public District() {
        f = new JFrame("Add new District of Service");
        f.setLayout(null);
        
        nameL = new JLabel("Distric Name: ");
        nameL.setSize(100, 50);
        nameL.setLocation(30, 30);
        f.getContentPane().add(nameL);
        
        name = new JTextField();
        name.setSize(200, 50);
        name.setLocation(150, 30);
        name.setBackground(Color.white);
        f.getContentPane().add(name);
        
        submit = new JButton("Submit");
        submit.setSize(80, 50);
        submit.setLocation(30, 100);
        submit.addActionListener(new Handler());
        f.getContentPane().add(submit);
        
        
        //f.pack();
        f.setSize(400, 300);
        f.setVisible(true);
        
    }
    
    class Handler implements ActionListener {
        Connection conn;
        Statement st;
        String query;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DB.getConn();
                st = conn.createStatement();
                int reply = JOptionPane.showConfirmDialog(f, "Confirm?");
                if(reply == JOptionPane.YES_OPTION) {
                    query = "insert into districts values ('" + name.getText() + "')";
                    st.executeQuery(query);
                    f.dispose();
                }
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(f, "Name already exists or Connection error");
            }
           
        }
        
    }
}
