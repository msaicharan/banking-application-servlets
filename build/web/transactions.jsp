<%-- 
    Document   : transactions
    Created on : Dec 10, 2018, 12:36:48 AM
    Author     : admin
--%>

<%@page import="bank.DbConnection"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./transactiontable.css"/> 
    </head>
    <body>
<%       
            HttpSession sess = request.getSession(false);
            try 
            {
            String deb="",cred="";
            String transid = "";
            String amount = "";
            String debit =  "";          
            String credit="" ;
            Timestamp  datetime ; 
            
            Connection c = DbConnection.getConnect();
            System.out.print("connect hua");
            Statement st1 = c.createStatement();
            
            String acnum = (String)sess.getAttribute("acnum");
            System.out.print(acnum);
            
            String sql = "select * from transactions where debit_from = '"+acnum+"' or credit_to ='"+acnum+"' order by timestamp";
            System.out.println(sql);
            ResultSet rs1 = st1.executeQuery(sql);
            System.out.println("trans db connect");
            
         out.print("<table id='transactions'>");
         out.print("<tr>");
         out.print("<th>Date</th>");
         out.print("<th>Reference ID</th>");
         out.print("<th>Credit</th>");
         out.print("<th>Debit</th>");
         out.print("</tr>");
         
         while(rs1.next()) {
                transid = rs1.getString("transaction_id");
                debit = rs1.getString("debit_from");
                credit = rs1.getString("credit_to");
                amount = rs1.getString("amount");
                datetime = rs1.getTimestamp("timestamp");
              
                out.print("<tr>");
                Date d = new Date(datetime.getTime());
                out.print("<td>"+d+"</td>");
                out.print("<td>"+transid+"</td>");
                
                if(acnum.equals(debit))
                {
                    deb = amount;
                    cred = " ";
                    out.print("<td>"+cred+"</td>");
                    out.print("<td>"+deb+"</td>");
                }
                else{
                    cred = amount;
                    deb=" ";
                    out.print("<td>"+cred+"</td>");
                    out.print("<td>"+deb+"</td>");
                }
                    out.print("</tr>");
            
         }
        }catch(SQLException e){}

            finally{
                String balance = (String)sess.getAttribute("balance");
                out.print("Available Balance: Rs. "+balance );
                out.print("<br>");
                 out.print("<a href='Logout'>Logout</a>");
                  out.print("<br>");
                }

    
    
    %>
    </body>
</html>
