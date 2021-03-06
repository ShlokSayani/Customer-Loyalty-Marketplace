import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class CustomerHomeMenu {

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void main(String args[]){
        
        System.out.println("Select one of the below options: ");
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Reward Activities");
        System.out.println("3. View Wallet");
        System.out.println("4. Redeem points");
        System.out.println("5. Go Back");
        System.out.println("Choose from above options");
        
        String customerID = args[0];
        selection = sc.nextInt();
        switch(selection){
            case 1:
                CustomerMethods.showPrograms(customerID);
                break;
            case 2:
                CustomerMethods.rewardActivities(customerID);
                break;
            case 3:
                CustomerMethods.viewWallet(customerID);
                break;
            case 4:
                CustomerMethods.redeemPoints(customerID);
                break;
            case 5:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
        }
    }
}
