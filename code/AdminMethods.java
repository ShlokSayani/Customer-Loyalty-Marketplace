import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class AdminMethods {

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void AddBrand(){
        
    }   
    
    public static void AddCustomer(){

    }

    public static void DisplayBrandInfo(){

    }

    public static void DisplayCustomerInfo(){

    }

    public static void AddActivityType(){

    }

    public static void AddRewardType(){

    }

    public static void addBrand(){
        System.out.println("1. Add Brand");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddBrand();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addBrand();
        }
    }   
    
    public static void addCustomer(){
        System.out.println("1. Add Customer");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddCustomer();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addCustomer();
        }
    }

    public static void displayBrandInfo(){
        System.out.println("1. Display Brand's Information");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                DisplayBrandInfo();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                displayBrandInfo();
        }
    }

    public static void displayCustomerInfo(){
        System.out.println("1. Display Customer's Information");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                DisplayCustomerInfo();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                displayCustomerInfo();
        }
    }

    public static void addActivityType(){
        System.out.println("1. Add Activity Type");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddActivityType();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addActivityType();
        }
    }

    public static void addRewardType(){
        System.out.println("1. Add Reward Type");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                AddRewardType();
                break;
            case 2:
                AdminHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                addRewardType();
        }
    }
}
