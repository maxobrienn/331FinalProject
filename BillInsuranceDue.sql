--Bill
create or replace function Bill_Insurance(insure_id varchar, pay_id varchar) return int as
due int := 0;
begin
select p.amount into due
from healthcaremanagement_payment P
where insure_id = P.insurance_id and pay_id = p.payment_id;


Return due;
end;


--select Bill_Insurance('INS001', 'PAT001') from dual;

