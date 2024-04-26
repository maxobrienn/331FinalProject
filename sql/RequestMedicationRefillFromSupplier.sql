

--These are place holders for the actual trigger which will be the press of a button in the UI
select quantity
from healthcaremanagement_medication;

CREATE OR REPLACE PROCEDURE UpdateSupplierQuantity(supplier_id varchar) as
begin
    UPDATE healthcaremanagement_medication M
    SET quantity = 100
    WHERE supplier_id = M.supplier_id;


END;
/

CREATE OR REPLACE TRIGGER CheckQuantityTrigger
BEFORE INSERT OR UPDATE ON healthcaremanagement_medication
FOR EACH ROW
WHEN (NEW.quantity < 10)

BEGIN

UpdateSupplierQuantity(:NEW.supplier_id);
END;
/

--Tests

BEGIN
   UpdateSupplierQuantity('SUP001');
    END;


update healthcaremanagement_medication
set quantity = 5
where supplier_id = 'SUP001';






