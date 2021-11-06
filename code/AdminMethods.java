import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;
import java.text.*;

public class AdminMethods {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void AddBrand(){
       try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to Brand Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tEnter the details to add a brand:\n\n");
                sc.nextLine();
                System.out.println("Enter brand id: ");
                String brand_id = sc.nextLine();
                System.out.println("Enter brand name: ");
                String brand_name = sc.nextLine();
                System.out.println("Enter brand address: ");
                String brand_address = sc.nextLine();
                
                long millis=System.currentTimeMillis();  
                java.sql.Date join_date = new java.sql.Date(millis);  

                System.out.println("Enter brand password: ");
                String brand_password = sc.nextLine();
                String checkcred = "INSERT INTO Brand(brand_id, brand_name, brand_address, join_date, brand_password) VALUES ('"+brand_id+"','"+brand_name+"','"+brand_address+"',DATE '"+join_date+"','"+brand_password+"')";
                statement.executeQuery(checkcred);
                
                System.out.println("Brand added successfully!!! \n");

                AdminHomeMenu.main(null);

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
    
    public static void AddCustomer(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to Customer Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tEnter the details to add a Customer:\n\n");
                sc.nextLine();
                System.out.println("Enter customer id: ");
                String customer_id = sc.nextLine();
                System.out.println("Enter customer name: ");
                String customer_name = sc.nextLine();
                System.out.println("Enter customer address: ");
                String csutomer_address = sc.nextLine();
                System.out.println("Enter customer phone number: ");
                String phone_number = sc.nextLine();
                System.out.println("Enter customer password: ");
                String customer_password = sc.nextLine();

                String checkcred = "INSERT INTO Customer(customer_id, customer_name, customer_address, phone_number, customer_password) VALUES ('"+customer_id+"','"+customer_name+"','"+customer_address+"','"+phone_number+"','"+customer_password+"')";
                statement.executeQuery(checkcred);
                
                System.out.println("Customer added successfully!!! \n");

                AdminHomeMenu.main(null);

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

    public static void DisplayBrandInfo(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Fetching Brand details...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");

                System.out.println("Enter Brand ID to fetch Brand Details\n");
                int selection = sc.nextInt();

                String checkcred = "SELECT * FROM Brand WHERE brand_id = "+selection;
                statement.executeQuery(checkcred);
                
                System.out.println("\t Brand Details: \n");
                while (checkcred.next()) {
                    String b_id = checkcred.getString("brand_id");
                    String b_name = checkcred.getString("brand_name");
                    String b_address = checkcred.getString("brand_address");
                    Date date = checkcred.getDate("join_date");
                    System.out.println("Brand Id: " + b_id + ", Customer Name: " + b_name + ", Customer Address: " + b_address+ ", Joining date: " + date);
                }

                AdminHomeMenu.main(null);

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

    public static void DisplayCustomerInfo(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Fetching Customer details...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                
                System.out.println("Enter Customer ID to fetch Customer Details\n");
                int selection = sc.nextInt();

                String checkcred = "SELECT * FROM Customer WHERE customer_id = "+selection;
                statement.executeQuery(checkcred);
                
                System.out.println("\tCustomer details: \n");
                while (checkcred.next()) {
                    String c_id = checkcred.getString("customer_id");
                    String c_name = checkcred.getString("customer_name");
                    String c_address = checkcred.getString("customer_address");
                    String phone = checkcred.getString("phone_number");
                    System.out.println("Customer Id: " + c_id + ", Customer Name: " + c_name + ", Customer Address: " + c_address+ ", Phone Number: " + phone);
                }

                AdminHomeMenu.main(null);

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

    public static void AddActivityType(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to Adding Activity Type Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tEnter the details to add a Activity Type:\n\n");
                sc.nextLine();
                System.out.println("Enter activity code: ");
                String activity_code = sc.nextLine();
                System.out.println("Enter activity_name: ");
                String activity_name = sc.nextLine();

                String checkcred = "INSERT INTO Activity_Type(activity_code, activity_name) VALUES ('"+activity_code+"','"+activity_name+"'";
                statement.executeQuery(checkcred);
                
                System.out.println("Activity Type added successfully!!! \n");

                AdminHomeMenu.main(null);

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

    public static void AddRewardType(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to Adding Reward Type Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tEnter the details to add a Reward Type:\n\n");
                sc.nextLine();
                System.out.println("Enter Reward code: ");
                String reward_code = sc.nextLine();
                System.out.println("Enter Reward name: ");
                String reward_name = sc.nextLine();

                String checkcred = "INSERT INTO Reward_Type(reward_code, reward_name) VALUES ('"+reward_code+"','"+reward_name+"'";
                statement.executeQuery(checkcred);
                
                System.out.println("Reward Type added successfully!!! \n");

                AdminHomeMenu.main(null);

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

    public static void addBrand(){
        
        System.out.println("1. Add Brand");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddBrand();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addBrand();
        }
    }   
    
    public static void addCustomer(){
        System.out.println("1. Add Customer");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddCustomer();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addCustomer();
        }
    }

    public static void displayBrandInfo(){
        System.out.println("1. Display Brand's Information");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                DisplayBrandInfo();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                displayBrandInfo();
        }
    }

    public static void displayCustomerInfo(){
        System.out.println("1. Display Customer's Information");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                DisplayCustomerInfo();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                displayCustomerInfo();
        }
    }

    public static void addActivityType(){
        System.out.println("1. Add Activity Type");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddActivityType();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addActivityType();
        }
    }

    public static void addRewardType(){
        System.out.println("1. Add Reward Type");
        System.out.println("2. Go Back");
        System.out.println("Select from above option:\n");
        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddRewardType();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addRewardType();
        }
    }
}
