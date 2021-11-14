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
insert into RRRules(RR_rule_code, reward_code, reward_name, redeem_points, brand_id) values('RB1234','R01','Gift Card', 80,'Brand01');
insert into RRRules(RR_rule_code, reward_code, reward_name, redeem_points, brand_id) values('RB1235','R02','Free Product',70 ,'Brand01');
insert into RRRules(RR_rule_code, reward_code, reward_name, redeem_points, brand_id) values('RB2234','R01','Gift Card', 120,'Brand02');
insert into RRRules(RR_rule_code, reward_code, reward_name, redeem_points, brand_id) values('RB2235','R02','Free Product', 90,'Brand02');
insert into RRRules(RR_rule_code, reward_code, reward_name, redeem_points, brand_id) values('RB3234','R01','Gift Card',100 ,'Brand03');

/*RERules*/
insert into RERules(RE_rule_code, activity_code, activity_name, activity_points, brand_id) values('BR1234','A01','Purchase',15 ,'Brand01');
insert into RERules(RE_rule_code, activity_code, activity_name, activity_points, brand_id) values('BR1235','A02','Leave a review', 10,'Brand01');
insert into RERules(RE_rule_code, activity_code, activity_name, activity_points, brand_id) values('BR2234','A01','Purchase', 40,'Brand02');
insert into RERules(RE_rule_code, activity_code, activity_name, activity_points, brand_id) values('BR2235','A03','Refer a friend',30 ,'Brand02');
insert into RERules(RE_rule_code, activity_code, activity_name, activity_points, brand_id) values('BR3234','A03','Refer a friend', 10,'Brand03');

/*Customer_program*/
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0001','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0001','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0002','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0003','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points) values('C0003','RLP01','Brand03', 0);
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0005','TLP01','Brand01', 0,'Bronze');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points, customer_tier) values('C0005','TLP02','Brand02', 0,'Special');
insert into Customer_program(customer_id, loyalty_id, brand_id, customer_points) values('C0005','RLP01','Brand03', 0);

/*Wallet*/
insert into Wallet(customer_id, wallet_id) values('C0001','W0001');
insert into Wallet(customer_id, wallet_id) values('C0002','W0002');
insert into Wallet(customer_id, wallet_id) values('C0003','W0003');
insert into Wallet(customer_id, wallet_id) values('C0004','W0004');
insert into Wallet(customer_id, wallet_id) values('C0005','W0005');

