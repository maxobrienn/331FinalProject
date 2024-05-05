
-- returns unpaid balance for a patient to a pharmacy employee
CREATE OR REPLACE FUNCTION GetUnpaidBalanceForPatient(
    patient_id HealthCareManagement_PRESCRIPTIONBALANCE.PATIENT_ID%TYPE) 
    RETURN DECIMAL IS
    unpaid_balance DECIMAL(10, 2);
BEGIN
    SELECT SUM(PR.PRICE * (1 - IC.PERCENT))
    INTO unpaid_balance
    FROM HealthCareManagement_PRESCRIPTION PR
    JOIN HealthCareManagement_PATIENT P ON PR.PATIENT_ID = P.PATIENT_ID
    JOIN HealthCareManagement_INSURANCECOMPANY IC ON P.INSURANCE_ID = IC.INSURANCE_ID
    WHERE PR.PATIENT_ID = GetUnpaidBalanceForPatient.patient_id;

    RETURN unpaid_balance;
END;

-- currently returns the entire sum of PB.PatientBalance and doesn't filter
SELECT GetUnpaidBalanceForPatient('PAT001') FROM DUAL;


-- returns unpaid balance for a insurance company to a pharmacy employee
CREATE OR REPLACE FUNCTION GetUnpaidBalanceForInsuranceCompany(
    insurance_id HealthCareManagement_PRESCRIPTIONBALANCE.INSURANCE_ID%TYPE,
    pharmacy_id HealthCareManagement_FILLS.PHARMACY_ID%TYPE
) RETURN DECIMAL IS
    unpaid_balance DECIMAL(10, 2);
BEGIN
    SELECT SUM(PB.InsuranceBalance) INTO unpaid_balance
    FROM HealthCareManagement_PRESCRIPTIONBALANCE PB
    JOIN HealthCareManagement_FILLS F ON PB.PRESCRIPTION_ID = F.PRESCRIPTION_ID
    WHERE PB.INSURANCE_ID = GetUnpaidBalanceForInsuranceCompany.insurance_id 
    AND F.PHARMACY_ID = GetUnpaidBalanceForInsuranceCompany.pharmacy_id;

    RETURN unpaid_balance;
END;


-- currently returns the entire sum of PB.InsuranceBalance and doesn't filter
SELECT GetUnpaidBalanceForInsuranceCompany('INS001', 'PHRM001') FROM DUAL;


