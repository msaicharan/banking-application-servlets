
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="Logout">Logout</a>
        <br>
        <form action="transfermoney" method="post">
            Account Number: <input type="text" name="accnum"> <br><br>
            Confirm Account Number: <input type="text" name="accnum1"> <br><br>
            Name of account holder: <input type="text" name="accname"><br><br>
            Amount to transfer: <input type="text" name="amount"><br><br>
            Enter transaction password: <input type="password" name="tpass1"><br><br>
            Confirm transaction password: <input type="password" name="tpass2"><br><br>
            
           <input type="submit" value="Submit" name="Submit" /> 
           <input type="reset" value="Reset" name="Reset" /> 
        </form>
    </body>
</html>
