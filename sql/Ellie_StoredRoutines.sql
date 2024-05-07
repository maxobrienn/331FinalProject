-- Ellie Smith's Stored Routines for CSCI 331 Final Project


-- Returns unpaid balance for a patient to a pharmacy employee
CREATE OR REPLACE FUNCTION GetUnpaidBalanceForPatient(
    patient_id HealthCareManagement_PRESCRIPTIONBALANCE.PATIENT_ID%TYPE) 
    RETURN DECIMAL IS
    unpaid_balance DECIMAL(10, 2);
BEGIN
    SELECT CAST(SUM(PR.PRICE * (1 - IC.PERCENT)) AS DECIMAL(10, 2))
    INTO unpaid_balance
    FROM HealthCareManagement_PRESCRIPTION PR
    JOIN HealthCareManagement_PATIENT P ON PR.PATIENT_ID = P.PATIENT_ID
    JOIN HealthCareManagement_INSURANCECOMPANY IC ON P.INSURANCE_ID = IC.INSURANCE_ID
    WHERE PR.PATIENT_ID = GetUnpaidBalanceForPatient.patient_id;
    RETURN unpaid_balance;
END;
/

-- Returns unpaid balance for a insurance company to a pharmacy employee
CREATE OR REPLACE FUNCTION GetUnpaidBalanceForInsuranceCompany(
   insurance_id HealthCareManagement_PRESCRIPTIONBALANCE.INSURANCE_ID%TYPE) 
    RETURN DECIMAL IS
    unpaid_balance DECIMAL(10, 2);
BEGIN
    SELECT CAST(SUM(PB.InsuranceBalance) AS DECIMAL(10, 2)) INTO unpaid_balance
    FROM HealthCareManagement_PRESCRIPTIONBALANCE PB
    WHERE PB.INSURANCE_ID = GetUnpaidBalanceForInsuranceCompany.insurance_id;
    RETURN unpaid_balance;
END;
/

-- Test statements for functions:
-- SELECT * from HealthCareManagement_PRESCRIPTIONBALANCE;
--PRESRIPTION_ID    PATIENT_ID  INSURANCE_ID    INSURANCEBALANCE    PATIENTBALANCE
--PRSC001	        PAT001	    INS001	        2.5	                22.5
--PRSC002	        PAT002	    INS002	        3.75	            11.25
--PRSC003	        PAT003	    INS003	        12	                18
--PRSC004	        PAT004	    INS004	        3.3	                18.7
--PRSC005	        PAT005	    INS005	        4.5	                40.5
--PRSC006	        PAT006	    INS006	        0	                10
--SELECT GetUnpaidBalanceForPatient('PAT001') FROM DUAL;
--23
--SELECT GetUnpaidBalanceForInsuranceCompany('INS003') FROM DUAL;
--12



-- Create a view to show insurance companies all of their prescrptions and the outstanding balance on each
CREATE OR REPLACE VIEW Insurance_Company_Covered_Patients AS
SELECT      P.PATIENT_ID,
            P.LAST || ', ' || P.FIRST AS PATIENT_NAME,
            PB.PRESCRIPTION_ID,
            P.INSURANCE_ID,
            SUM(PB.InsuranceBalance) AS AMOUNT_OWED
FROM        HealthCareManagement_PATIENT P
            JOIN    HealthCareManagement_PRESCRIPTIONBALANCE PB ON P.PATIENT_ID = PB.PATIENT_ID
            JOIN    HealthCareManagement_PRESCRIPTION PR ON P.PATIENT_ID = PR.PATIENT_ID
GROUP BY    P.PATIENT_ID, P.LAST, P.FIRST, PB.PRESCRIPTION_ID, P.INSURANCE_ID;
/

-- Create a view to show patients all of their prescrptions and the outstanding balance on each
CREATE OR REPLACE VIEW Patient_Prescription_Balance AS
SELECT      P.PATIENT_ID,
            PR.PRESCRIPTION_ID,
            PR.DATE_ISSUED,
            PR.PRESCRIPTION_NAME,
            SUM(PB.PatientBalance) AS AMOUNT_OWED
FROM        HealthCareManagement_PATIENT P
            JOIN    HealthCareManagement_PRESCRIPTIONBALANCE PB ON P.PATIENT_ID = PB.PATIENT_ID
            JOIN    HealthCareManagement_PRESCRIPTION PR ON P.PATIENT_ID = PR.PATIENT_ID
GROUP BY    P.PATIENT_ID, PR.PRESCRIPTION_ID, PR.DATE_ISSUED, PR.PRESCRIPTION_NAME;
/

-- Test statements for views:
--SELECT * FROM Insurance_Company_Covered_Patients WHERE INSURANCE_ID = 'INS001';
--PATIENT_ID    PATIENT_NAME    PRESCRIPTION_ID INSURANCE_ID    AMOUNT_OWED
--PAT001	    Doe, Jane	    PRSC001	        INS001	        0
--SELECT * FROM Patient_Prescription_Balance WHERE PATIENT_ID = 'PAT001';
--PATIENT_ID    PRESCRPTION_ID  DATE_ISSUED PRESCRPTION_NAME    AMOUNT_OWED
--PAT001	    PRSC001	        01-JAN-23	Amoxicillin	        2.5



