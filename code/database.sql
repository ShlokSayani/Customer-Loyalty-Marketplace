CREATE TABLE Customer(
    customer_id VARCHAR2(15) PRIMARY KEY,
	customer_name VARCHAR2(10),
	customer_address VARCHAR2(50),
	phone_number VARCHAR2(10), 
    customer_password VARCHAR2(10)
);

CREATE TABLE Activity_Type(
    activity_code VARCHAR2(10) PRIMARY KEY,
    activity_name VARCHAR2(10)
);

CREATE TABLE Reward_Type(
    reward_code VARCHAR2(10) PRIMARY KEY,
    reward_name VARCHAR2(10)
);

CREATE TABLE AdminUser(
    username VARCHAR2(10) PRIMARY KEY,
    pass VARCHAR2(10)
);

CREATE TABLE Brand( 
    brand_id VARCHAR2(15) PRIMARY KEY,
    brand_name VARCHAR2(10),
    brand_address VARCHAR2(50),
    join_date DATE,
    brand_password VARCHAR2(10)
);

CREATE TABLE Loyalty_program( 
    loyalty_id VARCHAR2(15) PRIMARY KEY,
    loyalty_program_name VARCHAR2(10),
    tier VARCHAR2(30),
    multiplier VARCHAR2(10),
    points_required VARCHAR2(20),
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10),
    brand_id VARCHAR2(15),  
    lp_status VARCHAR2(10),
    constraint el_activity_code FOREIGN KEY (activity_code) REFERENCES Activity_Type(activity_code),
    constraint el_reward_code FOREIGN KEY (reward_code) REFERENCES Reward_Type(reward_code),
    constraint el_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

CREATE TABLE RRRules(
    RR_rule_code VARCHAR2(6),
    tier VARCHAR2(30),
    rr_rule_version int,
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(10),
    reward_instances int,
    redeem_points int,
    brand_id VARCHAR2(15),
    loyalty_id VARCHAR2(15),
    constraint rrrule_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint rrule_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (RR_rule_code, brand_id,loyalty_id) 
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    re_rule_version int,
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(10),
    activity_points VARCHAR2(10),
    brand_id VARCHAR2(15),
    loyalty_id VARCHAR2(15),
    constraint rerule_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint rerule_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (RE_rule_code,brand_id,loyalty_id) 
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(10),
    brand_id VARCHAR2(15), 
    constraint rp_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (brand_id,product_name) 
);

CREATE TABLE Customer_program(
    customer_id VARCHAR2(15),
    loyalty_id VARCHAR2(15), 
    customer_points int,
    customer_tier VARCHAR2(30),
    constraint cp_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    constraint cp_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Wallet(
    customer_id VARCHAR2(15), 
    wallet_id VARCHAR2(15) PRIMARY KEY,
    constraint w_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Customer_Transaction(
	wallet_id VARCHAR2(15),
	tier VARCHAR2(30),
    transaction_id VARCHAR2(15) PRIMARY KEY,
	transaction_date DATE NOT NULL,
	activity_type VARCHAR2(10) NOT NULL,
	redeem_points int,
    gained_points int,
	loyalty_id VARCHAR2(15) NOT NULL,
    activity_code VARCHAR2(10),
    reward_code VARCHAR2(10),
    brand_id VARCHAR2(15), 
    constraint ct_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id),
	constraint ct_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint ct_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id)
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    value int,
    loyalty_id VARCHAR2(15),
    customer_id VARCHAR2(15),
    transaction_id VARCHAR2(15),
    constraint rg_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    constraint rg_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    constraint rg_transaction_id FOREIGN KEY (transaction_id) REFERENCES Customer_Transaction(transaction_id),
    PRIMARY KEY (customer_id,loyalty_id,giftcard_code) 
);

CREATE TABLE Customer_Redeem(
    transaction_id VARCHAR2(15),
    redeem_id VARCHAR2(15) PRIMARY KEY,
    reward_instances VARCHAR2(15),
    constraint rd_transaction_id FOREIGN KEY (transaction_id) REFERENCES Customer_Transaction(transaction_id)
);

CREATE TABLE Customer_Reviews(
	review_id VARCHAR2(15),
	loyalty_id VARCHAR2(15),
	review_date DATE,
	review_content VARCHAR2(100),
    transaction_id VARCHAR2(15), 
    customer_id VARCHAR2(15), 
	constraint rev_transaction_id FOREIGN KEY (transaction_id) REFERENCES Customer_Transaction(transaction_id),
    constraint rev_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    constraint rev_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (review_id,customer_id)
);
