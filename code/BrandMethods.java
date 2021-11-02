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
                LoyaltyProgram();
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

            System.out.println(("Enter Activity Name: \n"));
            String ActivityName = "";
            ActivityName = sc.next();

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
                    RERule(number_of_points,ActivityType,Version,brandRERule,ActivityName,BrandId,LoyaltyId);
                    break;
                case 2:
                    BrandHomeMenu.main(null);
                    break;
                default:
                    System.out.println("Invalid Input. Enter your choice again");
                    AddRERule();
        }
        }
        while(select!=2)
    }

    public static void AddRRRule(){

        System.out.println("Enter Brand Reward Rule Code: \n");
        String brandRRRule = "";
        brandRRRule = sc.next();

        System.out.println("Enter Number of Points: \n");
        int number_of_points = 0;
        number_of_points = sc.nextInt();

        System.out.println(("Enter Reward Category: \n"));
        String RewardType = "";
        RewardType = sc.next();

        System.out.println("Enter Version number: \n");
        int Version = sc.nextInt();

        System.out.println("1. Add RR Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RRRule(number_of_points,RewardType,Version,brandRRRule);
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                AddRRRule();
        }
    }
    public static void UpdateRERule(){

        System.out.println("Enter Brand Reward Rule Code: \n");
        String brandRERule = "";
        brandRERule = sc.next();

        System.out.println("Enter Number of Points: \n");
        int number_of_points = 0;
        number_of_points = sc.nextInt();

        System.out.println(("Enter Activity Category: \n"));
        String ActivityType = "";
        ActivityType = sc.next();

        System.out.println("Enter Version number: \n");
        int Version = sc.nextInt();

        System.out.println("1. Update RE Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RERuleupdate(number_of_points,ActivityType,Version,brandRERule);
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                UpdateRERule();
        }

    }
    public static void UpdateRRRule(){

        System.out.println("Enter Brand Reward Rule Code: \n");
        String brandRRRule = "";
        brandRRRule = sc.next();

        System.out.println("Enter Number of Points: \n");
        int number_of_points = 0;
        number_of_points = sc.nextInt();

        System.out.println(("Enter Reward Category: \n"));
        String RewardType = "";
        RewardType = sc.next();

        System.out.println("Enter Version number: \n");
        int Version = sc.nextInt();

        System.out.println("1. Update RR Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RRRuleupdate(number_of_points,RewardType,Version,brandRRRule);
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                UpdateRERule();
        }

    }
    public static void ValidateLoyaltyProgram(){
        System.out.println("1. Validate Loyalty Program");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Validate();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                ValidateLoyaltyProgram();
        }
    }

    public static void RERule(int number_of_points,String ActivityType, int Version, String brandRERule,String ActivityName,String BrandId,String LoyaltyId){

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

    public static void RRRule(int number_of_points,String RewardType, int Version,String brandRRRule){
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
                       

                    String addRR = "";

                    
                    statement.executeQuery(addRR);
        
                    
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

    public static void RERuleupdate(int number_of_points,String ActivityType, int Version, String brandRERule ){
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

                    System.out.println("\t\tNow RE Rule will be Updated and Added:\n\n");
                       

                    String updateRE = "";

                    
                    statement.executeQuery(updateRE);
        
                    
                    System.out.println("RE Rule Updation successful!!! \n");


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
               
                    System.out.println("\t\tNow RR Rule will be Updated:\n\n");
                       

                    String addRR = "";

                    
                    statement.executeQuery(addRR);
        
                    
                    System.out.println("RR Rule Updation successful!!! \n");

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