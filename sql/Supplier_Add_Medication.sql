
CREATE OR REPLACE PROCEDURE Add_Medication (
    p_medication_name IN VARCHAR2,
    p_quantity IN NUMBER,
    p_supplier_id IN VARCHAR2
) AS
BEGIN
    INSERT INTO HealthCareManagement_MEDICATION (NAME, QUANTITY, SUPPLIER_ID)
    VALUES (p_medication_name, p_quantity, p_supplier_id);
END Add_Medication;

SELECT * FROM HealthCareManagement_MEDICATION;
--NAME          QUANITITY  SUPPLIER_ID
--Amoxicillin	200  	    SUP001
--Ibuprofen	    200  	    SUP002
--Metformin	    150  	    SUP003
--Lisinopril	120  	    SUP004
--Atorvastatin	80   	    SUP005
--Aspirin	    80   	    SUP005

EXEC Add_Medication('SampleMed', 100, 'SUP001');
SELECT * FROM HealthCareManagement_MEDICATION;
--NAME          QUANITITY  SUPPLIER_ID
--Amoxicillin	200  	    SUP001
--Ibuprofen	    200  	    SUP002
--Metformin	    150  	    SUP003
--Lisinopril	120  	    SUP004
--Atorvastatin	80   	    SUP005
--Aspirin	    80   	    SUP005
--SampleMed	    100  	    SUP001



