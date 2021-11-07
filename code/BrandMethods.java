import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandMethods{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void LoyaltyProgram(String BrandId){
        
        System.out.println("1. Regular");
        System.out.println("2. Tier");
        System.out.println("3. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                BrandRegularPage.Regular(BrandId);
                break;
            case 2:
                BrandTierPage.Tier(BrandId);
                break;
            case 3:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                LoyaltyProgram(BrandId);
        }

    }

    public static void AddRERule(String BrandId){
        do
        {
            System.out.println("Enter Brand Reward Rule Code: \n");
            String brandRERule = "";
            brandRERule = sc.next();

            System.out.println("Enter Number of Points: \n");
            int number_of_points = 0;
            number_of_points = sc.nextInt();

            System.out.println(("Enter Activity Category: \n"));
            String ActivityType = "";
            ActivityType = sc.next();

            System.out.println(("Enter Loyalty Id: \n"));
            String LoyaltyId = "";
            LoyaltyId = sc.next();

            System.out.println("Enter Version number: \n");
            int Version = sc.nextInt();


            System.out.println("1. Add RE Rule");
            System.out.println("2. Go Back");

            select = sc.nextInt();

            switch(select){
                case 1:
                    RERule(number_of_points,ActivityType,Version,brandRERule,BrandId,LoyaltyId);
                    break;
                case 2:
                    BrandHomeMenu.main(null);
                    break;
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    AddRERule(BrandId);
            }
        }
        while(select!=2);
    }

    public static void AddRRRule(String BrandId){
        do{
            System.out.println("Enter Brand Reward Rule Code: \n");
            String brandRRRule = "";
            brandRRRule = sc.next();
            
            System.out.println("Enter Tier for adding rule: \n");
            String Tier = "";
            Tier = sc.next();

            System.out.println(("Enter Reward Category: \n"));
            String RewardType = "";
            RewardType = sc.next();

            System.out.println("Enter Number of Points: \n");
            int redeempoints = 0;
            redeempoints = sc.nextInt();

            System.out.println("Enter Reward Quantity: \n");
            int instances = sc.nextInt();

            System.out.println(("Enter Loyalty Id: \n"));
            String LoyaltyId = "";
            LoyaltyId = sc.next();

            System.out.println("Enter Version number: \n");
            int Version = sc.nextInt();

            System.out.println("1. Add RR Rule");
            System.out.println("2. Go Back");

            select = sc.nextInt();

            switch(select){
                case 1:
                    RRRule(brandRRRule,Tier,RewardType,redeempoints,instances,LoyaltyId,Version,BrandId);
                    break;
                case 2:
                    BrandHomeMenu.main(null);
                    break;
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    AddRRRule(BrandId);
            }
        }while(select!=2);
    }
    public static void UpdateRERule(String BrandId){
        do
        {
            System.out.println("Enter Brand Reward Rule Code: \n");
            String brandRERule = "";
            brandRERule = sc.next();

            System.out.println("Enter Number of Points: \n");
            int number_of_points = 0;
            number_of_points = sc.nextInt();

            System.out.println(("Enter Activity Category: \n"));
            String ActivityType = "";
            ActivityType = sc.next();

            System.out.println(("Enter Loyalty Id: \n"));
            String LoyaltyId = "";
            LoyaltyId = sc.next();

            System.out.println("Enter Version number: \n");
            int Version = sc.nextInt();


            System.out.println("1. Add RE Rule");
            System.out.println("2. Go Back");

            select = sc.nextInt();

            switch(select){
                case 1:
                    RERuleupdate(number_of_points,ActivityType,Version,brandRERule,BrandId,LoyaltyId);
                    break;
                case 2:
                    BrandHomeMenu.main(null);
                    break;
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    UpdateRERule(BrandId);
            }
        }
        while(select!=2);

    }
    public static void UpdateRRRule(String BrandId){

        do{
            System.out.println("Enter Brand Reward Rule Code: \n");
            String brandRRRule = "";
            brandRRRule = sc.next();
            
            System.out.println("Enter Tier for adding rule: \n");
            String Tier = "";
            Tier = sc.next();

            System.out.println(("Enter Reward Category: \n"));
            String RewardType = "";
            RewardType = sc.next();

            System.out.println("Enter Number of Points: \n");
            int redeempoints = 0;
            redeempoints = sc.nextInt();

            System.out.println("Enter Reward Quantity: \n");
            int instances = sc.nextInt();

            System.out.println(("Enter Loyalty Id: \n"));
            String LoyaltyId = "";
            LoyaltyId = sc.next();

            System.out.println("Enter Version number: \n");
            int Version = sc.nextInt();

            System.out.println("1. Add RR Rule");
            System.out.println("2. Go Back");

            select = sc.nextInt();

            switch(select){
                case 1:
                    RRRuleupdate(brandRRRule,Tier,RewardType,redeempoints,instances,LoyaltyId,Version,BrandId);
                    break;
                case 2:
                    BrandHomeMenu.main(null);
                    break;
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    UpdateRRRule(BrandId);
            }
        }while(select!=2);

    }


    public static void ValidateLoyaltyProgram(String BrandId){
        System.out.println("1. Validate Loyalty Program");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Validate(BrandId);
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                ValidateLoyaltyProgram(BrandId);
        }
    }

    public static void RERule(int number_of_points,String ActivityType, int Version, String brandRERule,String BrandId,String LoyaltyId){

        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RE Rule will be added:\n\n");

                    String getactivity = "Select activity_name from Activity_Type where activity_code='"+ ActivityType +"'";
                    result = statement.executeQuery(getactivity);
                    String ActivityName = "";
                    if(result.next())
                        ActivityName = result.getString("activity_name");   
                    System.out.println(ActivityName);
                    String addRE = "INSERT INTO RERules Values(?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(addRE);
                    pstmt.setString(1,brandRERule);
                    pstmt.setInt(2,Version);
                    pstmt.setString(3,ActivityType);
                    pstmt.setString(4,ActivityName);
                    pstmt.setInt(5,number_of_points);
                    pstmt.setString(6,BrandId);
                    pstmt.setString(7,LoyaltyId);
                    pstmt.executeQuery();
                    
                    System.out.println("RE Rule Addition successful!!! \n");
                    AddRERule(BrandId);

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

    public static void RRRule(String brandRRRule,String Tier,String RewardType,int redeempoints,int instances,String LoyaltyId,int Version,String BrandId){
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");
                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RR Rule will be added:\n\n");

                    String getrewardname = "Select reward_name from Reward_Type where reward_code = '"+ RewardType +"'";

                    result = statement.executeQuery(getrewardname);
                    String rewardname = result.getString("reward_name");
                       

                    String addRR = "INSERT INTO RRRules VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(addRR);
                    pstmt.setString(1,brandRRRule);
                    pstmt.setString(2,Tier);
                    pstmt.setInt(3,Version);
                    pstmt.setString(4,RewardType);
                    pstmt.setString(5,rewardname);
                    pstmt.setInt(6,instances);
                    pstmt.setInt(7,redeempoints);
                    pstmt.setString(8,BrandId);
                    pstmt.setString(9,LoyaltyId);
                    pstmt.executeQuery();
                    
                    System.out.println("RR Rule Addition successful!!! \n");
                    AddRRRule(BrandId);

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

    public static void RERuleupdate(int number_of_points,String ActivityType, int Version, String brandRERule,String BrandId,String LoyaltyId){
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RE Rule will be updated and version will be stored:\n\n");

                    String getactivity = "Select activity_name from Activity_Type where activity_code='"+ ActivityType +"'";
                    result = statement.executeQuery(getactivity);

                    String ActivityName = result.getString("activity_name");   
                    
                    String updateRE = "INSERT INTO RERules Values('"+ brandRERule +"','"+ Version +"','"+ ActivityType +"','"+ ActivityName +"','"+ number_of_points +"','"+ BrandId +",'"+ LoyaltyId +"')";

                    
                    statement.executeQuery(updateRE);
        
                    
                    System.out.println("RE Rule Updated successful!!! \n");
                    UpdateRERule(BrandId);

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

    public static void RRRuleupdate(String brandRRRule,String Tier,String RewardType,int redeempoints,int instances,String LoyaltyId,int Version,String BrandId){
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RR Rule will be added:\n\n");

                    String getrewardname = "Select reward_name from Reward_Type where reward_code = '"+ RewardType +"'";

                    result = statement.executeQuery(getrewardname);
                    String rewardname = result.getString("reward_name");
                       

                    String updateRR = "INSERT INTO RRRules VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = connection.prepareStatement(updateRR);
                    pstmt.setString(1,brandRRRule);
                    pstmt.setString(2,Tier);
                    pstmt.setInt(3,Version);
                    pstmt.setString(4,RewardType);
                    pstmt.setString(5,rewardname);
                    pstmt.setInt(6,instances);
                    pstmt.setInt(7,redeempoints);
                    pstmt.setString(8,BrandId);
                    pstmt.setString(9,LoyaltyId);
                    pstmt.executeQuery();
                    
                    System.out.println("RR Rule Addition successful!!! \n");
                    UpdateRRRule(BrandId);

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

    public static void Validate(String BrandId){
        
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        ResultSet result1 = null;
        ResultSet result2 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String getactivitytuples = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND activity_code IS NOT NULL";
                    result = statement.executeQuery(getactivitytuples);
                    
                    int act = result.getRow();
                    System.out.println(act);

                    String getrewardtuples = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND reward_code IS NOT NULL";
                    result1 = statement.executeQuery(getrewardtuples);
            
                    int rew = result1.getRow();
                    System.out.println(rew);
                    
                    System.out.println("Is your program tiered? y or n:");
                    String ans = sc.next();

                    if(ans.equals("y"))
                    {
                        String getTier = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND tier IS NOT NULL";
                        result2 = statement.executeQuery(getTier);

                        int tier = result2.getRow();

                        if((rew+act+tier)>=3)
                        {
                            System.out.println("Your program is active");
                            BrandHomeMenu.main(null);
                        }
                        else
                        {
                            System.out.println("Your program is inactive");
                            BrandHomeMenu.main(null);
                        }
                    }
                    else if(ans.equals("n"))
                    {
                        if((rew+act)>=2)
                        {
                            System.out.println("Your program is active");
                            BrandHomeMenu.main(null);
                        }
                        else
                        {
                            System.out.println("Your program is inactive");
                            BrandHomeMenu.main(null);
                        }
                    }

                } finally {
                    result.close();
                    result1.close();
                    result2.close();
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