--THIS TRIGGER CHANGES THE PRESCRIPTION PRICE AFTER A PAYMENT HAS BEEN MADE BY THE PATIENT
CREATE or REPLACE TRIGGER ChangePrescriptionPriceAfterPatientPayment
    AFTER INSERT ON HealthCareManagement_PATIENTPAYMENT
    For Each Row
BEGIN
    UPDATE  HealthCareManagement_PRESCRIPTIONBALANCE
    SET     PATIENTBALANCE=PATIENTBALANCE-:NEW.AMOUNT
    WHERE   PRESCRIPTION_ID=:NEW.PRESCRIPTION_ID;
END;
/

--THIS TRIGGER CHANGES THE PRESCRIPTION PRICE AFTER A PAYMENT HAS BEEN MADE BY THE INSURANCE COMPANY
CREATE or REPLACE TRIGGER ChangePrescriptionPriceAfterInsurancePayment
    AFTER INSERT ON HealthCareManagement_INSURANCEPAYMENT
    For Each Row
BEGIN
    UPDATE  HealthCareManagement_PRESCRIPTIONBALANCE
    SET     INSURANCEBALANCE=INSURANCEBALANCE-:NEW.AMOUNT
    WHERE   PRESCRIPTION_ID=:NEW.PRESCRIPTION_ID;
END;
/

--TEST STATEMENTS:
--SELECT * FROM HealthCareManagement_PRESCRIPTIONBALANCE;
--INSERT INTO HealthCareManagement_PATIENTPAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, PATIENT_ID, PRESCRIPTION_ID)
--        VALUES ('PAY001', TO_DATE('2023-06-15', 'YYYY-MM-DD'), 15.00, 'PAT001', 'PRSC001');
--INSERT INTO HealthCareManagement_INSURANCEPAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, INSURANCE_ID, PRESCRIPTION_ID)
--        VALUES ('PAY001', TO_DATE('2023-06-15', 'YYYY-MM-DD'), 2.00, 'INS001', 'PRSC001');      
--SELECT * FROM HealthCareManagement_PRESCRIPTIONBALANCE;
--FIRST SELECT:
--PRESCRIPTION_ID   PATIENT_ID      INSURANCE_ID   INSURANCEBAL PATIENTBAL 
--PRSC001	        PAT001	        INS001	        2.5	        22.5
--PRSC002	        PAT002	        INS002	        3.75	    11.25
--PRSC003	        PAT003	        INS003	        12	        18
--PRSC004	        PAT004	        INS004	        3.3	        18.7
--PRSC005	        PAT005	        INS005	        4.5	        40.5
--PRSC006	        PAT006	        INS006	        0	        10
--AFTER INSERT:
--PRESCRIPTION_ID   PATIENT_ID      INSURANCE_ID   INSURANCEBAL PATIENTBAL 
--PRSC001	        PAT001	        INS001	        0.5	        7.5
--PRSC002	        PAT002	        INS002	        3.75	    11.25
--PRSC003	        PAT003	        INS003	        12	        18
--PRSC004	        PAT004	        INS004	        3.3	        18.7
--PRSC005	        PAT005	        INS005	        4.5	        40.5
--PRSC006	        PAT006	        INS006	        0	        10



-- Procedure that allows suppliers to add and remove medications.
CREATE OR REPLACE PROCEDURE Add_Medication (
    p_medication_name IN VARCHAR2,
    p_quantity IN NUMBER,
    p_supplier_id IN VARCHAR2
) AS
BEGIN
    INSERT INTO HealthCareManagement_MEDICATION (NAME, QUANTITY, SUPPLIER_ID)
    VALUES (p_medication_name, p_quantity, p_supplier_id);
END Add_Medication;

--TEST STATEMENTS:
--SELECT * FROM HealthCareManagement_MEDICATION;
--NAME          QUANITITY  SUPPLIER_ID
--Amoxicillin	200  	    SUP001
--Ibuprofen	    200  	    SUP002
--Metformin	    150  	    SUP003
--Lisinopril	120  	    SUP004
--Atorvastatin	80   	    SUP005
--Aspirin	    80   	    SUP005

--EXEC Add_Medication('SampleMed', 100, 'SUP001');
--SELECT * FROM HealthCareManagement_MEDICATION;
--NAME          QUANITITY  SUPPLIER_ID
--Amoxicillin	200  	    SUP001
--Ibuprofen	    200  	    SUP002
--Metformin	    150  	    SUP003
--Lisinopril	120  	    SUP004
--Atorvastatin	80   	    SUP005
--Aspirin	    80   	    SUP005
--SampleMed	    100  	    SUP001

