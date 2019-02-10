package bank;

import static bank.EncryptDecrypt.crypt;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
public class loginCheck extends HttpServlet {
    
    String name="";
    String accnumber="";
    String lp="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
        try (PrintWriter out = response.getWriter()) {
        
            Connection c = DbConnection.getConnect();
            Statement st = c.createStatement();
            String user = request.getParameter("uname");
            String passwd = request.getParameter("pass");
            String sql = "select name,account_number,password from users where user_name ='"+user+"'";
            System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        {
            name = rs.getString("name");
            accnumber = rs.getString("account_number");
            lp = rs.getString("password");
        }
        System.out.println(name);
        System.out.println(accnumber);
        System.out.println(lp);
        String elp = crypt(passwd);

          if(elp.equals(lp))
        {
            out.print("success login");
            HttpSession sess = request.getSession();
            sess.setMaxInactiveInterval(300);
            sess.setAttribute("acname",name);
            sess.setAttribute("acnum",accnumber);
            
           RequestDispatcher rd = request.getRequestDispatcher("successlogin.jsp");
           rd.forward(request,response);
           
        }
   
    else
        {

        out.println("<script type=\"text/javascript\">");
        out.println("alert('User or password incorrect');");
        out.println("</script>");
        
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.include(request,response);
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(loginCheck.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
