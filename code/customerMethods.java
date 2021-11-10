import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class customerMethods {
    
    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void Enrollment(){

    }
    
    public static void ViewWallet(){
        
    }

    public static void CustomerActivitiesCall(){
        customerActivity.main(null);
    }

    public static void RedeemPoints(){
        System.out.println("1. Reward Selection");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                rewardSelection();
                break;
            case 2:
                redeemPoints();
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                RedeemPoints();
        }
    }

    public static void rewardSelection(){
        final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/amanend";
        final String user = "dmehta3";
        final String password = "abcd1234";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        ResultSet result1 = null;
        

        System.out.println("1. Gift Card");
        System.out.println("2. Free Product");
        System.out.println("3. Go Back");

        selection = sc.nextInt();
        String option ;

        if(selection == 1)
            option = "Gift Card";
        else if(selection == 2)
            option = "Free Product";
        else if(selection == 3)
            RedeemPoints();

        try {

                Class.forName("org.mariadb.jdbc.Driver");

                try {
                    System.out.println("Connecting to database...");
                    connection = DriverManager.getConnection(jdbcURL, user, password);
                    statement = connection.createStatement();

                    System.out.println("Enter your customer id");
                    String customerID = sc.nextLine(); 
                    System.out.println("Enter the brand id");
                    String brandID = sc.nextLine(); 

                    String query = "Select wallet_id from Wallet W where W.customer_id = '" +customerID+ "'";
                    result = statement.executeQuery(query);
                    String walletID = result.getString("wallet_id");


                    String getstatus = "Select * from Customer_Program C where  C.customer_id = ' " +customerID+"' AND C.brand_id = '" +brandID+ "' " ;
                    result1 = statement.executeQuery(getstatus);
                    int redeemPoints = result1.getInt("customer_points");
                    String loyaltyID = result1.getString("loyalty_id");

                    getstatus = "Select * from RRRules R where  R.brand_ID = '" +brandID+"' AND R.reward_name = '" +option+"'";
                    result1 = statement.executeQuery(getstatus);
                    int pointsReq = result1.getInt("redeem_points");


                    if(pointsReq < redeemPoints){
                        System.out.println("Redeemed Successfully");
                        int points = pointsReq - redeemPoints;
                        String pointsRedeeming = "Update Customer_Program Set cutomer_points = ' " +points+" ' where cutomer_id = '" +customerID+ "' AND brand_id = ' " +brandID+"'";
                    }
                    else
                        System.out.println("Not enough points");
    }finally {
                //result.close();
                statement.close();
                connection.close();
            }
            
        }

        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void enrollment(){
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                Enrollment();
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                enrollment();
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
                CustomerHomeMenu.main(null);
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
                CustomerHomeMenu.main(null);
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
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                redeemPoints();
        }
    }
}
