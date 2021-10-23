import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandActivityPages{
    static Scanner sc = new Scanner(System.in);
    static int select = 0;

    public static void ActivityTypes(int flag){

        System.out.println("1. Purchase");
        System.out.println("2. Leave a Review");
        System.out.println("3. Refer a Friend");
        System.out.println("4. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Purchase();
                break;
            case 2:
                LeaveaReview();
                break;
            case 3:
                ReferaFriend();
                break;
            case 4:
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
                ActivityTypes(flag);
        }

    }

    public static void Purchase(){

    }

    public static void LeaveaReview(){

    }

    public static void ReferaFriend(){

    }
}