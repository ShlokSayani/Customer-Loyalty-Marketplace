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

    public static void RewardTypes(int flag, String BrandId){
        System.out.println("1. Gift Card");
        System.out.println("2. Free Product");
        System.out.println("3. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                GiftCard(flag,BrandId);
                break;
            case 2:
                FreeProduct(flag,BrandId);
                break;
            case 3:
                if(flag == 0)
                {
                    BrandRegularPage.Regular(BrandId);
                    break;
                }
                else{
                    BrandTierPage.Tier(BrandId);
                }
            default:
                System.out.println("Invalid Input. Enter your choice again");
                RewardTypes(flag,BrandId);
        }

    }

    public static void GiftCard(int flag,String BrandId){

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
        ResultSet result1 = null;

        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();


                    String getstatus = "Select COUNT(*) from Loyalty program where loyalty_id = '"+ LoyaltyId +"'";
                    result1 = statement.executeQuery(getstatus);
                    String lpstatus = "";
                    if(result1.getInt(1) >= 3)
                        lpstatus = "active";
                    else
                        lpstatus = "inactive";


                    String getactivitycode = "Select reward_code from Reward_Type where reward_name='Gift Card'";

                    result = statement.executeQuery(getactivitycode);
                    String reward_code = result.getString("reward_code");
                       
                    if(flag==0)
                    {
                        String query = "INSERT INTO Loyalty_program VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, LoyaltyId);
                        pstmt.setString(2, lpname);
                        pstmt.setNull(3,Types.NULL);
                        pstmt.setNull(4,Types.NULL);
                        pstmt.setNull(5,Types.NULL);
                        pstmt.setNull(6,Types.NULL);
                        pstmt.setNull(7,Types.NULL);
                        pstmt.setString(8, reward_code);
                        pstmt.setString(9,"Gift Card");
                        pstmt.setString(10,BrandId);
                        pstmt.setString(11,lpstatus);
                        pstmt.execute();
                    }
                    else
                    {
                        System.out.println("Enter Tier");
                        String Tier = "";
                        Tier = sc.next();

                        String getpoints = "Select tier,multilplier,points_required from Loyalty_program where loyalty_id = '"+ LoyaltyId +"'";
                        result = statement.executeQuery(getpoints);

                        String gettier = result.getString("tier");

                        String[] str = gettier.split(",");
                        int i = 0;
                        int ans = -1;
                        while(i<str.length)
                        {
                            if(str[i]==Tier)
                                ans = i;
                            i++;
                        }

                        String[] points = result.getString("points_required").split(",");
                        String anspoints = points[ans];

                        String[] multilplier = result.getString("multiplier").split(",");
                        String ansmulti = multilplier[ans];

                        String query = "INSERT INTO Loyalty_program VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, LoyaltyId);
                        pstmt.setString(2, lpname);
                        pstmt.setString(3,Tier);
                        pstmt.setString(4,ansmulti);
                        pstmt.setString(5,anspoints);
                        pstmt.setNull(6,Types.NULL);
                        pstmt.setNull(7,Types.NULL);
                        pstmt.setString(8, reward_code);
                        pstmt.setString(9,"Gift Card");
                        pstmt.setString(10,BrandId);
                        pstmt.setString(11,lpstatus);
                        pstmt.execute();



                    }
                
                    System.out.println("Activity Added to loyalty program \n");

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

    public static void FreeProduct(int flag,String BrandId){
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
        ResultSet result1 = null;
        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();


                    String getstatus = "Select COUNT(*) from Loyalty program where loyalty_id = '"+ LoyaltyId +"'";
                    result1 = statement.executeQuery(getstatus);
                    String lpstatus = "";
                    if(result1.getInt(1) >= 3)
                        lpstatus = "active";
                    else
                        lpstatus = "inactive";


                    String getactivitycode = "Select reward_code from Reward_Type where reward_name='Free Product'";

                    result = statement.executeQuery(getactivitycode);
                    String reward_code = result.getString("reward_code");
                       
                    if(flag==0)
                    {
                        String query = "INSERT INTO Loyalty_program VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, LoyaltyId);
                        pstmt.setString(2, lpname);
                        pstmt.setNull(3,Types.NULL);
                        pstmt.setNull(4,Types.NULL);
                        pstmt.setNull(5,Types.NULL);
                        pstmt.setNull(6,Types.NULL);
                        pstmt.setNull(7,Types.NULL);
                        pstmt.setString(8, reward_code);
                        pstmt.setString(9,"Free Product");
                        pstmt.setString(10,BrandId);
                        pstmt.setString(11,lpstatus);
                        pstmt.execute();
                    }
                    else
                    {
                        System.out.println("Enter Tier");
                        String Tier = "";
                        Tier = sc.next();

                        String getpoints = "Select tier,multilplier,points_required from Loyalty_program where loyalty_id = '"+ LoyaltyId +"'";
                        result = statement.executeQuery(getpoints);

                        String gettier = result.getString("tier");

                        String[] str = gettier.split(",");
                        int i = 0;
                        int ans = -1;
                        while(i<str.length)
                        {
                            if(str[i]==Tier)
                               ans = i;
                            i++;
                        }

                        String[] points = result.getString("points_required").split(",");
                        String anspoints = points[ans];

                        String[] multilplier = result.getString("multiplier").split(",");
                        String ansmulti = multilplier[ans];

                        String query = "INSERT INTO Loyalty_program VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, LoyaltyId);
                        pstmt.setString(2, lpname);
                        pstmt.setString(3,Tier);
                        pstmt.setString(4,ansmulti);
                        pstmt.setString(5,anspoints);
                        pstmt.setNull(6,Types.NULL);
                        pstmt.setNull(7,Types.NULL);
                        pstmt.setString(8, reward_code);
                        pstmt.setString(9,"Free Product");
                        pstmt.setString(10,BrandId);
                        pstmt.setString(11,lpstatus);
                        pstmt.execute();
                    }
                
                    System.out.println("Activity Added to loyalty program \n");

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