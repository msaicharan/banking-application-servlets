package bank;

import static bank.EncryptDecrypt.crypt;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Transfer extends HttpServlet {
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    Statement st1,st2,st3,st4,st5 = null;
    Connection c;
   PreparedStatement pst ;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     response.setContentType("text/html;charset=UTF-8");
           }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try (PrintWriter out = response.getWriter()) {
            String tp="";
            int bal=0;
            int bal3 = 0 ;
     
            
            c = DbConnection.getConnect();
            System.out.println("connect hua transfer");
            st1 = c.createStatement();
            st2 = c.createStatement();
            st3 = c.createStatement();
            st4 = c.createStatement();
            st5 = c.createStatement();
            
            HttpSession sess=request.getSession(false);  
            String accnumber=(String)sess.getAttribute("acnum");
            
            out.println(accnumber);
            
            
            
           String acc1 = request.getParameter("accnum");
           String acc2 = request.getParameter("accnum1");
           String acname = request.getParameter("accname");
           int amount = Integer.parseInt(request.getParameter("amount"));
           String tp1 = request.getParameter("tpass1");
           String tp2 = request.getParameter("tpass2");
            
          String etp = crypt(tp1);
                     
                        
            String sql = "select transaction_password from users where account_number ='"+accnumber+"'";
            rs1 = st1.executeQuery(sql);
            while(rs1.next())
            {
                tp = rs1.getString("transaction_password");
            }
           
           out.print("encrypt kiya hua");
           out.println(etp);
           out.print("fetch kiya hua");
           out.println(tp);
          
            String transaction_id = randomgenerator();
                      
            if(tp1.equals(tp2)&& etp.equals(tp) && acc1.equals(acc2))
             {
             
            String sql1 = "select balance from account_holders where account_number ='"+accnumber+"'";
            rs2 = st2.executeQuery(sql1);
            while(rs2.next())
            {
                bal = Integer.parseInt(rs2.getString("balance"));
            }
            
                if(bal > amount)
                    {
                        c.setAutoCommit(false);
                        
                        String curr_bal= String.valueOf(bal-amount);    //new balance of the login user
                        out.print(curr_bal);
                        
                        String sql2 = "select balance from account_holders where account_number ='"+acc1+"'";
                        rs3 = st3.executeQuery(sql2);
                        while(rs3.next())
                        {
                          bal3 = Integer.parseInt(rs3.getString("balance"));
                        }
                        String b4 = String.valueOf(bal3 +amount);    //new balance of 3rd party
                        out.print(b4);
                     
                     
                        //update balance of login user in both tables.
                        String sql3 = "update account_holders set balance= '"+curr_bal+"' where account_number='"+accnumber+"'";
                        System.out.println(sql3);
                        int p = st5.executeUpdate(sql3);
                        
                        String sql4 = "update users set balance= '"+curr_bal+"' where account_number='"+accnumber+"'";
                        System.out.println(sql4);
                        p = st5.executeUpdate(sql4);
                        
                      
                        //update balance of 3rd party user in both tables.
                        String sql5 = "update account_holders set balance= '"+b4+"' where account_number='"+acc1+"'";
                        System.out.println(sql5);
                        int q = st5.executeUpdate(sql5);
                        
                        String sql6 = "update users set balance= '"+b4+"' where account_number='"+acc1+"'";
                        System.out.println(sql6);
                        q = st5.executeUpdate(sql6);
                      
            
                        sess.setAttribute("creditToname", acname);
                        sess.setAttribute("creditTonumb", acc1);
                        sess.setAttribute("transid", transaction_id);
                       
                        String sql7 = "insert into transactions values(?,?,?,?,?,?)";
                        pst = c.prepareStatement(sql7);
                        pst.setString(1, transaction_id);
                        pst.setString(2, accnumber);
                        pst.setString(3, acc1);
                        pst.setString(4, String.valueOf(amount));
                        pst.setString(5, "true");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        pst.setTimestamp(6,timestamp );
                        
                       int i = pst.executeUpdate();
                       out.print("ho gaya");
                        
                     
                       c.commit();
                        //Transaction complete
                     
                     RequestDispatcher rd = request.getRequestDispatcher("successtransfer.jsp");
                  rd.forward(request,response);
                        
                    }
                else
                { 
                   out.println("<script type=\"text/javascript\">");
                   out.println("alert('Insufficient balance. Check the amount again!');");
                   out.println("</script>");
                    RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
                    rd.forward(request,response);
                }            
            } 
            else{
                 out.println("<script type=\"text/javascript\">");
                 out.println("alert('User or password incorrect');");
                 out.println("</script>");
                
//                RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
//                    rd.forward(request,response);
            }
        }   catch (SQLException ex) {   
           try{
               c.rollback();
           }
           catch(SQLException e)
           {
               
           }
        }
    }

    private String randomgenerator() {
      
   final String charlist = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<10; i++)
        {
             Random randomGenerator = new Random();
             int number = randomGenerator.nextInt(charlist.length());
             char ch = charlist.charAt(number);
             randStr.append(ch);
        }
       
        
        return randStr.toString();

}
    
    
}
//--------------------Trigger ke liye-------------------
//////CREATE TRIGGER changeBal
//AFTER UPDATE ON account_holders REFERENCING NEW AS NEW OLD AS OLD
//FOR EACH ROW 
//    Users u
//    SET u.balance = new.balance
//    WHERE u.account_number = new.account_number;
