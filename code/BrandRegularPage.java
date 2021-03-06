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

    public static void Regular(String LoyaltyId,String BrandId){
        System.out.println("1. Activity Types");
        System.out.println("2. Reward Types");
        System.out.println("3. Go Back");
        System.out.println("Choose from above options");
        select = sc.nextInt();

        switch(select){
            case 1:
                BrandActivityPages.ActivityTypes(0,LoyaltyId,BrandId);
                break;
            case 2:
                BrandRewardPages.RewardTypes(0,LoyaltyId,BrandId);
                break;
            case 3:
                BrandMethods.LoyaltyProgram(BrandId);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                Regular(LoyaltyId,BrandId);
        }
    }
}