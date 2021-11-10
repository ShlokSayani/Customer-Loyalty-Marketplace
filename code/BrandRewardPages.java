import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandRewardPages{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void RewardTypes(int flag, String LoyaltyId,String BrandId){
        System.out.println("1. Gift Card");
        System.out.println("2. Free Product");
        System.out.println("3. Go Back");
        System.out.println("Choose from above options");

        select = sc.nextInt();

        switch(select){
            case 1:
                GiftCard(flag,BrandId,LoyaltyId);
                break;
            case 2:
                FreeProduct(flag,BrandId,LoyaltyId);
                break;
            case 3:
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
                RewardTypes(flag,LoyaltyId,BrandId);
        }

    }

    public static void GiftCard(int flag,String BrandId,String LoyaltyId){

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
                    System.out.println("Loading Gift Card module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();


                    String getactivitycode = "Select * from Reward_Type where reward_name='Gift Card'";

                    result = statement.executeQuery(getactivitycode);
                    String reward_code = "";
                    if(result.next())
                        reward_code = result.getString("reward_code");
                    
                    System.out.println("Enter quantity of rewards:");
                    int quantity = sc.nextInt();

                    String query = "INSERT INTO Reward_program VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, reward_code);
                    pstmt.setString(2, "Gift Card");
                    pstmt.setString(3,LoyaltyId);
                    pstmt.setInt(4,quantity);
                    pstmt.execute();
                    
                
                    System.out.println("Reward Added to loyalty program \n");
                    RewardTypes(flag,LoyaltyId,BrandId);

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

    public static void FreeProduct(int flag,String BrandId, String LoyaltyId){

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
                    System.out.println("Loading free product module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();

                    String getactivitycode = "Select * from Reward_Type where reward_name='Free Product'";

                    result = statement.executeQuery(getactivitycode);
                    String reward_code = "";
                    if(result.next())
                        reward_code = result.getString("reward_code");

                    System.out.println("Enter quantity of rewards:");
                    int quantity = sc.nextInt();
                       
                    String query = "INSERT INTO Reward_program VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, reward_code);
                    pstmt.setString(2, "Free Product");
                    pstmt.setString(3,LoyaltyId);
                    pstmt.setInt(4,quantity);
                    pstmt.execute();
                    
                    System.out.println("Reward Added to loyalty program \n");
                    RewardTypes(flag,LoyaltyId,BrandId);

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