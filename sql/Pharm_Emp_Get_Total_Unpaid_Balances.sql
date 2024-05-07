
-- returns unpaid balance for a patient to a pharmacy employee
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


-- returns unpaid balance for a insurance company to a pharmacy employee
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

SELECT * from HealthCareManagement_PRESCRIPTIONBALANCE;
--PRESRIPTION_ID    PATIENT_ID  INSURANCE_ID    INSURANCEBALANCE    PATIENTBALANCE
--PRSC001	        PAT001	    INS001	        2.5	                22.5
--PRSC002	        PAT002	    INS002	        3.75	            11.25
--PRSC003	        PAT003	    INS003	        12	                18
--PRSC004	        PAT004	    INS004	        3.3	                18.7
--PRSC005	        PAT005	    INS005	        4.5	                40.5
--PRSC006	        PAT006	    INS006	        0	                10

SELECT GetUnpaidBalanceForPatient('PAT001') FROM DUAL;
--23

SELECT GetUnpaidBalanceForInsuranceCompany('INS003') FROM DUAL;
--12

