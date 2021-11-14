import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class SignUpMenu{

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;
    public static void main(String args[]){
        System.out.println("Sign Up to the Customer Loyalty Program MarketPlace");
        System.out.println("1. Brand Sign-Up");
        System.out.println("2. Customer Sign-Up");
        System.out.println("3. Go Back");
        System.out.println("Choose from above options");

        selection = sc.nextInt();
        System.out.println();
        switch(selection){
            case 1:
                BrandSignUpMenu.BrandSignUp();
                break;
            case 2:
                CustomerSignUpMenu.CustomerSignUp();
                break;
            case 3:
                HomePage.main(null);
                break;
            default:
                System.out.println("Invalid Input!");
                main(null);
        }
    } 
}
