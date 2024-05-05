DROP TABLE HealthCareManagement_PATIENT CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PATIENT(
PATIENT_ID VARCHAR(10) PRIMARY KEY,
DOB DATE,
STREET VARCHAR(30),
CITY VARCHAR(15),
STATE CHAR(2),
ZIP_CODE CHAR(5),
EMAIL VARCHAR(50),
PHONE_NUMBER VARCHAR(20),
LAST VARCHAR(10),
FIRST VARCHAR(10),
SEX VARCHAR(10),
INSURANCE_ID VARCHAR(10),
PASSWORD VARCHAR(30),
FOREIGN KEY (INSURANCE_ID) REFERENCES HealthCareManagement_INSURANCECOMPANY(INSURANCE_ID)
ON DELETE SET NULL);

DROP TABLE HealthCareManagement_DOCTOR CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_DOCTOR(
DOCTOR_ID VARCHAR(10) PRIMARY KEY,
LAST VARCHAR(10),
FIRST VARCHAR(10),
EMAIL VARCHAR(50),
PASSWORD VARCHAR(30),
SPECIALIZATION VARCHAR(20),
OFFICE_NUMBER CHAR(3));

DROP TABLE HealthCareManagement_INSURANCECOMPANY CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_INSURANCECOMPANY(
INSURANCE_ID VARCHAR(10) PRIMARY KEY,
INSURANCE_NAME VARCHAR(20),
STREET VARCHAR(30),
CITY VARCHAR(15),
STATE CHAR(2),
ZIP_CODE CHAR(5),
PHONE_NUMBER VARCHAR(20),
EMAIL VARCHAR(50),
PASSWORD VARCHAR(30),
PERCENT DECIMAL(4, 3));


DROP TABLE HealthCareManagement_PRESCRIPTION CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PRESCRIPTION(
PRESCRIPTION_ID VARCHAR(10) PRIMARY KEY,
DATE_ISSUED DATE,
PRESCRIPTION_NAME VARCHAR(20),
DOSAGE VARCHAR(5),
REFILLS_REMAINING CHAR(3),
PRICE DECIMAL(6,2),
QUANTITY CHAR(5),
DOCTOR_ID VARCHAR(10),
PATIENT_ID VARCHAR(10),
FILLED VARCHAR(3),
FOREIGN KEY (DOCTOR_ID) REFERENCES HealthCareManagement_DOCTOR(DOCTOR_ID)
ON DELETE CASCADE,
FOREIGN KEY (PATIENT_ID) REFERENCES HealthCareManagement_PATIENT(PATIENT_ID)
ON DELETE CASCADE);

DROP TABLE HealthCareManagement_SUPPLIER CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_SUPPLIER(
SUPPLIER_ID VARCHAR(10) PRIMARY KEY,
SUPPLIER_NAME VARCHAR(20),
STREET VARCHAR(30),
CITY VARCHAR(15),
STATE CHAR(2),
ZIP_CODE CHAR(5),
PHONE_NUMBER VARCHAR(20),
PASSWORD VARCHAR(30),
EMAIL VARCHAR(50));

DROP TABLE HealthCareManagement_MEDICATION CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_MEDICATION(
NAME VARCHAR(30) PRIMARY KEY,
QUANTITY CHAR(5),
SUPPLIER_ID VARCHAR(10),
FOREIGN KEY (SUPPLIER_ID) REFERENCES HealthCareManagement_SUPPLIER(SUPPLIER_ID)
ON DELETE SET NULL);

DROP TABLE HealthCareManagement_PHARMACYEMPLOYEE CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PHARMACYEMPLOYEE(
EMPLOYEE_ID VARCHAR(10) PRIMARY KEY,
LAST VARCHAR(10),
FIRST VARCHAR(10),
SSN VARCHAR(9),
PHONE_NUMBER VARCHAR(20),
EMAIL VARCHAR(50),
POSITION VARCHAR(10),
PHARMACY_ID VARCHAR(10),
PASSWORD VARCHAR(30),
FOREIGN KEY (PHARMACY_ID) REFERENCES HealthCareManagement_PHARMACY(PHARMACY_ID)
ON DELETE SET NULL);

