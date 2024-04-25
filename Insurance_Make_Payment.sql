-- THIS FUNCTION TAKES IN A PATIENT ID AND PRESCRIPTION ID AND RETURNS UNPAID BALANCE TO INSURANCE COMPANY
CREATE OR REPLACE FUNCTION GetPrescriptionPrice(p_patient_id IN HealthCareManagement_PRESCRIPTION.PATIENT_ID%TYPE,
                                                p_prescription_id IN HealthCareManagement_PRESCRIPTION.PRESCRIPTION_ID%TYPE) RETURN DECIMAL
AS
    v_prescription_price DECIMAL := 0;
    
BEGIN
    -- Retrieve the prescription price for the given patient ID and prescription ID
    SELECT PRICE
    INTO v_prescription_price
    FROM HealthCareManagement_PRESCRIPTION
    WHERE PATIENT_ID = p_patient_id AND PRESCRIPTION_ID = p_prescription_id;

    -- Return the prescription price
    RETURN v_prescription_price;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL;
END;

--THIS TRIGGER CHANGES THE PRESCRIPTION PRICE AFTER A PAYMENT HAS BEEN MADE
CREATE or REPLACE TRIGGER ChangePrescriptionPriceAfterPayment
    AFTER INSERT ON HealthCareManagement_PAYMENT
    For Each Row
BEGIN
    UPDATE  HealthCareManagement_PRESCRIPTION
    SET     PRICE=PRICE-:NEW.AMOUNT
    WHERE   PRESCRIPTION_ID=:NEW.PRESCRIPTION_ID;
END;


-- FUNCTION TEST STATEMENTS:
SELECT * FROM HealthCareManagement_PRESCRIPTION WHERE PATIENT_ID='PAT001' AND PRESCRIPTION_ID='PRSC001';
--PRESCRIPTION_ID   DATE_ISSUED     PRESCRIPTION_NAME   DOSAGE  REFILLS_REMAINING   PRICE   QUANTITY    DOCTOR_ID   PATIENT_ID 
-- PRSC001   	    01-JAN-23	    Amoxicillin	        500mg	05 	                25	    30   	    DOC001    	PAT001    
SELECT GetPrescriptionPrice('PAT001','PRSC001') from DUAL;
-- 25

--TRIGGER TEST STATEMENTS:
SELECT * FROM HealthCareManagement_PRESCRIPTION;
INSERT INTO HealthCareManagement_PAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, INSURANCE_ID, PRESCRIPTION_ID)
        VALUES ('PAY001', TO_DATE('2023-06-15', 'YYYY-MM-DD'), 15.00, 'INS002', 'PRSC002');
SELECT * FROM HealthCareManagement_PRESCRIPTION;

--FIRST SELECT:
--PRESCRIPTION_ID   DATE_ISSUED     PRESCRIPTION_NAME   DOSAGE  REFILLS_REMAINING   PRICE   QUANTITY    DOCTOR_ID   PATIENT_ID 
--PRSC001   	    01-JAN-23	    Amoxicillin	        500mg	05 	                25	    30   	    DOC001    	PAT001    
--PRSC002   	    01-FEB-23	    Ibuprofen	        200mg	03 	                15	    20   	    DOC002    	PAT002    
--PRSC003   	    01-MAR-23	    Metformin	        850mg	02 	                30	    60   	    DOC003    	PAT003    
--PRSC004   	    01-APR-23	    Lisinopril	        10mg	04 	                22	    90   	    DOC004    	PAT004    
--PRSC005   	    01-MAY-23	    Atorvastatin	    20mg	01 	                45	    10   	    DOC005    	PAT005 

--AFTER INSERT:
--PRESCRIPTION_ID   DATE_ISSUED     PRESCRIPTION_NAME   DOSAGE  REFILLS_REMAINING   PRICE   QUANTITY    DOCTOR_ID   PATIENT_ID 
--PRSC001   	    01-JAN-23	    Amoxicillin	        500mg	05 	                25	    30   	    DOC001    	PAT001    
--PRSC002   	    01-FEB-23	    Ibuprofen	        200mg	03 	                0	    20   	    DOC002    	PAT002    
--PRSC003   	    01-MAR-23	    Metformin	        850mg	02 	                30	    60   	    DOC003    	PAT003    
--PRSC004   	    01-APR-23	    Lisinopril	        10mg	04 	                22	    90   	    DOC004    	PAT004    
--PRSC005   	    01-MAY-23	    Atorvastatin	    20mg	01 	                45	    10   	    DOC005    	PAT005 


