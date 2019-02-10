<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bank.DbConnection"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Map" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Success Transfer </h3>
        <br>
        
        <%
            ResultSet rs1= null;
            Statement st1;
            Connection c;
            boolean tran_status= false;
            String amount= "";
            try{
            c = DbConnection.getConnect();
            System.out.print("connect hua");
            st1 = c.createStatement();
            
           HttpSession sess=request.getSession(false);  
           
            String transaction_id=(String)sess.getAttribute("transid");
            String credit_to_name =(String)sess.getAttribute("creditToname");
            
            out.print("Transaction Id: "+ transaction_id);
            out.print("Transfer to: "+credit_to_name);
            
            String sql = "select amount,status from transactions where transaction_id = '"+transaction_id+"'";
            rs1 = st1.executeQuery(sql);
            while(rs1.next())
            {
                amount = rs1.getString("amount");
                tran_status = rs1.getBoolean("status");
            }
           
            out.print("Amount: "+amount);
   
            System.out.println(amount);
            System.out.println(tran_status);
            
            if(tran_status==true)
                out.print("Status: Success");
            else
                out.print("Status: Failed");
            
            
            }
            
            catch(SQLException e)
            {
               out.print("Sorry could not fetch the details. Try after sometime.") ;
            }

            %>
             <br>
            <a href="Logout">Logout</a>
    </body>
</html>
