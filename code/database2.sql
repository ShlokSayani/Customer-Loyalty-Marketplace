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