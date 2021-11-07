import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;

public class CustomerMethods {
    
    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void enrollment(String customerID, String programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tEnrolling Customer to a loyalty program\n\n");
                
                String enrollCustomer = "insert into Customer_program values('" + customerID + "','" + programID + "')";
                result = statement.executeQuery(enrollCustomer);

                System.out.println("\t\tCustomer Successfully Enrolled");

                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);

                if(!result.next()){
                    System.out.println("Adding Customer Wallet...");
                    String createWallet = "insert into Wallet values ('" + customerID + "')";
                    System.out.println("Wallet created Successfully for Customer");
                }

                CustomerHomeMenu.main(new String[]{customerID});
            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void purchase(String customerID, String programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "')"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                System.out.println("Press (Y/N) if you want to use a Gift Card.");
                String useGiftCard = sc.nextLine();

                if(useGiftCard=="Y"){
                    System.out.println("\t\tSelect a Gift Card to use.\n\n");
                    String displayGiftCard = "select * from Reward_GiftCard where customer_id = '" + customerID + "' AND loyalty_id='" + programID;
                    result = statement.executeQuery(displayGiftCard);

                    System.out.println("\t\tGiftCard Code\t\tExpiryDate");
                    while(result.next()){
                        System.out.println(result.getString("giftcard_code") + "\t\t" + result.getString("expiry_date"));
                    }

                    String selectedCard = sc.nextLine();
                    String cardPresent = "select * from Reward_GiftCard where giftcard_code='" + selectedCard + "')";
                    result = statement.executeQuery(cardPresent);
                    if(result.next()){
                        System.out.println("Enter Transaction Date");
                        String transaction_date = sc.nextLine();
                        
                        String cardTransaction = "insert into Customer_Transaction(wallet_id, transaction_date, activity_type, loyalty_id, brand_id) values ('" + customerWallet + "', 'TO_DATE('" + transaction_date + "','MM/DD/YYYY'), 'Purchase', '" + programID + "', '" + customerBrand + "')";
                        
                    }
                    else{
                        System.out.println("Please select a correct card code.");
                        CustomerHomeMenu.main(new String[]{customerID});
                    }
                }
                else{
                    String activityPoints = "select * from RERules where loyalty_id='" + programID + "' AND brand_id='" + customerBrand + "')";
                    int customerPoints = 0;

                    result = statement.executeQuery(activityPoints);
                    if(result.next()){
                        customerPoints = Integer.valueOf(result.getString("activity_points"));
                    }

                    String getTier = "select * from Loyalty_program where loyalty_id='" + programID + "')";
                    result = statement.executeQuery(getTier);

                    if(result.next()){
                        String[] ptsReqd = result.getString("points_required").split(",");
                        String[] mult = result.getString("multiplier").split(",");

                        if(customerPoints>=Integer.valueOf(ptsReqd[0]) && customerPoints<Integer.valueOf(ptsReqd[1])){
                            customerPoints *= Integer.valueOf(mult[0]);
                        }
                        else if(customerPoints>=Integer.valueOf(ptsReqd[1]) && customerPoints<Integer.valueOf(ptsReqd[2])){
                            customerPoints *= Integer.valueOf(mult[1]);
                        }
                        else if(customerPoints>=Integer.valueOf(ptsReqd[2])){
                            customerPoints *= Integer.valueOf(mult[2]);
                        }
                    }

                    System.out.println("Enter Transaction Date");
                    String transaction_date = sc.nextLine();
                    
                    String cardTransaction = "insert into Customer_Transaction(wallet_id, transaction_date, activity_type, loyalty_id, brand_id, gained_points) values ('" + customerWallet + "', 'TO_DATE('" + transaction_date + "','MM/DD/YYYY'), 'Purchase', '" + programID + "', '" + customerBrand + "', " + customerPoints + "')";
                    
                }

            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void addReview(String customerID, String programID, String reviewContent){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "')"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                String activityPoints = "select * from RERules where loyalty_id='" + programID + "' AND brand_id='" + customerBrand + "')";
                int customerPoints = 0;

                result = statement.executeQuery(activityPoints);
                if(result.next()){
                    customerPoints = Integer.valueOf(result.getString("activity_points"));
                }

                String getTier = "select * from Loyalty_program where loyalty_id='" + programID + "')";
                result = statement.executeQuery(getTier);

                if(result.next()){
                    String[] ptsReqd = result.getString("points_required").split(",");
                    String[] mult = result.getString("multiplier").split(",");

                    if(customerPoints>=Integer.valueOf(ptsReqd[0]) && customerPoints<Integer.valueOf(ptsReqd[1])){
                        customerPoints *= Integer.valueOf(mult[0]);
                    }
                    else if(customerPoints>=Integer.valueOf(ptsReqd[1]) && customerPoints<Integer.valueOf(ptsReqd[2])){
                        customerPoints *= Integer.valueOf(mult[1]);
                    }
                    else if(customerPoints>=Integer.valueOf(ptsReqd[2])){
                        customerPoints *= Integer.valueOf(mult[2]);
                    }
                }

                System.out.println("Enter Transaction Date");
                String transactionDate = sc.nextLine();
                System.out.println("Enter Transaction ID");
                String transactionID = sc.nextLine();

                String reviewTransaction = "insert into Customer_Transaction(transaction_id, wallet_id, transaction_date, activity_type, loyalty_id, brand_id, gained_points) values ('" + customerWallet + "', 'TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Review', '" + programID + "', '" + customerBrand + "', '" + customerPoints + "')";
                result = statement.executeQuery(reviewTransaction);

                String reviewTable = "insert into Customer_Reviews(loyalty_id, review_date, review_content, transaction_id, customer_id) values ('" + programID + "', 'TO_DATE('" + transactionDate +  "','MM/DD/YYYY'), '" + reviewContent + "', '" + transactionID + "', '" + customerID + "')";
                System.out.println("Review added Successfully!");

            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void reviewMenu(String customerID, String programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tLeave a Review!\n\n");
                
                String reviewContent = sc.nextLine();

                selection = sc.nextInt();
                System.out.println("1. Leave a Review");
                System.out.println("2. Go Back");

                switch(selection){
                    case 1:
                        addReview(customerID, programID, reviewContent);
                        break;
                    case 2:
                        CustomerHomeMenu.main(new String[]{customerID});
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        CustomerHomeMenu.main(new String[]{customerID});
                }
            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void addReferral(String customerID, String programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "')"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                String activityPoints = "select * from RERules where loyalty_id='" + programID + "' AND brand_id='" + customerBrand + "')";
                int customerPoints = 0;

                result = statement.executeQuery(activityPoints);
                if(result.next()){
                    customerPoints = Integer.valueOf(result.getString("activity_points"));
                }

                String getTier = "select * from Loyalty_program where loyalty_id='" + programID + "')";
                result = statement.executeQuery(getTier);

                if(result.next()){
                    String[] ptsReqd = result.getString("points_required").split(",");
                    String[] mult = result.getString("multiplier").split(",");

                    if(customerPoints>=Integer.valueOf(ptsReqd[0]) && customerPoints<Integer.valueOf(ptsReqd[1])){
                        customerPoints *= Integer.valueOf(mult[0]);
                    }
                    else if(customerPoints>=Integer.valueOf(ptsReqd[1]) && customerPoints<Integer.valueOf(ptsReqd[2])){
                        customerPoints *= Integer.valueOf(mult[1]);
                    }
                    else if(customerPoints>=Integer.valueOf(ptsReqd[2])){
                        customerPoints *= Integer.valueOf(mult[2]);
                    }
                }

                System.out.println("Enter Transaction Date");
                String transactionDate = sc.nextLine();
                System.out.println("Enter Transaction ID");
                String transactionID = sc.nextLine();

                String reviewTransaction = "insert into Customer_Transaction(transaction_id, wallet_id, transaction_date, activity_type, loyalty_id, brand_id, gained_points) values ('" + customerWallet + "', 'TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Refer', '" + programID + "', '" + customerBrand + "', '" + customerPoints + "')";
                result = statement.executeQuery(reviewTransaction);

                System.out.println("Referral added Successfully!");


            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void refer(String customerID, String programID){

        System.out.println("\t\tRefer a Friend to the loyalty Program!\n\n");        
        selection = sc.nextInt();
        System.out.println("1. Refer");
        System.out.println("2. Go Back");

        switch(selection){
            case 1:
                addReferral(customerID, programID);
                break;
            case 2:
                CustomerHomeMenu.main(new String[]{customerID});
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                CustomerHomeMenu.main(new String[]{customerID});
        }
    }

    public static void RedeemPoints(String CustomerID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying the list of Available rewards to redeem\n\n");
                
                String customer_points_list = "select brand_id,loyalty_id,customer_points from Customer_program where customer_id = '"+customerID+"'";
                result = statement.executeQuery(customer_points_list);
                Map<String, Integer> brand_customer_points = new HashMap<>();
                while(result.next()){
                    String bi = result.getString("brand_id");
                    String li = result.getString("loyalty_id");
                    String cp = result.getInt("customer_points");
                    brand_customer_points.put(bi,cp);
                    System.out.println("Brand Id: "+bi+" Loyalty Id: "+li + " Total Points: "+cp);
                }
                System.out.println();
                System.out.println("Select Brand ID for which you have to redeem points.");
                String get_brand_id = sc.nextLine();

                String fetchloyaltyid = "select brand_id,loyalty_id,customer_points from Customer_program where customer_id = '"+customerID+"'";
                result3 = statement.executeQuery(fetchloyaltyid);
                while(result3.next()){
                    String get_loyalty_id = result3.getString("brand_id");
                }

                String fetchtotalpoints = "select customer_points from Customer_program where customer_id = '"+customerID+"' and brand_id = '"+get_brand_id+"' and loyalty_id ='"+get_loyalty_id+"'";
                result5 = statement.executeQuery(fetchtotalpoints);
                while(result5.next()){
                    String get_total_points = result3.getString("customer_points");
                }
                
                String fetch_reward_details = "select reward_code,reward_name, redeem_points from RRRules where brand_id = '"+get_brand_id+"'";
                result2 = statement.executeQuery(fetch_reward_details);
                System.out.println("Displaying list of Rewards available for "+get_brand_id+);
                Map<String, Integer> reward_name_points = new HashMap<>();
                while(result2.next()){
                    String rc = result2.getString("reward_code");
                    String rn = result2.getString("reward_name");
                    String rp = result2.getInt("redeem_points");
                    reward_name_points.put(rn,rp);
                    System.out.println("Reward Code: "+ reward_code + " Reward Name: "+ reward_name + " Points required to redeem reward: "+rp);
                }
                System.out.println("Select Reward code to redeem that reward.");
                String get_reward_code = sc.nextLine();
                System.out.println("Enter Quantity: ");
                int get_quantity = sc.nextInt();
                
                String quantity_check = "select quantity from Reward_program where reward_code = '"+get_reward_code+"' and loyalty_id = '"+get_loyalty_id+"'";
                result4 = statement.executeQuery(quantity_check);
                //System.out.println("Loading.....");
                while(result4.next()){
                    int max_quantity = result4.getInt("quantity");
                }
                int current_amount = get_quantity * reward_name_points(get_reward_name);
                if(get_quantity>max_quantity){
                    System.out.println("Unsuccessfull, Maximum Quantity available to redeem is :"+max_quantity);
                    System.out.println("Want to try again ?");
                    System.out.println("1. Redeem points");
                    System.out.println("2. Go Back");
                    int selection = sc.nextInt();
                    switch(selection){
                        case 1: 
                            RedeemPoints(customerID);
                            break;
                        case 2:
                            CustomerHomeMenu.main(null);
                            break;
                    }

                }
                else if (current_amount>get_total_points){
                    System.out.println("Unsuccessfull, Maximum points available to redeem are :"+get_total_points);
                    System.out.println("Want to try again ?");
                    System.out.println("1. Redeem points");
                    System.out.println("2. Go Back");
                    int selection = sc.nextInt();
                    switch(selection){
                        case 1: 
                            RedeemPoints(customerID);
                            break;
                        case 2:
                            CustomerHomeMenu.main(null);
                            break;
                    }
                }
                else{
                    String fetch_wallet_id = "select wallet_id from Wallet where customer_id = '"+customerID+"'";
                    result6 = statement.executeQuery(fetch_wallet_id);
                    while(result6.next()){
                        String get_wallet_id = result6.getString("wallet_id");
                    }
                    sc.nextLine();
                    System.out.println("Enter Reward Transaction: ");
                    String get_reward_transaction_id = sc.nextLine();

                    System.out.println("Enter Transaction date: ");
                    String get_reward_transaction_date = sc.nextLine();

                    System.out.println("Enter Redeem Id: ");
                    String get_redeem_id = sc.nextLine();

                    String add_reward_transaction = "insert into Reward_Transaction(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('"+get_reward_transaction_id+"',TO_DATE('"+get_reward_transaction_date+"','mm/dd/yyyy'),'"+get_reward_code+"', '"+ current_amount+"', '"+get_loyalty_id+"','"+get_brand_id+"', '"+ get_wallet_id+"')";
                    result7 = statement.executeQuery(add_reward_transaction);
                    
                    String add_customer_redeem = "insert into Customer_Redeem(reward_transaction_id,redeem_id, reward_instances) values('"+get_reward_transaction_id+"','"+get_redeem_id+"', '"+ get_quantity+"')";
                    result8 = statement.executeQuery(add_customer_redeem);
                    
                    int new_customer_points = get_total_points - current_amount;
                    String add_customer_program = "update Customer_program set customer_points ="+new_customer_points+" where customer_id = '"CustomerID+"' and brand_id = '"+get_brand_id+"' and loyalty_id = '"+get_loyalty_id+"'";
                    result9 = statement.executeQuery(add_customer_program);

                    System.out.println("Reward Redeemed suddefully. Thank You!!");                     
                }

                CustomerHomeMenu.main(null);

            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void showPrograms(String customerID){

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying the list of Available Loyalty Programs\n\n");
                
                String programList = "select loyalty_id, loyalty_program_name from Loyalty_program where lp_status = active";
                result = statement.executeQuery(programList);

                System.out.println("\t\tLoyalty Program ID\t\tLoalty Program Name");
                int cnt = 1;
                while(result.next()){
                    System.out.println("\t\t" + result.getString("loyalty_id") + "\t\t" + result.getString("loyalty_program_name"));
                }

                System.out.println("1. Enroll in Loyalty Program");
                System.out.println("2. Go Back");

                selection = sc.nextInt();

                switch(selection){
                    case 1:
                        System.out.println("Enter a Loyalty Program Name");
                        String selectedProgram = sc.next();

                        String enrollCustomer = "select * from Loyalty_program where loyalty_program_name='" + selectedProgram + "'";
                        result = statement.executeQuery(enrollCustomer);

                        if(result.next()){
                            String programID = result.getString("loyalty_id");
                            enrollment(customerID,programID);
                        }
                        else{
                            CustomerHomeMenu.main(new String[]{customerID});
                        }
                        break;
                    case 2:
                        CustomerHomeMenu.main(new String[]{customerID});
                        break;
                    default:
                        System.out.println("Invalid Input. Enter your choice again");
                        CustomerHomeMenu.main(new String[]{customerID});
                }

            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }
    
    public static void viewWallet(String customerID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String viewTransaction = "select * from Customer C, Wallet W, Customer_Transaction T where C.customer_id=W.customer_id AND T.wallet_id=W.wallet_id";
                result = statement.executeQuery(viewTransaction);

                System.out.println("Displaying all the transactions..");
                while(result.next()){
                    System.out.println(result.getString("transaction_id") + " " + result.getString("transaction_date") + " " + result.getString("activity_type") + " " + result.getString("brand_id"));
                }
            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }

        System.out.println("1. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                viewWallet(customerID);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                CustomerHomeMenu.main(new String[]{customerID});
        }
    }

    public static void rewardActivities(String customerID){
        System.out.println("1. Purchase");
        System.out.println("2. Leave a Review");
        System.out.println("3. Refer a Friend");
        System.out.println("4. Go Back");

        selection = sc.nextInt();

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying a list of enrolled Loyalty Programs\n\n");
                
                String enrolledPrograms = "select * from Customer_program where customer_id = '" + customerID + "')";
                result = statement.executeQuery(enrolledPrograms);

                while(result.next()){
                    System.out.println("Select a Loyalty Program.");
                }

                HashMap<Integer, String> map = new HashMap<>();
                String selectedProgram = sc.next();
                String programPresent = "select * from Loyalty_program where loyalty_programe_name='" + selectedProgram + "')";
                result = statement.executeQuery(programPresent);
                String programID = "";
                if(!result.next()){
                    System.out.println("Please enter a correct loyalty program name.");
                    CustomerHomeMenu.main(new String[]{customerID});
                }
                else{
                    programID = result.getString("loyalty_id");
                }

                String purchasePresent = "select * from Loyalty_program where loyalty_program_name='" + selectedProgram + "' AND activity_name='Purchase'";
                result = statement.executeQuery(purchasePresent);
                int cnt = 1;
                if(result.next()){
                    System.out.println(cnt + ". Purchase");
                    map.put(cnt, "Purchase");
                    cnt++;
                }

                String reviewPresent = "select * from Loyalty_program where loyalty_program_name='" + selectedProgram + "' AND activity_name='Review'";
                result = statement.executeQuery(purchasePresent);
                if(result.next()){
                    System.out.println(cnt + ". Leave a Review");
                    map.put(cnt, "Review");
                    cnt++;
                }

                String referPresent = "select * from Loyalty_program where loyalty_program_name='" + selectedProgram + "' AND activity_name='Refer'";
                result = statement.executeQuery(purchasePresent);
                if(result.next()){
                    System.out.println(cnt + ". Refer a Friend");
                    map.put(cnt, "Refer");
                    cnt++;
                }

                System.out.println(cnt + ". Go Back");
                map.put(cnt, "GoBack");

                System.out.println("Enter your Choice: ");
                int choice = sc.nextInt();

                if(map.get(choice) == "Purchase"){
                    purchase(customerID, programID);
                }
                else if(map.get(choice) == "Review"){
                    reviewMenu(customerID, programID);
                }
                else if(map.get(choice) == "Refer"){
                    addReferral(customerID, programID);
                }
                else{
                    System.out.println("Please enter a valid choice!");
                    CustomerHomeMenu.main(new String[]{customerID});
                }
                
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "')";
                result = statement.executeQuery(customerWallet);

                if(!result.next()){
                    System.out.println("Adding Customer Wallet...");
                    String createWallet = "insert into Wallet values ('" + customerID + "')";
                    System.out.println("Wallet created Successfully for Customer");
                }

                CustomerHomeMenu.main(new String[]{customerID});

            } finally {
                result.close();
                statement.close();
                connection.close();
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void redeemPoints(){
        System.out.println("1. Redeem Points");
        System.out.println("2. Go Back");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                RedeemPoints();
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                redeemPoints();
        }
    }
}
