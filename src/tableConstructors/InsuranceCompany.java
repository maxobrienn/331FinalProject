package tableConstructors;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.text.*;

/**
 * This class represents an Insurance Company with its attributes and functionalities.
 * It includes methods for login, logout, adding insurance companies, making payments, and viewing covered patients' information.
 * It also provides database connectivity methods and utility methods for generating IDs and formatting dates.
 * 
 * @author Matt DeRosa, Ellie Smith, Evan Quinn, Max O'Brien, and Mason Meyer
 */
@SuppressWarnings("unused")

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
  private BigDecimal percent;
  
  /**
   * The following stores whether or not the customer has successfully logged
   * to the System
   */    
  private boolean loggedIn = false;

  
  //need log in method
  
  /**
   * Default constructor
   */
  public InsuranceCompany() {
  }
  
  /**
   * Parameterized constructor
   */
  public InsuranceCompany(String insuranceId, String insuranceName, String street, String city, String state, String zipCode, String phoneNumber, String email, String password, BigDecimal percent) {
    this.insuranceId = insuranceId;
    this.insuranceName = insuranceName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.password = password;
    this.setPercent(percent);
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
  public boolean insuranceCompanyLogin(String insuanceCompanyId, String password) {

    Connection con = openDBConnection();
    try {
      PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_INSURANCECOMPANY WHERE INSURANCE_ID = ? AND PASSWORD = ?");
      statement.setString(1, insuanceCompanyId);
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
     
  
  public void addInsuranceCompany(InsuranceCompany insuranceCompany) {
     try (Connection connection = openDBConnection()) {
         // Generate a new insurance company ID using stored procedure
         CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_Insurance_ID}");
         callableStatement.registerOutParameter(1, Types.CHAR);
         callableStatement.execute();

         String generatedId = callableStatement.getString(1);
         callableStatement.close();

         // Insert insurance company data into the database
         String sql = "INSERT INTO HealthCareManagement_INSURANCECOMPANY (INSURANCE_ID, INSURANCE_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, EMAIL, PASSWORD, PERCENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, generatedId);
         preparedStatement.setString(2, insuranceCompany.getInsuranceName());
         preparedStatement.setString(3, insuranceCompany.getStreet());
         preparedStatement.setString(4, insuranceCompany.getCity());
         preparedStatement.setString(5, insuranceCompany.getState());
         preparedStatement.setString(6, insuranceCompany.getZipCode());
         preparedStatement.setString(7, insuranceCompany.getPhoneNumber());
         preparedStatement.setString(8, insuranceCompany.getEmail());
         preparedStatement.setString(9, insuranceCompany.getPassword());
         preparedStatement.setBigDecimal(10, insuranceCompany.getPercent());

         preparedStatement.executeUpdate();
         System.out.println("Insurance Company added successfully with ID: " + generatedId);

         preparedStatement.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
  
  // Method to update insurance company information
public void updateInsuranceCompanyInfo(String phoneNumber, String email, String street,
                                       String city, String state, String zipCode, String insuranceName,
                                       BigDecimal percent) {
    try {
        // Connect to Oracle database
        Connection connection = openDBConnection();

        // Prepare the stored procedure call
        CallableStatement callableStatement = connection.prepareCall("{call Edit_InsuranceCompany_Info(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

        // Set the input parameters
        callableStatement.setString(1, getInsuranceId());
        callableStatement.setString(2, phoneNumber);
        callableStatement.setString(3, email);
        callableStatement.setString(4, street);
        callableStatement.setString(5, city);
        callableStatement.setString(6, state);
        callableStatement.setString(7, zipCode);
        callableStatement.setString(8, insuranceName);
        callableStatement.setBigDecimal(9, percent);

        // Execute the stored procedure
        callableStatement.execute();

        // Output success message
        System.out.println("Insurance company information updated successfully.");

        // Close JDBC objects
        callableStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

  
  public InsuranceCompany displayInsuranceCompanyInfo(String insuranceId) {
    InsuranceCompany insuranceCompany = new InsuranceCompany();
    Connection con = openDBConnection();
    try {
        // Prepare and execute SQL query to retrieve insurance company information
        String sql = "SELECT * FROM HealthCareManagement_INSURANCECOMPANY WHERE INSURANCE_ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, insuranceId);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Set insurance company information
        while (resultSet.next()) {
            insuranceCompany.setInsuranceId(resultSet.getString("INSURANCE_ID"));
            insuranceCompany.setInsuranceName(resultSet.getString("INSURANCE_NAME"));
            insuranceCompany.setStreet(resultSet.getString("STREET"));
            insuranceCompany.setCity(resultSet.getString("CITY"));
            insuranceCompany.setState(resultSet.getString("STATE"));
            insuranceCompany.setZipCode(resultSet.getString("ZIP_CODE"));
            insuranceCompany.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
            insuranceCompany.setEmail(resultSet.getString("EMAIL"));
            insuranceCompany.setPassword(resultSet.getString("PASSWORD"));
            insuranceCompany.setPercent(resultSet.getBigDecimal("PERCENT"));
        }

        // Close JDBC objects
        resultSet.close();
        preparedStatement.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return insuranceCompany;
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
     * @param AMOUNT
     * @param PRESCRIPTION_ID
     */
    public void makePayment(String AMOUNT, String PRESCRIPTION_ID) {
        // Variable of type database connection
        Connection myConnection;
        // Variable of type prepared statement
        PreparedStatement preparedStmt;
        
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
          String insuranceId = this.insuranceId; // Assuming insuranceId is a field in the InsuranceCompany class
          
            // Prepare the SQL statement with placeholders
            String sqlStatement = "INSERT INTO HealthCareManagement_INSURANCEPAYMENT (PAYMENT_ID, PAYMENT_DATE, AMOUNT, INSURANCE_ID, PRESCRIPTION_ID) " +
                                  "VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";
            
            // Create a PreparedStatement for executing the statement
            preparedStmt = myConnection.prepareStatement(sqlStatement);
            
            // Set the values for the placeholders
            preparedStmt.setString(1, paymentId);
            preparedStmt.setString(2, paymentDate);
            preparedStmt.setString(3, AMOUNT);
            preparedStmt.setString(4, insuranceId);
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
      String sql = "SELECT COUNT(*) FROM HealthCareManagement_INSURANCEPAYMENT WHERE PAYMENT_ID = ?";
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
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = new java.util.Date();
    return dateFormat.format(date);
  }
  
  
  
  /**
   * Method that allows insurance companies to view Covered Patients Information
   * @return a two-dimensional array of strings representing the patient information
   */
  public String[][] viewCoveredPatientsInformation() {
    Connection myConnection;
    PreparedStatement preparedStmt;
    List<String[]> patientData = new ArrayList<>();
    
    try {
      myConnection = openDBConnection();
      
      // Prepare the SQL statement
      String queryString = "SELECT * FROM Insurance_Company_Covered_Patients WHERE INSURANCE_ID = ?";
      preparedStmt = myConnection.prepareStatement(queryString);
      preparedStmt.setString(1, getInsuranceId());
      
      ResultSet rs = preparedStmt.executeQuery(); 
      
      // Iterate through the result set and add each row to the list
      while (rs.next()) {
        String patientId = rs.getString("PATIENT_ID");
        String patientName = rs.getString("PATIENT_NAME");
        String prescriptionId = rs.getString("PRESCRIPTION_ID"); // New prescription ID
        String insuranceIdResult = rs.getString("INSURANCE_ID");
        String amountOwed = String.format("%.2f", rs.getDouble("AMOUNT_OWED"));
        patientData.add(new String[]{patientId, patientName, prescriptionId, insuranceIdResult, amountOwed});
      }
      
      // Close resources
      rs.close();
      preparedStmt.close();
      myConnection.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    // Convert the list to a two-dimensional array
    return patientData.toArray(new String[0][0]);
  }


  
  /**
   * Main method to test JDBC methods
   */
  public static void main(String[] args) {
    try {
      // Create an instance of InsuranceCompanyController
      InsuranceCompany test = new InsuranceCompany();
      
      // Call the makePayment method with the given parameters
      test.setInsuranceId("INS001");
      test.makePayment("2.5","PRSC001");
      
      // Call the instance method on the created instance to view covered patients information
      test.viewCoveredPatientsInformation();
    } 
    
    catch (Exception e) {
      e.printStackTrace();
    }
  }

/**
 * @return the percent
 */
public BigDecimal getPercent() {
 return percent;
}

/**
 * @param percent the percent to set
 */
public void setPercent(BigDecimal percent) {
 this.percent = percent;
}
}
