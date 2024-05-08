-- Section: Stored Procedures

-- Procedure to edit certain fields about a patient's info
-- Author: Matt DeRosa
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


-- Procedure to edit certain fields about a doctor's info
-- Author: Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_Doctor_Info(
    p_doctor_id IN VARCHAR,
    p_email IN VARCHAR,
    p_last IN VARCHAR,
    p_first IN VARCHAR,
    p_specialization IN VARCHAR,
    p_office_number IN CHAR
)
AS
BEGIN
    -- Update the specified columns for the doctor
    UPDATE HealthCareManagement_DOCTOR
    SET 
        EMAIL = p_email,
        LAST = p_last,
        FIRST = p_first,
        SPECIALIZATION = p_specialization,
        OFFICE_NUMBER = p_office_number
    WHERE DOCTOR_ID = p_doctor_id;
    
    -- Commit the transaction
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Doctor information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating doctor information: ' || SQLERRM);
END;
/


-- Procedure to edit certain fields about an insurance company's info
-- Author: Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_InsuranceCompany_Info(
    p_insurance_id IN VARCHAR,
    p_phone_number IN VARCHAR,
    p_email IN VARCHAR,
    p_street IN VARCHAR,
    p_city IN VARCHAR,
    p_state IN CHAR,
    p_zip_code IN CHAR,
    p_insurance_name IN VARCHAR,
    p_percent IN DECIMAL
)
AS
BEGIN
    -- Update the specified columns for the insurance company
    UPDATE HealthCareManagement_INSURANCECOMPANY
    SET 
        PHONE_NUMBER = p_phone_number,
        EMAIL = p_email,
        STREET = p_street,
        CITY = p_city,
        STATE = p_state,
        ZIP_CODE = p_zip_code,
        INSURANCE_NAME = p_insurance_name,
        PERCENT = p_percent
    WHERE INSURANCE_ID = p_insurance_id;
    
    -- Commit the transaction
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Insurance company information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating insurance company information: ' || SQLERRM);
END;
/


-- Procedure to edit certain fields about a pharmacy's info
-- Author: Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_Pharmacy_Info(
    p_pharmacy_id IN VARCHAR,
    p_phone_number IN VARCHAR,
    p_email IN VARCHAR,
    p_street IN VARCHAR,
    p_city IN VARCHAR,
    p_state IN CHAR,
    p_zip_code IN CHAR,
    p_pharmacy_name IN VARCHAR
)
AS
BEGIN
    -- Update the specified columns for the pharmacy
    UPDATE HealthCareManagement_PHARMACY
    SET 
        PHONE_NUMBER = p_phone_number,
        EMAIL = p_email,
        STREET = p_street,
        CITY = p_city,
        STATE = p_state,
        ZIP_CODE = p_zip_code,
        PHARMACY_NAME = p_pharmacy_name
    WHERE PHARMACY_ID = p_pharmacy_id;
    
    -- Commit the transaction
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Pharmacy information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating pharmacy information: ' || SQLERRM);
END;
/


-- Procedure to edit certain fields about a pharmacy employee's info
-- Author: Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_PharmacyEmployee_Info(
    p_employee_id IN VARCHAR,
    p_phone_number IN VARCHAR,
    p_email IN VARCHAR,
    p_last IN VARCHAR,
    p_first IN VARCHAR,
    p_position IN VARCHAR
)
AS
BEGIN
    -- Update the specified columns for the pharmacy employee
    UPDATE HealthCareManagement_PHARMACYEMPLOYEE
    SET 
        PHONE_NUMBER = p_phone_number,
        EMAIL = p_email,
        LAST = p_last,
        FIRST = p_first,
        POSITION = p_position
    WHERE EMPLOYEE_ID = p_employee_id;
    
    -- Commit the transaction
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Pharmacy employee information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating pharmacy employee information: ' || SQLERRM);
END;
/


