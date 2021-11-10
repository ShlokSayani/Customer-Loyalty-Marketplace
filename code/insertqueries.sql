/*
Run this queries after successfull execution of database.sql file.
*/

/*Admin*/
insert into AdminUser(username,pass) values('admin','admin1234');

/*Customer*/
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('','','','','');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('','','','','');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('','','','','');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('','','','','');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('','','','','');

/*Activity_Type*/
insert into Activity_Type(activity_code, activity_name) values('','');
insert into Activity_Type(activity_code, activity_name) values('','');
insert into Activity_Type(activity_code, activity_name) values('','');

/*Reward_Type*/
insert into Reward_Type(reward_code, reward_type) values('','');
insert into Reward_Type(reward_code, reward_type) values('','');

/*Brand*/
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('','','',TO_DATE('',''),'');
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('','','',TO_DATE('',''),'');
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('','','',TO_DATE('',''),'');

/*Loyalty_program*/
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('','','','','');
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('','','','','');
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('','','','','');

/*Activity_program*/
insert into Activity_program(activity_code, activity_name, loyalty_id) values('','','');
insert into Activity_program(activity_code, activity_name, loyalty_id) values('','','');

/*Reward_program*/
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('','','',);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('','','',);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('','','',);

/*Tier*/
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('','','','');

/*RRRules*/
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('', ,'','', ,'');

/*RERules*/
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');
insert into RRRules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('', ,'','', ,'');

/*Reward_product*/
insert into Reward_product(product_name, brand_id) values('','');
insert into Reward_product(product_name, brand_id) values('','');
insert into Reward_product(product_name, brand_id) values('','');

/*Customer_program*/
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('','','', ,'');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('','','', ,'');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('','','', ,'');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('','','', ,'');

/*Wallet*/
insert into Wallet(customer_id, wallet_id) values('','');
insert into Wallet(customer_id, wallet_id) values('','');
insert into Wallet(customer_id, wallet_id) values('','');
insert into Wallet(customer_id, wallet_id) values('','');

/*Activity_Transactions*/
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');

/*Reward_Transactions*/
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id) values('',TO_DATE('',''),'', ,'','','');

/*Reward_GiftCard*/
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('',TO_DATE('',''),'','','');
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('',TO_DATE('',''),'','','');
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('',TO_DATE('',''),'','','');

/*Customer_Redeem*/
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('','','');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('','','');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('','','');

/*Customer_Reviews*/
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('','',TO_DATE('',''),'','','');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('','',TO_DATE('',''),'','','');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('','',TO_DATE('',''),'','','');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('','',TO_DATE('',''),'','','');


