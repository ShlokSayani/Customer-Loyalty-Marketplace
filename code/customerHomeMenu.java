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

    public static void CustomerMenuOptions(){
        System.out.println("Select below options: ");
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Reward Activities");
        System.out.println("3. View Wallet");
        System.out.println("4. Redeem points");
        System.out.println("5. Go Back");
    } 
    

    public static void main(String args[]){
        
        CustomerMenuOptions();

        selection = sc.nextInt();
        switch(selection){
            case 1:
                customerMethods.enrollment();
                break;
            case 2:
                customerMethods.customerActivitiesCall();
                break;
            case 3:
                customerMethods.viewWallet();
                break;
            case 4:
                customerMethods.redeemPoints();
                break;
            case 5:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
        }
    }
}