-- Procedure to edit certain fields about a supplier's info
-- Author: Matt DeRosa
CREATE OR REPLACE PROCEDURE Edit_Supplier_Info(
    p_supplier_id IN VARCHAR,
    p_phone_number IN VARCHAR,
    p_email IN VARCHAR,
    p_street IN VARCHAR,
    p_city IN VARCHAR,
    p_state IN CHAR,
    p_zip_code IN CHAR,
    p_supplier_name IN VARCHAR
)
AS
BEGIN
    -- Update the specified columns for the supplier
    UPDATE HealthCareManagement_SUPPLIER
    SET 
        PHONE_NUMBER = p_phone_number,
        EMAIL = p_email,
        STREET = p_street,
        CITY = p_city,
        STATE = p_state,
        ZIP_CODE = p_zip_code,
        SUPPLIER_NAME = p_supplier_name
    WHERE SUPPLIER_ID = p_supplier_id;
    
    -- Commit the transaction
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Supplier information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating supplier information: ' || SQLERRM);
END;
/


-- Section: Views

-- View: Login
-- This view will validate the login credentials provided by the user.
-- Author: Matt DeRosa
CREATE OR REPLACE VIEW User_Login AS
SELECT patient_ID, email, PASSWORD
FROM healthcaremanagement_patient;


-- View: Appointment Details
-- This view retrieves appointment details for a patient.
-- Author: Matt DeRosa
CREATE OR REPLACE VIEW appointment_Details AS
SELECT D.FIRST || ' '|| D.LAST AS DOCTOR_NAME, A.APPOINTMENT_DATE, A.NOTE, A.patient_id
FROM HealthCareManagement_APPOINTMENT A 
JOIN HealthCareManagement_DOCTOR D ON A.DOCTOR_ID = D.DOCTOR_ID;

-- Section: Functions

-- Function for Creating a new patient Id when they create an account
-- Author: Matt DeRosa
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


-- Function to generate a random Doctor ID
-- Author: Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_Doctor_ID
RETURN CHAR IS
    l_prefix CHAR(3) := 'DOC';
    l_suffix CHAR(7);
BEGIN
    -- Generate a random number between 1000000 and 9999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000, 9999999)));

    -- Concatenate prefix and suffix to form the Doctor ID
    RETURN l_prefix || l_suffix;
END;
/


-- Function to generate a random Insurance Company ID
-- Author: Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_Insurance_ID
RETURN CHAR IS
    l_prefix CHAR(3) := 'INS';
    l_suffix CHAR(7);
BEGIN
    -- Generate a random number between 1000000 and 9999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000, 9999999)));

    -- Concatenate prefix and suffix to form the Insurance Company ID
    RETURN l_prefix || l_suffix;
END;
/


-- Function to generate a random Pharmacy ID
-- Author: Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_Pharmacy_ID
RETURN CHAR IS
    l_prefix CHAR(4) := 'PHRM';
    l_suffix CHAR(6);
BEGIN
    -- Generate a random number between 100000 and 999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(100000, 999999)));

    -- Concatenate prefix and suffix to form the Pharmacy ID
    RETURN l_prefix || l_suffix;
END;
/


-- Function to generate a random Pharmacy Employee ID
-- Author: Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_PharmacyEmployee_ID
RETURN CHAR IS
    l_prefix CHAR(3) := 'EMP';
    l_suffix CHAR(7);
BEGIN
    -- Generate a random number between 1000000 and 9999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000, 9999999)));

    -- Concatenate prefix and suffix to form the Pharmacy Employee ID
    RETURN l_prefix || l_suffix;
END;
/


-- Function to generate a random Supplier ID
-- Author: Matt DeRosa
CREATE OR REPLACE FUNCTION Generate_Random_Supplier_ID
RETURN CHAR IS
    l_prefix CHAR(4) := 'SUPP';
    l_suffix CHAR(6); -- Increased size to 6 characters
BEGIN
    -- Generate a random number between 100000 and 999999
    l_suffix := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(100000, 999999)));

    -- Concatenate prefix and suffix to form the Supplier ID
    RETURN l_prefix || l_suffix;
END;
/


-- Section: Triggers

-- Trigger to update the patient table when a new patient is created
-- Uses the function Generate_Random_Patient_ID to create an id for a patient
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_PatientAccount
BEFORE INSERT ON HealthCareManagement_Patient
FOR EACH ROW
BEGIN
    :NEW.PATIENT_ID := Generate_Random_Patient_ID;
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


