CREATE OR REPLACE VIEW DoctorPatientDiagnosisView AS 
SELECT 
    P.PATIENT_ID,
    P.LAST AS LAST,
    P.FIRST AS FIRST,
    P.DOB AS DOB,
    P.SEX AS SEX,
    D.DIAGNOSES AS DIAGNOSIS,
    D.DOCTOR_ID,
    P.PHONE_NUMBER, -- Ensure PHONE_NUMBER is included in the view
    P.EMAIL -- Include EMAIL as well if needed
FROM 
    HealthCareManagement_PATIENT P
JOIN 
    HealthCareManagement_DIAGNOSES D ON P.PATIENT_ID = D.PATIENT_ID;

SELECT 
    p.PATIENT_ID, 
    p.LAST, 
    p.FIRST, 
    p.EMAIL, 
    p.PHONE_NUMBER, 
    p.DIAGNOSIS 
FROM 
    DoctorPatientDiagnosisView p
WHERE 
    p.DOCTOR_ID = 'DOC001';
