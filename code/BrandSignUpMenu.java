import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandSignUpMenu {

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "dmehta3";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner in = new Scanner(System.in);
    static int option = 0;

    public static void BrandSignUp(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Brand Sign Up\n\n");
                System.out.println("Please enter your Brand ID: ");
                String brandID = in.nextLine();
                System.out.println("Please enter your Brand Name: ");
                String brandName = in.nextLine();
                System.out.println("Please enter your Brand Address: ");
                String brandAddress = in.nextLine();
                System.out.println("Enter Joining Date: ");
                String join_date = in.nextLine();
                System.out.println("Please enter your new Password: ");
                String brandPassword = in.nextLine();

                String sqlCred = "INSERT INTO Brand(brand_id, brand_name, brand_address,join_date, brand_password) VALUES ('"+brandID+"','"+brandName+"','"+brandAddress+"',TO_DATE('"+join_date+"','mm/dd/yyyy'),'"+brandPassword+"')";

                statement.executeQuery(sqlCred);

                System.out.println("SignUp successful!!! \n");

                LoginMenu.main(null);

            } finally {
                //result.close();
                statement.close();
                connection.close();
            }
        }

        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }


    public static void main(String[] args) {

        System.out.println("Sign Up to the Customer Loyalty Program MarketPlace as a Brand Manager.");
        System.out.println("1. Brand Sign-Up");
        System.out.println("2. Go Back");
        int option = in.nextInt();

        switch(option){
            case 1 : 
                BrandSignUp();
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