-- Trigger to update the Doctor table when a new Doctor is created
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_DoctorAccount
BEFORE INSERT ON HealthCareManagement_Doctor
FOR EACH ROW
BEGIN
    :NEW.DOCTOR_ID := Generate_Random_Doctor_ID; 
    :NEW.LAST := :NEW.LAST;
    :NEW.FIRST := :NEW.FIRST;
    :NEW.EMAIL := :NEW.EMAIL;
    :NEW.PASSWORD := :NEW.PASSWORD;
    :NEW.SPECIALIZATION := :NEW.SPECIALIZATION;
    :NEW.OFFICE_NUMBER := :NEW.OFFICE_NUMBER;
END;
/


-- Trigger to update the Insurance Company table when a new Insurance Company is created
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_InsuranceAccount
BEFORE INSERT ON HealthCareManagement_InsuranceCompany
FOR EACH ROW
BEGIN
    :NEW.INSURANCE_ID := Generate_Random_Insurance_ID;
    :NEW.INSURANCE_NAME := :NEW.INSURANCE_NAME;
    :NEW.STREET := :NEW.STREET;
    :NEW.CITY := :NEW.CITY;
    :NEW.STATE := :NEW.STATE;
    :NEW.ZIP_CODE := :NEW.ZIP_CODE;
    :NEW.PHONE_NUMBER := :NEW.PHONE_NUMBER;
    :NEW.EMAIL := :NEW.EMAIL;
    :NEW.PASSWORD := :NEW.PASSWORD;
    :NEW.PERCENT := :NEW.PERCENT;
END;
/


-- Trigger to update the Pharmacy table when a new Pharmacy is created
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_PharmacyAccount
BEFORE INSERT ON HealthCareManagement_Pharmacy
FOR EACH ROW
BEGIN
    :NEW.PHARMACY_ID := Generate_Random_Pharmacy_ID;
    :NEW.PHARMACY_NAME := :NEW.PHARMACY_NAME;
    :NEW.STREET := :NEW.STREET;
    :NEW.CITY := :NEW.CITY;
    :NEW.STATE := :NEW.STATE;
    :NEW.ZIP_CODE := :NEW.ZIP_CODE;
    :NEW.PHONE_NUMBER := :NEW.PHONE_NUMBER;
    :NEW.EMAIL := :NEW.EMAIL;
    :NEW.PASSWORD := :NEW.PASSWORD;
END;
/


-- Trigger to update the Pharmacy Employee table when a new Pharmacy Employee is created
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_PharmacyEmployeeAccount
BEFORE INSERT ON HealthCareManagement_PharmacyEmployee
FOR EACH ROW
BEGIN
    :NEW.EMPLOYEE_ID := Generate_Random_PharmacyEmployee_ID;
    :NEW.LAST := :NEW.LAST;
    :NEW.FIRST := :NEW.FIRST;
    :NEW.SSN := :NEW.SSN;
    :NEW.PHONE_NUMBER := :NEW.PHONE_NUMBER;
    :NEW.EMAIL := :NEW.EMAIL;
    :NEW.POSITION := :NEW.POSITION;
    :NEW.PHARMACY_ID := :NEW.PHARMACY_ID;
    :NEW.PASSWORD := :NEW.PASSWORD;
END;
/


-- Trigger to update the Supplier table when a new Supplier is created
-- Author: Matt DeRosa
CREATE OR REPLACE TRIGGER create_SupplierAccount
BEFORE INSERT ON HealthCareManagement_Supplier
FOR EACH ROW
BEGIN
    :NEW.SUPPLIER_ID := Generate_Random_Supplier_ID;
    :NEW.SUPPLIER_NAME := :NEW.SUPPLIER_NAME;
    :NEW.STREET := :NEW.STREET;
    :NEW.CITY := :NEW.CITY;
    :NEW.STATE := :NEW.STATE;
    :NEW.ZIP_CODE := :NEW.ZIP_CODE;
    :NEW.PHONE_NUMBER := :NEW.PHONE_NUMBER;
    :NEW.PASSWORD := :NEW.PASSWORD;
    :NEW.EMAIL := :NEW.EMAIL;
END;
/

-- Test Section: Pre-test Output

-- Selecting all rows from the HealthCareManagement_Patient table
-- Author: Matt DeRosa
--SELECT * FROM HealthCareManagement_Patient;


-- Selecting all rows from the HealthCareManagement_Patient table to test the output
-- Author: Matt DeRosa
--SELECT * FROM HealthCareManagement_Patient;
