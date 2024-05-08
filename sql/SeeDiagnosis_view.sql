CREATE OR REPLACE VIEW HealthCareManagement_SEEDIAGNOSIS AS
SELECT
    p.PATIENT_ID,
    p.FIRST || ' ' || p.LAST AS Patient_Name,
    p.DOB,
    p.EMAIL,
    p.PHONE_NUMBER,
    p.SEX,
    d.DIAGNOSES,
    a.APPOINTMENT_DATE AS Diagnosis_Date
FROM 
    HealthCareManagement_PATIENT p
LEFT JOIN 
    HealthCareManagement_APPOINTMENT a ON p.PATIENT_ID = a.PATIENT_ID
LEFT JOIN 
    HealthCareManagement_DIAGNOSES d ON p.PATIENT_ID = d.PATIENT_ID;


SELECT * FROM HealthCareManagement_SEEDIAGNOSIS;
