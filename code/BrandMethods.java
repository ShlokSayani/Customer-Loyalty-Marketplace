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
        System.out.println("Choose from above options");

        select = sc.nextInt();

        String[] args = new String[2];
        args[0] = BrandId;
        String LoyaltyId = "";

        switch(select){
            case 1:
                LoyaltyId = EnrollLoyaltyProgram(BrandId,1);

                args[0] = BrandId;
                args[1] = LoyaltyId;
                BrandRegularPage.Regular(LoyaltyId,BrandId);
                break;
            case 2:
                LoyaltyId = EnrollLoyaltyProgram(BrandId,2);

                args[0] = BrandId;
                args[1] = LoyaltyId;
                BrandTierPage.Tier(LoyaltyId,BrandId);
                break;
            case 3:
                BrandHomeMenu.main(args);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                LoyaltyProgram(BrandId);
        }

    }

    public static void AddRERule(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        if(LoyaltyId.equals(""))
        {
            System.out.println("Please Enroll in Loyalty Program first");
        }
        else
        {
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

                System.out.println("1. Add RE Rule");
                System.out.println("2. Go Back");

                select = sc.nextInt();

                switch(select){
                    case 1:
                        RERule(number_of_points,ActivityType,brandRERule,BrandId,LoyaltyId);
                        break;
                    case 2:
                        BrandHomeMenu.main(args);
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        AddRERule(BrandId,LoyaltyId);
                }
            }
            while(select!=2);
        }
    }

    public static void AddRRRule(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        if(LoyaltyId.equals(""))
        {
            System.out.println("Please Enroll in Loyalty Program first");
        }
        else
        {
            do{
                System.out.println("Enter Brand Reward Rule Code: \n");
                String brandRRRule = "";
                brandRRRule = sc.next();
            
                System.out.println(("Enter Reward Category: \n"));
                String RewardType = "";
                RewardType = sc.next();

                System.out.println("Enter Number of Points: \n");
                int redeempoints = 0;
                redeempoints = sc.nextInt();

                System.out.println("1. Add RR Rule");
                System.out.println("2. Go Back");

                select = sc.nextInt();

                switch(select){
                    case 1:
                        RRRule(brandRRRule,RewardType,redeempoints,BrandId,LoyaltyId);
                        break;
                    case 2:
                        BrandHomeMenu.main(args);
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        AddRRRule(BrandId,LoyaltyId);
                }
            }while(select!=2);
        }
    }

    public static void UpdateRERule(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        if(LoyaltyId.equals(""))
        {
            System.out.println("Please Enroll in Loyalty Program first");
        }
        else
        {
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

                System.out.println("1. Add RE Rule");
                System.out.println("2. Go Back");

                select = sc.nextInt();

                switch(select){
                    case 1:
                        RERuleupdate(number_of_points,ActivityType,brandRERule,BrandId,LoyaltyId);
                        break;
                    case 2:
                        BrandHomeMenu.main(args);
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        UpdateRERule(BrandId,LoyaltyId);
                }
            }
            while(select!=2);
        }

    }
    public static void UpdateRRRule(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        if(LoyaltyId.equals(""))
        {
            System.out.println("Please Enroll in Loyalty Program first");
        }
        else
        {
            do{
                System.out.println("Enter Brand Reward Rule Code: \n");
                String brandRRRule = "";
                brandRRRule = sc.next();

                System.out.println(("Enter Reward Category: \n"));
                String RewardType = "";
                RewardType = sc.next();

                System.out.println("Enter Number of Points: \n");
                int redeempoints = 0;
                redeempoints = sc.nextInt();

                System.out.println("1. Add RR Rule");
                System.out.println("2. Go Back");

                select = sc.nextInt();

                switch(select){
                    case 1:
                        RRRuleupdate(brandRRRule,RewardType,redeempoints,BrandId,LoyaltyId);
                        break;
                    case 2:
                        BrandHomeMenu.main(args);
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        UpdateRRRule(BrandId,LoyaltyId);
                }
            }while(select!=2);
        }

    }


    public static void ValidateLoyaltyProgram(String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        System.out.println("1. Validate Loyalty Program");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Validate(LoyaltyId,BrandId);
                break;
            case 2:
                BrandHomeMenu.main(args);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                ValidateLoyaltyProgram(BrandId,LoyaltyId);
        }
    }

    public static String EnrollLoyaltyProgram(String BrandId,int select){
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Enrolling into Loyalty Program...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
                    System.out.println("\t\t Check if Program already exists Enrolled:\n\n");

                    String gettuples = "Select * from Loyalty_program where brand_id='"+ BrandId +"'";
                    result = statement.executeQuery(gettuples);
                    String LoyaltyId = "";

                    if(result.next())
                    {
                        System.out.println("Program is already there");
                        LoyaltyId = result.getString("loyalty_id");
                        return LoyaltyId;
                    }
                    else
                    {

                        System.out.println("\t\tYour Program will be Enrolled:\n\n");

                        System.out.println("Enter Loyalty Id:");
                        LoyaltyId = sc.next();
                        System.out.println("Enter Loyalty Program Name:");
                        String Loyalty_Program_Name = sc.next();

                        String program_type = "";
                        if(select == 1)
                            program_type = "Regular";
                        else
                            program_type = "Tier";
                        
                        String addprogram = "INSERT INTO Loyalty_program Values(?,?,?,?,?)";
                        PreparedStatement pstmt = connection.prepareStatement(addprogram);
                        pstmt.setString(1,LoyaltyId);
                        pstmt.setString(2,Loyalty_Program_Name);
                        pstmt.setString(3,BrandId);
                        pstmt.setString(4,"inactive");
                        pstmt.setString(5,program_type);
                        pstmt.executeQuery();
                        
                        System.out.println("Enrolled in Loyalty Program \n");
                        return LoyaltyId;
                    }

                } finally {
                    // result.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
        return "";
    }

    public static void RERule(int number_of_points,String ActivityType,String brandRERule,String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result1 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Loading RE Rule module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String gettuples = "Select * from Activity_program where loyalty_id='"+ LoyaltyId +"' and activity_code='"+ActivityType+"'";
                    result1 = statement.executeQuery(gettuples);

                    if(result1.next())
                    {
                        System.out.println("\t\tNow RE Rule will be added:\n\n");
                        String getactivity = "Select * from Activity_Type where activity_code='"+ ActivityType +"'";
                        result1 = statement.executeQuery(getactivity);
                        String ActivityName = "";
                        if(result1.next())
                            ActivityName = result1.getString("activity_name");
                        String addRE = "INSERT INTO RERules(RE_rule_code,activity_code,activity_name,activity_points,brand_id) Values(?,?,?,?,?)";
                        PreparedStatement pstmt = connection.prepareStatement(addRE);
                        pstmt.setString(1,brandRERule);
                        pstmt.setString(2,ActivityType);
                        pstmt.setString(3,ActivityName);
                        pstmt.setInt(4,number_of_points);
                        pstmt.setString(5,BrandId);
                        pstmt.executeQuery();
                        
                        System.out.println("RE Rule Addition successful!!! \n");
                        AddRERule(BrandId,LoyaltyId);
                    }
                    else
                    {
                        System.out.println("Add Activity First:");
                        BrandHomeMenu.main(args);
                    }

                } finally {
                    result1.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
        
    }

    public static void RRRule(String brandRRRule,String RewardType,int redeempoints,String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result2 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");
                try {
                    System.out.println("Loading RR Rule module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
                    
                    String gettuples = "Select * from Reward_program where loyalty_id='"+ LoyaltyId +"' and reward_code='"+RewardType+"'";
                    result2 = statement.executeQuery(gettuples);

                    if(result2.next())
                    {
                        System.out.println("\t\tNow RR Rule will be added:\n\n");

                        String getrewardname = "Select * from Reward_Type where reward_code = '"+ RewardType +"'";

                        result2 = statement.executeQuery(getrewardname);
                        String rewardname = "";
                        if(result2.next())
                            rewardname = result2.getString("reward_name");

                        String addRR = "INSERT INTO RRRules(RR_rule_code,reward_code,reward_name,redeem_points,brand_id) VALUES(?,?,?,?,?)";
                        PreparedStatement pstmt = connection.prepareStatement(addRR);
                        pstmt.setString(1,brandRRRule);
                        pstmt.setString(2,RewardType);
                        pstmt.setString(3,rewardname);
                        pstmt.setInt(4,redeempoints);
                        pstmt.setString(5,BrandId);
                        pstmt.executeQuery();
                        
                        System.out.println("RR Rule Addition successful!!! \n");
                        AddRRRule(BrandId,LoyaltyId);
                    }
                    else
                    {
                        System.out.println("Add Activity First:");
                        BrandHomeMenu.main(args);
                    }

                } finally {
                    result2.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
    }

    public static void RERuleupdate(int number_of_points,String ActivityType,String brandRERule,String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result3 = null;
        ResultSet result4 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Loading Update RE Rule module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String gettuples = "Select * from Activity_program where loyalty_id='"+ LoyaltyId +"' and activity_code='"+ActivityType+"'";
                    result3 = statement.executeQuery(gettuples);
                    if(result3.next())
                    {
                        String gettuples1 = "Select * from RERules where brand_id='"+ BrandId +"' and activity_code='"+ActivityType+"'";
                        result3 = statement.executeQuery(gettuples1);

                    
                        if(result3.next())
                        {
                            System.out.println("\t\tNow RE Rule will be added:\n\n");
                            String getactivity = "Select * from Activity_Type where activity_code='"+ ActivityType +"'";
                            result3 = statement.executeQuery(getactivity);
                            String ActivityName = "";
                            if(result3.next())
                                ActivityName = result3.getString("activity_name");
                            String addRE = "INSERT INTO RERules(RE_rule_code,activity_code,activity_name,activity_points,brand_id) Values(?,?,?,?,?)";
                            PreparedStatement pstmt = connection.prepareStatement(addRE);
                            pstmt.setString(1,brandRERule);
                            pstmt.setString(2,ActivityType);
                            pstmt.setString(3,ActivityName);
                            pstmt.setInt(4,number_of_points);
                            pstmt.setString(5,BrandId);
                            pstmt.executeQuery();
                            
                            System.out.println("RE Rule Addition successful!!! \n");
                            UpdateRERule(BrandId,LoyaltyId);
                        }
                    }
                    else
                    {
                        System.out.println("Add Activity First or Add RE Rule first");
                        BrandHomeMenu.main(args);
                    }

                } finally {
                    result3.close();
                    result4.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
    }

    public static void RRRuleupdate(String brandRRRule,String RewardType,int redeempoints,String BrandId,String LoyaltyId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result5 = null;
        ResultSet result6 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Loading update RR Rule module...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String gettuples = "Select * from Reward_program where loyalty_id='"+ LoyaltyId +"' and reward_code='"+RewardType+"'";
                    result5 = statement.executeQuery(gettuples);
                    if(result5.next())
                    {
                        String gettuples1 = "Select * from RRRules where brand_id='"+ BrandId +"' and reward_code='"+RewardType+"'";
                        result5 = statement.executeQuery(gettuples1);

                    
                        if(result5.next())
                        {
                            System.out.println("\t\tNow RR Rule will be added:\n\n");

                            String getrewardname = "Select * from Reward_Type where reward_code = '"+ RewardType +"'";

                            result5 = statement.executeQuery(getrewardname);
                            String rewardname = "";
                            if(result5.next())
                                rewardname = result5.getString("reward_name");
                            

                            String addRR = "INSERT INTO RRRules(RR_rule_code,reward_code,reward_name,redeem_points,brand_id) VALUES(?,?,?,?,?)";
                            PreparedStatement pstmt = connection.prepareStatement(addRR);
                            pstmt.setString(1,brandRRRule);
                            pstmt.setString(2,RewardType);
                            pstmt.setString(3,rewardname);
                            pstmt.setInt(4,redeempoints);
                            pstmt.setString(5,BrandId);
                            pstmt.executeQuery();
                            
                            System.out.println("RR Rule Addition successful!!! \n");
                            AddRRRule(BrandId,LoyaltyId);
                        }
                    }
                    else
                    {
                        System.out.println("Add Reward First or add RR rule first");
                        BrandHomeMenu.main(args);
                    }
                    
                } finally {
                    //result5.close();
                    statement.close();
                    connection.close();
                }
            }

            catch (Throwable oops) {
                oops.printStackTrace();
            }
    }

    public static void Validate(String LoyaltyId,String BrandId){
        String[] args = new String[2];
        args[0] = BrandId;
        args[1] = LoyaltyId;
        
        final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
        final String user = "hpatel28";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result7 = null;
        ResultSet result8 = null;
        ResultSet result9 = null;
        ResultSet result10 = null;

        try {

                Class.forName("oracle.jdbc.OracleDriver");

                try {
                    System.out.println("Validating Loyalty Program...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();
               
                    String getactivitytuples = "Select * from Activity_program where loyalty_id='"+ LoyaltyId +"'";
                    result7 = statement.executeQuery(getactivitytuples);
                    if(result7.next())
                    {
                        String getrewardtuples = "Select * from Reward_program where loyalty_id='"+ LoyaltyId +"'";
                        result7 = statement.executeQuery(getrewardtuples);
            
                        if(result7.next())
                        {
                            

                            String gettype = "Select * from Loyalty_program where loyalty_id='"+ LoyaltyId +"'";
                            result7 = statement.executeQuery(gettype);
                            String type = "";
                            if(result7.next())
                                type = result7.getString("program_type");
                            String getloyaltytuples = "Select * from Loyalty_program where loyalty_id='"+ LoyaltyId +"'";
                            result7 = statement.executeQuery(getloyaltytuples);
                        
                            if(result7.next())
                            {
                                if(type.equals("Tier"))
                                {
                                    String getTier = "Select * from Tier where loyalty_id='"+ LoyaltyId +"'";
                                    result7 = statement.executeQuery(getTier);
                                    if(result7.next())
                                    {

                                        System.out.println("Your program is active");
                                        String updatestatus = "UPDATE Loyalty_program SET lp_status = 'active' Where loyalty_id='"+ LoyaltyId +"'";
                                        statement.executeQuery(updatestatus);
                                        
                                        BrandHomeMenu.main(args);
                                    }
                                    else
                                    {
                                        System.out.println("Your program is inactive");
                                        BrandHomeMenu.main(args);
                                    } 
                                }
                                else
                                {
                                    System.out.println("Your program is active");
                                    String updatestatus = "UPDATE Loyalty_program SET lpstatus = 'active' Where loyalty_id='"+ LoyaltyId +"'";
                                    statement.executeQuery(updatestatus);
                                }
                            }
                            else
                            {
                                System.out.println("Your program is inactive");
                                BrandHomeMenu.main(args);
                            }
                        }
                        else
                        {
                            System.out.println("Your program is inactive");
                            BrandHomeMenu.main(args);
                        }
                    }
                    else
                    {
                        System.out.println("Your program is inactive");
                        BrandHomeMenu.main(args);
                    }


                } finally {
                    result7.close();
                    // result8.close();
                    // result9.close();
                    // result10.close();
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