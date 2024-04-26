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
