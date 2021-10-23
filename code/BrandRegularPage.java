import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandRegularPage{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void Regular(){
        System.out.println("1. Loyalty Points");
        System.out.println("2. Activity Types");
        System.out.println("2. Reward Types");
        System.out.println("3. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                BrandLoyaltyPoints.LoyaltyPoints(0);
                break;
            case 2:
                BrandActivityPages.ActivityTypes(0);
                break;
            case 3:
                BrandRewardPages.RewardTypes(0);
                break;
            case 4:
                BrandMethods.LoyaltyProgram();
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                Regular();
        }
    }
}