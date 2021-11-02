import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandActivityPages{
    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void ActivityTypes(int flag,String BrandId){
        

        
        System.out.println("1. Purchase");
        System.out.println("2. Leave a Review");
        System.out.println("3. Refer a Friend");
        System.out.println("4. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Purchase(BrandId);
                break;
            case 2:
                LeaveaReview();
                break;
            case 3:
                ReferaFriend();
                break;
            case 4:
                if(flag == 0)
                {
                    BrandRegularPage.Regular();
                    break;
                }
                else{
                    BrandTierPage.Tier();
                }
            default:
                System.out.println("Invalid Input. Enter your choice again");
                ActivityTypes(flag);
        }

    }

    public static void Purchase(String BrandId){

        System.out.println("Enter Loyalty Id: \n");
        String LoyaltyId = "";
        LoyaltyId = sc.next();

        System.out.println("Enter Loyalty_Program_Name");
        String lpname = "";
        lpname = sc.next();

        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();


                    String getactivitycode = "Select activity_code from ActivityType where activity_name='Purchase'";

                    result = statement.executeQuery(getactivitycode);
                    activity_code = result.getString("activity_code");
                       
                    if(flag==0)
                    {
                        String addActivity = "INSERT INTO Loyalty_program Values('"+ LoyaltyId +"','"+ lpname +"','""')";
                    }
                    
                    statement.executeQuery(addRE);
        
                    
                    System.out.println("RE Rule Addition successful!!! \n");

                } finally {
                    close(result);
                    close(statement);
                    close(connection);
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
        
    }

    public static void LeaveaReview(){

    }

    public static void ReferaFriend(){

    }
}