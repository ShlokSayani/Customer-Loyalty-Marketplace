CREATE TABLE Customers(
    customer_id int PRIMARY KEY,
	customer_name VARCHAR2(10),
	customer_address VARCHAR2(10),
	phone_number int
);

CREATE TABLE Brand( 
    brand_id int PRIMARY KEY,
    brand_address VARCHAR2(10),
    join_date DATE
);

CREATE TABLE Activity_Type(
    activity_code VARCHAR2(10) PRIMARY KEY,
    activity_name VARCHAR2(10)
);

CREATE TABLE Reward_Type(
    reward_code VARCHAR2(10) PRIMARY KEY,
    reward_name VARCHAR2(10)
);

CREATE TABLE RRRules(
    RR_rule_code VARCHAR2(6)n,
    tier VARCHAR2(10),
    version int,
    Brandid int) FOREIGN KEY REFERENCES Brand(brand_id),
    (RR_rule_code, Brandid) PRIMARY KEY)
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    tier VARCHAR2(10),
    version int,
    Brandid int FOREIGN KEY REFERENCES Brand(brand_id),
    (RE_rule_code,Brandid)  PRIMARY KEY)
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(6),
    Brandid int FOREIGN KEY REFERENCES Brand(brand_id),
    (Brandid,product_name) PRIMARY KEY)
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    value int,
    Brandid int FOREIGN KEY REFERENCES Brand(brand_id),
    (Brand_id,giftcard_code) PRIMARY KEY)
);

CREATE TABLE Loyalty_program( 
    loyalty_id int PRIMARY KEY,
    tier int,
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10)),
    loyalty_points int
);

CREATE TABLE Enroll_Loyalty(
    activitycode VARCHAR2(10) FOREIGN KEY REFERENCES Activity_Type(activity_code),
    rewardcode VARCHAR2(10) FOREIGN KEY REFERENCES Reward_Type(reward_code),
    Brandid int FOREIGN KEY REFERENCES Brand(brand_id),
    loyaltyid int FOREIGN KEY REFERENCES Loyalty_program(loyalty_id)
);

CREATE TABLE Wallet(
	wallet_id VARCHAR2(4) PRIMARY_KEY,
	tier VARCHAR2(10),
	Date DATE NOT_NULL,
	ActivityType VARCHAR2(10) NOT_NULL,
	Points int NOT_NULL,
	LoyaltyProgramCode int NOT_NULL,
	RECode int NOT_NULL,
	BrandID int FOREIGN KEY REFERENCES Brand(brand_id)
);

CREATE TABLE REVIEWS(
	ReviewID int PRIMARY_KEY,
	LoyaltyID int,
	ReviewDate DATE,
	Review VARCHAR2(100),
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE REFER(
	NewCustomerID int FOREIGN_KEY REFERENCES Customers(customer_id),
	LoyaltyID int,
	ReferDate DATE,
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE PURCHASE(
	BrandID int FOREIGN KEY REFERENCES Brand(brand_id),
	Amount int,
	purchasedate DATE,
	GiftcardCode int,
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE AdminUser(username VARCHAR2(10),pass VARCHAR2(10));

CREATE TABLE BrandUsers(username VARCHAR2(10), pass VARCHAR2(10));

CREATE TABLE CustomerUsers(username VARCHAR2(10),pass VARCHAR2(10));
