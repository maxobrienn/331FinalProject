
----View for Prescription Information
create or replace view Pharmacy_inventory as
    select *
    from HealthCareManagement_MEDICATION;  

select *
from Pharmacy_Inventory
/
-- View for Prescription Information
CREATE OR REPLACE VIEW Pharmacy_Inventory AS
SELECT *
FROM HealthCareManagement_MEDICATION;
>>>>>>> 6ff5d6793071beae175871ceb89accb22d355f79

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

-- Procedure to update medication quantity from supplier
CREATE OR REPLACE PROCEDURE UpdateSupplierQuantity(
    supplierID  IN VARCHAR2,
    amount IN NUMBER -- Change data type to NUMBER
) AS
BEGIN
    UPDATE healthcaremanagement_medication
    SET quantity = amount
    WHERE supplier_id = supplierID;
END;
/

-- Test the UpdateSupplierQuantity procedure
BEGIN
    UpdateSupplierQuantity('SUP001', 70); -- Update supplier with ID 'SUP001' to quantity 70
END;
/

select *
from healthcaremanagement_medication;
/
-- Query to view the Pharmacy_Inventory
SELECT * FROM Pharmacy_Inventory;

-- Query to view Pharmacy_Prescriptions for a specific pharmacy
SELECT * FROM Pharmacy_Prescriptions WHERE PHARMACY_ID = 'PHRM001';
>>>>>>> 6ff5d6793071beae175871ceb89accb22d355f79
