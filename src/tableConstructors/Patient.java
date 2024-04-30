package tableConstructors;
/**
 * @author Matt DeROsa
 */
import java.util.Date;
import java.sql.*;

public class Patient {
    private String patientId;
    private Date dob;
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
  * @param patientId
  * @param dob
  * @param street
  * @param city
  * @param state
  * @param zipCode
  * @param email
  * @param phoneNumber
  * @param lastName
  * @param firstName
  * @param sex
  * @param insuranceId
  * @param password
  * @param loggedIn
  */
 public Patient(String patientId, Date dob, String street, String city, String state, String zipCode, String email,
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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
     if(! isLoggedIn())
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
    
    public boolean login(String email, String password) {
        Connection con = openDBConnection();
        try {
          PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HEALTHCAREMANAGEMENT_PATIENT WHERE email = ? AND password = ?");
          statement.setString(1, email);
          
          statement.setString(2, password);
          
          ResultSet rs = statement.executeQuery();
     
          if(rs.next() && rs.getString(1) != null){
            this.loggedIn = true;
            return true;
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return false;
      }
    
    public void editPatientInfo(String patientId, String phoneNumber, String email, String street, String city, String state, String zipCode, String insuranceId, String sex) {
        Connection con = openDBConnection();
     String sql = "{CALL Edit_Patient_Info(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, patientId);
            statement.setString(2, phoneNumber);
            statement.setString(3, email);
            statement.setString(4, street);
            statement.setString(5, city);
            statement.setString(6, state);
            statement.setString(7, zipCode);
            statement.setString(8, insuranceId);
            statement.setString(9, sex);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String generateRandomPatientId() {
     Connection con = openDBConnection();
        String patientId = null;
        String sql = "{? = CALL Generate_Random_Patient_ID()}";
        try (CallableStatement statement = con.prepareCall(sql)) {
            statement.registerOutParameter(1, java.sql.Types.CHAR);
            statement.execute();
            patientId = statement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientId;
    }
    
    
    public void addPatient(Patient patient) {
     Connection con = openDBConnection();
        String sql = "INSERT INTO HealthCareManagement_Patient (PATIENT_ID, DOB, STREET, CITY, STATE, ZIP_CODE, EMAIL, PHONE_NUMBER, LAST, FIRST, SEX, INSURANCE_ID, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Generate patient ID using the function
            String patientId = generateRandomPatientId();

            // Set values for other columns
            statement.setString(1, patientId);
            statement.setDate(2, new java.sql.Date(patient.getDob().getTime()));
            statement.setString(3, patient.getStreet());
            statement.setString(4, patient.getCity());
            statement.setString(5, patient.getState());
            statement.setString(6, patient.getZipCode());
            statement.setString(7, patient.getEmail());
            statement.setString(8, patient.getPhoneNumber());
            statement.setString(9, patient.getLastName());
            statement.setString(10, patient.getFirstName());
            statement.setString(11, patient.getSex());
            statement.setString(12, patient.getInsuranceId());
            statement.setString(13, patient.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Patient viewPatientProfile(String patientId) {
     Connection con = openDBConnection();
        Patient patient = null;
        String sql = "SELECT * FROM HealthCareManagement_Patient WHERE PATIENT_ID = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new Patient(
                        resultSet.getString("PATIENT_ID"),
                        resultSet.getDate("DOB"),
                        resultSet.getString("STREET"),
                        resultSet.getString("CITY"),
                        resultSet.getString("STATE"),
                        resultSet.getString("ZIP_CODE"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PHONE_NUMBER"),
                        resultSet.getString("LAST"),
                        resultSet.getString("FIRST"),
                        resultSet.getString("SEX"),
                        resultSet.getString("INSURANCE_ID"),
                        resultSet.getString("PASSWORD")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }
    
    @SuppressWarnings("deprecation")
 public static void main(String[] args) {
        // Creating a sample patient
        Patient patient = new Patient();
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

        // Testing login functionality
        System.out.println("Logging in...");
        System.out.println(patient.login("patient1@email.com", "thsbaibniincd58n"));

        // Testing editing patient info
        System.out.println("\nEditing patient info...");
        patient.editPatientInfo(patient.getPatientId(), "987-654-3210", "updatedemail1@example.com", "789 Health Ave",
                "Carecity", "CA", "34567", "INS003", "Male");

        // Testing viewing patient profile
        System.out.println("\nViewing patient profile...");
        Patient retrievedPatient = patient.viewPatientProfile(patient.getPatientId());
        if (retrievedPatient != null) {
            System.out.println(retrievedPatient.toString());
        } else {
            System.out.println("Patient not found!");
        }

        // Testing adding a new patient
        System.out.println("\nAdding new patient...");
        Patient newPatient = new Patient();
        newPatient.setFirstName("John");
        newPatient.setLastName("Smith");
        newPatient.setEmail("patient7@email.com");
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

        // Testing viewing added patient profile
        System.out.println("\nViewing added patient profile...");
        retrievedPatient = patient.viewPatientProfile("PAT001");
        if (retrievedPatient != null) {
            System.out.println(retrievedPatient.toString());
        } else {
            System.out.println("Patient not found!");
        }
    }
   
}
