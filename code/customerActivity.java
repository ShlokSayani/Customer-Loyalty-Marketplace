import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class customerActivity {
    
    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void Purchase(){
        
    }

    public static void LeaveAReview(){
        
    }

    public static void ReferAFriend(){
        
    }
    
    public static void purchase(){
        System.out.println("1. Purchase");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                Purchase();
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                purchase();
        }    
    }

    public static void leaveAReview(){
        System.out.println("1. Leave a review");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                LeaveAReview();
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                leaveAReview();
        }        
    }

    public static void referAFriend(){
        System.out.println("1. Refer a friend");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                ReferAFriend();
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                referAFriend();
        }
    }

    public static void main(String args[]){
        System.out.println("Select Activity Types:");
        System.out.println("1. Purchase ");
        System.out.println("2. Leave a review ");
        System.out.println("3. Refer a friend ");
        System.out.println("4. Go Back ");
        System.out.println("Choose from above options");
        selection = sc.nextInt();
        
        switch(selection){
            case 1:
                purchase();
                break;
            case 2:
                leaveAReview();
                break;
            case 3:
                referAFriend();
                break;
            case 4: 
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
        }

    }    
}
