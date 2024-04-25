--View to see all users regardless of their type
CREATE OR REPLACE VIEW AllUsersOverview AS
SELECT USER_ID, NAME, USER_TYPE, EMAIL, PHONE_NUMBER
FROM AllUsersOverview
ORDER BY User_Type, NAME;

SELECT * FROM AllUsersOverview

--Function to find total count of each user type
CREATE OR REPLACE FUNCTION CountUsersByType (userType VARCHAR2)
RETURN INT IS
    userCount INT;
BEGIN
    SELECT COUNT(*)
    INTO userCount
    FROM AllUsersOverview
    WHERE User_Type = userType;

    RETURN userCount;
END;
/

SELECT CountUsersByType('Patient') AS Patient_Count FROM dual;
SELECT CountUsersByType('Doctor') AS Doctor_Count FROM dual;
SELECT CountUsersByType('Pharmacy Employee') AS PharmacyEmployee_Count FROM dual;

//