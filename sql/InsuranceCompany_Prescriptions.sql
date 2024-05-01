
-- Create a view to show all prescriptions - including total unpaid balance on each
CREATE OR REPLACE VIEW Pharmacy_Prescriptions AS
SELECT      P.PATIENT_ID,
            P.LAST || ', ' || P.FIRST AS PATIENT_NAME,
            PC.INSURANCE_ID,
            F.PHARMACY_ID,
            SUM(PB.InsuranceBalance + PB.PatientBalance) AS AMOUNT_OWED
FROM        HealthCareManagement_PATIENT P
            JOIN    HealthCareManagement_PAYSFOR PC ON P.INSURANCE_ID = PC.INSURANCE_ID
            JOIN    HealthCareManagement_PRESCRIPTIONBALANCE PB ON P.PATIENT_ID = PB.PATIENT_ID
            JOIN    HealthCareManagement_FILLS F ON PB.PRESCRIPTION_ID = F.PRESCRIPTION_ID
GROUP BY    P.PATIENT_ID, P.LAST, P.FIRST, PC.INSURANCE_ID, F.PHARMACY_ID;
