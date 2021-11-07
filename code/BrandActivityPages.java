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

    public static void ActivityTypes(int flag,String LoyaltyId,String BrandId){
    
        
        System.out.println("1. Purchase");
        System.out.println("2. Leave a Review");
        System.out.println("3. Refer a Friend");
        System.out.println("4. Go Back");
        System.out.println("Choose from above options");

        select = sc.nextInt();

        switch(select){
            case 1:
                Purchase(BrandId,flag,LoyaltyId);
                break;
            case 2:
                LeaveaReview(BrandId,flag,LoyaltyId);
                break;
            case 3:
                ReferaFriend(BrandId,flag,LoyaltyId);
                break;
            case 4:
                if(flag == 0)
                {
                    BrandRegularPage.Regular(LoyaltyId,BrandId);
                    break;
                }
                else{
                    BrandTierPage.Tier(LoyaltyId,BrandId);
                }
            default:
                System.out.println("Invalid Input. Enter your choice again");
                ActivityTypes(flag,LoyaltyId,BrandId);
        }

    }

    public static void Purchase(String BrandId,int flag,String LoyaltyId){

        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        ResultSet result1 = null;
        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Loading purchase module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();

                    String getactivitycode = "Select * from Activity_Type where activity_name='Purchase'";

                    result = statement.executeQuery(getactivitycode);
                    String activity_code = "";
                    if(result.next())
                        activity_code = result.getString("activity_code");

                    System.out.print(activity_code);
                       
                    String query = "INSERT INTO Activity_program VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, activity_code);
                    pstmt.setString(2, "Purchase");
                    pstmt.setString(3,LoyaltyId);
                    pstmt.execute();
                                
                    System.out.println("Activity Added to loyalty program \n");
                    ActivityTypes(flag,LoyaltyId,BrandId);

                } finally {
                    result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
        
    }

    public static void LeaveaReview(String BrandId,int flag, String LoyaltyId){

        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        ResultSet result1 = null;
        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("loading leave a review module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();


                    String getactivitycode = "Select * from Activity_Type where activity_name='Leave a Review'";

                    result = statement.executeQuery(getactivitycode);
                    String activity_code = "";
                    if(result.next())
                        activity_code = result.getString("activity_code");
                       
                    String query = "INSERT INTO Activity_program VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, activity_code);
                    pstmt.setString(2, "Leave a Review");
                    pstmt.setString(3,LoyaltyId);
                    pstmt.execute();
                    
                
                    System.out.println("Activity Added to loyalty program \n");
                    ActivityTypes(flag,LoyaltyId,BrandId);

                } finally {
                    //result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }

    }

    public static void ReferaFriend(String BrandId, int flag, String LoyaltyId){

        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        ResultSet result1 = null;
        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("loading refer a friend module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();

                    String getactivitycode = "Select * from Activity_Type where activity_name='Refer a Friend'";

                    result = statement.executeQuery(getactivitycode);
                    String activity_code = "";
                    if(result.next())
                        activity_code = result.getString("activity_code");
                       
                
                    String query = "INSERT INTO Activity_program VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, activity_code);
                    pstmt.setString(2, "Refer a friend");
                    pstmt.setString(3,LoyaltyId);
                    pstmt.execute();
                
            
                    System.out.println("Activity Added to loyalty program \n");
                    ActivityTypes(flag,LoyaltyId,BrandId);

                } finally {
                    //result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }

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