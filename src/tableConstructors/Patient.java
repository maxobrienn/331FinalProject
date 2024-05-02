package tableConstructors;

import tableConstructors.AppointmentDetails;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;


@SuppressWarnings("unused")
public class Patient {
  private String patientId;
  private java.util.Date dob;
  private String street;
  private String city;
  private String state;
  private String zipCode;
  private String email;
  private String phoneNumber;
  private String lastName;
  private String firstName;
  private String sex;
  private String insuranceId;
  private String password;
  private Boolean loggedIn = false;
  
  // Constructor without parameters
  public Patient() {
  }
  
  /**
   * Constructor with parameters
   */
  public Patient(String patientId, java.util.Date dob, String street, String city, String state, String zipCode, String email,
                 String phoneNumber, String lastName, String firstName, String sex, String insuranceId, String password) {
    this.patientId = patientId;
    this.dob = dob;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.lastName = lastName;
    this.firstName = firstName;
    this.sex = sex;
    this.insuranceId = insuranceId;
    this.password = password;
  }
  
  
  // Getters and Setters
  public String getPatientId() {
    return patientId;
  }
  
  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }
  
  public java.util.Date getDob() {
    return dob;
  }
  
  public void setDob(java.util.Date dob) {
    this.dob = dob;
  }
  
  public String getStreet() {
    return street;
  }
  
  public void setStreet(String street) {
    this.street = street;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getState() {
    return state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public String getZipCode() {
    return zipCode;
  }
  
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getSex() {
    return sex;
  }
  
  public void setSex(String sex) {
    this.sex = sex;
  }
  
  public String getInsuranceId() {
    return insuranceId;
  }
  
  public void setInsuranceId(String insuranceId) {
    this.insuranceId = insuranceId;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * @return the loggedIn status
   */
  public Boolean isLoggedIn() {
    return this.loggedIn;
  }
  
  /**
   * sets loggedIn instance field to false
   * @throws IllegalStateException if then method is called when loggedIn = false
   */
  public void logout(){
    if(!isLoggedIn())
      throw new IllegalStateException("MUST BE LOGGED IN FIRST!");
    
    this.loggedIn = false;
  }
  
  @Override
  public String toString() {
    return "Patient{" +
      "patientId='" + patientId + '\'' +
      ", dob=" + dob +
      ", street='" + street + '\'' +
      ", city='" + city + '\'' +
      ", state='" + state + '\'' +
      ", zipCode='" + zipCode + '\'' +
      ", email='" + email + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", lastName='" + lastName + '\'' +
      ", firstName='" + firstName + '\'' +
      ", sex='" + sex + '\'' +
      ", insuranceId='" + insuranceId + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
  
  public Connection openDBConnection() {
    try {
      // Load driver and link to driver manager
      Class.forName("oracle.jdbc.OracleDriver");
      // Create a connection to the specified database
      Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                            "csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");
      return myConnection;
    } catch (Exception E) {
      E.printStackTrace();
    }
    return null;
  }
  
  public boolean patientLogin(String email, String password) {
    Connection con = openDBConnection();
    try {
      PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_PATIENT WHERE EMAIL = ? AND PASSWORD = ?");
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
  
  // Method to update patient information
  public void updatePatientInfo(String phoneNumber, String email, String street, String city, String state, String zipCode, String insuranceId, String sex) {
    try {
      // Connect to Oracle database
      Connection connection = openDBConnection();
      
      // Prepare the stored procedure call
      CallableStatement callableStatement = connection.prepareCall("{call Edit_Patient_Info(?,?,?,?,?,?,?,?,?)}");
      
      // Set the input parameters
      callableStatement.setString(1,getPatientId());
      callableStatement.setString(2, phoneNumber);
      callableStatement.setString(3, email);
      callableStatement.setString(4, street);
      callableStatement.setString(5, city);
      callableStatement.setString(6, state);
      callableStatement.setString(7, zipCode);
      callableStatement.setString(8, insuranceId);
      callableStatement.setString(9, sex);
      
      // Execute the stored procedure
      callableStatement.execute();
      
      // Output success message
      System.out.println("Patient information updated successfully.");
      
      // Close JDBC objects
      callableStatement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
    
    /**
     * This method displays all doctors, their info, and their specialties so patients can view and find the best doctor to schedule with 
     */
    /**
 * This method displays all doctors, their info, and their specialties so patients can view and find the best doctor to schedule with 
 */
public void viewDoctorList(){
    //Variable of type database connection
    Connection myConnection;
    //Variable of type prepared statement
    PreparedStatement preparedStmt;
    
    try {
        // Open a database connection.
        myConnection = openDBConnection();
        
        // Prepare the SQL statement.
        String queryString = "SELECT LAST, FIRST, EMAIL, SPECIALIZATION FROM HealthCareManagement_DOCTOR";
        
        // Create a PreparedStatement for executing the query.
        preparedStmt = myConnection.prepareStatement(queryString);
        
        // Execute the query
        ResultSet rs = preparedStmt.executeQuery(); 
        
        // Print the column headers
        System.out.println("LAST\tFIRST\tEMAIL\tSPECIALIZATION");
        
        // Iterate through the result set and print each row
        while (rs.next()) {
            String dLast = rs.getString("LAST");
            String dFirst = rs.getString("FIRST");
            String dEmail = rs.getString("EMAIL");
            String dSpecialization = rs.getString("SPECIALIZATION");
            System.out.println(dLast + "\t\t" + dFirst + "\t\t" + dEmail + "\t\t" + dSpecialization);
        }
        
        // Close the resources
        rs.close();
        preparedStmt.close();
        myConnection.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

      
      

  
  public void addPatient(Patient patient) {
    try (Connection connection = openDBConnection()) {
      // Generate a new patient ID
      CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_Patient_ID}");
      callableStatement.registerOutParameter(1, Types.CHAR);
      callableStatement.execute();

      
      String generatedId = callableStatement.getString(1);
      callableStatement.close();
      
      String sql = "INSERT INTO HealthCareManagement_Patient (PATIENT_ID, FIRST, LAST, EMAIL, PASSWORD, DOB, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, SEX, INSURANCE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, generatedId);
      preparedStatement.setString(2, patient.getFirstName());
      preparedStatement.setString(3, patient.getLastName());
      preparedStatement.setString(4, patient.getEmail());
      preparedStatement.setString(5, patient.getPassword());
      preparedStatement.setDate(6, new java.sql.Date(patient.getDob().getTime()));
      preparedStatement.setString(7, patient.getStreet());
      preparedStatement.setString(8, patient.getCity());
      preparedStatement.setString(9, patient.getState());
      preparedStatement.setString(10, patient.getZipCode());
      preparedStatement.setString(11, patient.getPhoneNumber());
      preparedStatement.setString(12, patient.getSex());
      preparedStatement.setString(13, patient.getInsuranceId());
      
      preparedStatement.executeUpdate();
      System.out.println("Patient added successfully with ID: " + generatedId);
      
      preparedStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  
  
  public Patient displayPatientInfo() {
    Patient patient = new Patient();
    Connection con = openDBConnection();
    try {
      // Prepare and execute SQL query to retrieve patient information
      String sql = "SELECT * FROM HealthCareManagement_PATIENT WHERE PATIENT_ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, getPatientId());
      ResultSet resultSet = preparedStatement.executeQuery();
      
      // Print patient information
      while (resultSet.next()) {
        patient.setPatientId(resultSet.getString("PATIENT_ID"));
        patient.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
        patient.setEmail(resultSet.getString("EMAIL"));
        patient.setStreet(resultSet.getString("STREET"));
        patient.setCity(resultSet.getString("CITY"));
        patient.setState(resultSet.getString("STATE"));
        patient.setZipCode(resultSet.getString("ZIP_CODE"));
        patient.setInsuranceId(resultSet.getString("INSURANCE_ID"));
        patient.setSex(resultSet.getString("SEX"));
      }
      
      // Close JDBC objects
      resultSet.close();
      preparedStatement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return patient;
  }
  
  /**
   * 
   * 
   */
  public void viewPrescriptionBalances(String patientId) {
    
    //Variable of type database connection
    Connection myConnection;
    //Variable of type prepared statement
    PreparedStatement preparedStmt;
    
    try {
      // Open a database connection.
      myConnection = openDBConnection();
      
      // Prepare the SQL update statement.
      String queryString = "SELECT * FROM Patient_Prescription_Balance WHERE PATIENT_ID = ?";
      
      // Create a PreparedStatement for executing the update.
      preparedStmt = myConnection.prepareStatement(queryString);
      
      // Bind the instance field values to the PreparedStatement's parameters.
      preparedStmt.setString(1, patientId);
      
      // Execute the query
      ResultSet rs = preparedStmt.executeQuery(); 
      
      // Print the column headers
      System.out.println("PATIENT_ID\tPRESCRIPTION_ID\tDATE_ISSUED\tPRESCRIPTION_NAME\tAMOUNT_OWED");
      
      // Iterate through the result set and print each row
      while (rs.next()) {
        String pId = rs.getString("PATIENT_ID");
        String prescriptionId = rs.getString("PRESCRIPTION_ID");
        java.util.Date dateIssued = rs.getDate("DATE_ISSUED");
        String prescriptionName = rs.getString("PRESCRIPTION_NAME");
        double amountOwed = rs.getDouble("AMOUNT_OWED");
        System.out.println(pId + "\t\t" + prescriptionId + "\t\t" + dateIssued + "\t" + prescriptionName + "\t" + amountOwed);
      }
    }
    catch (SQLException e) {
        e.printStackTrace();
      }
  }
     /**
     * This method allows users to view their diagnosis after they have a visit by matching PatientID with diagnosis in the HealthCareManagement_DIAGNOSIS table
     * @param PATIENT_ID
     */
    public void viewDiagnoses(String patientId) {
      
      //Variable of type database connection
      Connection myConnection;
      //Variable of type prepared statement
      PreparedStatement preparedStmt;
      
      try {
        // Open a database connection.
        myConnection = openDBConnection();
        
        // Prepare the SQL update statement.
        String queryString = "SELECT * FROM DOCTOR_PATIENT_DIAGNOSES WHERE PATIENT_ID = ?";
        
        // Create a PreparedStatement for executing the update.
        preparedStmt = myConnection.prepareStatement(queryString);
        
        // Bind the instance field values to the PreparedStatement's parameters.
        preparedStmt.setString(1, patientId);
        
        // Execute the query
        ResultSet rs = preparedStmt.executeQuery(); 
        
        // Print the column headers
        System.out.println("PATIENT_ID\t          DIAGNOSES");
        
        // Iterate through the result set and print each row
        while (rs.next()) {
          String pId = rs.getString("PATIENT_ID");
          String diagnoses = rs.getString("DIAGNOSES");
          System.out.println(pId + "\t\t" + diagnoses);
        }
      }
      
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
    /**

    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
>>>>>>> b0b5739788bf8f0545996dfd60b054036c505f58
   * This method allows InsuranceCompanies to make a payment by inserting a value into HealthCareManagement_INSURANCEPAYMENT
   * After inserting, trigger ChangePrescriptionBalanceAfterInsurancePayment alters InsuranceBalance in 
   * HealthCareManagement_PRESCRIPTIONBALANCE table
   * @param PAYMENT_ID
   * @param PAYMENT_DATE
   * @param AMOUNT
   * @param INSURANCE_ID
   * @param PRESCRIPTION_ID
   */
  public void makePayment(String AMOUNT, String PRESCRIPTION_ID) {
    // Variable of type database connection
    Connection myConnection = null;
    // Variable of type prepared statement
    PreparedStatement preparedStmt = null;
    ResultSet resultSet = null;
    
    try {
      // Open a database connection.
      myConnection = openDBConnection();
      
      // Initialize payment ID
      String paymentId = null;
      
      // Generate a unique payment ID
      do {
        paymentId = generatePaymentId();
      } while (isPaymentIdExists(paymentId, myConnection)); // Loop until a unique payment ID is generated
      
      // Get current date
      String paymentDate = getCurrentDate();
      
      // Get insurance ID from the insurance object
      String patientId = this.patientId; // Assuming insuranceId is a field in the InsuranceCompany class
      
      // Prepare the SQL statement with placeholders
      String sqlStatement = "INSERT INTO HealthCareManagement_PATIENTPAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, PATIENT_ID, PRESCRIPTION_ID) " +
        "VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
      
      // Create a PreparedStatement for executing the statement
      preparedStmt = myConnection.prepareStatement(sqlStatement);
      
      // Set the values for the placeholders
      preparedStmt.setString(1, paymentId);
      preparedStmt.setString(2, paymentDate);
      preparedStmt.setString(3, AMOUNT);
      preparedStmt.setString(4, patientId);
      preparedStmt.setString(5, PRESCRIPTION_ID);
      
      // Execute the prepared statement
      preparedStmt.executeUpdate();
      
      // Print success message
      System.out.println("Payment successfully made.");
    } 
    
    catch (SQLException e) {
      // Handle any SQL exceptions that occur during the operation.
      e.printStackTrace();
    } finally {
      // Close JDBC objects in finally block
      try {
        if (resultSet != null) resultSet.close();
        if (preparedStmt != null) preparedStmt.close();
        if (myConnection != null) myConnection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
// Method to generate random payment ID
  private String generatePaymentId() {
    Random rand = new Random();
    int num = rand.nextInt(1000); // Generate a random number between 0 and 999
    return "PAY" + String.format("%03d", num); // Format the number to have 3 digits
  }
  
// Method to check if payment ID exists in the database
  private boolean isPaymentIdExists(String paymentId, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      String sql = "SELECT COUNT(*) FROM HealthCareManagement_PATIENTPAYMENT WHERE PAYMENT_ID = ?";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, paymentId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int count = resultSet.getInt(1);
        return count > 0;
      }
    } finally {
      // Close JDBC objects in finally block
      if (resultSet != null) resultSet.close();
      if (preparedStatement != null) preparedStatement.close();
    }
    return false;
  }
  
// Method to get current date in YYYY-MM-DD format
  private String getCurrentDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = new java.util.Date();
    return dateFormat.format(date);
  }
  
  
  public List<AppointmentDetails> getAppointmentDetails() {
    List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
    
    try (Connection connection = openDBConnection()) {
      String sql = "SELECT DOCTOR_NAME, APPOINTMENT_DATE, NOTE, PATIENT_ID FROM appointment_Details WHERE PATIENT_ID = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement .setString(1, getPatientId());
      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
        String doctorName = resultSet.getString("DOCTOR_NAME");
        java.util.Date appointmentDate = resultSet.getDate("APPOINTMENT_DATE");
        String note = resultSet.getString("NOTE");
        String patientId = resultSet.getString("PATIENT_ID");
        
        AppointmentDetails appointmentDetails = new AppointmentDetails(doctorName, appointmentDate, note, patientId);
        appointmentDetailsList.add(appointmentDetails);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return appointmentDetailsList;
  }

 
  
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
      Patient patient = new Patient();
      patient.setPatientId("PAT001");
      patient.setFirstName("Jane");
      patient.setLastName("Doe");
      patient.setEmail("patient1@email.com");
      patient.setPassword("thsbaibniincd58n");
      patient.setDob(new Date(1990, 1, 1));
      patient.setStreet("1234 Life St");
      patient.setCity("Anytown");
      patient.setState("NY");
      patient.setZipCode("12345");
      patient.setPhoneNumber("123-456-7890");
      patient.setSex("Female");
      patient.setInsuranceId("INS001");

      // Test login functionality
      System.out.println("Testing login functionality...");
      boolean loginSuccess = patient.patientLogin("patient9999@email.com", "password");
      System.out.println("Login successful: " + loginSuccess);

      // Test login with incorrect credentials
      System.out.println("\nTesting login with incorrect credentials...");
      patient.setEmail("mderosa912@gov.edu"); 
      patient.setPassword("notmypassword");
      loginSuccess = patient.patientLogin("mderosa912@gov.edu","notmypassword");
      System.out.println("Login successful: " + loginSuccess);

      // Test login with null credentials
      System.out.println("\nTesting login with null credentials...");
      patient.setEmail(null); 
      patient.setPassword(null);
      loginSuccess = patient.patientLogin(null,null);
      System.out.println("Login successful: " + loginSuccess);

      // Test editing patient info
      System.out.println("\nTesting editing patient info...");
      System.out.println("Patient info before update:");
      System.out.println(patient.displayPatientInfo().toString());
      patient.updatePatientInfo("987-654-3210", "upatient@example.com", "789 Health Ave",
              "Carecity", "CA", "34567", "INS003", "Male"); 
      System.out.println("\nPatient info after update:");
      System.out.println(patient.displayPatientInfo().toString());

      // Test viewing patient profile
      System.out.println("\nTesting viewing patient profile...");
      System.out.println(patient.displayPatientInfo().toString());

      // Test adding a new patient
      System.out.println("\nTesting adding new patient...");
      Patient newPatient = new Patient();
      newPatient.setFirstName("John");
      newPatient.setLastName("Smith");
      newPatient.setEmail("patient9999@email.com");
      newPatient.setPassword("password");
      newPatient.setDob(new Date(1980, 5, 15));
      newPatient.setStreet("456 New St");
      newPatient.setCity("Newcity");
      newPatient.setState("TX");
      newPatient.setZipCode("23456");
      newPatient.setPhoneNumber("234-567-8901");
      newPatient.setSex("Male");
      newPatient.setInsuranceId("INS002");

      patient.addPatient(newPatient);
      System.out.println("New patient added successfully!");

      // Test viewing added patient profile
      System.out.println("\nTesting viewing added patient profile...");
      System.out.println(newPatient.displayPatientInfo().toString());
      
      
      // Test viewing prescription balances
      System.out.println("\nTesting viewing prescription balances...");
      patient.viewPrescriptionBalances("PAT001");
      
      //Test viewing patient diagnosis
      System.out.println("\nTesting viewing patient diagnosises...");
      patient.viewDiagnoses("PAT001");
   
      //Test viewing list of doctors and info
      System.out.println("\nTesting viewing doctor list...");
      patient.viewDoctorList();
   
      // Test making payment
      System.out.println("\nTesting making payment...");
      newPatient.makePayment("5", "PRSC001");
      patient.makePayment("2.5",  "PRSC001");

      // Assuming "PRSC001" is a valid prescription ID
      
      // Test logout functionality
      System.out.println("\nTesting logout functionality...");
      patient.logout();
      System.out.println("Logout successful.");
      
      // Test getAppointmentDetails()
      System.out.println("\nTesting getAppointmentDetails()...");
    
      List<AppointmentDetails> appointmentDetailsList = patient.getAppointmentDetails();
      System.out.println("Retrieved appointment details:");
      for (AppointmentDetails appointmentDetails : appointmentDetailsList) {
          System.out.println(appointmentDetails.toString());
         
      }
  }
  }