/*Activity_Transactions*/
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CP02',TO_DATE('06/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CP01',TO_DATE('06/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CP03',TO_DATE('06/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CP04',TO_DATE('06/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CLR01',TO_DATE('06/18/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1CLR02',TO_DATE('06/18/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CP01',TO_DATE('08/09/2021','MM/DD/YYYY'),'A01', 40,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CP02',TO_DATE('08/09/2021','MM/DD/YYYY'),'A01', 40,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CP03',TO_DATE('08/15/2021','MM/DD/YYYY'),'A01', 40,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CRF01',TO_DATE('10/03/2021','MM/DD/YYYY'),'A03', 30,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CRF02',TO_DATE('10/18/2021','MM/DD/YYYY'),'A03', 30,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2CRF03',TO_DATE('10/18/2021','MM/DD/YYYY'),'A03', 30,'TLP02','Brand02','W0001','C0001');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1CP01',TO_DATE('07/02/2021','MM/DD/YYYY'),'A01', 15,'TLP01','Brand01','W0002','C0002');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1CP02',TO_DATE('07/02/2021','MM/DD/YYYY'),'A01', 15,'TLP01','Brand01','W0002','C0002')
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1CP03',TO_DATE('07/08/2021','MM/DD/YYYY'),'A01', 15,'TLP01','Brand01','W0002','C0002');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1CP04',TO_DATE('07/08/2021','MM/DD/YYYY'),'A01', 15,'TLP01','Brand01','W0002','C0002');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1CLR01',TO_DATE('08/05/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0002','C0002');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B3CRF01',TO_DATE('07/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B3CRF02',TO_DATE('07/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B3CRF03',TO_DATE('07/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B3CRF04',TO_DATE('07/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CP01',TO_DATE('08/01/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CP02',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CP03',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CP04',TO_DATE('09/02/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CRF01',TO_DATE('10/01/2021','MM/DD/YYYY'),'A03',30 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2CRF02',TO_DATE('10/16/2021','MM/DD/YYYY'),'A03',30 ,'TLP02','Brand02','W0003','C0003');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP01',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP02',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP03',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP04',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP05',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP06',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP07',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP08',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CP09',TO_DATE('08/10/2021','MM/DD/YYYY'),'A01',15 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B3CRF01',TO_DATE('09/16/2021','MM/DD/YYYY'),'A03', 10,'RLP01','Brand03','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B3CRF02',TO_DATE('09/16/2021','MM/DD/YYYY'),'A03', 10,'RLP01','Brand03','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B3CRF03',TO_DATE('09/16/2021','MM/DD/YYYY'),'A03', 10,'RLP01','Brand03','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B3CRF04',TO_DATE('09/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B3CRF05',TO_DATE('09/30/2021','MM/DD/YYYY'),'A03',10 ,'RLP01','Brand03','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR01',TO_DATE('09/30/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR02',TO_DATE('09/30/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR03',TO_DATE('09/30/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR04',TO_DATE('09/30/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR05',TO_DATE('09/30/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR06',TO_DATE('10/15/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR07',TO_DATE('10/15/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1CLR08',TO_DATE('10/15/2021','MM/DD/YYYY'),'A02',10 ,'TLP01','Brand01','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B2CP01',TO_DATE('10/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B2CP02',TO_DATE('10/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B2CP03',TO_DATE('10/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0005','C0005');
insert into Activity_Transactions(activity_transaction_id, activity_transaction_date, activity_type, gained_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B2CP04',TO_DATE('10/10/2021','MM/DD/YYYY'),'A01',40 ,'TLP02','Brand02','W0005','C0005');
/*Reward_Transactions*/
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B1RTGC01',TO_DATE('07/02/2021','07/02/2021'),'R01', 80,'TLP01','Brand01','W0001','C0001');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2RTGC01',TO_DATE('08/25/2021','08/25/2021'),'R01', 120,'TLP02','Brand02','W0001','C0001');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C1B2RTFP01',TO_DATE('10/20/2021','10/20/2021'),'R02', 90,'TLP02','Brand02','W0001','C0001');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C2B1RTFP01',TO_DATE('09/01/2021','09/01/2021'),'R02', 70,'TLP01','Brand01','W0002','C0002');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2RTFP01',TO_DATE('08/26/2021','08/26/2021'),'R02', 90,'TLP02','Brand02','W0003','C0003');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C3B2RTFP02',TO_DATE('10/18/2021','10/18/2021'),'R02', 90,'TLP02','Brand02','W0003','C0003');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B2RTGC01',TO_DATE('10/11/2021','10/11/2021'),'R01', 120,'TLP02','Brand02','W0005','C0005');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1RTGC01',TO_DATE('10/11/2021','10/11/2021'),'R01', 80,'TLP01','Brand01','W0005','C0005');
insert into Reward_Transactions(reward_transaction_id, reward_transaction_date, reward_code, redeem_points, loyalty_id, brand_id, wallet_id,customer_id) values('C5B1RTFP01',TO_DATE('10/17/2021','10/17/2021'),'R02', 70,'TLP01','Brand01','W0005','C0005');

/*Reward_product*/
insert into Reward_product(product_code,product_quantity,customer_id,reward_transaction_id, loyalty_id) values('C1FP01',1,'C0001','C1B2RTFP01','TLP02');
insert into Reward_product(product_code,product_quantity,customer_id,reward_transaction_id, loyalty_id) values('C2FP01',1,'C0002','C2B1RTFP01','TLP01');
insert into Reward_product(product_code,product_quantity,customer_id,reward_transaction_id, loyalty_id) values('C3FP01',1,'C0003','C3B2RTFP01','TLP02');
insert into Reward_product(product_code,product_quantity,customer_id,reward_transaction_id, loyalty_id) values('C3FP02',1,'C0003','C3B2RTFP02','TLP02');
insert into Reward_product(product_code,product_quantity,customer_id,reward_transaction_id, loyalty_id) values('C5FP01',1,'C0005','C5B1RTFP01','TLP01');

/*Reward_GiftCard*/
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('C1GC01',TO_DATE('07/02/2021','07/02/2021'),'C0001','C1B1RTGC01','TLP01');
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('C1GC02',TO_DATE('08/25/2021','08/25/2021'),'C0001','C1B2RTGC01','TLP02');
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('C5GC01',TO_DATE('10/11/2021','10/11/2021'),'C0005','C5B2RTGC01','TLP02');
insert into Reward_GiftCard(giftcard_code, expiry_date, customer_id, reward_transaction_id, loyalty_id) values('C5GC02',TO_DATE('10/11/2021','10/11/2021'),'C0005','C5B1RTGC01','TLP01');

/*Customer_Redeem*/
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C1B1RTGC01','C1RD01','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C1B2RTGC01','C1RD02','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C1B2RTFP01','C1RD03','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C2B1RTFP01','C2RD01','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C3B2RTFP01','C3RD01','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C3B2RTFP02','C3RD02','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C5B2RTGC01','C5RD01','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C5B1RTGC01','C5RD02','1');
insert into Customer_Redeem(reward_transaction_id, redeem_id, reward_instances) values('C5B1RTGC01','C5RD03','1');

/*Customer_Reviews*/
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C1RV01','TLP01',TO_DATE('06/18/2021','06/18/2021'),'Good','C1B1CLR01','C0001');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C1RV02','TLP01',TO_DATE('06/18/2021','06/18/2021'),'Fantastic','C1B1CLR02','C0001');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C2RV01','TLP01',TO_DATE('08/05/2021','08/05/2021'),'Awesome','C2B1CLR01','C0002');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV01','TLP01',TO_DATE('09/30/2021','09/30/2021'),'Bad','C5B1CLR01','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV02','TLP01',TO_DATE('09/30/2021','09/30/2021'),'Awesome','C5B1CLR02','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV03','TLP01',TO_DATE('09/30/2021','09/30/2021'),'Fantastic','C5B1CLR03','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV04','TLP01',TO_DATE('09/30/2021','09/30/2021'),'Good','C5B1CLR04','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV05','TLP01',TO_DATE('09/30/2021','09/30/2021'),'Bad','C5B1CLR05','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV06','TLP01',TO_DATE('10/15/2021','10/15/2021'),'Awesome','C5B1CLR06','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV07','TLP01',TO_DATE('10/15/2021','10/15/2021'),'Good','C5B1CLR07','C0005');
insert into Customer_Reviews(review_id, loyalty_id, review_date, review_content, activity_transaction_id, customer_id) values('C5RV08','TLP01',TO_DATE('10/15/2021','10/15/2021'),'Fantastic','C5B1CLR08','C0005');

