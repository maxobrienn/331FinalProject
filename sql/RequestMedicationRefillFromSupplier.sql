

--These are place holders for the actual trigger which will be the press of a button in the UI
select *
from healthcaremanagement_medication;

CREATE OR REPLACE PROCEDURE UpdateSupplierQuantity(supplierID  IN varchar2,
                                                    amount IN char) 
as
begin
    UPDATE healthcaremanagement_medication
    SET quantity = amount
    WHERE supplier_id = supplierID;


END;
/

Exec UpdateSupplierQuantity('SUP001', '70')

select *
from healthcaremanagement_medication;

--CREATE OR REPLACE TRIGGER CheckQuantityTrigger
--BEFORE INSERT OR UPDATE ON healthcaremanagement_medication
--FOR EACH ROW
--WHEN (NEW.quantity < 10)
--
--BEGIN

------UpdateSupplierQuantity(:NEW.supplier_id);
--END;
--/

--Tests

--BEGIN
--   UpdateSupplierQuantity('SUP001');
 --   END;


--update healthcaremanagement_medication
--set quantity = 5
--where supplier_id = 'SUP001';







