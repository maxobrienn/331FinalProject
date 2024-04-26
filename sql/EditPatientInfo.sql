create or replace PROCEDURE Edit_Patient_Info(
    p_patient_id IN CHAR,
    p_phone_number IN VARCHAR,
    p_email IN VARCHAR,
    p_street IN VARCHAR,
    p_city IN VARCHAR,
    p_state IN CHAR,
    p_zip_code IN CHAR,
    p_insurance_id IN CHAR,
    p_sex IN VARCHAR
)
AS
BEGIN
    -- Update the specified columns for the patient
    UPDATE HealthCareManagement_PATIENT
    SET 
        PHONE_NUMBER = p_phone_number,
        EMAIL = p_email,
        STREET = p_street,
        CITY = p_city,
        STATE = p_state,
        ZIP_CODE = p_zip_code,
        INSURANCE_ID = p_insurance_id,
        SEX = p_sex
    WHERE PATIENT_ID = p_patient_id;

    -- Commit the transaction
    COMMIT;

    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Patient information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating patient information: ' || SQLERRM);
END;
