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
                customerHomeMenu.main(null);
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
