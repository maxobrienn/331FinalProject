--Procedure to edit certaint fields about a patients info
-- Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_Patient_Info(
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
/

--Test procedure for editing a patient profile
SELECT * FROM HealthCareManagement_PATIENT WHERE PATIENT_ID = 'PAT001';

-- Call the procedure to update the patient's information
BEGIN
    Edit_Patient_Info(
        p_patient_id => 'PAT001',
        p_phone_number => '555-555-5555', 
        p_email => 'updated_email@example.com', 
        p_street => '789 Updated St', 
        p_city => 'Updated City', 
        p_state => 'NY', 
        p_zip_code => '54321', 
        p_insurance_id => 'INS-UPDATE', 
        p_sex => 'Female' 
    );
END;
/

-- After running the procedure, select the patient's information again to verify the changes
SELECT * FROM HealthCareManagement_PATIENT WHERE PATIENT_ID = 'PAT001';


--View: Login
--This view will validate the login credentials provided by the user.
--Matt DeRosa
CREATE OR REPLACE VIEW User_Login AS
SELECT patient_ID, email, PASSWORD
FROM healthcaremanagement_patient;

--Function for Creating a new patient Id when they create an account
--Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_Patient_ID
RETURN CHAR IS
    l_prefix CHAR(3) := 'PAT';
    l_suffix CHAR(7);
BEGIN
    -- Generate a random number between 1000000 and 9999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000, 9999999)));

    -- Concatenate prefix and suffix to form the patient ID
    RETURN l_prefix || l_suffix;
END;
/
--Trigger to update the patient table when a new patient is created
--uses the function Generategenerate_random_patient_id to create an id for a patient
--Matt DeRosa
CREATE OR REPLACE TRIGGER create_Account
BEFORE INSERT ON HealthCareManagement_Patient
FOR EACH ROW
BEGIN
    :NEW.PATIENT_ID := Generate_Random_Patient_ID();
    :NEW.DOB := :NEW.DOB; -- DOB
    :NEW.STREET := :NEW.STREET; -- STREET
    :NEW.CITY := :NEW.CITY; -- CITY
    :NEW.STATE := :NEW.STATE; -- STATE
    :NEW.ZIP_CODE := :NEW.ZIP_CODE; -- ZIP_CODE
    :NEW.EMAIL := :NEW.EMAIL; -- EMAIL
    :NEW.PHONE_NUMBER := :NEW.PHONE_NUMBER; -- PHONE_NUMBER
    :NEW.LAST := :NEW.LAST; -- LAST
    :NEW.FIRST := :NEW.FIRST; -- FIRST
    :NEW.SEX := :NEW.SEX; -- SEX
    :NEW.INSURANCE_ID := :NEW.INSURANCE_ID; -- INSURANCE_ID
    :NEW.PASSWORD := :NEW.PASSWORD; -- PASSWORD
END;
/
--pretest output
-- Selecting all rows from the healthcaremanagement_patient table
--Matt DeRosa
SELECT * FROM HealthCareManagement_Patient;

-- Inserting a new patient without specifying the patient ID
--INSERT INTO HealthCareManagement_Patient (DOB, STREET, CITY, STATE, ZIP_CODE, EMAIL, PHONE_NUMBER, LAST, FIRST, SEX, INSURANCE_ID, PASSWORD)
--VALUES (TO_DATE('1990-01-01','YYYY-MM-DD'), '1234 Life St', 'Atlanta', 'NY', '12345', 'test@email.com', '123-480-4387', 'Doe', 'John', 'Male', 'INS001', 'password123');

-- Selecting all rows from the healthcaremanagement_patient table to test the output
SELECT * FROM HealthCareManagement_Patient;




-- Create a view to provide patients with information about their appointments, 
-- including the name of the doctor, appointment date, and notes.
--Matt DeRosa
CREATE OR REPLACE VIEW Patient_Appointment_Info AS
SELECT D.LAST || ', ' || D.FIRST AS DOCTOR_NAME,
       A.APPOINTMENT_DATE,
       A.NOTE
FROM HealthCareManagement_APPOINTMENT A
JOIN HealthCareManagement_DOCTOR D ON A.DOCTOR_ID = D.DOCTOR_ID;

--Test Patient_Appointment_Info
Select * From Patient_Appointment_Info

