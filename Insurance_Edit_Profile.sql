-- Procedure to edit certaint fields about a doctors info
-- Ellie Smith


CREATE OR REPLACE PROCEDURE Edit_Insurance_Info(i_insurance_id IN CHAR,
                                                i_insurance_name IN VARCHAR,
                                                i_street IN VARCHAR,
                                                i_city IN VARCHAR,
                                                i_state IN CHAR,
                                                i_zip_code IN CHAR,
                                                i_phone_number IN VARCHAR,
                                                i_email IN VARCHAR,
                                                i_password IN CHAR)
AS
BEGIN
    -- Update the specified columns for the insurance company
    UPDATE HealthCareManagement_INSURANCECOMPANY
    SET 
        INSURANCE_NAME = i_insurance_name,
        STREET = i_street,
        CITY = i_city,
        STATE = i_state,
        ZIP_CODE = i_zip_code,
        PHONE_NUMBER = i_phone_number,
        EMAIL = i_email,
        PASSWORD = i_password
    WHERE INSURANCE_ID = i_insurance_id;
    
    COMMIT;
    
    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Insurance company information updated successfully.');
EXCEPTION
    WHEN OTHERS THEN
        -- Output error message if an exception occurs
        DBMS_OUTPUT.PUT_LINE('Error updating insurance company information: ' || SQLERRM);
END;



--TEST STATEMENTS:

SELECT * FROM HealthCareManagement_INSURANCECOMPANY WHERE INSURANCE_ID = 'INS001';
-- OUTPUT:
--INSURANCE_ID  INSURANCE       STREET          CITY        ZIP_CODE    PHONE_NUMBER    EMAIL               PASSWORD            
--INS001    	HealthPlus	    1234 Main St	Anytown	NY	12345	    123-456-7890	info@healthplus.com	ths8673incd58n                

-- Call the procedure to update the insurance company's information
EXEC Edit_Insurance_Info('INS001', 'New Insurance', '123 New St', 'New City', 'NY', '54321', '555-555-5555', 'info@newinsurance.com', 'newpassword');


SELECT * FROM HealthCareManagement_INSURANCECOMPANY WHERE INSURANCE_ID = 'INS001';
-- NEW OUTPUT WITH UPDATED CHANGES:
--INSURANCE_ID  INSURANCE       STREET          CITY        ZIP_CODE    PHONE_NUMBER    EMAIL               PASSWORD 
--INS001    	New Insurance	123 New St	    New City	NY	54321	555-555-5555	info@newinsurance.com	newpassword                                   
