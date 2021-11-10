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
    public static void TierSetUp(String BrandId,int flag,String LoyaltyId){
        System.out.println("1. Enter Tier Information: Maximum 3 Tiers and name in increasing Order");
        System.out.println("2. Go Back");
        System.out.println("Choose from above options");

        select = sc.nextInt();

        switch(select){
            case 1:
                Setup(BrandId,LoyaltyId);
                break;
            case 2:
                BrandTierPage.Tier(LoyaltyId,BrandId);
            default:
                System.out.println("Invalid Input. Enter your choice again");
                TierSetUp(BrandId,flag,LoyaltyId);
        }

    }

    public static void Setup(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Setting up Tier...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();

                    String gettier = "Select * from Tier where loyalty_id='"+LoyaltyId+"'";
                    result = statement.executeQuery(gettier);

                    if(result.next())
                    {
                        System.out.println("Tier already added");
                        BrandTierPage.Tier(LoyaltyId,BrandId);
                        
                    }
                    else
                    {
                        System.out.println("Enter Number of Tiers");
                        int ntier = sc.nextInt();
                        int i = 0;
                        
                        System.out.println("Enter Tier in increasing sequence:");
                        while(i<ntier)
                        {
                            String Tier = "";
                            System.out.println("Enter Tier " +(i+1) + ": ");
                            Tier = sc.nextLine();
                            String multilplier = "";
                            String points = "";
                            if(i==0)
                                multilplier = "1";
                            else
                            {
                                System.out.println("Enter multiplier for this tier: ");
                                multilplier = sc.nextLine();
                            }
                            if(i==0)
                                points = "0";
                            else
                            {
                                System.out.println("Enter points for this tier: ");
                                points = sc.nextLine();
                            }
                            String query = "INSERT INTO Tier VALUES (?, ?, ?, ?)";
                            PreparedStatement pstmt = connection.prepareStatement(query);
                            pstmt.setString(1,Tier);
                            pstmt.setString(2,multilplier);
                            pstmt.setString(3,points);
                            pstmt.setString(4,LoyaltyId);
                            pstmt.execute();

                            i++;
                        }
                        
                        System.out.println("Tiers Added to loyalty program \n");

                        BrandHomeMenu.main(args);
                    }

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
