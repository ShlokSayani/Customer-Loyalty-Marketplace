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

        String customerID = args[0];
        selection = sc.nextInt();
        switch(selection){
            case 1:
                customerMethods.showPrograms(customerID);
                break;
            case 2:
                customerMethods.rewardActivities(customerID);
                break;
            case 3:
                customerMethods.viewWallet(customerID);
                break;
            case 4:
                customerMethods.redeemPoints(customerID);
                break;
            case 5:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
        }
    }
}
