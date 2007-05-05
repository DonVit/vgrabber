package vgrabber;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.Font;

/**
 * Modify and query SQL Server 2000 database
 *
 */
public class SQLServerEx {
    
    /** Creates a new instance of SQLServerEx */
    public SQLServerEx() {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=northwind";
//            String url = "jdbc:jtds:sqlserver://localhost:1433/northwind";
            Properties props = new Properties();
            props.put("user", "sa");
            props.put("password", "as");
            Connection con = DriverManager.getConnection(url, props);
            Statement stmt = con.createStatement();
            /*
            String update = "UPDATE Customers SET City=N'Hà N?i' WHERE City=N'London'";
            stmt.execute(update);
            update = "UPDATE Customers SET CompanyName=N'Café Th??ng Uy?n', Address=N'Ph? L??ng V?n Can', Country=N'Vi?t Nam' WHERE City=N'Hà N?i'";
            stmt.execute(update);
            
            String updateString = "UPDATE Customers SET CompanyName=?, Address=?, City=?, Country=? WHERE City=?";
            PreparedStatement preStmt = con.prepareStatement(updateString);
            preStmt.setString(1, "L?c Huy?n C?m");
            preStmt.setString(2, "Tr?n Qu?c To?n");
            preStmt.setString(3, "?à N?ng");
            preStmt.setString(4, "Vi?t Nam"); 
            preStmt.setString(5, "México D.F."); 
            preStmt.executeUpdate();
            */
            //String query = "SELECT CompanyName AS 'Tên ti?m', Address AS '??a ch?', City AS 'Thành ph?', Phone AS '?i?n tho?i', Country AS 'Qu?c gia' FROM Customers";
            String query = "SELECT nume AS 'Tên ti?m', nume AS '??a ch?', nume AS 'Thành ph?', nume AS '?i?n tho?i', nume AS 'Qu?c gia' FROM categorie";
            //String query = "SELECT 'id', nume, 'City', 'Phone', 'Country' FROM categorie";
            ResultSet rs = stmt.executeQuery(query);
            //while (rs.next()){
            //    System.out.println(rs.getString(2));
            //}
            displayResult(rs, "Khách hàng");
            
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    /** 
     * Shows resultset 
     */
    void displayResult(ResultSet rs, String tableName) {
        ResultsModel model = new ResultsModel();     // Create a table model
        model.setResultSet(rs);
        JTable table = new JTable(model);            // Create a table from the model
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);   // Use scrollbars
        Font font = table.getTableHeader().getFont().deriveFont(Font.BOLD);
        table.getTableHeader().setFont(font); // Bold header font
        
        JFrame jf = new JFrame(tableName);
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(800, 400);
        JTextArea jt = new JTextArea();
        jf.getContentPane().add(new JScrollPane(table));        
        jf.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main1(String[] args) {
        new SQLServerEx();
    }
}