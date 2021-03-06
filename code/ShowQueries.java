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
                    System.out.println("Query1: List all customers that are not part of Brand02’s program.");
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
                    System.out.println("Query2: List customers that have joined a loyalty program but have not participated in any activityin that program (list the customerid and the loyalty program id).");
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
                    System.out.println("Query3: List the rewards that are part of Brand01 loyalty program.");
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
                    System.out.println("Query4: List all the loyalty programs that include “refer a friend” as an activity in at least one oftheir reward rules.");
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
                    System.out.println("Query5: For Brand01, list for each activity type in their loyalty program, the number instances thathave occurred.");
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
                    System.out.println("Query6: List customers of Brand01 that have redeemed at least twice.");
                    System.out.println();
                    String query6 = "Select C.customer_id from Customer C where C.customer_id IN (Select R.customer_id from Reward_Transactions R where R.brand_id = 'Brand01' GROUP BY R.customer_id HAVING COUNT(*) > 1)";
                    result = statement.executeQuery(query6);
                    
                    while(result.next())
                    {
                        String customer_id = result.getString("customer_id");
                        System.out.println("Customer_Id: " + customer_id);
                    }
                    System.out.println();
                    System.out.println("Query7: All brands where total number of points redeemed overall is less than 500 points");
                    System.out.println();
                    String query7 = "select B.brand_id from Brand B where B.brand_id NOT IN (select R.brand_id from Reward_Transactions R GROUP BY R.brand_id HAVING SUM(R.redeem_points)>500)";
                    result = statement.executeQuery(query7);

                    while(result.next())
                    {
                        String brand_id = result.getString("brand_id");
                        System.out.println("Brand_Id: " + brand_id);
                    }
                    System.out.println();
                    System.out.println("Query8: For CustomerC0003, and Brand02, number of activitiesthey have done in the period of 08/1/2021 and 9/30/2021.");
                    System.out.println();
                    String query8 = "Select COUNT(*) from Activity_Transactions A where  A.customer_id = 'C0003' AND A.brand_id = 'Brand02' AND A.activity_transaction_date between '01-AUG-2021' AND '30-SEP-2021'";
                    result = statement.executeQuery(query8);

                    while(result.next())
                    {
                        int numberact = result.getInt(1);
                        System.out.println("Number of Activities: "+ numberact);
                    }
                    System.out.println();
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
