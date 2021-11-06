import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class AdminHomeMenu {
    
    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void main(String args[]){
        System.out.println("*****Welcome to Admin Module*****"); 
        System.out.println("1. Add brand");
        System.out.println("2. Add customer");
        System.out.println("3. Show brand's information");
        System.out.println("4. Show customer's information");
        System.out.println("5. Add activity type");
        System.out.println("6. Add reward type");
        System.out.println("7. Log out");
        System.out.println("Choose from above options");
        selection = sc.nextInt();
        switch(selection){
            case 1:
                AdminMethods.addBrand();
                break;
            case 2:
                AdminMethods.addCustomer();
                break;
            case 3:
                AdminMethods.displayBrandInfo();
                break;
            case 4:
                AdminMethods.displayCustomerInfo();
                break;
            case 5:
                AdminMethods.addActivityType();
                break;
            case 6:
                AdminMethods.addRewardType();
                break;
            case 7:
                System.out.println("You have been successfully Logged out.");
                //HomePage.main(null);
            case 8:
                System.out.println("Invalid Input. Enter your choice again");
        }

    }
}
