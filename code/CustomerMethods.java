import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class CustomerMethods {
    
    private static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
    private static final String user = "hpatel28";
    private static final String password = "abcd1234";

    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet result = null;
    public static ResultSet result2 = null;
    public static ResultSet result3 = null;
    public static ResultSet result4 = null;
    public static ResultSet result5 = null;
    public static ResultSet result6 = null;
    public static ResultSet result7 = null;
    public static ResultSet result8 = null;
    public static ResultSet result9 = null;
    public static ResultSet result10 = null;

    static Scanner sc = new Scanner(System.in);
    static int selection = 0;

    public static void enrollment(String customerID, String programID){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Loading Customer Enrollment module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tEnrolling Customer to a loyalty program\n");
                
                String brandQuery = "select * from Loyalty_program where loyalty_id='" + programID + "'";
                result = statement.executeQuery(brandQuery);
                String brandId = "";
                String getTier = "";
                if(result.next()){
                    brandId = result.getString("brand_id");
                    getTier = result.getString("program_type");
                }
                String checkEnrollment = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id='" + programID + "'";
                result = statement.executeQuery(checkEnrollment);
                String tierVal = "Regular";
                if(getTier.equals("Tier")){
                    String baseTier = "select * from Tier where loyalty_id='" + programID + "' and multiplier='1'";
                    result = statement.executeQuery(baseTier);
                    if(result.next()){
                        tierVal = result.getString("tier");
                    }
                }
                if(!result.next()){
                    String enrollCustomer = "insert into Customer_program values('" + customerID + "','" + programID + "', '" + brandId + "', " + 0 + ", '" + tierVal + "')";
                    result = statement.executeQuery(enrollCustomer);
                    System.out.println("Customer Successfully Enrolled");
                    System.out.println();
                }
                else{
                    System.out.println("Customer already enrolled in the program.");
                }

                String customerWallet = "select * from Wallet where customer_id='" + customerID + "'";
                result = statement.executeQuery(customerWallet);

                if(!result.next()){
                    System.out.println("Adding Customer Wallet...");
                    String walletId = "W" + customerID.substring(1, customerID.length());
                    String createWallet = "insert into Wallet values ('" + customerID + "', '" + walletId + "')";
                    result = statement.executeQuery(createWallet);
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
        //AfterInsertOn();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Loading Customer purchase module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "'";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "'"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                System.out.println("Press (Y/N) if you want to use a Gift Card.");
                sc.next();
                String useGiftCard = sc.nextLine();

                if(useGiftCard.equals("Y")){
                    System.out.println("\t\tSelect a Gift Card to use.\n\n");
                    String displayGiftCard = "select * from Reward_GiftCard where customer_id = '" + customerID + "' AND loyalty_id='" + programID;
                    result = statement.executeQuery(displayGiftCard);

                    System.out.println("\t\tGiftCard Code\t\tExpiryDate");
                    while(result.next()){
                        System.out.println(result.getString("giftcard_code") + "\t\t" + result.getString("expiry_date"));
                    }

                    String selectedCard = sc.nextLine();
                    String cardPresent = "select * from Reward_GiftCard where giftcard_code='" + selectedCard + "'";
                    result = statement.executeQuery(cardPresent);
                    if(result.next()){
                        System.out.println("Enter Transaction Date");
                        String transactionDate = sc.nextLine();

                        System.out.println("Enter Transaction ID");
                        String transactionID = sc.nextLine();

                        java.sql.Date expiryDate = result.getDate("expiry_date");
                        long milsec = System.currentTimeMillis();
                        java.sql.Date curDate = new java.sql.Date(milsec);

                        if(curDate.after(expiryDate)){
                            System.out.println("Gift Card Expired!");
                            rewardActivities(customerID);
                        }                        
                        
                        String cardTransaction = "insert into Activity_Transactions(activity_transaction_id, wallet_id, activity_transaction_date, activity_type, loyalty_id, brand_id, gained_points,customer_id) values ('" + transactionID + "', " + walletId + "', TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Purchase', '" + programID + "', '" + customerBrand + "','"+ customerID +"')";
                        statement.executeQuery(cardTransaction);
                    }
                    else{
                        System.out.println("Please select a correct card code.");
                        CustomerHomeMenu.main(new String[]{customerID});
                    }
                }
                else{
                    String activityPoints = "select activity_points from RERules where brand_id='" + customerBrand + "' AND activity_name='Purchase'";
                    int customerPoints = 0;

                    result = statement.executeQuery(activityPoints);
                    if(result.next()){
                        customerPoints = Integer.valueOf(result.getString("activity_points"));
                    }

                    String getTier = "select * from Tier where loyalty_id='" + programID + "' AND tier='" + customerTier + "'";
                    result = statement.executeQuery(getTier);

                    if(result.next()){
                        customerPoints *= Integer.valueOf(result.getString("multiplier"));
                    }

                    System.out.println("Enter Transaction Date");
                    String transactionDate = sc.nextLine();

                    System.out.println("Enter Transaction ID");
                    String transactionID = sc.nextLine();
                    
                    String cardTransaction = "insert into Activity_Transactions(activity_transaction_id, wallet_id, activity_transaction_date, activity_type, loyalty_id, brand_id, gained_points,customer_id) values ('" + transactionID + "', '" + walletId + "', TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Purchase', '" + programID + "', '" + customerBrand + "', " + customerPoints + ",'"+customerID +"')";
                    statement.executeQuery(cardTransaction);
                    

                    System.out.println("Product Purchased Successfully!");
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
        //AfterInsertOn();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Loading Customer add review module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "'";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "'"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                String activityPoints = "select activity_points from RERules where brand_id='" + customerBrand + "' AND activity_name='Leave a review'";
                int customerPoints = 0;

                result = statement.executeQuery(activityPoints);
                if(result.next()){
                    customerPoints = Integer.valueOf(result.getString("activity_points"));
                }

                String getTier = "select * from Tier where loyalty_id='" + programID + "' AND tier='" + customerTier + "'";
                result = statement.executeQuery(getTier);

                if(result.next()){
                    customerPoints *= Integer.valueOf(result.getString("multiplier"));
                }

                System.out.println("Enter Transaction Date");
                sc.nextLine();
                String transactionDate = sc.nextLine();
                System.out.println("Enter Transaction ID");
                String transactionID = sc.nextLine();

                String reviewTransaction = "insert into Activity_Transactions(activity_transaction_id, wallet_id, activity_transaction_date, activity_type, loyalty_id, brand_id, gained_points, customer_id) values ('" + transactionID + "', '" + walletId + "', TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Leave a review', '" + programID + "', '" + customerBrand + "', " + customerPoints + ",'"+customerID+"')";
                result = statement.executeQuery(reviewTransaction);

                System.out.println("Enter Review Id: ");
                String get_review_id = sc.nextLine();

                String reviewTable = "insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values ('"+get_review_id+"','" + programID + "', TO_DATE('" + transactionDate +  "','MM/DD/YYYY'), '" + reviewContent + "', '" + transactionID + "', '" + customerID + "')";
                result = statement.executeQuery(reviewTable);

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
                System.out.println("Loading Customer reveiw menu module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tLeave a Review!\n\n");

                System.out.println("\n Enter review: ");
                sc.nextLine();
                String reviewContent = sc.nextLine();

                
                System.out.println("1. Leave a Review");
                System.out.println("2. Go Back");
                System.out.println("Choose from above options");
                selection = sc.nextInt();
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
        //AfterInsertOn();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Loading Customer refer a friend module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "'";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }

                String customerTier = "";
                String customerBrand = "";

                String getCustomerProgram = "select * from Customer_program where customer_id='" + customerID + "' AND loyalty_id = '" + programID + "'"; 
                result = statement.executeQuery(getCustomerProgram);

                if(result.next()){
                    customerTier = result.getString("customer_tier");
                    customerBrand = result.getString("brand_id");
                }

                String activityPoints = "select activity_points from RERules where brand_id='" + customerBrand + "' AND activity_name='Refer a friend'";
                int customerPoints = 0;

                result = statement.executeQuery(activityPoints);
                if(result.next()){
                    customerPoints = Integer.valueOf(result.getString("activity_points"));
                }

                String getTier = "select * from Tier where loyalty_id='" + programID + "' AND tier='" + customerTier + "'";
                result = statement.executeQuery(getTier);

                if(result.next()){
                    customerPoints *= Integer.valueOf(result.getString("multiplier"));
                }

                System.out.println("Enter Transaction Date");
                String transactionDate = sc.next();
                
                System.out.println("Enter Transaction ID");
                String transactionID = sc.next();

                String referralTransaction = "insert into Activity_Transactions(activity_transaction_id, wallet_id, activity_transaction_date, activity_type, loyalty_id, brand_id, gained_points,customer_id) values ('" + transactionID + "', '" + walletId + "', TO_DATE('" + transactionDate + "','MM/DD/YYYY'), 'Refer a friend', '" + programID + "', '" + customerBrand + "', " + customerPoints + ",'"+customerID+"')";
                result = statement.executeQuery(referralTransaction);

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
        System.out.println("Choose from above options");

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
                System.out.println("Loading Customer Redeem points module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying the list of Available rewards to redeem\n\n");
                
                String customer_points_list = "select brand_id,loyalty_id,customer_points from Customer_program where customer_id = '"+CustomerID+"'";
                result = statement.executeQuery(customer_points_list);
                Map<String, Integer> brand_customer_points = new HashMap<>();
                while(result.next()){
                    String bi = result.getString("brand_id");
                    String li = result.getString("loyalty_id");
                    int cp = result.getInt(3);
                    brand_customer_points.put(bi,cp);
                    System.out.println("Brand Id: "+bi+" Loyalty Id: "+li + " Total Points: "+cp);
                }
                System.out.println();
                System.out.println("Select Brand ID for which you have to redeem points.");
                sc.nextLine();
                String get_brand_id = sc.nextLine();

                String fetchloyaltyid = "select loyalty_id from Customer_program where customer_id = '"+CustomerID+"' and brand_id='" + get_brand_id + "'";
                result3 = statement.executeQuery(fetchloyaltyid);
                String get_loyalty_id = "";
                while(result3.next()){
                    get_loyalty_id = result3.getString("loyalty_id");
                }

                String fetchtotalpoints = "select customer_points from Customer_program where customer_id = '"+CustomerID+"' and brand_id = '"+get_brand_id+"' and loyalty_id ='"+get_loyalty_id+"'";
                result5 = statement.executeQuery(fetchtotalpoints);
                int get_total_points = 0;
                while(result5.next()){
                    get_total_points = result5.getInt(1);
                }
                
                String fetch_reward_details = "select reward_code,reward_name, redeem_points from RRRules where brand_id = '"+get_brand_id+"'";
                result2 = statement.executeQuery(fetch_reward_details);
                System.out.println("Displaying list of Rewards available for "+get_brand_id);
                Map<String, Integer> reward_name_points = new HashMap<>();
                Map<String, String> reward_name_code = new HashMap<>();
                while(result2.next()){
                    String rc = result2.getString("reward_code");
                    String rn = result2.getString("reward_name");
                    int rp = result2.getInt(3);
                    reward_name_points.put(rc,rp);
                    reward_name_code.put(rc,rn);
                    System.out.println("Reward Code: "+ rc + " Reward Name: "+ rn + " Points required to redeem reward: "+rp);
                }
                System.out.println("Select Reward code to redeem that reward.");
                String get_reward_code = sc.nextLine();
                System.out.println("Enter Quantity: ");
                int get_quantity = sc.nextInt();
                String get_reward_name = reward_name_code.get(get_reward_code);
                String quantity_check = "select quantity from Reward_program where reward_code = '"+get_reward_code+"' and loyalty_id = '"+get_loyalty_id+"'";
                result4 = statement.executeQuery(quantity_check);
                //System.out.println("Loading.....");
                int max_quantity = 0;
                while(result4.next()){
                    max_quantity = result4.getInt(1);
                }
                int current_amount = get_quantity * reward_name_points.get(get_reward_code);
                if(get_quantity>max_quantity){
                    System.out.println("Unsuccessfull, Maximum Quantity available to redeem is :"+max_quantity);
                    System.out.println("Want to try again ?");
                    System.out.println("1. Redeem points");
                    System.out.println("2. Go Back");
                    System.out.println("Choose from above options");

                    int selection = sc.nextInt();
                    switch(selection){
                        case 1: 
                            RedeemPoints(CustomerID);
                            break;
                        case 2:
                            CustomerHomeMenu.main(new String[]{CustomerID});
                            break;
                    }

                }
                else if (current_amount>get_total_points){
                    System.out.println("Unsuccessfull, Maximum points available to redeem are :"+get_total_points);
                    System.out.println("Want to try again ?");
                    System.out.println("1. Redeem points");
                    System.out.println("2. Go Back");
                    System.out.println("Choose from above options");

                    int selection = sc.nextInt();
                    switch(selection){
                        case 1: 
                            RedeemPoints(CustomerID);
                            break;
                        case 2:
                            CustomerHomeMenu.main(new String[]{CustomerID});
                            break;
                    }
                }
                else{
                    String fetch_wallet_id = "select wallet_id from Wallet where customer_id = '"+CustomerID+"'";
                    result6 = statement.executeQuery(fetch_wallet_id);
                    String get_wallet_id = "";
                    while(result6.next()){
                        get_wallet_id = result6.getString("wallet_id");
                    }
                    sc.nextLine();
                    System.out.println("Enter Reward Transaction ID: ");
                    String get_reward_transaction_id = sc.nextLine();

                    System.out.println("Enter Transaction date: ");
                    String get_reward_transaction_date = sc.nextLine();

                    System.out.println("Enter Redeem Id: ");
                    String get_redeem_id = sc.nextLine();

                    String add_reward_transaction = "insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id, customer_id) values('"+get_reward_transaction_id+"',TO_DATE('"+get_reward_transaction_date+"','mm/dd/yyyy'),'"+get_reward_code+"', '"+ current_amount+"', '"+get_loyalty_id+"','"+get_brand_id+"', '"+ get_wallet_id+"','"+CustomerID+"')";
                    result7 = statement.executeQuery(add_reward_transaction);
                    
                    String add_customer_redeem = "insert into Customer_Redeem(reward_transaction_id,redeem_id, reward_instances) values('"+get_reward_transaction_id+"','"+get_redeem_id+"', '"+ get_quantity+"')";
                    result8 = statement.executeQuery(add_customer_redeem);
                    
                    int new_customer_points = get_total_points - current_amount;
                    // String add_customer_program = "update Customer_program set customer_points ="+new_customer_points+" where customer_id = '"+CustomerID+"' and brand_id = '"+get_brand_id+"' and loyalty_id = '"+get_loyalty_id+"'";
                    // result9 = statement.executeQuery(add_customer_program);

                    // String Trigger4 = "CREATE OR REPLACE TRIGGER redeem_update AFTER INSERT ON Customer_Redeem BEGIN update Customer_program set customer_points ="+new_customer_points+" where customer_id = '"+CustomerID+"' and brand_id = '"+get_brand_id+"' and loyalty_id = '"+get_loyalty_id+"' END";
                    // statement.executeQuery(Trigger4);
                    int updatedQuantity = max_quantity - get_quantity;
                    // String Trigger5 = "CREATE OR REPLACE TRIGGER instance_update AFTER INSERT ON Customer_Redeem BEGIN UPDATE Reward_program SET quantity='"+ updatedQuantity +"' END";

                    // statement.executeQuery(Trigger5);

                    if(get_reward_name.equals("Gift Card")){
                        System.out.println("Enter Gift card Code : ");
                        String get_gift_card_code = sc.nextLine();

                        System.out.println("Enter expiry date: ");
                        String get_expiry_date = sc.nextLine();

                        String add_reward_giftcard = "insert into Reward_GiftCard(giftcard_code, expiry_date,customer_id,reward_transaction_id,loyalty_id) values('"+get_gift_card_code+"',TO_DATE('"+get_expiry_date+"','mm/dd/yyyy')','"+CustomerID+"','"+get_reward_transaction_id+"','"+get_loyalty_id+"')";
                        result10 = statement.executeQuery(add_reward_giftcard);
                    }
                    System.out.println("Reward Redeemed successfully. Thank You!!");                     
                }

                CustomerHomeMenu.main(new String[]{CustomerID});

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
                System.out.println("Loading Customer Show Programs module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying the list of Available Loyalty Programs\n");
                
                String programList = "select loyalty_id, loyalty_program_name from Loyalty_program where lp_status = 'active'";
                result = statement.executeQuery(programList);
                System.out.println("Loyalty Program ID\tLoalty Program Name");
                System.out.println();
                int cnt = 1;
                while(result.next()){
                    System.out.println(result.getString("loyalty_id") + "\t\t" + result.getString("loyalty_program_name"));
                }
                System.out.println();
                System.out.println("1. Enroll in Loyalty Program");
                System.out.println("2. Go Back");
                System.out.println("Choose from above options");

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
                            System.out.println("Incorrect Loyalty Program Name Entered!");
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
                System.out.println("Loading Wallet...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                String walletId = "";
                String customerWallet = "select * from Wallet where customer_id='" + customerID + "'";
                result = statement.executeQuery(customerWallet);
                if(result.next()){
                    walletId = result.getString("wallet_id");
                }
                
                System.out.println("Displaying a List of Activity Transactions...");
                System.out.println();
                String viewActTransaction = "select * from Customer C, Wallet W, Activity_Transactions T where C.customer_id='" + customerID + "' AND C.customer_id=W.customer_id AND T.wallet_id=W.wallet_id";
                result = statement.executeQuery(viewActTransaction);
                System.out.println("Activity Transaction ID\tTransaction Date\tActivity Type\tGained Points\tLoyalty Program ID\tBrand ID\tWallet ID");
                while(result.next()){
                    System.out.println(result.getString("activity_transaction_id") + "\t" + result.getString("activity_transaction_date") + "\t" + result.getString("activity_type") + "\t" + result.getString("gained_points") + "\t" + result.getString("loyalty_id") + "\t" + result.getString("brand_id") + "\t" + result.getString("wallet_id"));
                }
                System.out.println();
                System.out.println("Displaying a List of Reward Transactions...");
                System.out.println();
                String viewRwdTransaction = "select * from Customer C, Wallet W, Reward_Transactions T where C.customer_id='" + customerID + "' AND C.customer_id=W.customer_id AND T.wallet_id=W.wallet_id";
                result = statement.executeQuery(viewRwdTransaction);
                System.out.println("Reward Transaction ID\tTransaction Date\tReward Code\tRedeemed Points\tLoyalty Program ID\tBrand ID\tWallet ID");
                while(result.next()){
                    System.out.println(result.getString("reward_transaction_id") + "\t" + result.getString("reward_transaction_date") + "\t" + result.getString("reward_code") + "\t" + result.getString("redeem_points") + "\t" + result.getString("loyalty_id") + "\t" + result.getString("brand_id") + "\t" + result.getString("wallet_id"));
                }
                System.out.println();
                System.out.println("Displaying the total number of Points for each Loyalty Program Enrolled");
                System.out.println();
                String ptsTotal = "select * from Customer_program where customer_id='" + customerID + "'";
                result = statement.executeQuery(ptsTotal);
                System.out.println();
                System.out.println("Loyalty Program ID\tPoints\tTier");
                System.out.println();
                while(result.next()){
                    System.out.println(result.getString("loyalty_id") + "\t" + result.getString("customer_points") + "\t" + result.getString("customer_tier"));
                }
                System.out.println();
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
        // System.out.println("1. Purchase");
        // System.out.println("2. Leave a Review");
        // System.out.println("3. Refer a Friend");
        // System.out.println("4. Go Back");
        // System.out.println("Choose from above options");

        // selection = sc.nextInt();

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            try {
                System.out.println("Loading Reward Activities module...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                statement = connection.createStatement();
                System.out.println("\t\tDisplaying a list of enrolled Loyalty Programs\n\n");
                
                String enrolledPrograms = "select C.loyalty_id AS loyaltyId, L.loyalty_program_name AS loyaltyName from Customer_program C, Loyalty_program L where C.customer_id = '" + customerID + "' AND C.loyalty_id=L.loyalty_id";
                result = statement.executeQuery(enrolledPrograms);

                System.out.println("\t\tLoyalty Program ID\tLoyalty Program Name");
                while(result.next()){
                    System.out.println(result.getString("loyaltyId") + "\t" + result.getString("loyaltyName"));
                }

                HashMap<Integer, String> map = new HashMap<>();
                System.out.println("Select a Loyalty Program ID.");
                String selectedProgram = sc.next();
                String programPresent = "select * from Customer_program where loyalty_id='" + selectedProgram + "'";
                result = statement.executeQuery(programPresent);
                String programID = "";
                if(!result.next()){
                    System.out.println("Please enter a correct loyalty program name.");
                    CustomerHomeMenu.main(new String[]{customerID});
                }
                else{
                    programID = result.getString("loyalty_id");
                }

                String purchasePresent = "select * from Activity_program where loyalty_id='" + selectedProgram + "' and activity_name='Purchase'";
                result = statement.executeQuery(purchasePresent);
                int cnt = 1;
                if(result.next()){
                    System.out.println(cnt + ". Purchase");
                    map.put(cnt, "Purchase");
                    cnt++;
                }

                String reviewPresent = "select * from Activity_program where loyalty_id='" + selectedProgram + "' and activity_name='Leave a review'";
                result = statement.executeQuery(reviewPresent);
                if(result.next()){
                    System.out.println(cnt + ". Leave a Review");
                    map.put(cnt, "Leave a review");
                    cnt++;
                }

                String referPresent = "select * from Activity_program where loyalty_id='" + selectedProgram + "' and activity_name='Refer a friend'";
                result = statement.executeQuery(referPresent);
                if(result.next()){
                    System.out.println(cnt + ". Refer a Friend");
                    map.put(cnt, "Refer a friend");
                    cnt++;
                }

                System.out.println(cnt + ". Go Back");
                map.put(cnt, "GoBack");

                System.out.println("Enter your Choice: ");
                int choice = sc.nextInt();

                if(map.get(choice).equals("Purchase")){
                    purchase(customerID, programID);
                }
                else if(map.get(choice).equals("Leave a review")){
                    reviewMenu(customerID, programID);
                }
                else if(map.get(choice).equals("Refer a friend")){
                    addReferral(customerID, programID);
                }
                else{
                    System.out.println("Please enter a valid choice!");
                    CustomerHomeMenu.main(new String[]{customerID});
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

    public static void redeemPoints(String customerID){
        System.out.println("1. Redeem Points");
        System.out.println("2. Go Back");
        System.out.println("Choose from above options");

        selection = sc.nextInt();

        switch(selection){
            case 1:
                RedeemPoints(customerID);
                break;
            case 2:
                CustomerHomeMenu.main(null);
                break;
            default:
                System.out.println("Invalid Input. Enter your choice again");
                redeemPoints(customerID);
        }
    }

}
