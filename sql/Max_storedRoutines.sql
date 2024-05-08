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


--SELECT * FROM HealthCareManagement_SEEDIAGNOSIS;



CREATE OR REPLACE PROCEDURE Edit_Patient_Preferred_Doctor(
    p_patient_id IN VARCHAR,
    p_preferred_doctor_id IN VARCHAR
)
AS
BEGIN
    -- Update the preferred doctor for the patient
    UPDATE HealthCareManagement_PATIENT
    SET 
        PREFERRED_DOCTOR = p_preferred_doctor_id
    WHERE PATIENT_ID = p_patient_id;

    -- Commit the transaction
    COMMIT;

    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Patient preferred doctor updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating patient preferred doctor: ');
END;
/

--EXEC Edit_Patient_Preferred_Doctor('PAT001', 'Williams');


CREATE OR REPLACE FUNCTION DoctorAppointmentCount(
    doctorId VARCHAR2,
    appointmentDate VARCHAR2 
)
RETURN INT
IS
    appointmentCount INT;
BEGIN
    SELECT COUNT(*)
    INTO appointmentCount
    FROM HealthCareManagement_APPOINTMENT
    WHERE DOCTOR_ID = doctorId
    AND TO_CHAR(APPOINTMENT_DATE, 'DD-MON-YY') = appointmentDate;

    RETURN appointmentCount;
END;
/

-- Test statement
--DECLARE
    --appointmentTotal INT;
    --specificDate VARCHAR2(9) := '01-JUL-23'; 
--BEGIN
    --appointmentTotal := DoctorAppointmentCount('DOC002', specificDate);
    --DBMS_OUTPUT.PUT_LINE('Total Appointments for Doctor DOC002 on ' || specificDate || ': ' || appointmentTotal);
--END;
--/





