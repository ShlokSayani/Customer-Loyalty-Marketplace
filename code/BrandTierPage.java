import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandTierPage{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void Tier(){
            System.out.println("1. Tier Set Up");    
            System.out.println("2. Loyalty Points");
            System.out.println("3. Activity Types");
            System.out.println("4. Reward Types");
            System.out.println("5. Go Back");
    
            select = sc.nextInt();
    
            switch(select){
            case 1:
                BrandTierSetUp.TierSetUp();
                break;
            case 2:
                BrandLoyaltyPoints.LoyaltyPoints(1);
                break;
            case 3:
                BrandActivityPages.ActivityTypes(1);
                break;
            case 4:
                BrandRewardPages.RewardTypes(1);
                break;
            case 5:
                BrandMethods.LoyaltyProgram();
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                Tier();
            }
        }
}