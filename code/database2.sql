CREATE OR REPLACE FUNCTION givesubtr(ps number, q number)
return number is
BEGIN
   return ps - q;
END;
/

create or replace trigger after_redeem
after insert on Reward_Transactions
for each row
declare 
    currentpoints number; 
begin       
    select TO_NUMBER(customer_points) into currentpoints from Customer_program where customer_id = :new.customer_id AND loyalty_id = :new.loyalty_id;
    update Customer_program set customer_points = givesubtr(currentpoints, TO_NUMBER(:new.redeem_points)) where customer_id = :new.customer_id and loyalty_id = :new.loyalty_id;
end;
/

