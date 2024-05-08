-- Mason Meyer's Stored Routines for CSCI 331 Final Project

create or replace FUNCTION Add_Appointment_Note
(
    p_patient_id IN HealthCareManagement_APPOINTMENT.PATIENT_ID%TYPE,
    p_doctor_id IN HealthCareManagement_APPOINTMENT.DOCTOR_ID%TYPE,
    p_note IN HealthCareManagement_APPOINTMENT.NOTE%TYPE,
    p_appointment_date IN HealthCareManagement_APPOINTMENT.APPOINTMENT_DATE%TYPE
)
RETURN VARCHAR2
IS
BEGIN
    -- Insert new appointment note
    INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE)
    VALUES (p_patient_id, p_doctor_id, p_note, p_appointment_date);

    -- Commit the transaction to save changes
    COMMIT;

    RETURN 'Appointment note added successfully.';
EXCEPTION
    WHEN OTHERS THEN
        -- In case of any exception, rollback changes and return error message
        ROLLBACK;
        RETURN 'Error adding appointment note: ' || SQLERRM;
END;
/

CREATE OR REPLACE PROCEDURE Insert_Prescription (
    p_prescription_id IN HealthCareManagement_PRESCRIPTION.PRESCRIPTION_ID%TYPE,
    p_date_issued IN HealthCareManagement_PRESCRIPTION.DATE_ISSUED%TYPE DEFAULT SYSDATE,
    p_prescription_name IN HealthCareManagement_PRESCRIPTION.PRESCRIPTION_NAME%TYPE,
    p_dosage IN HealthCareManagement_PRESCRIPTION.DOSAGE%TYPE,
    p_refills_remaining IN HealthCareManagement_PRESCRIPTION.REFILLS_REMAINING%TYPE,
    p_price IN HealthCareManagement_PRESCRIPTION.PRICE%TYPE,
    p_quantity IN HealthCareManagement_PRESCRIPTION.QUANTITY%TYPE,
    p_doctor_id IN HealthCareManagement_PRESCRIPTION.DOCTOR_ID%TYPE,
    p_patient_id IN HealthCareManagement_PRESCRIPTION.PATIENT_ID%TYPE
) AS
BEGIN
    INSERT INTO HealthCareManagement_PRESCRIPTION (
        PRESCRIPTION_ID, DATE_ISSUED, PRESCRIPTION_NAME, DOSAGE, REFILLS_REMAINING, PRICE, QUANTITY, DOCTOR_ID, PATIENT_ID
    ) VALUES (
        p_prescription_id, COALESCE(p_date_issued, SYSDATE), p_prescription_name, p_dosage, 
        p_refills_remaining, p_price, p_quantity, p_doctor_id, p_patient_id
    );
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END;
/

-- Create a trigger that ensures DATE_ISSUED is set to SYSDATE if not provided
CREATE OR REPLACE TRIGGER Ensure_Date_Issued
BEFORE INSERT ON HealthCareManagement_PRESCRIPTION
FOR EACH ROW
WHEN (NEW.DATE_ISSUED IS NULL)
BEGIN
    :NEW.DATE_ISSUED := SYSDATE;
END;
/

-- Example usage of the Insert_Prescription procedure
BEGIN
    Insert_Prescription(
        p_prescription_id => 'RX102030',
        p_date_issued => NULL, -- Trigger will set this to SYSDATE
        p_prescription_name => 'Ibuprofen',
        p_dosage => '200mg',
        p_refills_remaining => '10',
        p_price => 8.99,
        p_quantity => '30',
        p_doctor_id => 'DOC001', -- Assume this is a valid doctor ID
        p_patient_id => 'PAT001'  -- Assume this is a valid patient ID
    );
END;
/

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
/


    