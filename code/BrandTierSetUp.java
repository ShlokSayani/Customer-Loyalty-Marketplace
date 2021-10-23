import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class BrandTierSetUp{

    static Scanner sc = new Scanner(System.in);
    static int select = 0;
    public static void TierSetUp(){
        System.out.println("1. Enter Tier Information: Maximum 3 Tiers and name in increasing Order");
        System.out.println("2. Go Back");

        select = sc.nextInt();

        switch(select){
            case 1:
                Setup();
                break;
            case 2:
                BrandTierPage.Tier();
            default:
                System.out.println("Invalid Input. Enter your choice again");
                TierSetUp();
        }

    }

    public static void Setup(){

    }
}
