package tableConstructors;

import java.sql.*;
/**
 * @author Matt DeROsa
 */
public class PharmacyEmployee {
  private String employeeId;
  private String lastName;
  private String firstName;
  private String ssn;
  private String phoneNumber;
  private String email;
  private String position;
  private String pharmacyId;
  private String password;
  
  private Boolean loggedIn = false;
  
  public PharmacyEmployee() {
  }
  
  public PharmacyEmployee(String employeeId, String lastName, String firstName, String ssn, String phoneNumber, String email, String position, String pharmacyId, String password) {
    this.employeeId = employeeId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.ssn = ssn;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.position = position;
    this.pharmacyId = pharmacyId;
    this.password = password;
  }
  
  /**
   * @return the employeeId
   */
  public String getEmployeeId() {
    return employeeId;
  }
  
  /**
   * @param employeeId the employeeId to set
   */
  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }
  
  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * @return the ssn
   */
  public String getSsn() {
    return ssn;
  }
  
  /**
   * @param ssn the ssn to set
   */
  public void setSsn(String ssn) {
    this.ssn = ssn;
  }
  
  /**
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  /**
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }
  
  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }
  
  /**
   * @return the pharmacyId
   */
  public String getPharmacyId() {
    return pharmacyId;
  }
  
  /**
   * @param pharmacyId the pharmacyId to set
   */
  public void setPharmacyId(String pharmacyId) {
    this.pharmacyId = pharmacyId;
  }
  
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  
  
  @Override
  public String toString() {
    return "PharmacyEmployee [employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName
      + ", ssn=" + ssn + ", phoneNumber=" + phoneNumber + ", email=" + email + ", position=" + position
      + ", pharmacyId=" + pharmacyId + ", password=" + password + ", loggedIn=" + loggedIn + "]";
  }
  
  public Connection openDBConnection() {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
      Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                            "csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");
      return myConnection;
    } catch (Exception E) {
      E.printStackTrace();
    }
    return null;
  }
  
  public boolean pharmacyEmployeeLogin(String email, String password) {
	    Connection con = openDBConnection();
	    try {
	        PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_PHARMACYEMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?");
	        statement.setString(1, email);
	        statement.setString(2, password);

	        ResultSet rs = statement.executeQuery();
	        
	        if(rs.next() && rs.getInt(1) > 0){
	            this.loggedIn = true;
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
  
  public void addPharmacyEmployee(PharmacyEmployee pharmacyEmployee) {
	    try (Connection connection = openDBConnection()) {
	        // Generate a new employee ID using stored procedure
	        CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_PharmacyEmployee_ID}");
	        callableStatement.registerOutParameter(1, Types.CHAR);
	        callableStatement.execute();

	        String generatedId = callableStatement.getString(1);
	        callableStatement.close();

	        // Insert employee data into the database
	        String sql = "INSERT INTO HealthCareManagement_PHARMACYEMPLOYEE (EMPLOYEE_ID, FIRST, LAST, SSN, PHONE_NUMBER, EMAIL, POSITION, PHARMACY_ID, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, generatedId);
	        preparedStatement.setString(2, pharmacyEmployee.getFirstName());
	        preparedStatement.setString(3, pharmacyEmployee.getLastName());
	        preparedStatement.setString(4, pharmacyEmployee.getSsn());
	        preparedStatement.setString(5, pharmacyEmployee.getPhoneNumber());
	        preparedStatement.setString(6, pharmacyEmployee.getEmail());
	        preparedStatement.setString(7, pharmacyEmployee.getPosition());
	        preparedStatement.setString(8, pharmacyEmployee.getPharmacyId());
	        preparedStatement.setString(9, pharmacyEmployee.getPassword());

	        preparedStatement.executeUpdate();
	        System.out.println("Pharmacy Employee added successfully with ID: " + generatedId);

	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


  
  public void updateEmployeeInfo(String employeeId, String lastName, String firstName, String ssn, String phoneNumber, String email, String position, String pharmacyId, String password) {
    try {
      Connection connection = openDBConnection();
      CallableStatement callableStatement = connection.prepareCall("{call Edit_Pharmacy_Employee_Info(?,?,?,?,?,?,?,?,?)}");
      callableStatement.setString(1, employeeId);
      callableStatement.setString(2, lastName);
      callableStatement.setString(3, firstName);
      callableStatement.setString(4, ssn);
      callableStatement.setString(5, phoneNumber);
      callableStatement.setString(6, email);
      callableStatement.setString(7, position);
      callableStatement.setString(8, pharmacyId);
      callableStatement.setString(9, password);
      
      callableStatement.execute();
      
      System.out.println("Pharmacy employee information updated successfully.");
      
      callableStatement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public PharmacyEmployee displayEmployeeInfo(String employeeId) {
    PharmacyEmployee employee = new PharmacyEmployee();
    Connection con = openDBConnection();
    try {
      String sql = "SELECT * FROM HealthCareManagement_PHARMACYEMPLOYEE WHERE EMPLOYEE_ID = ?";
      
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, employeeId);
      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
        employee.setEmployeeId(resultSet.getString("EMPLOYEE_ID"));
        employee.setLastName(resultSet.getString("LAST_NAME"));
        employee.setFirstName(resultSet.getString("FIRST_NAME"));
        employee.setSsn(resultSet.getString("SSN"));
        employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
        employee.setEmail(resultSet.getString("EMAIL"));
        employee.setPosition(resultSet.getString("POSITION"));
        employee.setPharmacyId(resultSet.getString("PHARMACY_ID"));
        employee.setPassword(resultSet.getString("PASSWORD"));
        
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("SSN: " + employee.getSsn());
        System.out.println("Phone Number: " + employee.getPhoneNumber());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Pharmacy ID: " + employee.getPharmacyId());
        System.out.println("Password: " + employee.getPassword());
      }
      
      resultSet.close();
      preparedStatement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }
  
  /**
   * @return the loggedIn
   */
  public Boolean getLoggedIn() {
    return loggedIn;
  }
  
  /**
   * @param loggedIn the loggedIn to set
   */
  public void setLoggedIn(Boolean loggedIn) {
    this.loggedIn = loggedIn;
  }
  
  /**
   * Method that allows pharmacy employees to view all presscriptions including any unpaid balance for each
   * 
   * @param PHARMACY_ID -- NEED TO REMOVE THIS TO GET INSURANCEID METHOD
   * 
   */
  public void viewPrescriptions(String PHARMACY_ID) {
    
    Connection myConnection;
    PreparedStatement preparedStmt;
    
    try {
      myConnection = openDBConnection();
      
      // Prepare the SQL update statement.
      String queryString = "SELECT * FROM Pharmacy_Prescriptions WHERE PHARMACY_ID = ?";
      
      preparedStmt = myConnection.prepareStatement(queryString);
      
      preparedStmt.setString(1, PHARMACY_ID);
      
      ResultSet rs = preparedStmt.executeQuery(); 
      
      // Print the column headers
      System.out.println("PRESCRIPTION_ID\tPATIENT_ID\tINSURANCE_ID\tAMOUNT_OWED");
      
      // Iterate through the result set and print each row
      while (rs.next()) {
        String prescriptionId = rs.getString("PRESCRIPTION_ID");
        String patientId = rs.getString("PATIENT_ID");
        String insuranceId = rs.getString("INSURANCE_ID");
        double amountOwed = rs.getDouble("AMOUNT_OWED");
        System.out.println(prescriptionId + "\t\t" + patientId + "\t\t" + insuranceId + "\t\t" + amountOwed);
      }
    }
    
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Method that allows pharamacy employees to see total unpaid balance
   * for a given patient
   * 
   * @param pharmacyId -- NEED TO REMOVE THIS TO GET INSURANCEID METHOD
   * @param patientId
   */
  public double GetUnpaidBalanceForPatient(String pharmacyId, String patientId) {
    
    Connection myConnection = null;
    PreparedStatement preparedStmt = null;
    ResultSet result = null;
    double unpaidPatientBalance = -1;
    
    try {
      myConnection = openDBConnection();
      
      String queryString = "SELECT GetUnpaidBalanceForPatient(?, ?) FROM DUAL";
      
      preparedStmt = myConnection.prepareStatement(queryString);
      
      preparedStmt.clearParameters();
      
      preparedStmt.setString(1,patientId);
      preparedStmt.setString(2,pharmacyId);
      
      result = preparedStmt.executeQuery();
      
      if(result.next())
        unpaidPatientBalance = result.getDouble(1);
    } 
    
    catch (SQLException e) {
      e.printStackTrace();
    }
    
    // If the operation failed, return a default value indicating failure.
    System.out.println(unpaidPatientBalance);
    return unpaidPatientBalance;
  }
  
  /**
   * Method that allows pharamacy employees to see total unpaid balance
   * for a given patient
   * 
   * @param pharmacyId -- NEED TO REMOVE THIS TO GET INSURANCEID METHOD
   * @param patientId
   */
  public double GetUnpaidBalanceForInsuranceCompany(String pharmacyId, String insuranceId) {
    
    Connection myConnection = null;
    PreparedStatement preparedStmt = null;
    ResultSet result = null;
    double unpaidICBalance = -1;
    
    try {
      myConnection = openDBConnection();
      
      String queryString = "SELECT GetUnpaidBalanceForInsuranceCompany(?, ?) FROM DUAL";
      
      preparedStmt = myConnection.prepareStatement(queryString);
      
      preparedStmt.clearParameters();
      
      preparedStmt.setString(1,insuranceId);
      preparedStmt.setString(2,pharmacyId);
      
      result = preparedStmt.executeQuery();
      
      if(result.next())
        unpaidICBalance = result.getDouble(1);
    } 
    
    catch (SQLException e) {
      e.printStackTrace();
    }
    
    // If the operation failed, return a default value indicating failure.
    System.out.println(unpaidICBalance);
    return unpaidICBalance;
  }
  
  /**
   * Main method to test JDBC methods
   */
  public static void main(String[] args) {
    try {
      // Create an instance of InsuranceCompanyController
      PharmacyEmployee test = new PharmacyEmployee();
      
      // Call the instance method on the created instance to view covered patients information
      test.viewPrescriptions("PHRM001");
      
      test.GetUnpaidBalanceForPatient("PHRM001","PAT001");
      
      test.GetUnpaidBalanceForInsuranceCompany("PHRM001","INS001");
      
   // Call method for viewing all available medication
      //test.viewAvailableMedication();
      //call method for billing insurance
      //test.billInsurance("PRSC001");
      //call method for viewing medication info
      //test.viewMedicationInfo("PAT001");
      //call method to refill medication
      //test.requestRefill("Aspirin");
    } 
    
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Method to view all of the medication available at the pharmacy
   */
  
  public void viewAvailableMedication() {
    Connection con = openDBConnection();
    String sql = "select *\n"
      + "from healthcaremanagement_medication";
    
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          String name = rs.getString("NAME");
          String supplierID = rs.getString("SUPPLIER_ID");
          String quantity = rs.getString("QUANTITY");
          System.out.println(name + "\t\t" + supplierID + "\t\t" + quantity);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
     }
   
  
  /**
   * 
   * @param prescriptionId
   * @return amount due
   */
  public String billInsurance(String prescriptionId) {
    Connection con = openDBConnection();
    String sql = "select insurancebalance from prescriptionbalancetable WHERE prescription_id = ?";
    try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setString(1, prescriptionId);
      try (ResultSet rs = statement.executeQuery()) {
        while(rs.next()){
          String due = rs.getString("INSURANCEBALANCE");
          
          System.out.println(due);
          return due;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      
    }
    return "Unknown Insurance";
  }
  
  /**
   * Method to view information about a prescription
   * @param patientID is the ID of the patient whose prescription info we are finding
   */
  public void viewMedicationInfo(String patientID) {
     Connection con = openDBConnection();
     String sql = "select *\n"
       + "from healthcaremanagement_prescription\n"
       + "where patient_id = ?";
     
     try (PreparedStatement statement = con.prepareStatement(sql)) {
      statement.setString(1, patientID);
       try (ResultSet rs = statement.executeQuery()) {
         while (rs.next()) {
           String prescriptionID = rs.getString("PRESCRIPTION_ID");
           String date = rs.getString("DATE_ISSUED");
           String prescriptionName = rs.getString("PRESCRIPTION_NAME");
           String dosage = rs.getString("DOSAGE");
           String refills = rs.getString("REFILLS_REMAINING");
           String price = rs.getString("PRICE");
           String quantity = rs.getString("QUANTITY");
           String doctorID = rs.getString("DOCTOR_ID");
           //String newPatientID = rs.getString("PATIENT_ID");
           System.out.println(prescriptionID + "\t\t" + date + "\t\t" + prescriptionName + "\t\t" + dosage + "\t\t" + refills + "\t\t" + price + "\t\t" + quantity + "\t\t" + doctorID + "\t\t" + patientID);
         }
       }
     } catch (SQLException e) {
       e.printStackTrace();
     }
  }
  
   
 /**
 * Method for a pharmacy employee to request a refill for a certain medication
 */
  public String requestRefill(String prescriptionName) {
  Connection con = openDBConnection();
  String sql = "update healthcaremanagement_medication \n"
          + "set QUANTITY = 200 \n"
          + "where NAME = ?";
  try (PreparedStatement statement = con.prepareStatement(sql)) {
  statement.setString(1, prescriptionName);
  statement.executeUpdate();
  return "Refill Request Recieved";
  } catch (SQLException e) {
   e.printStackTrace();
   return "Invalid Medication Name";
  }

 }
  
}