DROP TABLE HealthCareManagement_PAYSFOR CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PAYSFOR(
PRESCRIPTION_ID VARCHAR(10),
INSURANCE_ID VARCHAR(10),
PRIMARY KEY (PRESCRIPTION_ID, INSURANCE_ID),
    FOREIGN KEY (PRESCRIPTION_ID) REFERENCES HealthCareManagement_PRESCRIPTION(PRESCRIPTION_ID) ON DELETE CASCADE,
    FOREIGN KEY (INSURANCE_ID) REFERENCES HealthCareManagement_INSURANCECOMPANY(INSURANCE_ID) ON DELETE CASCADE
);

DROP TABLE HealthCareManagement_APPOINTMENT CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_APPOINTMENT(
PRIMARY KEY(PATIENT_ID, DOCTOR_ID),
NOTE VARCHAR(30),
APPOINTMENT_DATE DATE,
PATIENT_ID VARCHAR(10),
DOCTOR_ID VARCHAR(10),
FOREIGN KEY (PATIENT_ID) REFERENCES HealthCareManagement_PATIENT(PATIENT_ID)
ON DELETE CASCADE,
FOREIGN KEY (DOCTOR_ID) REFERENCES HealthCareManagement_DOCTOR(DOCTOR_ID)
ON DELETE CASCADE);

DROP TABLE HealthCareManagement_PHARMACY CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PHARMACY(
PHARMACY_ID VARCHAR(10) PRIMARY KEY,
PHARMACY_NAME VARCHAR(20),
STREET VARCHAR(30),
CITY VARCHAR(15),
STATE CHAR(2),
ZIP_CODE CHAR(5),
PHONE_NUMBER VARCHAR(20),
PASSWORD VARCHAR(30),
EMAIL VARCHAR(50));

DROP TABLE HealthCareManagement_FILLS CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_FILLS(
PHARMACY_ID VARCHAR(10),
PRESCRIPTION_ID VARCHAR(10),
PRIMARY KEY(PHARMACY_ID, PRESCRIPTION_ID),
FOREIGN KEY (PHARMACY_ID) REFERENCES HealthCareManagement_PHARMACY(PHARMACY_ID)
ON DELETE CASCADE,
FOREIGN KEY (PRESCRIPTION_ID) REFERENCES HealthCareManagement_PRESCRIPTION(PRESCRIPTION_ID)
ON DELETE CASCADE);

DROP TABLE HealthCareManagement_DIAGNOSES CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_DIAGNOSES (
    PATIENT_ID VARCHAR(10),
    DIAGNOSES VARCHAR(30),
    DOCTOR_ID VARCHAR(10),
    PRIMARY KEY (PATIENT_ID, DIAGNOSES),
    FOREIGN KEY (PATIENT_ID) REFERENCES HealthCareManagement_PATIENT(PATIENT_ID) ON DELETE SET NULL,
    FOREIGN KEY (DOCTOR_ID) REFERENCES HealthCareManagement_DOCTOR(DOCTOR_ID) ON DELETE SET NULL
);


DROP TABLE HealthCareManagement_SUPPLIES CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_SUPPLIES(
SUPPLIER_ID VARCHAR(10),
PHARMACY_ID VARCHAR(10),
PRIMARY KEY(SUPPLIER_ID, PHARMACY_ID),
FOREIGN KEY (SUPPLIER_ID) REFERENCES HealthCareManagement_SUPPLIER(SUPPLIER_ID)
ON DELETE CASCADE,
FOREIGN KEY (PHARMACY_ID) REFERENCES HealthCareManagement_PHARMACY(PHARMACY_ID)
ON DELETE CASCADE);

DROP TABLE HealthCareManagement_INSURANCEPAYMENT CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_INSURANCEPAYMENT(
PAYMENT_ID VARCHAR(10) PRIMARY KEY,
PAYMENT_DATE DATE,
AMOUNT DECIMAL(10,2),
INSURANCE_ID VARCHAR(10),
PRESCRIPTION_ID VARCHAR(10),
FOREIGN KEY (INSURANCE_ID) REFERENCES HealthCareManagement_INSURANCECOMPANY(INSURANCE_ID)
ON DELETE CASCADE,
FOREIGN KEY (PRESCRIPTION_ID) REFERENCES HealthCareManagement_PRESCRIPTION(PRESCRIPTION_ID)
ON DELETE CASCADE);

