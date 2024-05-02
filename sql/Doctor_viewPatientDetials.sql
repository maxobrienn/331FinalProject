CREATE OR REPLACE VIEW DoctorPatientDiagnosisView AS 
SELECT 
    P.PATIENT_ID,
    P.LAST AS PATIENT_LAST_NAME,
    P.FIRST AS PATIENT_FIRST_NAME,
    P.DOB AS PATIENT_DATE_OF_BIRTH,
    P.SEX AS PATIENT_SEX,
    D.DIAGNOSES AS PATIENT_DIAGNOSIS,
    A.DOCTOR_ID
FROM 
    HealthCareManagement_PATIENT P
JOIN 
    HealthCareManagement_APPOINTMENT A ON P.PATIENT_ID = A.PATIENT_ID 
JOIN 
    HealthCareManagement_DIAGNOSES D ON P.PATIENT_ID = D.PATIENT_ID AND A.DOCTOR_ID = D.DOCTOR_ID;