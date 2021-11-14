import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class CustomerSignUpMenu {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner in = new Scanner(System.in);
    static int option = 0;

    public static void CustomerSignUp(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Loading Customer Sign Up module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Customer Sign Up\n\n");
                System.out.println("Please enter your Customer ID: ");
                Scanner in = new Scanner(System.in);
                String customerID = in.nextLine();
                System.out.println("Please enter your Name: ");
                String customerName = in.nextLine();
                System.out.println("Please enter your Address: ");
                String customerAddress = in.nextLine();
                System.out.println("Please enter your Phone Number: ");
                String customerPhone = in.nextLine();
                System.out.println("Please enter your Password: ");
                String customerPassword = in.nextLine();

                String sqlCred = "INSERT INTO Customer(customer_id , customer_name, customer_address, phone_number, customer_password) VALUES ('"+customerID+"','"+customerName+"','"+customerAddress+"','"+customerPhone+"','"+customerPassword+"')";

                result = statement.executeQuery(sqlCred);

                System.out.println("Adding Customer Wallet...");
                String walletId = "W" + customerID.substring(1, customerID.length());
                String createWallet = "insert into Wallet values ('" + customerID + "', '" + walletId + "')";
                result = statement.executeQuery(createWallet);
                System.out.println("Wallet created Successfully for Customer");

                System.out.println("SignUp successful!!! \n");

                LoginMenu.main(null);

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


    public static void main(String[] args) {

        System.out.println("1. Customer Sign Up");
        System.out.println("2. Go Back");
        System.out.println("Choose from above options");
        
        int option = in.nextInt();

        switch(option){
            case 1 : 
                CustomerSignUp();
                break;
            
            case 2 : 
                SignUpMenu.main(null);
                break;

            default :
                System.out.println("Invalid Input");
                break;

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