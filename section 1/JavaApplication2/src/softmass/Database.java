package softmass;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Database {
    //DB Connection path/username/password
    private static final String URLDB="jdbc:mysql://localhost:3306/smassignment";
    private static final String USERNAMEDB="root";
    private static final String PASSWORDDB="";
    //DB Connection classes
    public Connection conn;
    public PreparedStatement prepareSatement=null;
    public ResultSet rs= null;
    public DefaultTableModel model;
    public ResultSetMetaData rss;
    
    
    public void Database(){
        try {
            conn = (Connection) DriverManager.getConnection(URLDB, USERNAMEDB, PASSWORDDB);
            System.out.println(URLDB+" - MYSQL DATABASE CONNCTED.");
       
        }catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public void view(String selectQuery){
        System.out.println(" View() ");
        try {
            prepareSatement = (PreparedStatement) conn.prepareStatement(selectQuery);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add(String addQuery){
        System.out.println(" add() ");
        try {
            prepareSatement =(PreparedStatement) conn.prepareStatement(addQuery);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void update(String updateQuery){
        System.out.println(" update() ");
        try {
            prepareSatement = (PreparedStatement) conn.prepareStatement(updateQuery);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(String deleteQuery){
        //"DELETE FROM perchaseorder  WHERE order_id=?";
        try {
            prepareSatement = (PreparedStatement) conn.prepareStatement(deleteQuery);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void queryexeute(){
        try {
            if( prepareSatement.executeUpdate()== 1 )
            { System.out.println("EXECUTED QUERY"); }
            else{  System.out.println("Opps Errors Execute Query.........?");  }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Connectionclose(){
        try {
            conn.close();   
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    
    
}
