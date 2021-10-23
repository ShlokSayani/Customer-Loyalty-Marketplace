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
        System.out.println("1. Admin");
        System.out.println("2. Brands");
        System.out.println("3. Customers");
        System.out.println("Select User Type: ");
        selection = sc.nextInt();
        System.out.println();
        switch(selection){
            case 1: 
                AdminMenu.main(null);
                break;
            case 2: 
                BrandsMenu.main(null);
                break;
            case 3:
                CustomerMenu.main(null);
                break;
        }
   } 
}
