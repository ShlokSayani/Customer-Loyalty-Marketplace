/*
Run this queries after successfull execution of database.sql file.
*/

/*Admin*/
insert into AdminUser(username,pass) values('admin','admin1234');

/*Customer*/
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('C0001','Peter Parker','8636368778','20 Ingram Street, NY','c0001pwd');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('C0002','Steve Rogers','8972468552','569 Leaman Place, NY','c0002pwd');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('C0003','Diana Prince','8547963210','1700 Broadway St, NY','c0003pwd');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('C0004','Billy Batson','8974562583','5015 Broad St, Philadelphia, PA','c0004pwd');
insert into Customer(customer_id, customer_name, customer_address, phone_number, customer_password) values('C0005','Tony Stark','8731596464','10880 Malibu Point, CA','c0005pwd');

/*Activity_Type*/
insert into Activity_Type(activity_code, activity_name) values('A01','Purchase');
insert into Activity_Type(activity_code, activity_name) values('A02','Leave a review');
insert into Activity_Type(activity_code, activity_name) values('A03','Refer a friend');

/*Reward_Type*/
insert into Reward_Type(reward_code, reward_type) values('R01','Gift Card');
insert into Reward_Type(reward_code, reward_type) values('R02','Free Product');

/*Brand*/
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('Brand01','Brand X','503 Rolling Creek Dr Austin, AR',TO_DATE('04/01/2021','04/01/2021'),'brand01pwd');
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('Brand02','Brand Y','939 Orange AveCoronado, CA',TO_DATE('03/25/2021','03/25/2021'),'brand02pwd');
insert into Brand(brand_id, brand_name, brand_address, join_date, brand_password) values('Brand03','Brand Z','20 Roszel Rd Princeton, NJ',TO_DATE('05/08/2021','05/08/2021'),'brand03pwd');

/*Loyalty_program*/
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('TLP01','SportGoods','Brand01','inactive','Tier');
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('TLP02','MegaCenter','Brand02','inactive','Tier');
insert into Loyalty_program(loyalty_id, loyalty_program_name, brand_id, lp_status, program_type) values('RLP01','TechSups','Brand03','inactive','Regular');

/*Activity_program*/
insert into Activity_program(activity_code, activity_name, loyalty_id) values('A01','Purchase','TLP01');
insert into Activity_program(activity_code, activity_name, loyalty_id) values('A02','Leave a review','TLP01');
insert into Activity_program(activity_code, activity_name, loyalty_id) values('A01','Purchase','TLP02');
insert into Activity_program(activity_code, activity_name, loyalty_id) values('A03','Refer a friend','TLP02');
insert into Activity_program(activity_code, activity_name, loyalty_id) values('A03','Refer a friend','RLP01');


/*Reward_program*/
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('R01','Gift Card','TLP01',40);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('R02','Free Product','TLP01',25);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('R01','Gift Card','TLP02',30);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('R02','Free Product','TLP02',50);
insert into Reward_program(reward_code, reward_name, loyalty_id, quantity) values('R01','Gift Card','RLP01',25);

/*Tier*/
insert into Tier(tier, multiplier, points_required, loyalty_id) values('Bronze','1','0','TLP01');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('Gold','2','170','TLP01');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('Silver','3','270','TLP01');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('Special','1','0','TLP02');
insert into Tier(tier, multiplier, points_required, loyalty_id) values('Premium','2','210','TLP02');


/*RRRules*/
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('RB1234', 1,'R01','Gift Card', 80,'Brand01');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('RB1235', 1,'R02','Free Product',70 ,'Brand01');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('RB2234',1 ,'R01','Gift Card', 120,'Brand02');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('RB2235',1 ,'R02','Free Product', 90,'Brand02');
insert into RRRules(RR_rule_code, rr_rule_version, reward_code, reward_name, redeem_points, brand_id) values('RB3234',1 ,'R01','Gift Card',100 ,'Brand03');

/*RERules*/
insert into RERules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('BR1234', 1,'A01','Purchase',15 ,'Brand01');
insert into RERules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('BR1235', 1,'A02','Leave a review', 10,'Brand01');
insert into RERules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('BR2234', 1,'A01','Purchase', 40,'Brand02');
insert into RERules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('BR2235', 1,'A03','Refer a friend',30 ,'Brand02');
insert into RERules(RE_rule_code, re_rule_version, activity_code, activity_name, activity_points, brand_id) values('BR3234', 1,'A03','Refer a friend', 10,'Brand03');

-- /*Reward_product*/
-- insert into Reward_product(product_name, brand_id) values('','');
-- insert into Reward_product(product_name, brand_id) values('','');
-- insert into Reward_product(product_name, brand_id) values('','');

/*Customer_program*/
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0001','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0001','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0002','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0003','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0003','RLP01','Brand03', 0,'');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0005','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0005','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0005','RLP01','Brand03', 0,'');

/*Wallet*/
insert into Wallet(customer_id, wallet_id) values('C0001','W0001');
insert into Wallet(customer_id, wallet_id) values('C0002','W0002');
insert into Wallet(customer_id, wallet_id) values('C0003','W0003');
insert into Wallet(customer_id, wallet_id) values('C0004','W0004');
insert into Wallet(customer_id, wallet_id) values('C0005','W0005');

/*Activity_Transactions*/
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');

/*Reward_Transactions*/
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('',TO_DATE('',''),'', ,'','','');

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


