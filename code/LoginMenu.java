import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class LoginPage{

    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static  void CustomerLogin(){
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                // Runtime.getRuntime().exec("clear");
                System.out.println("\t\tWelcome to Marketplace Login\n\n");
                boolean firstCheck = false;
                
                while(true){
                    in.nextLine();
                    System.out.println("Please enter your User ID: ");
                    String userID = in.nextLine();
                    System.out.println("Please enter your Password: ");
                    String userPassword = in.nextLine();
                    String sqlAdminCred = "select admin_id,admin_password from AdminUser where admin_id = '" + userID
                        + "' and admin_password='" + userPassword + "'";
                    result = statement.executeQuery(sqlCred);
                    if(result.next){
                        System.out.println("Login successful!!! \n");
                        AdminMenu.main(null);
                        break;
                    }
                    String sqlBrandCred = "select brand_id,brand_password from CustomerUsers where brand_id = '" + userID
                        + "' and brand_password='" + userPassword + "'";
                    result = statement.executeQuery(sqlCred);
                    if(result.next){
                        System.out.println("Login successful!!! \n");
                        BrandMenu.main(null);
                        break;
                    }
                    String sqlCustomerCred = "select customer_id,customer_password from CustomerUsers where customer_id = '" + userID
                        + "' and customer_password='" + userPassword + "'";
                    result = statement.executeQuery(sqlCred);
                    if(result.next){
                        System.out.println("Login successful!!! \n");
                        CustomerMenu.main(null);
                        break;
                    }
                    System.out.println("Enter Valid Credentials!!");
                }
                // do {
                //     if (firstCheck)
                //         System.out.println("Incorrect Credentials Entered!!! \nPlease Try Again.\n\n");
                //     // in.nextLine();
                //     // System.out.println("Please enter your User ID: ");
                //     // String userID = in.nextLine();
                //     // System.out.println("Please enter your Password: ");
                //     // String userPassword = in.nextLine();

                //     String sqlAdminCred = "select username,pass from CustomerUsers where username = '" + loginCustomer
                //             + "' and pass='" + loginPassword + "'";

                //     result = statement.executeQuery(sqlCred);
                //     firstCheck = true;
                // } while (!result.next());
                /*
                 * { System.out.println("Incorrect Credentials Entered!!! "); }
                 */

                // customerHomeMenu.main(null);


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

    public static void main(String args[]){
        System.out.println("Login to the Customer Loyalty Program MarketPlace");
        System.out.println("1. Sign-In");
        System.out.println("2. Go Back");
        selection = sc.nextInt();
        System.out.println();
        switch(selection){
            case 1:
                UserLogin();
                break;
            case 3:
                HomeMenu.main(null);
                break;
        }
    } 
}
