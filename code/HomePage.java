import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class HomePage{
   
    static Scanner sc = new Scanner(System.in);
    static int selection = 0;
    public static void main(String args[]){
        System.out.println("Welcome to Customer Loyalty Program MarketPlace");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("3. Show Queries");
        System.out.println("4. Exit");
        System.out.println("Choose from above options");
        selection = sc.nextInt();
        System.out.println();
        switch(selection){
            case 1: 
                LoginMenu.main(null);
                break;
            case 2: 
                SignUpMenu.main(null);
                break;
            case 3:
                ShowQueries.main(null);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input!");
                main(null);
        }
   } 
}
