CREATE VIEW HealthCareManagement_SEEDIAGNOSIS AS
SELECT
    p.PATIENT_ID, 
    p.FIRST || ' ' || p.LAST AS Patient_Name,
    p.DOB, 
    d.DIAGNOSES 
FROM 
    HealthCareManagement_PATIENT p
JOIN 
    HealthCareManagement_DIAGNOSES d ON p.PATIENT_ID = d.PATIENT_ID;

SELECT * FROM HealthCareManagement_SEEDIAGNOSIS