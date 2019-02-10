<%-- 
    Document   : CreateAccount
    Created on : Aug 24, 2018, 12:04:38 AM
    Author     : Saicharan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="create" method="post">
            Name: <input type="text" name="name" value="" size="20" /> <br><br>
            Account Number: <input type="text" name="accnumber" value="" size="20" /> <br><br>
            Mobile Number: <input type="text" name="number" value="" size="20" /> <br><br>
            Username: <input type="text" name="uname" value="" size="20" /> <br><br>
            Login Password: <input type="password" name="lpass1" value="" size="20" /> <br><br>
            Confirm Login Password: <input type="password" name="lpass2" value="" size="20" /> <br><br>
            Transaction Password: <input type="password" name="tpass1" value="" size="20" /> <br><br>
            Confirm Transaction Password:<input type="password" name="tpass2" value="" size="20" /> <br><br>
            
           <input type="submit" value="Submit" name="Submit" /> 
           <input type="reset" value="Reset" name="Reset" /> 
       </form>
    </body>
</html>
