import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;


public class AdminMenu {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "sdsayani";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner in = new Scanner(System.in);
    static int option = 0;

    public static void AdminLogin(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Admin Login\n\n");
                boolean firstCheck = false;
                do {
                    if (firstCheck)
                        System.out.println("Incorrect Credentials Entered!!! \nPlease Try Again.\n\n");
                    in.nextLine();
                    System.out.println("Please enter you Username: ");
                    String loginAdmin = in.nextLine();
                    System.out.println("Please enter your Password: ");
                    String loginPassword = in.nextLine();

                    String sqlCred = "select username,pass from AdminUser where username = '" + loginAdmin
                            + "' and pass='" + loginPassword + "'";

                    // System.out.println(sqlCred);
                    result = statement.executeQuery(sqlCred);
                    firstCheck = true;
                } while (!result.next());

                System.out.println("Login successful!!! \n");

                AdminHomeMenu.main(null);


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

        System.out.println("1. Login");
        System.out.println("2. Go Back");
        int option = in.nextInt();

        switch(option){
            case 1 : 
                AdminLogin();
                break;
            
            case 2 : 
                HomePage.main(null);
                break;

            default :
                System.out.println("Invalid Input. Enter your choice again");
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