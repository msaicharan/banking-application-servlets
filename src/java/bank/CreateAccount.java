package bank;

import static bank.CheckAccount.accValid;
import static bank.CheckAccount.giveBalance;
//import static bank.CheckAccount.aisehi;
import static bank.EncryptDecrypt.crypt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;




public class CreateAccount extends HttpServlet {

      RequestDispatcher rd;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    }
    
    @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
       {
                     response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String u = request.getParameter("name");
            String acc = request.getParameter("accnumber");
            String no = request.getParameter("number");
            String uname = request.getParameter("uname");
            String lp1 = request.getParameter("lpass1");
            String lp2 = request.getParameter("lpass2");
            String tp1 = request.getParameter("tpass1");
            String tp2 = request.getParameter("tpass2");
       
            out.print(lp1);
            out.print(lp2);
            out.print(tp1);
            out.print(tp2);

     
               boolean x;
                x = accValid(u,acc,no);
                out.print("samjha");
                out.print(x);
//                aisehi();
           
            
            if(lp1.equals(lp2) && tp1.equals(tp2) && x)
            {
                
              String ep = crypt(lp1);
              String tp = crypt(tp1);
              out.print("hello1");
              Connection c = DbConnection.getConnect();
              System.out.println("connect hua2");
                PreparedStatement ps = c.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
              out.print("hell02");
            String bal = giveBalance();
                ps.setString(1, u);
                ps.setString(2, acc);
                ps.setString(3, no);
                ps.setString(4, uname);
                ps.setString(5, bal);
                ps.setString(6, ep);
                ps.setString(7, tp);
               int i = ps.executeUpdate();
               out.print("ho gaya");
               
 // forwarding page to login
             rd = request.getRequestDispatcher("login.jsp");
             rd.forward(request,response);
     
           }
            else{
                out.println("<script type=\"text/javascript\">");
   out.println("alert('Passwords do not match');");
   out.println("</script>");
   rd = request.getRequestDispatcher("createaccount.jsp");
            rd.include(request,response);
            }
          
            
        } catch (Exception ex) {
            System.out.print(ex);
        }
       
       }
}
