
CREATE OR REPLACE PROCEDURE Add_Medication (
    p_medication_name IN VARCHAR2,
    p_quantity IN NUMBER,
    p_supplier_id IN VARCHAR2
) AS
BEGIN
    INSERT INTO HealthCareManagement_MEDICATION (NAME, QUANTITY, SUPPLIER_ID)
    VALUES (p_medication_name, p_quantity, p_supplier_id);
END Add_Medication;
