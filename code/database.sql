CREATE TABLE Customer(
    customer_id int PRIMARY KEY,
	customer_name VARCHAR2(10),
	customer_address VARCHAR2(10),
	phone_number int, 
    wallet_id int,
    customer_password VARCHAR2(10),
    constraint wall_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);

CREATE TABLE Brand( 
    brand_id int PRIMARY KEY,
    brand_name VARCHAR2(10),
    brand_address VARCHAR2(10),
    join_date DATE,
    brand_password VARCHAR2(10),
    loyalty_program_name VARCHAR2(10),
    tier VARCHAR2(30),
    multiplier VARCHAR2(10),
    points_required VARCHAR2(20),
    loyalty_id int,
    constraint loyal_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id)
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
    tier VARCHAR2(30),
    rr_rule_version int,
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10),
    reward_instances int,
    redeem_points int,
    brand_id int,
    constraint br_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (RR_rule_code, brand_id) 
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    re_rule_version int,
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    activity_points VARCHAR2(10),
    brand_id int,
    constraint b_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (RE_rule_code,brand_id) 
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(6),
    brand_id int, 
    constraint rp_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (brand_id,product_name) 
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    value int,
    brand_id int,
    constraint rg_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (brand_id,giftcard_code) 
);

CREATE TABLE Loyalty_program( 
    loyalty_id int PRIMARY KEY,
    tier VARCHAR2(30),
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10),
    loyalty_points int
);

CREATE TABLE Enroll_Loyalty(
    activity_code VARCHAR2(10),
    reward_code VARCHAR2(10),
    brand_id int, 
    loyalty_id int, 
    constraint el_activity_code FOREIGN KEY (activity_code) REFERENCES Activity_Type(activity_code),
    constraint el_reward_code FOREIGN KEY (reward_code) REFERENCES Reward_Type(reward_code),
    constraint el_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint el_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id)
);

CREATE TABLE Wallet(
	wallet_id int PRIMARY KEY,
	tier VARCHAR2(30),
	transaction_date DATE NOT NULL,
	ActivityType VARCHAR2(10) NOT NULL,
	redeem_points int,
    gained_points int,
	LoyaltyProgramCode int NOT NULL,
	reward_code int,
    brand_id int, 
	constraint bran_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

CREATE TABLE REVIEWS(
	ReviewID int PRIMARY KEY,
	LoyaltyID int,
	ReviewDate DATE,
	Review VARCHAR2(100),
    wallet_id int, 
	constraint rev_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);

CREATE TABLE REFER(
    LoyaltyID int,
	ReferDate DATE,
    customer_id int, 
    wallet_id int, 
	constraint refer_nc_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
	constraint refer_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);

CREATE TABLE PURCHASE(
    Amount int,
	purchasedate DATE,
	GiftcardCode int,
    brand_id int,
    wallet_id int,
	constraint pur_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
	constraint pur_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id)
);

CREATE TABLE AdminUser(username VARCHAR2(10),pass VARCHAR2(10));

-- CREATE TABLE BrandUsers(username VARCHAR2(10), pass VARCHAR2(10));

-- CREATE TABLE CustomerUsers(username VARCHAR2(10),pass VARCHAR2(10));
