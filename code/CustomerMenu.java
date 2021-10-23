import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class CustomerMenu {

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
    private static final String user = "amanend";
    private static final String password = "ahnv8011";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner in = new Scanner(System.in);
    static int option = 0;

    public static  void CustomerLogin(){
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Customer Login\n\n");
                boolean firstCheck = false;
                do {
                    if (firstCheck)
                        System.out.println("Incorrect Credentials Entered!!! \nPlease Try Again.\n\n");
                    System.out.println("Please enter your Username: ");
                    
                    String loginCustomer = in.nextLine();
                    System.out.println("Please enter your Password: ");
                    String loginPassword = in.nextLine();

                    String sqlCred = "select username,password from CustomerHome where username = '" + loginCustomer
                            + "' and password='" + loginPassword + "';";

                    // System.out.println(sqlCred);
                    result = statement.executeQuery(sqlCred);
                    firstCheck = true;
                } while (!result.next());
                /*
                 * { System.out.println("Incorrect Credentials Entered!!! "); }
                 */
                System.out.println("Login successful!!! \n");

                customerHomeMenu.main(null);


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

    public static void CustomerSignUp(){
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Customer Sign Up\n\n");
                
                System.out.println("Please enter your Username: ");
                Scanner in = new Scanner(System.in);
                String signUpUsername = in.nextLine();
                System.out.println("Please enter your Password: ");
                String signUpPassword = in.nextLine();

                String sqlCred = "INSERT INTO CustomerUsers( Username , Password) VALUES ('"+signUpUsername+"','"+signUpPassword+"');";

                result = statement.executeQuery(sqlCred);

                System.out.println("SignUp successful!!! \n");

                CustomerMenu.main(null);

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

        System.out.println("1.Customer Login");
        System.out.println("2.Customer Sign Up");
        System.out.println("3.Go Back");
        int option = in.nextInt();

        switch(option){
            case 1 : 
                CustomerLogin();
                break;
            
            case 2 : 
                CustomerSignUp();
                break;
            
            case 3 : 
                HomePage.main(null);
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