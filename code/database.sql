CREATE TABLE Customers(
    customer_id INTEGER(4) PRIMARY KEY,
	customer_name VARCHAR2(10),
	customer_address VARCHAR2(10),
	phone_number INTEGER(4)
);

CREATE TABLE Brand( 
    brand_id INTEGER(4) PRIMARY KEY,
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
    version INTEGER(3),
    Brandid INTEGER(4) FOREIGN KEY REFERENCES Brand(brand_id),
    (RR_rule_code, Brandid) PRIMARY KEY)
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    tier VARCHAR2(10),
    version INTEGER(3),
    Brandid INTEGER(4) FOREIGN KEY REFERENCES Brand(brand_id),
    (RE_rule_code,Brandid)  PRIMARY KEY)
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(6),
    Brandid INTEGER(4) FOREIGN KEY REFERENCES Brand(brand_id),
    (Brandid,product_name) PRIMARY KEY)
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    value INTEGER(6),
    Brandid INTEGER(4) FOREIGN KEY REFERENCES Brand(brand_id),
    (Brand_id,giftcard_code) PRIMARY KEY)
);

CREATE TABLE Loyalty_program( 
    loyalty_id INTEGER(4) PRIMARY KEY,
    tier INTEGER(4),
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10)),
    loyalty_points INTEGER(4)
);

CREATE TABLE Enroll_Loyalty(
    activitycode VARCHAR2(10) FOREIGN KEY REFERENCES Activity_Type(activity_code),
    rewardcode VARCHAR2(10) FOREIGN KEY REFERENCES Reward_Type(reward_code),
    Brandid INTEGER(4) FOREIGN KEY REFERENCES Brand(brand_id),
    loyaltyid INTEGER(4) FOREIGN KEY REFERENCES Loyalty_program(loyalty_id)
);

CREATE TABLE Wallet(
	wallet_id VARCHAR2(4) PRIMARY_KEY,
	tier VARCHAR2(10),
	Date DATE NOT_NULL,
	ActivityType VARCHAR2(10) NOT_NULL,
	Points INTEGER(5) NOT_NULL,
	LoyaltyProgramCode INTEGER(4) NOT_NULL,
	RECode INTEGER(5) NOT_NULL,
	BrandID INTEGER(5) FOREIGN KEY REFERENCES Brand(brand_id)
);

CREATE TABLE REVIEWS(
	ReviewID INTEGER(5) PRIMARY_KEY,
	LoyaltyID INTEGER(5),
	ReviewDate DATE,
	Review VARCHAR2(100),
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE REFER(
	NewCustomerID INTEGER(5) FOREIGN_KEY REFERENCES Customers(customer_id),
	LoyaltyID INTEGER(5),
	ReferDate DATE,
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);

CREATE TABLE PURCHASE(
	BrandID INTEGER(5) FOREIGN KEY REFERENCES Brand(brand_id),
	Amount INTEGER(5),
	purchasedate DATE,
	GiftcardCode INTEGER(5),
	wallet_id FOREIGN KEY REFERENCES Wallet(wallet_id),
);