
-- returns unpaid balance for a patient to a pharmacy employee
CREATE OR REPLACE FUNCTION GetUnpaidBalanceForPatient(
    patient_id HealthCareManagement_PRESCRIPTIONBALANCE.PATIENT_ID%TYPE,
    pharmacy_id HealthCareManagement_FILLS.PHARMACY_ID%TYPE
) RETURN DECIMAL IS
    unpaid_balance DECIMAL(10, 2);
BEGIN
    SELECT SUM(PB.PatientBalance)
    INTO unpaid_balance
    FROM HealthCareManagement_PRESCRIPTIONBALANCE PB
    JOIN HealthCareManagement_FILLS F ON PB.PRESCRIPTION_ID = F.PRESCRIPTION_ID
    WHERE F.PHARMACY_ID = pharmacy_id AND PB.PATIENT_ID = patient_id;

    RETURN unpaid_balance;
END;

-- currently returns the entire sum of PB.PatientBalance and doesn't filter
SELECT GetUnpaidBalanceForPatient('PAT001', 'PHRM001') FROM DUAL;


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
    WHERE PB.INSURANCE_ID = insurance_id AND F.PHARMACY_ID = pharmacy_id;

    RETURN unpaid_balance;
END;

-- currently returns the entire sum of PB.InsuranceBalance and doesn't filter
SELECT GetUnpaidBalanceForInsuranceCompany('INS001', 'PHRM001') FROM DUAL;


