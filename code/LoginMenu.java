import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class LoginMenu{

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void UserLogin(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Login Module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Marketplace Login\n\n");
                boolean firstCheck = false;
                
                while(true){
                    sc.nextLine();
                    System.out.println("Please enter your User ID: ");
                    String userID = sc.nextLine();
                    System.out.println("Please enter your Password: ");
                    String userPassword = sc.nextLine();
                    String sqlAdminCred = "select username,pass from AdminUser where username = '" + userID
                        + "' and pass='" + userPassword + "'";
                    result = statement.executeQuery(sqlAdminCred);
                    if(result.next()){
                        System.out.println("Login successful!!! \n");
                        AdminHomeMenu.main(null);
                        break;
                    }
                    String sqlBrandCred = "select brand_id,brand_password from Brand where brand_id = '" + userID
                        + "' and brand_password='" + userPassword + "'";
                    result = statement.executeQuery(sqlBrandCred);
                    if(result.next()){
                        System.out.println("Login successful!!! \n");
                        String[] args = new String[2];
                        args[0] = userID;
                        BrandHomeMenu.main(args);
                        break;
                    }
                    String sqlCustomerCred = "select customer_id,customer_password from Customer where customer_id = '" + userID
                        + "' and customer_password='" + userPassword + "'";
                    result = statement.executeQuery(sqlCustomerCred);
                    if(result.next()){
                        System.out.println("Login successful!!! \n");
                        CustomerHomeMenu.main(new String[]{userID});
                        break;
                    }
                    System.out.println("Enter Valid Credentials!!");
                }

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

    public static void main(String args[]){
        System.out.println("Login to the Customer Loyalty Program MarketPlace");
        System.out.println("1. Sign-In");
        System.out.println("2. Go Back");
        System.out.println("Choose from above options");
        
        selection = sc.nextInt();
        System.out.println();
        switch(selection){
            case 1:
                UserLogin();
                break;
            case 2:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input!");
                main(null);
        }
    } 
}
