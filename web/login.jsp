<%-- 
    Document   : login
    Created on : Aug 23, 2018, 10:44:40 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
       <form action="login" method="post">
           Username: <input type="text" name="uname" value="" size="20" /> <br><br>
           Password: <input type="password" name="pass" value="" size="20" /> <br><br>
           <input type="submit" value="Submit" name="Submit" /> 
           <input type="reset" value="Reset" name="Reset" /> 
       </form>
        
    </body>
</html>
