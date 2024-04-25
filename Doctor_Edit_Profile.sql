-- Procedure to edit certaint fields about a doctors info
-- Ellie Smith


CREATE OR REPLACE PROCEDURE Edit_Doctor_Info(d_doctor_id IN CHAR,
                                            d_last IN VARCHAR,
                                            d_first IN VARCHAR,
                                            d_email IN VARCHAR,
                                            d_specialization IN VARCHAR,
                                            d_office_number IN CHAR,
                                            d_password IN CHAR)
AS
BEGIN
    -- Update the specified columns for the patient
    UPDATE HealthCareManagement_DOCTOR
    SET 
        LAST = d_last,
        FIRST = d_first,
        EMAIL = d_email,
        PASSWORD = d_password,
        SPECIALIZATION = d_specialization,
        OFFICE_NUMBER = d_office_number
    WHERE DOCTOR_ID = d_doctor_id;
    
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Patient information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating patient information: ' || SQLERRM);
END;



--TEST STATEMENTS:

SELECT * FROM HealthCareManagement_DOCTOR WHERE DOCTOR_ID = 'DOC001';
-- OUTPUT:
--DOCTOR_ID     LAST    FIRST   EMAIL                   PASSWORD            SPECIALIZATION  OFFICE_NUMBER
--DOC001    	Smith	John	john.smith@hospital.com	thsbaibniincd68n    Cardiology      101

-- Call the procedure to update the doctor's information
EXEC Edit_Doctor_Info('DOC001', 'Smith', 'Ellie', 'updated_email@example.com', 'Physicians Assistant', '102', 'newPassword');


SELECT * FROM HealthCareManagement_DOCTOR WHERE DOCTOR_ID = 'DOC001';
-- NEW OUTPUT WITH UPDATED CHANGES:
--DOCTOR_ID     LAST    FIRST   EMAIL                       PASSWORD        SPECIALIZATION          OFFICE_NUMBER
--DOC001    	Smith	Ellie	updated_email@example.com	newPassword     Physicians Assistant     102


