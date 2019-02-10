<%@page import="bank.DbConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Map" %>
<%@page import="bank.loginCheck" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="loginbal.js"></script>
        
</head> 
      
       <%
            ResultSet rs1= null;
            Statement st1;
            Connection c;
            String name="";
            String acc="";
            String bal="";
            
            HttpSession sess = request.getSession(false);
            String acnum = (String)sess.getAttribute("acnum");
      
            try 
            {
            c = DbConnection.getConnect();
            System.out.print("connect hua");
            st1 = c.createStatement();
            String sql = "select name,balance from users where account_number ='"+acnum+"'";
            rs1 = st1.executeQuery(sql);
            
        while(rs1.next()) {
           name= rs1.getString("name");
           bal = rs1.getString("balance");
            }
           sess.setAttribute("balance", bal);
          System.out.print(bal);
           out.print("<br>");    
       
       
        out.print("Welcome, "+name);    
        out.print("<div id='balance'>");
        out.print("<h2>Get Account Balance Info</h2>");
        %>
 
        <p id='bal1' onclick ="getBal('<%=bal%>')" style='cursor: pointer; cursor: hand;'> Click here for balance </p>
        <br>
        <a href="transactions.jsp">Click here for transactions</a>

       <%
        out.print("</div>");
        } catch(Exception e)
            {
                System.out.print(e);
            }
            
            finally{
            out.print("Transfer money to another account  ");
            
            out.print("<a href='transfer.jsp'>Click here</a>");
 out.print("<br>");
  out.print("<a href='Logout'>Logout</a>");
            }
          %>
 


