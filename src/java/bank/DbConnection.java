package bank;

import java.sql.*;
public class DbConnection {
     String driver = "org.apache.derby.jdbc.ClientDriver";
    String con = "jdbc:derby://localhost:1527/bank";
    String user = "root";
    String pass = "root";
   static Connection connect = null;
    public DbConnection(){
        
        try{
          Class.forName(driver); 
          connect = DriverManager.getConnection(con,user,pass);
               
        }
        catch(ClassNotFoundException | SQLException e)
        {
            
        }
        
    }
    
   public static Connection getConnect(){
       new DbConnection();
       System.out.print("db connect hua");
       return connect;
          
}
    
}
