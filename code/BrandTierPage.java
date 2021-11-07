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

    public static void Tier(String LoyaltyId,String BrandId){
            System.out.println("1. Tier Set Up");   
            System.out.println("2. Activity Types");
            System.out.println("3. Reward Types");
            System.out.println("4. Go Back");
    
            select = sc.nextInt();
    
            switch(select){
            case 1:
                BrandTierSetUp.TierSetUp(BrandId,1,LoyaltyId);
                break;
            case 2:
                BrandActivityPages.ActivityTypes(1,LoyaltyId,BrandId);
                break;
            case 3:
                BrandRewardPages.RewardTypes(1,LoyaltyId,BrandId);
                break;
            case 4:
                BrandMethods.LoyaltyProgram(BrandId);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                Tier(LoyaltyId,BrandId);
            }
        }
}