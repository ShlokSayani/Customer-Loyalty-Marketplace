import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandHomeMenu {
    static Scanner sc = new Scanner(System.in);
    static int select = 0;
    public static void BrandMenuoptions() {

        System.out.println("1. Add Loyalty Program");
        System.out.println("2. add RE Rules");
        System.out.println("3. update RE Rules");
        System.out.println("4. add RR Rules");
        System.out.println("5. Update RR Rules");
        System.out.println("6. validate Loyalty Program");
        System.out.println("7. Go Back");
        System.out.println("Choose from above options");

    }

    public static void main (String args[],String BrandId)
    {
        BrandMenuoptions();

        select = sc.nextInt();
        switch(select){
            case 1:
                BrandMethods.LoyaltyProgram(BrandId);
                break;
            case 2:
                BrandMethods.AddRERule(BrandId);
                break;
            case 3:
                BrandMethods.UpdateRERule(BrandId);
                break;
            case 4:
                BrandMethods.AddRRRule(BrandId);
                break;
            case 5:
                BrandMethods.UpdateRRRule(BrandId);
                break;
            case 6:
                BrandMethods.ValidateLoyaltyProgram(BrandId);
                break;
            case 7:
                HomePage.main(null);
                break;
            
            default:
                System.out.println("Invalid Input. Enter your choice again");
                BrandHomeMenu.main(null,BrandId);
        }
    }
}