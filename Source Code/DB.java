//package pos;
import java.sql.*;
import javax.swing.JOptionPane;

public class DB {
    private static Connection DBConn;
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    
    public static Connection getConn() {
        if(DBConn == null) {
            try {
                Class.forName(DRIVER);
                DBConn = DriverManager.getConnection(URL, "nazmi", "nazmi");
            } catch(ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Class Not Found");
            } catch(SQLException ex) {
            	JOptionPane.showMessageDialog(null, "Cannot connect");
            }
        }
        return DBConn;
    }
     
}
