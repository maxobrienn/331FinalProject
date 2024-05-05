package tableConstructors;

import tableConstructors.AppointmentDetails;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;

/**
 * A class representing a patient in the healthcare management system.
 * 
 * @author Matt DeRosa, Ellie Smith, Evan Quinn, Max O'Brien, and Mason Meyer
 */
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
   * Constructor with parameters.
   * 
   * @param patientId The ID of the patient.
   * @param dob The date of birth of the patient.
   * @param street The street address of the patient.
   * @param city The city of the patient.
   * @param state The state of the patient.
   * @param zipCode The ZIP code of the patient.
   * @param email The email address of the patient.
   * @param phoneNumber The phone number of the patient.
   * @param lastName The last name of the patient.
   * @param firstName The first name of the patient.
   * @param sex The gender of the patient.
   * @param insuranceId The insurance ID of the patient.
   * @param password The password of the patient.
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
  
  /**
   * Get the patient ID.
   * 
   * @return The patient ID.
   */
  public String getPatientId() {
    return patientId;
  }
  
  /**
   * Set the patient ID.
   * 
   * @param patientId The patient ID to set.
   */
  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }
  
  /**
   * Get the date of birth of the patient.
   * 
   * @return The date of birth.
   */
  public java.util.Date getDob() {
    return dob;
  }
  
  /**
   * Set the date of birth of the patient.
   * 
   * @param dob The date of birth to set.
   */
  public void setDob(java.util.Date dob) {
    this.dob = dob;
  }
  
  /**
   * Get the street address of the patient.
   * 
   * @return The street address.
   */
  public String getStreet() {
    return street;
  }
  
  /**
   * Set the street address of the patient.
   * 
   * @param street The street address to set.
   */
  public void setStreet(String street) {
    this.street = street;
  }
  
  /**
   * Get the city of the patient.
   * 
   * @return The city.
   */
  public String getCity() {
    return city;
  }
  
  /**
   * Set the city of the patient.
   * 
   * @param city The city to set.
   */
  public void setCity(String city) {
    this.city = city;
  }
  
  /**
   * Get the state of the patient.
   * 
   * @return The state.
   */
  public String getState() {
    return state;
  }
  
  /**
   * Set the state of the patient.
   * 
   * @param state The state to set.
   */
  public void setState(String state) {
    this.state = state;
  }
  
  /**
   * Get the ZIP code of the patient.
   * 
   * @return The ZIP code.
   */
  public String getZipCode() {
    return zipCode;
  }
  
  /**
   * Set the ZIP code of the patient.
   * 
   * @param zipCode The ZIP code to set.
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  
  /**
   * Get the email address of the patient.
   * 
   * @return The email address.
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Set the email address of the patient.
   * 
   * @param email The email address to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * Get the phone number of the patient.
   * 
   * @return The phone number.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  /**
   * Set the phone number of the patient.
   * 
   * @param phoneNumber The phone number to set.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  /**
   * Get the last name of the patient.
   * 
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Set the last name of the patient.
   * 
   * @param lastName The last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * Get the first name of the patient.
   * 
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Set the first name of the patient.
   * 
   * @param firstName The first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * Get the gender of the patient.
   * 
   * @return The gender.
   */
  public String getSex() {
    return sex;
  }
  
  /**
   * Set the gender of the patient.
   * 
   * @param sex The gender to set.
   */
  public void setSex(String sex) {
    this.sex = sex;
  }
  
  /**
   * Get the insurance ID of the patient.
   * 
   * @return The insurance ID.
   */
  public String getInsuranceId() {
    return insuranceId;
  }
  
  /**
   * Set the insurance ID of the patient.
   * 
   * @param insuranceId The insurance ID to set.
   */
  public void setInsuranceId(String insuranceId) {
    this.insuranceId = insuranceId;
  }
  
  /**
   * Get the password of the patient.
   * 
   * @return The password.
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Set the password of the patient.
   * 
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * Check if the patient is logged in.
   * 
   * @return True if the patient is logged in, otherwise false.
   */
  public Boolean isLoggedIn() {
    return this.loggedIn;
  }
  
  /**
   * Logout the patient.
   * 
   * @throws IllegalStateException if the method is called when logged in is false.
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
  
  /**
   * Open a database connection.
   * 
   * @return The database connection.
   */
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
  
  /**
   * Login functionality for the patient.
   * 
   * @param email The email of the patient.
   * @param password The password of the patient.
   * @return True if the login is successful, otherwise false.
   */
  public boolean patientLogin(String patientId, String password) {
    Connection con = openDBConnection();
    try {
      PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_PATIENT WHERE PATIENT_ID = ? AND PASSWORD = ?");
      statement.setString(1, patientId);
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
  
  
  
  public Patient displayPatientInfo(String patientId) {
    Patient patient = new Patient();
    Connection con = openDBConnection();
    try {
      // Prepare and execute SQL query to retrieve patient information
      String sql = "SELECT * FROM HealthCareManagement_PATIENT WHERE PATIENT_ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, patientId);
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
   * View prescription balances for the patient.
   */
  public void viewPrescriptionBalances() {
    
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
      preparedStmt.setString(1, getPatientId());
      
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
   * View diagnoses for the patient.
   */
  public void viewDiagnoses() {
    
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
      preparedStmt.setString(1, getPatientId());
      
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
   * Make a payment for the patient.
   * 
   * @param AMOUNT The amount to pay.
   * @param PRESCRIPTION_ID The ID of the prescription.
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
      
      // Execute the statement
      preparedStmt.executeUpdate();
      
      // Print success message
      System.out.println("Payment made successfully. Payment ID: " + paymentId);
    } catch (SQLException e) {
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
  
  /**
   * Generate a unique payment ID.
   * 
   * @return The generated payment ID.
   */
  private String generatePaymentId() {
    // Generate a random UUID
    UUID uuid = UUID.randomUUID();
    // Convert UUID to String and return it
    return uuid.toString();
  }
  
  /**
   * Check if a payment ID already exists in the database.
   * 
   * @param paymentId The payment ID to check.
   * @param connection The database connection.
   * @return True if the payment ID exists, otherwise false.
   */
  private boolean isPaymentIdExists(String paymentId, Connection connection) {
    try {
      // Prepare SQL query to check if payment ID exists
      String sqlQuery = "SELECT COUNT(*) FROM HealthCareManagement_PATIENTPAYMENT WHERE PAYMENT_ID = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      preparedStatement.setString(1, paymentId);
      
      // Execute query
      ResultSet resultSet = preparedStatement.executeQuery();
      
      // Get count
      resultSet.next();
      int count = resultSet.getInt(1);
      
      // Return true if count > 0, otherwise false
      return count > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false; // Return false in case of any exception
    }
  }
  
  /**
   * Get the current date in the format "YYYY-MM-DD".
   * 
   * @return The current date.
   */
  private String getCurrentDate() {
    // Create a SimpleDateFormat object with the desired date format
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // Get the current date
    Date currentDate = new Date(System.currentTimeMillis());
    // Format the current date and return it
    return dateFormat.format(currentDate);
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
    System.out.println(patient.displayPatientInfo(patient.getPatientId()).toString());
    patient.updatePatientInfo("987-654-3210", "upatient@example.com", "789 Health Ave",
                              "Carecity", "CA", "34567", "INS003", "Male"); 
    System.out.println("\nPatient info after update:");
    System.out.println(patient.displayPatientInfo(patient.getPatientId()).toString());
    
    // Test viewing patient profile
    System.out.println("\nTesting viewing patient profile...");
    System.out.println(patient.displayPatientInfo(patient.getPatientId()).toString());
    
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
    System.out.println(newPatient.displayPatientInfo(newPatient.getPatientId()).toString());
    
    
    // Test viewing prescription balances
    System.out.println("\nTesting viewing prescription balances...");
    patient.setPatientId("PAT001");
    patient.viewPrescriptionBalances();
    
    //Test viewing patient diagnosis
    System.out.println("\nTesting viewing patient diagnosises...");
    patient.viewDiagnoses();
    
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

