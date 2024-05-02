package tableConstructors;
import java.sql.*;

/**
 * @author Ellie Smith
 */
public class InsuranceCompany {
  private String insuranceId;
  private String insuranceName;
  private String street;
  private String city;
  private String state;
  private String zipCode;
  private String phoneNumber;
  private String email;
  private String password;
  
  /**
   * The following stores whether or not the customer has successfully logged
   * to the System
   */    
  private boolean loggedIn = false;
  
  //need log in method
  
  public InsuranceCompany() {
  }
  
  public InsuranceCompany(String insuranceId, String insuranceName, String street, String city, String state, String zipCode, String phoneNumber, String email, String password) {
    this.insuranceId = insuranceId;
    this.insuranceName = insuranceName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
  }
  
  /**
   * A getter for instance field loggedIn
   * @return whether the Customer is logged in or not
   */
  public Boolean isLoggedIn() {
    return this.loggedIn;
  }
  
  /**
   * sets loggedIn instance field to false
   * @throws IllegalStateException if then method is called when loggedIn = false
   */
  public void logout(){
    if(! isLoggedIn())
      throw new IllegalStateException("MUST BE LOGGED IN FIRST!");
    
    this.loggedIn = false;
  }
  
  /**
   * @return the insuranceId
   */
  public String getInsuranceId() {
    return insuranceId;
  }
  
  /**
   * @param insuranceId the insuranceId to set
   */
  public void setInsuranceId(String insuranceId) {
    this.insuranceId = insuranceId;
  }
  
  /**
   * @return the insuranceName
   */
  public String getInsuranceName() {
    return insuranceName;
  }
  
  /**
   * @param insuranceName the insuranceName to set
   */
  public void setInsuranceName(String insuranceName) {
    this.insuranceName = insuranceName;
  }
  
  /**
   * @return the street
   */
  public String getStreet() {
    return street;
  }
  
  /**
   * @param street the street to set
   */
  public void setStreet(String street) {
    this.street = street;
  }
  
  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }
  
  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }
  
  /**
   * @return the state
   */
  public String getState() {
    return state;
  }
  
  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }
  
  /**
   * @return the zipCode
   */
  public String getZipCode() {
    return zipCode;
  }
  
  /**
   * @param zipCode the zipCode to set
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
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
 return "InsuranceCompany [insuranceId=" + insuranceId + ", insuranceName=" + insuranceName + ", street=" + street
   + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber
   + ", email=" + email + ", password=" + password + ", loggedIn=" + loggedIn + "]";
}

/**
   * This method and creates and returns a Connection object to the database. 
   * All other methods that need database access will call this method.
   * @return a Connection object to Oracle
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
   * 
   */
  public boolean insuranceCompanyLogin(String email, String password) {
	    Connection con = openDBConnection();
	    try {
	        PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_INSURANCECOMPANY WHERE EMAIL = ? AND PASSWORD = ?");
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

  
  /**
   * TO DO!!!
   * Method to update insurance company information
   * 
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
    */
  
  /**
   * This method allows InsuranceCompanies to make a payment by inserting a value into HealthCareManagement_INSURANCEPAYMENT
   * After inserting, trigger ChangePrescriptionBalanceAfterInsurancePayment alters InsuranceBalance in 
   * HealthCareManagement_PRESCRIPTIONBALANCE table
   * @param PAYMENT_ID
   * @param PAYMENT_DATE
   * @param AMOUNT
   * @param INSURANCE_ID
   * @param PRESCRIPTION_ID
   */
  public void makePayment(String PAYMENT_ID, String PAYMENT_DATE, String AMOUNT, String INSURANCE_ID, String PRESCRIPTION_ID) {
    // Variable of type database connection
    Connection myConnection;
    // Variable of type prepared statement
    PreparedStatement preparedStmt;
    
    try {
      // Open a database connection.
      myConnection = openDBConnection();
      
      // Prepare the SQL statement with placeholders
      String sqlStatement = "INSERT INTO HealthCareManagement_INSURANCEPAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, INSURANCE_ID, PRESCRIPTION_ID) " +
        "VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
      
      // Create a PreparedStatement for executing the statement
      preparedStmt = myConnection.prepareStatement(sqlStatement);
      
      // Set the values for the placeholders
      preparedStmt.setString(1, PAYMENT_ID);
      preparedStmt.setString(2, PAYMENT_DATE);
      preparedStmt.setString(3, AMOUNT);
      preparedStmt.setString(4, INSURANCE_ID);
      preparedStmt.setString(5, PRESCRIPTION_ID);
      
      // Execute the prepared statement
      preparedStmt.executeUpdate();
      
      // Print success message
      System.out.println("Payment successfully made.");
    } 
    
    catch (SQLException e) {
      // Handle any SQL exceptions that occur during the operation.
      e.printStackTrace();
    }
  }
  
  /**
   * Method that allows insurance companies to view Covered Patients Information
   * 
   * @param INSURANCEID -- NEED TO REMOVE THIS TO GET INSURANCEID METHOD
   * 
   */
  public void viewCoveredPatientsInformation(String INSURANCEID) {
    
    Connection myConnection;
    PreparedStatement preparedStmt;
    
    try {
      myConnection = openDBConnection();
      
      // Prepare the SQL update statement.
      String queryString = "SELECT * FROM Insurance_Company_Covered_Patients WHERE INSURANCE_ID = ?";
      
      preparedStmt = myConnection.prepareStatement(queryString);
      
      preparedStmt.setString(1, INSURANCEID);
      
      ResultSet rs = preparedStmt.executeQuery(); 
      
      // Print the column headers
      System.out.println("PATIENT_ID\tPATIENT_NAME\tINSURANCE_ID\tAMOUNT_OWED");
      
      // Iterate through the result set and print each row
      while (rs.next()) {
        String patientId = rs.getString("PATIENT_ID");
        String patientName = rs.getString("PATIENT_NAME");
        String insuranceIdResult = rs.getString("INSURANCE_ID");
        double amountOwed = rs.getDouble("AMOUNT_OWED");
        System.out.println(patientId + "\t\t" + patientName + "\t\t" + insuranceIdResult + "\t\t" + amountOwed);
      }
    }
    
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  
  /**
   * Main method to test JDBC methods
   */
  public static void main(String[] args) {
    try {
      // Create an instance of InsuranceCompanyController
      InsuranceCompany test = new InsuranceCompany();
      
      // Call the makePayment method with the given parameters
      test.makePayment("PAY011", "2024-04-29", "2.5", "INS002", "PRSC001");
      
      // Call the instance method on the created instance to view covered patients information
      test.viewCoveredPatientsInformation("INS002");
    } 
    
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
