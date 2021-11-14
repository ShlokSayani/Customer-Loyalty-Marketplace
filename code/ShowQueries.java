import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class ShowQueries{
   
    public static void ShowQueries()
    {
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Fetching Sample queries...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
                    System.out.println();
                    System.out.println("Query1");
                    System.out.println();
                    String query1 = "Select DISTINCT C.customer_id,C.customer_name from Customer C where C.customer_id NOT IN (Select P.customer_id from Customer_program P, Loyalty_program L where P.loyalty_id = L.loyalty_id and P.brand_id = 'Brand02')";

                    //String query1 = "Select DISTINCT C.customer_id,C.customer_name From Customer C,Customer_program P,Loyalty_program L where P.loyalty_id = L.loyalty_id AND C.customer_id = P.customer_id AND P.brand_id <> 'Brand02'";
                    result = statement.executeQuery(query1);

                    while(result.next())
                    {
                        String c_id = result.getString("customer_id");
                        String c_name = result.getString("customer_name");
                        System.out.println("Customer Id: " + c_id + ", Customer Name: " + c_name);
                    }
                    System.out.println();
                    System.out.println("Query2");
                    System.out.println();
                    String query2 = "select customer_id, loyalty_id from Customer_program where customer_id NOT IN (select customer_id from Activity_Transactions)";
                    result = statement.executeQuery(query2);

                    while(result.next())
                    {
                        String customer_id = result.getString("customer_id");
                        String loyalty_id = result.getString("loyalty_id");
                        System.out.println("Customer Id: " + customer_id + ", Loyalty Id: " + loyalty_id);
                    }
                    System.out.println();
                    System.out.println("Query3");
                    System.out.println();
                    String query3 = "Select * from Reward_program R,Loyalty_program L where L.loyalty_id = R.loyalty_id AND L.brand_id = 'Brand01'";
                    result = statement.executeQuery(query3);

                    while(result.next())
                    {
                        String reward_code = result.getString("reward_code");
                        String reward_name = result.getString("reward_name");
                        System.out.println("Reward Code: " + reward_code + ", Reward Name: " + reward_name);
                    }
                    System.out.println();
                    System.out.println("Query4");
                    System.out.println();
                    String query4 = "Select DISTINCT * from Loyalty_program L,Activity_program A where L.loyalty_id = A.loyalty_id AND A.activity_name = 'Refer a friend'";
                    result = statement.executeQuery(query4);

                    while(result.next())
                    {
                        String Loyalty_id = result.getString("loyalty_id");
                        String Loyalty_Program_Name = result.getString("loyalty_program_name");
                        System.out.println("Loyalty_Code: " + Loyalty_id + ", Loyalty_Program_Name:" + Loyalty_Program_Name);
                    }
                    System.out.println();
                    System.out.println("Query5");
                    System.out.println();
                    String query5 = "Select COUNT(*),activity_type from Activity_Transactions where brand_id = 'Brand01' GROUP BY activity_type";
                    result = statement.executeQuery(query5);

                    while(result.next())
                    {
                        String activity_type = result.getString("activity_type");
                        int instances = result.getInt(1);
                        System.out.println("Activity_Type: " + activity_type + ", Number of Instanes:" +instances);
                    }
                    System.out.println();
                    System.out.println("Query6");
                    System.out.println();
                    String query6 = "Select C.customer_id from Customer_Redeem C,Reward_Transactions R,Customer C where R.reward_transaction_id = C.reward_transaction_id AND R.brand_id = 'Brand01' GROUP BY C.customer_id HAVING COUNT(*) > 1 ";
                    result = statement.executeQuery(query6);
                    
                    while(result.next())
                    {
                        String customer_id = result.getString("customer_id");
                        System.out.println("Customer_Id: " + customer_id);
                    }
                    System.out.println();
                    System.out.println("Query7");
                    System.out.println();
                    String query7 = "Select brand_id from Reward_Transactions GROUP BY brand_id HAVING SUM(redeem_points)<500";
                    result = statement.executeQuery(query6);

                    while(result.next())
                    {
                        String brand_id = result.getString("brand_id");
                        System.out.println("Brand_Id: " + brand_id);
                    }
                    System.out.println();
                    System.out.println("Query8");
                    System.out.println();
                    String query8 = "Select COUNT(*) from Activity_Transactions A,Wallet W where A.customer_id = W.customer_id AND A.wallet_id=W.wallet_id AND W.customer_id = 'C0003' AND A.brand_id = 'Brand02' AND A.activity_transaction_date between '26-AUG-2021' AND '30-SEP-2021'";
                    result = statement.executeQuery(query8);

                    while(result.next())
                    {
                        int numberact = result.getInt(1);
                        System.out.println("Number of Activities: "+ numberact);
                    }

                    }

                finally {
                    //result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
    }

    public static void main(String[] args)
    {
        ShowQueries();
    }
}
