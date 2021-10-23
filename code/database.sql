CREATE TABLE Customers(
    customer_id INT PRIMARY KEY,
	customer_name VARCHAR2(10),
	customer_address VARCHAR2(10),
	phone_number INT
);

CREATE TABLE Brand( 
    brand_id INT PRIMARY KEY,
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
    RR_rule_code VARCHAR2(6),
    tier VARCHAR2(10),
    version INT,
    Brandid INT FOREIGN KEY REFERENCES Brand(brand_id),
    (RR_rule_code, Brandid) PRIMARY KEY)
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    tier VARCHAR2(10),
    version INT,
    Brandid INT FOREIGN KEY REFERENCES Brand(brand_id),
    (RE_rule_code,Brandid)  PRIMARY KEY)
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(6),
    Brandid INT FOREIGN KEY REFERENCES Brand(brand_id),
    (Brandid,product_name) PRIMARY KEY)
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    value INT,
    Brandid INT FOREIGN KEY REFERENCES Brand(brand_id),
    (Brand_id,giftcard_code) PRIMARY KEY)
);

CREATE TABLE Loyalty_program( 
    loyalty_id INT PRIMARY KEY,
    tier INT,
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10)),
    loyalty_points INT
);

CREATE TABLE Enroll_Loyalty(
    activitycode VARCHAR2(10) FOREIGN KEY REFERENCES Activity_Type(activity_code),
    rewardcode VARCHAR2(10) FOREIGN KEY REFERENCES Reward_Type(reward_code),
    Brandid INT FOREIGN KEY REFERENCES Brand(brand_id),
    loyaltyid INT FOREIGN KEY REFERENCES Loyalty_program(loyalty_id)
);

CREATE TABLE Wallet(
	wallet_id VARCHAR2(4) PRIMARY_KEY,
	tier VARCHAR2(10),
	Date DATE NOT_NULL,
	ActivityType VARCHAR2(10) NOT_NULL,
	Points INT NOT_NULL,
	LoyaltyProgramCode INT NOT_NULL,
	RECode INT NOT_NULL,
	BrandID INT FOREIGN KEY REFERENCES Brand(brand_id)
);

CREATE TABLE REVIEWS(
	ReviewID INT PRIMARY_KEY,
	LoyaltyID INT,
	ReviewDate DATE,
	Review VARCHAR2(100),
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE REFER(
	NewCustomerID INT FOREIGN_KEY REFERENCES Customers(customer_id),
	LoyaltyID INT,
	ReferDate DATE,
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE PURCHASE(
	BrandID INT FOREIGN KEY REFERENCES Brand(brand_id),
	Amount INT,
	purchasedate DATE,
	GiftcardCode INT,
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE AdminUser(
    username VARCHAR2(10),
    password VARCHAR2(10)
);

CREATE TABLE BrandUsers(
    username VARCHAR2(10), 
    password VARCHAR2(10)
);

CREATE TABLE Customer(
    username VARCHAR2(10),
    password VARCHAR2(10)
);