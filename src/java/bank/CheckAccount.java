package bank;
import java.sql.*;

public class CheckAccount {
static String m,n,bal;
static Statement st;
static Connection c;
static  boolean valid;
    public static boolean accValid(String acname, String acno,String acmobile )
    {
        try
        {
        c = DbConnection.getConnect();
        st = c.createStatement();
        System.out.print("connect hua1");
//        System.out.print(acno);
//        System.out.print(acc_name);
//        System.out.print(number);
     String sql1 = "select name,mobile_number,balance from account_holders where account_number ='"+acno+"'" ;
            System.out.println(sql1);
       ResultSet rs3 = st.executeQuery(sql1);
        System.out.print("query execute hua");
        System.out.println(rs3);
       while(rs3.next()) {
        n = rs3.getString("name");
        m = rs3.getString("mobile_number");
        bal = rs3.getString("balance");
       }
        System.out.print(n);
        System.out.print(m);
               
        if(n.equals(acname) && m.equals(acmobile))
           valid = true;

        else
           valid =  false;
    }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return valid;
    }

    public static String giveBalance()
    {
        return bal;
    }
}
