import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class customerMethods {
    
    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void enrollment(customerID, selectedProgram, programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tEnrolling Customer to a loyalty program\n\n");
                
                String enrollCustomer = "insert into Customer_program values('" + customerID + "','" + programID + "')";
                result = statement.executeQuery(programList);

                System.out.println("\t\tCustomer Successfully Enrolled");

                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);

                if(!result.next){
                    System.out.println("Adding Customer Wallet...");
                    String createWallet = "insert into Wallet values ('" + customerID + "')";
                    System.out.println("Wallet created Successfully for Customer");
                }

                CustomerHomeMenu.main(customerID);

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
    
    public static void ViewWallet(){
        
    }

    public static void CustomerActivitiesCall(){
        customerActivity.main(null);
    }

    public static void RedeemPoints(){
        
    }

    public static void showPrograms(customerID){

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying the list of Available Loyalty Programs\n\n");
                
                String programList = "select loyalty_id, loyalty_program_name from Loyalty_program where 
                    lp_status = active";
                result = statement.executeQuery(programList);

                System.out.println("\t\tLoyalty Program ID\t\tLoalty Program Name");
                int cnt = 1;
                while(result.next()){
                    System.out.println("\t\t" + result.getString("loyalty_id") + "\t\t" + result.getString("loyalty_program_name"));
                }

                System.out.println("1. Enroll in Loyalty Program");
                System.out.println("2. Go Back");

                selection = sc.nextInt();

                switch(selection){
                    case 1:
                        System.out.println("Enter a Loyalty Program Name");
                        String selectedProgram = sc.next();

                        String enrollCustomer = "select * from Loyalty_program where loyalty_program_name='" + selectedProgram + "'";
                        result = statement.executeQuery(enrollCustomer);

                        if(result.next()){
                            String programID = result.getString("loyalty_id");
                            enrollment(customerID,selectedProgram,programID);
                        }
                        else{
                            CustomerHomeMenu.main(customerID);
                        }
                        break;
                    case 2:
                        customerHomeMenu.main(null);
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        customerHomeMenu.main(null);
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
    
    public static void viewWallet(){
        System.out.println("1. View Wallet");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                ViewWallet();
                break;
            case 2:
                customerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                viewWallet();
        }
    }

    public static void customerActivitiesCall(){
        System.out.println("1. Select Customer Activities");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                CustomerActivitiesCall();
                break;
            case 2:
                customerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                customerActivitiesCall();
        }
    }

    public static void redeemPoints(){
        System.out.println("1. Redeem Points");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                RedeemPoints();
                break;
            case 2:
                customerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                redeemPoints();
        }
    }
}
