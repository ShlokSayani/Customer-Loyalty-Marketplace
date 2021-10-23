import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandsMenu {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "sdsayani";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int select = 0;


    public static void BrandLogin() {

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Customer Loyalty Program:\n\n");
                boolean checkLogin = false;
                do {
                    if (checkLogin)
                        System.out.println("Incorrect Credentials Entered!!! \nPlease Try Again.\n\n");
                    sc.nextLine();
                    System.out.println("Please enter your username: ");
                    String User = sc.nextLine();
                    System.out.println("Please enter your password: ");
                    String Pwd = sc.nextLine();

                    String checkcred = "select username,pass from BrandUsers where username=  '" + User
                            + "' and pass='" + Pwd + "'";


                    result = statement.executeQuery(checkcred);
                    checkLogin = true;
                } while (!result.next());
                /*
                 * { System.out.println("Incorrect Credentials Entered!!! "); }
                 */
                System.out.println("Login successful!!! \n");

                BrandHomeMenu.main(null);

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

    public static void BrandSignUp() {
            
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Customer Loyalty Program:\n\n");
                sc.nextLine();
                System.out.println("Please enter your username: ");
                String User = sc.nextLine();
                System.out.println("Please enter your password: ");
                String Pwd = sc.nextLine();

                String checkcred = "INSERT INTO BrandUsers(username,pass) VALUES ('"+User+"','"+Pwd+"')" ;


                statement.executeQuery(checkcred);
                
                System.out.println("SignUp successful!!! \n");

                BrandsMenu.main(null);

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
    

    public static void main(String[] args) {
        System.out.println("Select Login or SignUp");
        System.out.println("1. Login");
        System.out.println("2. SignUp");
        System.out.println("3. Go Back");
        select = sc.nextInt();
        
        switch(select){
            case 1:
                BrandLogin();
                break;
            case 2:
                BrandSignUp();
                break;
            case 3:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");    
                BrandsMenu.main(null);
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