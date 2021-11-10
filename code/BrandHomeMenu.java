import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandHomeMenu {
    static Scanner sc = new Scanner(System.in);
    static int select = 0;
    
    public static void BrandMenuoptions() {

        System.out.println("1. Add Loyalty Program");
        System.out.println("2. add RE Rules");
        System.out.println("3. update RE Rules");
        System.out.println("4. add RR Rules");
        System.out.println("5. Update RR Rules");
        System.out.println("6. validate Loyalty Program");
        System.out.println("7. Go Back");
        System.out.println("Choose from above options");
        
    }

    public static void main (String args[])
    {
        String LoyaltyId = Check(args[0]);
        BrandMenuoptions();
        // System.out.println(LoyaltyId);
        select = sc.nextInt();
        String BrandId = args[0];
        // if(!LoyaltyId.equals(""))
        // {
        //     LoyaltyId = args[1];
        // }
            switch(select){
                case 1:
                    BrandMethods.LoyaltyProgram(BrandId);
                    break;
                case 2:
                    BrandMethods.AddRERule(BrandId,LoyaltyId);
                    break;
                case 3:
                    BrandMethods.UpdateRERule(BrandId,LoyaltyId);
                    break;
                case 4:
                    BrandMethods.AddRRRule(BrandId,LoyaltyId);
                    break;
                case 5:
                    BrandMethods.UpdateRRRule(BrandId,LoyaltyId);
                    break;
                case 6:
                    BrandMethods.ValidateLoyaltyProgram(BrandId,LoyaltyId);
                    break;
                case 7:
                    HomePage.main(null);
                    break;
                
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    BrandHomeMenu.main(args);
            }
    }

    public static String Check(String BrandId)
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
                    System.out.println("Checking if Loyalty Program exists for selected Brand...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String gettuples = "Select * from Loyalty_program where brand_id='"+ BrandId +"'";
                    result = statement.executeQuery(gettuples);
                    String LoyaltyId = "";

                    if(result.next())
                    {
                        LoyaltyId = result.getString("loyalty_id");
                        return LoyaltyId;
                    }
                    else
                    {
                        System.out.println("Enroll into Program first");
                        return "";
                    }
                    
                } finally {
                    result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
        return null;
    }

    static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Throwable whatever) {
            }
        }
    }
}