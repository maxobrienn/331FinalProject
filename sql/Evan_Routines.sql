----View for Prescription Information
create or replace view Pharmacy_inventory as
    select *
    from HealthCareManagement_MEDICATION;  

select *
from Pharmacy_Inventory
/

-- Create a view to show all prescriptions - including total unpaid balance on each
CREATE OR REPLACE VIEW Pharmacy_Prescriptions AS
SELECT      F.PRESCRIPTION_ID,
            P.PATIENT_ID,
            P.LAST || ', ' || P.FIRST AS PATIENT_NAME,
            PC.INSURANCE_ID,
            F.PHARMACY_ID,
            SUM(PB.InsuranceBalance + PB.PatientBalance) AS AMOUNT_OWED
FROM        HealthCareManagement_PATIENT P
            JOIN    HealthCareManagement_PAYSFOR PC ON P.INSURANCE_ID = PC.INSURANCE_ID
            JOIN    HealthCareManagement_PRESCRIPTIONBALANCE PB ON P.PATIENT_ID = PB.PATIENT_ID
            JOIN    HealthCareManagement_FILLS F ON PB.PRESCRIPTION_ID = F.PRESCRIPTION_ID
GROUP BY    F.PRESCRIPTION_ID, P.PATIENT_ID, P.LAST, P.FIRST, PC.INSURANCE_ID, F.PHARMACY_ID
ORDER BY    AMOUNT_OWED DESC;
/

SELECT * FROM Pharmacy_Prescriptions WHERE PHARMACY_ID = 'PHRM001';4




--Update a maedication quantity from supplier
Exec UpdateSupplierQuantity('SUP001', '200')
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
/