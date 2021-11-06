import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandTierSetUp{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;
    public static void TierSetUp(String BrandId,int flag){
        System.out.println("1. Enter Tier Information: Maximum 3 Tiers and name in increasing Order");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Setup(BrandId);
                break;
            case 2:
                BrandTierPage.Tier(flag,BrandId);
            default:
                System.out.println("Invalid Input. Enter your choice again");
                TierSetUp();
        }

    }

    public static void Setup(String BrandId){
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
                
                    System.out.println("Enter Number of Tiers");
                    int ntier = sc.nextInt();
                    int i = 0;
                    String Tier = "";
                    System.out.println("Enter Tier in increasing sequence:");
                    while(i<ntier)
                    {
                        System.out.println("Enter Tier:"+ i+1);
                        String temp = sc.next();
                        if(i>=1)
                            Tier = Tier.concat(',');
                        Tier = Tier.concat(temp);
                        i++;
                    }

                    System.out.println("Enter multipier for respective tiers");
                    String multilplier = "1,";
                    i = 1;
                    while(i<ntier)
                    {
                        System.out.println("Enter Multiplier:"+ i+1);
                        String temp = sc.next();
                        if(i>=2)
                            multilplier = multilplier.concat(',');
                        multilplier = multilplier.concat(temp);
                        i++;
                    }

                    System.out.println("Enter points required for respective tiers");
                    String points = "0,";
                    i = 1;
                    while(i<ntier)
                    {
                        System.out.println("Enter Points:"+ i+1);
                        String temp = sc.next();
                        if(i>=2)
                            points = points.concat(',');
                        points = points.concat(temp);
                        i++;
                    }

                    String query = "INSERT INTO Loyalty_program VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, LoyaltyId);
                    pstmt.setString(2, lpname);
                    pstmt.setString(3,Tier);
                    pstmt.setString(4,multilplier);
                    pstmt.setString(5,points);
                    pstmt.setNull(6,Types.NULL)
                    pstmt.setNull(7,Types.NULL);
                    pstmt.setNull(8, Types.NULL);
                    pstmt.setNull(9,Types.NULL);
                    pstmt.setString(10,BrandId);
                    pstmt.setNull(11,Types.NULL)
                    pstmt.execute();
             
                    System.out.println("Activity Added to loyalty program \n");

                } finally {
                    result.close();
                    pstmt.close();
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
