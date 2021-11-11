CREATE TABLE Customer(
    customer_id VARCHAR2(25) PRIMARY KEY,
	customer_name VARCHAR2(50),
	customer_address VARCHAR2(50),
	phone_number VARCHAR2(10), 
    customer_password VARCHAR2(20)
);

CREATE TABLE Activity_Type(
    activity_code VARCHAR2(10) PRIMARY KEY,
    activity_name VARCHAR2(20)
);

CREATE TABLE Reward_Type(
    reward_code VARCHAR2(10) PRIMARY KEY,
    reward_name VARCHAR2(20)
);

CREATE TABLE AdminUser(
    username VARCHAR2(10) PRIMARY KEY,
    pass VARCHAR2(20)
);

CREATE TABLE Brand( 
    brand_id VARCHAR2(15) PRIMARY KEY,
    brand_name VARCHAR2(50),
    brand_address VARCHAR2(50),
    join_date DATE,
    brand_password VARCHAR2(20)
);

CREATE TABLE Loyalty_program( 
    loyalty_id VARCHAR2(15) PRIMARY KEY,
    loyalty_program_name VARCHAR2(50) UNIQUE,
    brand_id VARCHAR2(15),  
    lp_status VARCHAR2(10),
    program_type VARCHAR2(30),
    constraint el_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

CREATE TABLE Activity_program(
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(20),
    loyalty_id VARCHAR2(15), 
    constraint ap_activity_code FOREIGN KEY (activity_code) REFERENCES Activity_Type(activity_code), 
    constraint ap_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (activity_code,loyalty_id)
);

CREATE TABLE Reward_program(
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(20),
    loyalty_id VARCHAR2(15),
    quantity int, 
    constraint rp_reward_code FOREIGN KEY (reward_code) REFERENCES Reward_Type(reward_code),
    constraint rp_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (reward_code,loyalty_id)
);

CREATE TABLE Tier(
    tier VARCHAR2(30),
    multiplier VARCHAR2(50),
    points_required VARCHAR2(20),
    loyalty_id VARCHAR2(15), 
    constraint tier_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (tier,loyalty_id)
);

CREATE TABLE RRRules(
    RR_rule_code VARCHAR2(6),
    rr_rule_version NUMBER GENERATED ALWAYS AS IDENTITY,
    reward_code VARCHAR2(10),
    reward_name VARCHAR2(20),
    redeem_points int,
    brand_id VARCHAR2(15),
    constraint rrrule_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (RR_rule_code, brand_id) 
);

CREATE TABLE RERules(
    RE_rule_code VARCHAR2(6),
    re_rule_version NUMBER GENERATED ALWAYS AS IDENTITY,
    activity_code VARCHAR2(10),
    activity_name VARCHAR2(20),
    activity_points int,
    brand_id VARCHAR2(15),
    constraint rerule_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (RE_rule_code,brand_id) 
);

CREATE TABLE Reward_Product(
    product_name VARCHAR2(50),
    brand_id VARCHAR2(15), 
    constraint rp_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    PRIMARY KEY (brand_id,product_name) 
);

CREATE TABLE Customer_program(
    customer_id VARCHAR2(15),
    loyalty_id VARCHAR2(15), 
    brand_id VARCHAR2(15), 
    customer_points int,
    customer_tier VARCHAR2(30),
    constraint cp_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    constraint cp_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    PRIMARY KEY (customer_id, loyalty_id)
);

CREATE TABLE Wallet(
    customer_id VARCHAR2(15), 
    wallet_id VARCHAR2(15) PRIMARY KEY,
    constraint w_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);


CREATE TABLE Activity_Transactions(
    activity_transaction_id VARCHAR2(15) PRIMARY KEY,
	activity_transaction_date DATE NOT NULL,
    activity_type VARCHAR2(20) NOT NULL,
    gained_points int,
    loyalty_id VARCHAR2(15) NOT NULL,
    brand_id VARCHAR2(15),
    wallet_id VARCHAR2(15),
    customer_id VARCHAR2(15),
    constraint at_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id),
	constraint at_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint at_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    constraint at_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Reward_Transactions(
    reward_transaction_id VARCHAR2(15) PRIMARY KEY,
	reward_transaction_date DATE NOT NULL,
    reward_code VARCHAR2(20),
   	redeem_points int,
    loyalty_id VARCHAR2(15) NOT NULL,
    brand_id VARCHAR2(15),
    wallet_id VARCHAR2(15),
    customer_id VARCHAR2(15),
    constraint rt_wallet_id FOREIGN KEY (wallet_id) REFERENCES Wallet(wallet_id),
	constraint rt_brand_id FOREIGN KEY (brand_id) REFERENCES Brand(brand_id),
    constraint rt_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    constraint rt_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Reward_GiftCard(
    giftcard_code VARCHAR2(6),
    expiry_date DATE,
    customer_id VARCHAR2(15),
    reward_transaction_id VARCHAR2(15),
    loyalty_id VARCHAR2(15),
    constraint rg_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    constraint rg_transaction_id FOREIGN KEY (reward_transaction_id) REFERENCES Reward_Transactions(reward_transaction_id),
    constraint rg_loylaty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (customer_id,giftcard_code) 
);

CREATE TABLE Customer_Redeem(
    reward_transaction_id VARCHAR2(15),
    redeem_id VARCHAR2(15) PRIMARY KEY,
    reward_instances VARCHAR2(15),
    constraint rd_transaction_id FOREIGN KEY (reward_transaction_id) REFERENCES Reward_Transactions(reward_transaction_id)
);

CREATE TABLE Customer_Reviews(
	review_id VARCHAR2(15),
	loyalty_id VARCHAR2(15),
	review_date DATE,
	review_content VARCHAR2(100),
    activity_transaction_id VARCHAR2(15), 
    customer_id VARCHAR2(15), 
	constraint rev_transaction_id FOREIGN KEY (activity_transaction_id) REFERENCES Activity_Transactions(activity_transaction_id),
    constraint rev_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    constraint rev_loyalty_id FOREIGN KEY (loyalty_id) REFERENCES Loyalty_program(loyalty_id),
    PRIMARY KEY (review_id,customer_id)
);


CREATE OR REPLACE FUNCTION givesu(ps number, q number)
return number is
BEGIN
   return ps + q;
END;
/

create or replace trigger insert_update
after insert on Activity_Transactions
for each row
declare 
    currentpoints number; 
begin
    select TO_NUMBER(customer_points) into currentpoints from Customer_program where customer_id = :new.customer_id AND loyalty_id = :new.loyalty_id;
    update Customer_program set customer_points = givesu(currentpoints, TO_NUMBER(:new.gained_points)) where customer_id = :new.customer_id and loyalty_id = :new.loyalty_id;
end;
/

create or replace trigger check_tier
after insert on Activity_Transactions
for each row
    follows insert_update
declare
    currentpoints number;
    getpoints1 number;
    getpoints2 number;
    totaltuples number;
    tier1 VARCHAR2(30);
    updatetier VARCHAR2(30);
begin
    select program_type into tier1 from Loyalty_program where loyalty_id = :new.loyalty_id;
    select TO_NUMBER(customer_points) into currentpoints from Customer_program where loyalty_id = :new.loyalty_id and customer_id = :new.customer_id;
    IF(tier1 = 'Tier') THEN
        select count(*) into totaltuples from Tier where loyalty_id = :new.loyalty_id;
        IF(totaltuples = 3) THEN
            select MAX(points_required) into getpoints2 from Tier where loyalty_id=:new.loyalty_id;
            IF(currentpoints > getpoints2) THEN
                select tier into updatetier from Tier where loyalty_id = :new.loyalty_id AND points_required = getpoints2;
                update Customer_program set customer_tier = updatetier where loyalty_id = :new.loyalty_id AND customer_id = :new.customer_id;
            ELSE
                select TO_NUMBER(points_required) into getpoints1 from Tier where points_required NOT IN (select MAX(points_required) from Tier where loyalty_id=:new.loyalty_id) AND points_required <> 0;
                IF(currentpoints > getpoints1) THEN
                    select tier into updatetier from Tier where loyalty_id = :new.loyalty_id AND points_required = getpoints1;
                    update Customer_program set customer_tier = updatetier where loyalty_id = :new.loyalty_id AND customer_id = :new.customer_id;
                END IF;
            END IF;
        ELSE 
            select MAX(points_required) into getpoints2 from Tier where loyalty_id=:new.loyalty_id;
            IF(currentpoints > getpoints2) THEN
                select tier into updatetier from Tier where loyalty_id = :new.loyalty_id AND points_required = getpoints2;
                update Customer_program set customer_tier = updatetier where loyalty_id = :new.loyalty_id AND customer_id = :new.customer_id;
            END IF;
        END IF;
    END IF;
END;
/                
