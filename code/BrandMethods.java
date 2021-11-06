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
        }while(select!=2)
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
                    RRRuleupdate(BrandId);
            }
        }while(select!=2)

    }


    public static void ValidateLoyaltyProgram(String BrandId){
        System.out.println("1. Validate Loyalty Program");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Validate(String BrandId);
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                ValidateLoyaltyProgram(String BrandId);
        }
    }

    public static void RERule(int number_of_points,String ActivityType, int Version, String brandRERule,String BrandId,String LoyaltyId){

        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "hpatel28";
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
               
                    System.out.println("\t\tNow RE Rule will be added:\n\n");

                    String getactivity = "Select activity_name from Activity_Type where activity_code='"+ ActivityType +"'";
                    result = statement.executeQuery(getactivity);

                    String ActivityName = result.getString("activity_name");   
                    
                    String addRE = "INSERT INTO RERules Values('"+ brandRERule +"','"+ Version +"','"+ ActivityType +"','"+ ActivityName +"','"+ number_of_points +"','"+ BrandId +",'"+ LoyaltyId +"')";

                    
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

    public static void RRRule(String brandRRRule,String Tier,String RewardType,int redeempoints,int instances,String LoyaltyId,int Version,String BrandId){
        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "amanend";
        final String password = "ahnv8011";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RR Rule will be added:\n\n");

                    String getrewardname = "Select reward_name from Reward_Type where reward_code = '"+ RewardType +"'";

                    result = statement.executeQuery(getrewardname);
                    String rewardname = result.getString("reward_name");
                       

                    String addRR = "INSERT INTO RRRules VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(addRR);
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

    public static void RERuleupdate(int number_of_points,String ActivityType, int Version, String brandRERule,String BrandId,String LoyaltyId){
        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "hpatel28";
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
               
                    System.out.println("\t\tNow RE Rule will be updated and version will be stored:\n\n");

                    String getactivity = "Select activity_name from Activity_Type where activity_code='"+ ActivityType +"'";
                    result = statement.executeQuery(getactivity);

                    String ActivityName = result.getString("activity_name");   
                    
                    String updateRE = "INSERT INTO RERules Values('"+ brandRERule +"','"+ Version +"','"+ ActivityType +"','"+ ActivityName +"','"+ number_of_points +"','"+ BrandId +",'"+ LoyaltyId +"')";

                    
                    statement.executeQuery(updateRE);
        
                    
                    System.out.println("RE Rule Updated successful!!! \n");

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

    public static void RRRuleupdate(int number_of_points,String RewardType, int Version,String brandRRRule){
        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "amanend";
        final String password = "ahnv8011";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    System.out.println("\t\tNow RR Rule will be added:\n\n");

                    String getrewardname = "Select reward_name from Reward_Type where reward_code = '"+ RewardType +"'";

                    result = statement.executeQuery(getrewardname);
                    String rewardname = result.getString("reward_name");
                       

                    String updateRR = "INSERT INTO RRRules VALUES(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(updateRR);
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

    public static void Validate(){
        
        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "hpatel28";
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
               
                    getactivitytuples = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND activity_code IS NOT NULL";
                    result = statement.executeQuery(getactivitytuples);

                    int act = result.getInt(1);

                    getrewardtuples = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND reward_code IS NOT NULL";
                    result1 = statement.executeQuery(getrewardtuples);

                    int rew = result.getInt(1);
                    
                    System.out.println("Is your program tiered? y or n:");
                    String ans = sc.next();

                    if(ans.equals("y"))
                    {
                        getTier = "Select COUNT(*) from Loyalty_program where brand_id='"+ BrandId +"' AND tier IS NOT NULL";
                        result2 = statement.executeQuery(getTier);

                        int tier = result2.getInt(1);

                        if((rew+act+tier)>=3)
                        {
                            System.out.println("Your program is active");
                            BrandHomeMenu.main(null,BrandId);
                        }
                        else
                        {
                            System.out.println("Your program is inactive");
                            BrandHomeMenu.main(null,BrandId);
                        }
                    }
                    else if(ans.equals("n"))
                    {
                        if((rew+act)>=2)
                        {
                            System.out.println("Your program is active");
                            BrandHomeMenu.main(null,BrandId);
                        }
                        else
                        {
                            System.out.println("Your program is inactive");
                            BrandHomeMenu.main(null,BrandId);
                        }
                    }

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