DROP TABLE HealthCareManagement_PATIENTPAYMENT CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PATIENTPAYMENT(
PAYMENT_ID VARCHAR(10) PRIMARY KEY,
PAYMENT_DATE DATE,
AMOUNT DECIMAL(10,2),
PATIENT_ID VARCHAR(10),
PRESCRIPTION_ID VARCHAR(10),
FOREIGN KEY (PATIENT_ID) REFERENCES HealthCareManagement_PATIENT(PATIENT_ID)
ON DELETE CASCADE,
FOREIGN KEY (PRESCRIPTION_ID) REFERENCES HealthCareManagement_PRESCRIPTION(PRESCRIPTION_ID)
ON DELETE CASCADE);


INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS001', 'HealthPlus', '1234 Main St', 'Anytown', 'NY', '12345', '123-456-7890', 'info@healthplus.com', 'ths8673incd58n', 0.10);

INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS002', 'MediCare', '5678 Elm St', 'Springfield', 'IL', '23456', '234-567-8901', 'support@medicare.com', 'thsbth6793niincd58n', 0.25);

INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS003', 'WellFare', '9101 Oak St', 'Liberty', 'TX', '34567', '345-678-9012', 'contact@wellfare.com', 'password', 0.40);

INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS004', 'SureHealth', '1213 Pine St', 'Centerville', 'CA', '45678', '456-789-0123', 'help@surehealth.com', 'password1', 0.15);

INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS005', 'LifeSecure', '1415 Maple St', 'New Hope', 'FL', '56789', '567-890-1234', 'info@lifesecure.com', 'password2', 0.10);

INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) 
VALUES ('INS006', 'NoInsurance', 'No Address', 'No City', 'NA', '00000', '000-000-0000', 'noinsurance@example.com', 'nopassword', 0.00);


INSERT INTO HealthCareManagement_SUPPLIER VALUES ('SUP001', 'PharmaCo', '1234 Drug St', 'Medicity', 'CA', '67890', '678-901-2345', 'password00', 'supply@pharmaco.com');
INSERT INTO HealthCareManagement_SUPPLIER VALUES ('SUP002', 'MedSupplies', '5678 Pharma St', 'Careville', 'TX', '78901', '789-012-3456', 'password194', 'order@medsupplies.com');
INSERT INTO HealthCareManagement_SUPPLIER VALUES ('SUP003', 'DrugNest', '9101 Health St', 'Pilltown', 'FL', '89012', '890-123-4567', 'password90732', 'contact@drugnest.com');
INSERT INTO HealthCareManagement_SUPPLIER VALUES ('SUP004', 'VitaPharm', '1213 Vitamin St', 'Supplecity', 'NY', '90123', '901-234-5678', 'password320', 'info@vitapharm.com');
INSERT INTO HealthCareManagement_SUPPLIER VALUES ('SUP005', 'CareGoods', '1415 Remedy St', 'Aidville', 'IL', '01234', '012-345-6789', 'password-4392', 'service@caregoods.com');

INSERT INTO HealthCareManagement_DOCTOR VALUES ('DOC001', 'Smith', 'John', 'john.smith@hospital.com', 'thsbaibniincd68n', 'Cardiology', '101');
INSERT INTO HealthCareManagement_DOCTOR VALUES ('DOC002', 'Johnson', 'Emily', 'emily.johnson@clinic.com', 'thsbaibniincd08n', 'Dermatology', '102');
INSERT INTO HealthCareManagement_DOCTOR VALUES ('DOC003', 'Williams', 'David', 'david.williams@medcenter.com', 'thsbaibniincd98n', 'Neurology', '103');
INSERT INTO HealthCareManagement_DOCTOR VALUES ('DOC004', 'Brown', 'Sophia', 'sophia.brown@healthcare.com', 'thsbaib', 'Pediatrics', '104');
INSERT INTO HealthCareManagement_DOCTOR VALUES ('DOC005', 'Davis', 'Michael', 'michael.davis@generalhospital.com', 'ibnincd98n', 'General', '105');

INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT001', TO_DATE('1990-01-01','YYYY-MM-DD'), '1234 Life St', 'Anytown', 'NY', '12345', 'patient1@email.com', '123-456-7890', 'Doe', 'Jane', 'Female', 'INS001', 'thsbaibniincd58n');
INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT002', TO_DATE('1985-02-02','YYYY-MM-DD'), '5678 Health Rd', 'Wellville', 'TX', '23456', 'patient2@email.com', '234-567-8901', 'Brown', 'John', 'Male', 'INS002', 'thsbaibniincd59n');
INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT003', TO_DATE('1975-03-03','YYYY-MM-DD'), '9101 Care Ave', 'Curecity', 'CA', '34567', 'patient3@email.com', '345-678-9012', 'Smith', 'Emily', 'Female', 'INS003', 'thsbaibniincd60n');
INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT004', TO_DATE('2000-04-04','YYYY-MM-DD'), '1213 Remedy Blvd', 'Aidtown', 'FL', '45678', 'patient4@email.com', '456-789-0123', 'Johnson', 'Michael', 'Male', 'INS004', 'thsbaibniincd61n');
INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT005', TO_DATE('1995-05-05','YYYY-MM-DD'), '1415 Wellness Ln', 'Hopetown', 'IL', '56789', 'patient5@email.com', '567-890-1234', 'Williams', 'Sophia', 'Female', 'INS005', 'thsbaibniincd62n');
INSERT INTO HealthCareManagement_PATIENT VALUES ('PAT006', TO_DATE('1988-06-10','YYYY-MM-DD'), '2468 Healing Blvd', 'Recovery City', 'CA', '98765', 'patient6@email.com', '789-012-3456', 'Carlson', 'Mary', 'Female', 'INS006', 'thsbaibniincd63n');


INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC001', TO_DATE('2023-01-01','YYYY-MM-DD'), 'Amoxicillin', '500mg', '05', 25.00, '30', 'DOC001', 'PAT001', 'Yes');
INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC002', TO_DATE('2023-02-01','YYYY-MM-DD'), 'Ibuprofen', '200mg', '03', 15.00, '20', 'DOC002', 'PAT002', 'No');
INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC003', TO_DATE('2023-03-01','YYYY-MM-DD'), 'Metformin', '850mg', '02', 30.00, '60', 'DOC003', 'PAT003', 'No');
INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC004', TO_DATE('2023-04-01','YYYY-MM-DD'), 'Lisinopril', '10mg', '04', 22.00, '90', 'DOC004', 'PAT004', 'Yes');
INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC005', TO_DATE('2023-05-01','YYYY-MM-DD'), 'Atorvastatin', '20mg', '01', 45.00, '10', 'DOC005', 'PAT005', 'No');
INSERT INTO HealthCareManagement_PRESCRIPTION VALUES ('PRSC006', TO_DATE('2023-06-01','YYYY-MM-DD'), 'Aspirin', '250mg', '02', 10.00, '40', 'DOC005', 'PAT006', 'Yes');


INSERT INTO HealthCareManagement_MEDICATION VALUES ('Amoxicillin', '100', 'SUP001');
INSERT INTO HealthCareManagement_MEDICATION VALUES ('Ibuprofen', '200', 'SUP002');
INSERT INTO HealthCareManagement_MEDICATION VALUES ('Metformin', '150', 'SUP003');
INSERT INTO HealthCareManagement_MEDICATION VALUES ('Lisinopril', '120', 'SUP004');
INSERT INTO HealthCareManagement_MEDICATION VALUES ('Atorvastatin', '80', 'SUP005');
INSERT INTO HealthCareManagement_MEDICATION VALUES ('Aspirin', '80', 'SUP005');

