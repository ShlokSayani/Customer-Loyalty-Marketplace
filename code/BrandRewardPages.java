import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandRewardPages{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void RewardTypes(int flag){
        System.out.println("1. Gift Card");
        System.out.println("2. Free Product");
        System.out.println("3. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                GiftCard();
                break;
            case 2:
                FreeProduct();
                break;
            case 3:
                if(flag == 0)
                {
                    BrandRegularPage.Regular();
                    break;
                }
                else{
                    BrandTierPage.Tier();
                }
            default:
                System.out.println("Invalid Input. Enter your choice again");
                RewardTypes(flag);
        }

    }

    public static void GiftCard(){

    }

    public static void FreeProduct(){

    }

}