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
                int brand_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter brand address: ");
                String brand_address = sc.nextLine();
                // System.out.println("Enter Joining Date: ");
                // String date1 = sc.nextLine();
                // java.util.Date joining_date = new SimpleDateFormat("yyyy/MM/DD").format(date1); 
                // // java.util.Date joining_date = (java.util.Date)formatter.parse(date1);
                // System.out.println(joining_date);
                
                long millis=System.currentTimeMillis();  
                java.sql.Date joining_date = new java.sql.Date(millis);  
                
                String checkcred = "INSERT INTO Brand VALUES ("+brand_id+",'"+brand_address+"',DATE '"+joining_date+"')";
                statement.executeQuery(checkcred);
                
                System.out.println("Brand added successfully!!! \n");

                BrandsMenu.main(null);

            } finally {
                //close(result);
                //close(statement);
                //close(connection);
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
                System.out.println("Enter Customer id: ");
                int customer_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Customer name : ");
                String customer_name = sc.nextLine();
                System.out.println("Enter Customer address : ");
                String customer_address = sc.nextLine();
                System.out.println("Enter Phone number: ");
                int phone_number = sc.nextInt();
                String checkcred = "INSERT INTO Customer VALUES ("+customer_id+",'"+customer_name+"','"+customer_address+"',"+phone_number+")";
                statement.executeQuery(checkcred);
                
                System.out.println("Customer added successfully!!! \n");

                CustomerMenu.main(null);

            } finally {
                //close(result);
                //close(statement);
                //close(connection);
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
                System.out.println("Connecting to Customer Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                
                String checkcred = "SELECT * FROM Customer";
                statement.executeQuery(checkcred);
                
                System.out.println("Displayed Customer Details \n");
                while (checkcred.next()) {
                    int brand_id = checkcred.getInt(1);
                    String c_name = checkcred.getString("brand_name");
                    String c_address = checkcred.getString("brand_address");
                    int phone = checkcred.getInt(4);
                    System.out.println("Customer Id: " + c_id + ", Customer Name: " + c_name + ", Customer Address: " + c_address+ ", Phone Number: " + phone);
                }

                CustomerMenu.main(null);

            } finally {
                //close(result);
                //close(statement);
                //close(connection);
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
                System.out.println("Connecting to Customer Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                
                String checkcred = "SELECT * FROM Customer";
                statement.executeQuery(checkcred);
                
                System.out.println("Displayed Customer Details \n");
                while (checkcred.next()) {
                    int c_id = checkcred.getInt(1);
                    String c_name = checkcred.getString("customer_id");
                    String c_address = checkcred.getString("customer_name");
                    int phone = checkcred.getInt(4);
                    System.out.println("Customer Id: " + c_id + ", Customer Name: " + c_name + ", Customer Address: " + c_address+ ", Phone Number: " + phone);
                }

                CustomerMenu.main(null);

            } finally {
                //close(result);
                //close(statement);
                //close(connection);
            }
        }

        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void AddActivityType(){

    }

    public static void AddRewardType(){

    }

    public static void addBrand(){
    
        System.out.println("1. Press 1 to add Brand");
        System.out.println("2. Go Back");
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
