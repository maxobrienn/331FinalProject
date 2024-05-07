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
        DBMS_OUTPUT.PUT_LINE('Error updating patient preferred doctor: ' || SQLERRM);
END;
