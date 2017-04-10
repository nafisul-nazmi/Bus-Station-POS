
//package pos;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.awt.print.*;

public class Booking {
    JFrame f;
    JComboBox day, from_d, to_d, type, time;
    JCheckBox[] seat = new JCheckBox[40];
    JButton submit;
    
    Connection conn;
    ResultSet rs;
    Statement st;
    String query, busID = null, table = null;
    
    int counts, fare;
    
    public Booking() {
        f = new JFrame();
        f.setLayout(null);
        
        day = new JComboBox();
        day.addItem("Select day:");
        day.addItem("Sunday");
        day.addItem("Monday");
        day.addItem("Tuesday");
        day.addItem("Wednesday");
        day.addItem("Thursday");
        day.addItem("Friday");
        day.addItem("Saturday");
        day.setSize(150, 30);
        day.setLocation(30, 30);
        day.addActionListener(new DayHandler());
        f.getContentPane().add(day);
        
        f.setSize(280, 700);
        f.setVisible(true);
    }
    
    class DayHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(from_d != null)
                f.getContentPane().remove(from_d);
            if(to_d != null)
                f.getContentPane().remove(to_d);
            if(type != null)
                f.getContentPane().remove(type);
            if(time != null)
                f.getContentPane().remove(time);
            for(int i = 0; i < 40; i++)
                if(seat[i] != null)
                    f.getContentPane().remove(seat[i]);
            if(submit != null)
                f.getContentPane().remove(submit);
            f.repaint();
            if(day.getSelectedItem().equals("Select day:") == false) {

                from_d = new JComboBox();
                from_d.setSize(150, 30);
                from_d.setLocation(30, 80);
                f.getContentPane().add(from_d);
                from_d.addActionListener(new FromHandler());
                from_d.addItem("From:");
                
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    query = "select unique from_d from bus where day = '" + 
                            (String)day.getSelectedItem() + "'";
                    rs = st.executeQuery(query);
                    while(rs.next())
                        from_d.addItem(rs.getString("from_d"));
                } catch (SQLException ex) {}
                

            }
        }
        
    }
    
    class FromHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(to_d != null)
                f.getContentPane().remove(to_d);
            if(type != null)
                f.getContentPane().remove(type);
            if(time != null)
                f.getContentPane().remove(time);
            for(int i = 0; i < 40; i++)
                if(seat[i] != null)
                    f.getContentPane().remove(seat[i]);
            if(submit != null)
                f.getContentPane().remove(submit);           
            f.repaint();
            if(from_d.getSelectedItem().equals("From:") == false) {

                to_d = new JComboBox();
                to_d.setSize(150, 30);
                to_d.setLocation(30, 130);
                f.getContentPane().add(to_d);
                to_d.addActionListener(new ToHandler());
                to_d.addItem("To:");
                
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    query = "select unique to_d from bus where day = '" + 
                            (String)day.getSelectedItem() + "' and from_d = '" +
                            (String)from_d.getSelectedItem() + "'";
                    rs = st.executeQuery(query);
                    while(rs.next())
                        to_d.addItem(rs.getString("to_d"));
                } catch (SQLException ex) {}
                
            }
        }
        
    }
    
    class ToHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(type != null)
                f.getContentPane().remove(type);
            if(time != null)
                f.getContentPane().remove(time);
            for(int i = 0; i < 40; i++)
                if(seat[i] != null)
                    f.getContentPane().remove(seat[i]);
            if(submit != null)
                f.getContentPane().remove(submit);            
            f.repaint();
            if(to_d.getSelectedItem().equals("To:") == false) {
                type = new JComboBox();
                type.setSize(150, 30);
                type.setLocation(30, 180);
                f.getContentPane().add(type);
                type.addActionListener(new TypeHandler());
                type.addItem("Type:");
                
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    query = "select unique type from bus where day = '" + 
                            (String)day.getSelectedItem() + "' and from_d = '" +
                            (String)from_d.getSelectedItem() + "' and to_d = '"
                            + (String)to_d.getSelectedItem() + "'";
                    rs = st.executeQuery(query);
                    while(rs.next())
                        type.addItem(rs.getString("type"));
                } catch (SQLException ex) {}
            }
        }
        
    }
    
    class TypeHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(time != null)
                f.getContentPane().remove(time);
            for(int i = 0; i < 40; i++)
                if(seat[i] != null)
                    f.getContentPane().remove(seat[i]);
            if(submit != null)
                f.getContentPane().remove(submit);
            f.repaint();
            if(type.getSelectedItem().equals("Type:") == false) {
                time = new JComboBox();
                time.setSize(150, 30);
                time.setLocation(30, 230);
                f.getContentPane().add(time);
                time.addActionListener(new SeatBooker());
                time.addItem("Time:");
                
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    query = "select time from bus where day = '" + 
                            (String)day.getSelectedItem() + "' and from_d = '" +
                            (String)from_d.getSelectedItem() + "' and to_d = '"
                            + (String)to_d.getSelectedItem() + "' and type = '"
                            + (String)type.getSelectedItem() + "'";
                    rs = st.executeQuery(query);
                    while(rs.next())
                        time.addItem(rs.getString("time"));
                } catch (SQLException ex) {}                
            }
        }
        
    }
    
    class SeatBooker implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < 40; i++)
                if(seat[i] != null)
                    f.getContentPane().remove(seat[i]);
            if(submit != null)
                f.getContentPane().remove(submit);
            f.repaint();
            if(time.getSelectedItem().equals("Time:") == false) {
                                           
                conn = DB.getConn();
                    try {
                        st = conn.createStatement();
                        query = "select busID from bus where day = '" + 
                            (String)day.getSelectedItem() + "' and from_d = '" +
                            (String)from_d.getSelectedItem() + "' and to_d = '"
                            + (String)to_d.getSelectedItem() + "' and type = '"
                            + (String)type.getSelectedItem() + "' and time = '"
                            + (String)time.getSelectedItem() + "'";
                            rs = st.executeQuery(query);

                            while(rs.next())
                                busID = rs.getString("busid");
                            table = (String)day.getSelectedItem() + "booking";
                    }catch(SQLException ex) {}
                   
                int index = 0;
                int x = 30, y = 280;
                for(char i = 'A'; i <= 'J'; i++) {
                    for(char j = '1'; j <= '4'; j++) {
                        String name = i + "";
                        name += j + "";
                        seat[index] = new JCheckBox(name);
                        seat[index].setSize(50, 30);
                        seat[index].setLocation(x, y);
                        f.getContentPane().add(seat[index]);
                        conn = DB.getConn();
                        try {
                            st = conn.createStatement();


                            query = "select seat from " + table + " where busID = '"
                                    + busID + "'";

                            rs = st.executeQuery(query);

                            while(rs.next()) {
                                if(rs.getString("seat").equals(name))
                                    seat[index].setEnabled(false);
                            }
                        } catch (SQLException ex) {}
                        
                        index++;
                        x += 50;


                    }
                    x -= 200;
                    y += 30;
                }
                
                submit = new JButton("Submit");
                submit.setSize(80, 20);
                submit.setLocation(30, 600);
                submit.addActionListener(new Submit());
                f.getContentPane().add(submit);
            }
        }
        
    }
    
    class Submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(f, "Confirm?");
            if(reply == JOptionPane.YES_OPTION) {

                for(int i = 0; i < 40; i++)
                    if(seat[i].isSelected())
                        counts++;
                if(counts > 0) {
                    query = "select fare from bus where busID = '" + busID + "'";
                    try {
                        rs = st.executeQuery(query);
                        while(rs.next())
                            fare = rs.getInt("fare");
                    } catch(SQLException ex) {}
                    fare *= counts;
                    Ticket t = new Ticket();
                    
                }
                else {
                    JOptionPane.showMessageDialog(f, "Select atleast one seat");
                }
            }
        }
        
    }
    
    class Ticket implements Printable, ActionListener{
        JFrame f2;  
        JLabel nameL, contactL, from_dL, to_dL, busIDL, timeL, dayL, seatsL, fareL;
        JLabel from_dNew, to_dNew, busIDNew, timeNew, dayNew, fareNew;
        JLabel[] seatsNew = new JLabel[counts];
        JTextField name, contact;
        JButton printer;
        
        public Ticket() {            
            f2 = new JFrame("Print Ticket");
            f2.setLayout(null);
            
            nameL = new JLabel("Name: ");
            nameL.setSize(100, 30);
            nameL.setLocation(30, 30);
            f2.getContentPane().add(nameL);
            
            contactL = new JLabel("Contact no.:");
            contactL.setSize(100, 30);
            contactL.setLocation(30, 80);
            f2.getContentPane().add(contactL);
            
            from_dL = new JLabel("From:");
            from_dL.setSize(100, 30);
            from_dL.setLocation(30, 130);
            f2.getContentPane().add(from_dL);
            
            to_dL = new JLabel("To:");
            to_dL.setSize(100, 30);
            to_dL.setLocation(30, 180);
            f2.getContentPane().add(to_dL);
            
            to_dL = new JLabel("To:");
            to_dL.setSize(100, 30);
            to_dL.setLocation(30, 180);
            f2.getContentPane().add(to_dL);
            
            busIDL = new JLabel("BusID:");
            busIDL.setSize(100, 30);
            busIDL.setLocation(30, 230);
            f2.getContentPane().add(busIDL);
            
            timeL = new JLabel("Time:");
            timeL.setSize(100, 30);
            timeL.setLocation(30, 280);
            f2.getContentPane().add(timeL);
            
            dayL = new JLabel("Day:");
            dayL.setSize(100, 30);
            dayL.setLocation(30, 330);
            f2.getContentPane().add(dayL);
            
            seatsL = new JLabel("Seats:");
            seatsL.setSize(100, 30);
            seatsL.setLocation(30, 380);
            f2.getContentPane().add(seatsL);
            
            fareL = new JLabel("Fare:");
            fareL.setSize(100, 30);
            fareL.setLocation(30, 430);
            f2.getContentPane().add(fareL);
            
            printer = new JButton("Print");
            printer.setSize(80, 30);
            printer.setLocation(30, 480);
            printer.addActionListener(this);
            f2.getContentPane().add(printer);
            
            name = new JTextField();
            name.setSize(200, 30);
            name.setLocation(150, 30);
            name.setBackground(Color.white);
            f2.getContentPane().add(name);
            
            contact = new JTextField();
            contact.setSize(200, 30);
            contact.setLocation(150, 80);
            contact.setBackground(Color.white);
            f2.getContentPane().add(contact);
            
            from_dNew = new JLabel((String)from_d.getSelectedItem());
            from_dNew.setSize(100, 30);
            from_dNew.setLocation(150, 130);
            f2.getContentPane().add(from_dNew);
            
            to_dNew = new JLabel((String)to_d.getSelectedItem());
            to_dNew.setSize(100, 30);
            to_dNew.setLocation(150, 180);
            f2.getContentPane().add(to_dNew);
            
            busIDNew = new JLabel(busID);
            busIDNew.setSize(100, 30);
            busIDNew.setLocation(150, 230);
            f2.getContentPane().add(busIDNew);
            
            timeNew = new JLabel((String)time.getSelectedItem());
            timeNew.setSize(100, 30);
            timeNew.setLocation(150, 280);
            f2.getContentPane().add(timeNew);
            
            dayNew = new JLabel((String)day.getSelectedItem());
            dayNew.setSize(100, 30);
            dayNew.setLocation(150, 330);
            f2.getContentPane().add(dayNew);
            
            fareNew = new JLabel(Integer.toString(fare));
            fareNew.setSize(100, 30);
            fareNew.setLocation(150, 430);
            f2.getContentPane().add(fareNew);
            int index = 0, x = 150;
            for(int i = 0; i < 40; i++) {
                if(seat[i].isSelected()) {
                    seatsNew[index] = new JLabel(seat[i].getActionCommand());
                    seatsNew[index].setSize(30, 30);
                    seatsNew[index].setLocation(x, 380);
                    f2.getContentPane().add(seatsNew[index]);
                    index++;
                    x += 30;
                }
            }
            
            f2.setSize(400, 600);
            f2.setVisible(true);
            
            
        }
        

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if(pageIndex > 0)
                return NO_SUCH_PAGE;
            
            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            f2.printAll(graphics);
            return PAGE_EXISTS;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(name.getText().equals("") == false && contact.getText().equals("") == false) {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(this);
                boolean ok = job.printDialog();
                if(ok) {
                    try {
                        job.print();
                    } catch(PrinterException ex) {}
                }
                
                conn = DB.getConn();
                try {
                    st = conn.createStatement();
                    for(int i = 0; i < 40; i++) {
                        if(seat[i].isSelected()) {
                            query = "insert into " + table + " values ('" + busID
                                    + "' , '" + (String)seat[i].getActionCommand()
                                    + "' , '" + contact.getText() + "' , '" +
                                    name.getText() + "')";
                            st.executeQuery(query);
                        }
                    }
                            
                } catch(SQLException ex) {}
                
                f2.dispose();
                f.dispose();
                
            }
            else {
                JOptionPane.showMessageDialog(f2, "Please enter name and contact number");
            }
        }
        
        
        
        
        
    }
}
