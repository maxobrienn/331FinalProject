package tableConstructors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Matt DeROsa
 */
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String password;
    private String email;

    public Supplier() {
    }

    public Supplier(String supplierId, String supplierName, String street, String city, String state, String zipCode, String phoneNumber, String password, String email) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

 /**
  * @return the supplierId
  */
 public String getSupplierId() {
  return supplierId;
 }

 /**
  * @param supplierId the supplierId to set
  */
 public void setSupplierId(String supplierId) {
  this.supplierId = supplierId;
 }

 /**
  * @return the supplierName
  */
 public String getSupplierName() {
  return supplierName;
 }

 /**
  * @param supplierName the supplierName to set
  */
 public void setSupplierName(String supplierName) {
  this.supplierName = supplierName;
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
 
 

    @Override
 public String toString() {
  return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", street=" + street
    + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber
    + ", password=" + password + ", email=" + email + "]";
 }

 // Getters and setters
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

                System.out.println("Patient ID: " + patient.getPatientId());
                System.out.println("Phone Number: " + patient.getPhoneNumber());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("Street: " + patient.getStreet());
                System.out.println("City: " + patient.getCity());
                System.out.println("State: " + patient.getState());
                System.out.println("Zip Code: " + patient.getZipCode());
                System.out.println("Insurance ID: " + patient.getInsuranceId());
                System.out.println("Sex: " + patient.getSex());
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
 