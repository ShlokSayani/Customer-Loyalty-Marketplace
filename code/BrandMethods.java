import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandMethods{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void LoyaltyProgram(){
        
        System.out.println("1. Regular");
        System.out.println("2. Tier");
        System.out.println("3. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                BrandRegularPage.Regular();
                break;
            case 2:
                BrandTierPage.Tier();
                break;
            case 3:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                LoyaltyProgram();
        }

    }

    public static void AddRERule(){
        System.out.println("1. Add RE Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RERule();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                AddRERule();
        }
    }

    public static void AddRRRule(){
        System.out.println("1. Add RR Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RRRule();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                AddRRRule();
        }
    }
    public static void UpdateRERule(){

        System.out.println("1. Update RE Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RERuleupdate();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                UpdateRERule();
        }

    }
    public static void UpdateRRRule(){

        System.out.println("1. Update RR Rule");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                RRRuleupdate();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                UpdateRERule();
        }

    }
    public static void ValidateLoyaltyProgram(){
        System.out.println("1. Validate Loyalty Program");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Validate();
                break;
            case 2:
                BrandHomeMenu.main(null);
                break;
            default:
               System.out.println("Invalid Input. Enter your choice again");
                ValidateLoyaltyProgram();
        }
    }

    public static void RERule(){

    }

    public static void RRRule(){

    }

    public static void RERuleupdate(){

    }

    public static void RRRuleupdate(){

    }

    public static void Validate(){

    }

}