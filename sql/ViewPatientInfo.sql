SELECT
    p.PATIENT_ID,
    p.FIRST || ' ' || p.LAST AS Patient_Name,
    p.DOB,
    p.STREET,
    p.CITY,
    p.STATE,
    p.ZIP_CODE,
    p.EMAIL,
    p.PHONE_NUMBER,
    p.SEX,
    d.DIAGNOSES AS Diagnosis,
    a.NOTE AS Appointment_Note,
    a.APPOINTMENT_DATE
FROM
    HealthCareManagement_PATIENT p
LEFT JOIN HealthCareManagement_DIAGNOSES d ON p.PATIENT_ID = d.PATIENT_ID
LEFT JOIN HealthCareManagement_APPOINTMENT a ON p.PATIENT_ID = a.PATIENT_ID
ORDER BY
    a.APPOINTMENT_DATE DESC;