INSERT INTO HealthCareManagement_PHARMACY VALUES ('PHRM001', 'City Pharmacy', '123 Cure St', 'Healtown', 'NY', '12345', '123-456-1111', 'password9', 'pharmacy@citypharm.com');
INSERT INTO HealthCareManagement_PHARMACY VALUES ('PHRM002', 'MediPharm', '456 Pill Rd', 'Medville', 'TX', '23456', '234-567-2222', 'password10', 'info@medipharm.com');
INSERT INTO HealthCareManagement_PHARMACY VALUES ('PHRM003', 'CarePlus Pharmacy', '789 Health Ave', 'Carecity', 'CA', '34567', '345-678-3333', 'password11', 'support@carepluspharm.com');
INSERT INTO HealthCareManagement_PHARMACY VALUES ('PHRM004', 'Wellness Pharmacy', '1012 Remedy Blvd', 'Welltown', 'FL', '45678', '456-789-4444', 'password12', 'contact@wellnesspharm.com');
INSERT INTO HealthCareManagement_PHARMACY VALUES ('PHRM005', 'Hope Pharmacy', '1314 Wellness Ln', 'Hopetown', 'IL', '56789', '567-890-5555', 'password13', 'service@hopepharm.com');

INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE VALUES ('EMP001', 'Miller', 'Alice', '123456789', '123-456-6666', 'alice.miller@pharmacy.com', 'Pharmacist', 'PHRM001', 'password123');
INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE VALUES ('EMP002', 'Wilson', 'Bob', '987654321', '234-567-7777', 'bob.wilson@pharmacy.com', 'Assistant', 'PHRM002', 'password1234');
INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE VALUES ('EMP003', 'Moore', 'Clara', '456789123', '345-678-8888', 'clara.moore@pharmacy.com', 'Manager', 'PHRM003', 'password93483');
INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE VALUES ('EMP004', 'Taylor', 'Dan', '654321987', '456-789-9999', 'dan.taylor@pharmacy.com', 'Technician', 'PHRM004', 'password0383');
INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE VALUES ('EMP005', 'Anderson', 'Eva', '321654987', '567-890-0000', 'eva.anderson@pharmacy.com', 'Clerk', 'PHRM005', 'password1849');

INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC001', 'INS001');
INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC002', 'INS002');
INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC003', 'INS003');
INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC004', 'INS004');
INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC005', 'INS005');
INSERT INTO HealthCareManagement_PAYSFOR VALUES ('PRSC006', 'INS006');

INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT001', 'DOC001', 'Follow-up Check', TO_DATE('2023-06-01', 'YYYY-MM-DD'));
INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT002', 'DOC002', 'Routine Checkup', TO_DATE('2023-07-01', 'YYYY-MM-DD'));
INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT003', 'DOC003', 'Consultation', TO_DATE('2023-08-01', 'YYYY-MM-DD'));
INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT004', 'DOC004', 'Annual Physical', TO_DATE('2023-09-01', 'YYYY-MM-DD'));
INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT005', 'DOC005', 'Emergency Visit', TO_DATE('2023-10-01', 'YYYY-MM-DD'));
INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES ('PAT006', 'DOC005', 'Headache Evaluation', TO_DATE('2023-06-15', 'YYYY-MM-DD'));

INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM001', 'PRSC001');
INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM002', 'PRSC002');
INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM003', 'PRSC003');
INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM004', 'PRSC004');
INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM005', 'PRSC005');
INSERT INTO HealthCareManagement_FILLS VALUES ('PHRM005', 'PRSC006');

INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT001', 'Hypertension', 'DOC001');
INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT002', 'Diabetes','DOC004');
INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT003', 'Arthritis','DOC003');
INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT004', 'Asthma','DOC002');
INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT005', 'High Cholesterol','DOC004');
INSERT INTO HealthCareManagement_DIAGNOSES VALUES ('PAT006', 'Migraine','DOC004');

DROP TABLE HealthCareManagement_PRESCRIPTIONBALANCE CASCADE CONSTRAINTS;
CREATE TABLE HealthCareManagement_PRESCRIPTIONBALANCE AS
SELECT PR.*,
    P.INSURANCE_ID,
    PR.PRICE * IC.PERCENT AS InsuranceBalance,
    PR.PRICE * (1 - IC.PERCENT) AS PatientBalance
FROM
    HealthCareManagement_PRESCRIPTION PR
JOIN
    HealthCareManagement_PATIENT P ON PR.PATIENT_ID = P.PATIENT_ID
JOIN
    HealthCareManagement_INSURANCECOMPANY IC ON P.INSURANCE_ID = IC.INSURANCE_ID;
