import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandLoyaltyPoints{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void LoyaltyPoints(int flag){

        System.out.println("1. Enter Points");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                EnterPoints();
                break;
            case 2:
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
                LoyaltyPoints(flag);
        }

    }

    public static void EnterPoints(){

    }

}
