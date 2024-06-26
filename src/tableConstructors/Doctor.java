package tableConstructors;

import java.sql.*;
import java.util.Date;
import java.util.Random;

/**
 * Represents a Doctor in the healthcare system.
 * Provides methods to interact with doctor data in the database.
 * 
 * @author Matt DeRosa, Ellie Smith, Evan Quinn, Max O'Brien, and Mason Meyer
 */
public class Doctor {
  private String doctorId;
  private String lastName;
  private String firstName;
  private String email;
  private String password;
  private String specialization;
  private String officeNumber;
  
  private Boolean loggedIn = false;
  
  /**
   * Constructs a new Doctor object.
   */
  public Doctor() {
  }
  
  /**
   * Constructs a new Doctor object with specified attributes.
   * 
   * @param doctorId The unique identifier for the doctor.
   * @param lastName The last name of the doctor.
   * @param firstName The first name of the doctor.
   * @param email The email address of the doctor.
   * @param password The password of the doctor.
   * @param specialization The specialization of the doctor.
   * @param officeNumber The office number of the doctor.
   */
  public Doctor(String doctorId, String lastName, String firstName, String email, String password, String specialization, String officeNumber) {
    this.doctorId = doctorId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.password = password;
    this.specialization = specialization;
    this.officeNumber = officeNumber;
  }
  
  /**
   * Returns the doctor ID.
   * 
   * @return The doctor ID.
   */
  public String getDoctorId() {
    return doctorId;
  }
  
  /**
   * Sets the doctor ID.
   * 
   * @param doctorId The doctor ID to set.
   */
  public void setDoctorId(String doctorId) {
    this.doctorId = doctorId;
  }
  
  /**
   * Returns the last name of the doctor.
   * 
   * @return The last name of the doctor.
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Sets the last name of the doctor.
   * 
   * @param lastName The last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * Returns the first name of the doctor.
   * 
   * @return The first name of the doctor.
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Sets the first name of the doctor.
   * 
   * @param firstName The first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /**
   * Returns the email address of the doctor.
   * 
   * @return The email address of the doctor.
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Sets the email address of the doctor.
   * 
   * @param email The email address to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * Returns the password of the doctor.
   * 
   * @return The password of the doctor.
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Sets the password of the doctor.
   * 
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * Returns the specialization of the doctor.
   * 
   * @return The specialization of the doctor.
   */
  public String getSpecialization() {
    return specialization;
  }
  
  /**
   * Sets the specialization of the doctor.
   * 
   * @param specialization The specialization to set.
   */
  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }
  
  /**
   * Returns the office number of the doctor.
   * 
   * @return The office number of the doctor.
   */
  public String getOfficeNumber() {
    return officeNumber;
  }
  
  /**
   * Sets the office number of the doctor.
   * 
   * @param officeNumber The office number to set.
   */
  public void setOfficeNumber(String officeNumber) {
    this.officeNumber = officeNumber;
  }
  
  /**
   * Returns the login status of the doctor.
   * 
   * @return The login status of the doctor.
   */
  public Boolean getLoggedIn() {
    return loggedIn;
  }
  
  /**
   * Sets the login status of the doctor.
   * 
   * @param loggedIn The login status to set.
   */
  public void setLoggedIn(Boolean loggedIn) {
    this.loggedIn = loggedIn;
  }
  
  /**
   * Generates a random prescription ID with format "PRSC" followed by 4 digits.
   * 
   * @return A randomly generated prescription ID.
   */
  private String generatePrescriptionId() {
    Random rand = new Random();
    int num = rand.nextInt(10000);
    String prescriptionId = "PRSC" + String.format("%04d", num);
    if (isPrescriptionIdExists(prescriptionId)) {
      return generatePrescriptionId();
    } else {
      return prescriptionId;
    }
  }
  
   public void addDoctor(Doctor newDoctor) {
        try (Connection connection = openDBConnection()) {
            // Generate a new doctor ID
            CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_Doctor_ID}");
            callableStatement.registerOutParameter(1, Types.CHAR);
            callableStatement.execute();

            String generatedId = callableStatement.getString(1);
            callableStatement.close();

            String sql = "INSERT INTO HealthCareManagement_Doctor " +
                    "(DOCTOR_ID, LAST, FIRST, EMAIL, PASSWORD, SPECIALIZATION, OFFICE_NUMBER) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, generatedId);
            preparedStatement.setString(2, newDoctor.getLastName());
            preparedStatement.setString(3, newDoctor.getFirstName());
            preparedStatement.setString(4, newDoctor.getEmail());
            preparedStatement.setString(5, newDoctor.getPassword());
            preparedStatement.setString(6, newDoctor.getSpecialization());
            preparedStatement.setString(7, newDoctor.getOfficeNumber());

            preparedStatement.executeUpdate();
            System.out.println("Doctor added successfully with ID: " + generatedId);

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  /**
   * Checks if the given prescription ID exists in the database.
   * 
   * @param prescriptionId The prescription ID to check.
   * @return True if the prescription ID exists, otherwise false.
   */
  private boolean isPrescriptionIdExists(String prescriptionId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = openDBConnection();
      String sql = "SELECT COUNT(*) FROM HealthCareManagement_PRESCRIPTION WHERE PRESCRIPTION_ID = ?";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, prescriptionId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int count = resultSet.getInt(1);
        return count > 0;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * Opens a connection to the database.
   * 
   * @return A Connection object representing the database connection.
   */
  public Connection openDBConnection() {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
      Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                            "csci.cscioraclerh7srv.ad.csbsju.edu", "TEAM05", "TEAM05");
      return myConnection;
    } catch (Exception E) {
      E.printStackTrace();
    }
    return null;
  }

  /**
   * Logs in a doctor with the specified email and password.
   * 
   * @param email The email of the doctor.
   * @param password The password of the doctor.
   * @return True if the login is successful, otherwise false.
   */
  public boolean doctorLogin(String doctorId, String password) {
    Connection con = openDBConnection();
    try {
      PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_DOCTOR WHERE DOCTOR_ID = ? AND PASSWORD = ?");
      statement.setString(1, doctorId);
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
   * Updates the information of the doctor in the database.
   * 
   * @param lastName The last name of the doctor.
   * @param firstName The first name of the doctor.
   * @param email The email address of the doctor.
   * @param password The password of the doctor.
   * @param specialization The specialization of the doctor.
   * @param officeNumber The office number of the doctor.
   */
  public void updateDoctorInfo(String lastName, String firstName, String email, String specialization, String officeNumber) {
    try {
      Connection connection = openDBConnection();
      CallableStatement callableStatement = connection.prepareCall("{call Edit_Doctor_Info(?,?,?,?,?,?)}");
      callableStatement.setString(1, getDoctorId());
      callableStatement.setString(2, email);
      callableStatement.setString(3, lastName);
      callableStatement.setString(4, firstName);
      callableStatement.setString(5, specialization);
      callableStatement.setString(6, officeNumber);
      
      callableStatement.execute();
      
      System.out.println("Doctor information updated successfully.");
      
      callableStatement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Displays the information of the doctor.
   * 
   * @return The Doctor object containing the information of the doctor.
   */
  public Doctor displayDoctorInfo(String doctorId) {
    Doctor doctor = new Doctor();
    Connection con = openDBConnection();
    try {
      String sql = "SELECT * FROM HealthCareManagement_DOCTOR WHERE DOCTOR_ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, doctorId);
      ResultSet resultSet = preparedStatement.executeQuery();
      
      while (resultSet.next()) {
        doctor.setDoctorId(resultSet.getString("DOCTOR_ID"));
        doctor.setLastName(resultSet.getString("LAST"));
        doctor.setFirstName(resultSet.getString("FIRST"));
        doctor.setEmail(resultSet.getString("EMAIL"));
        doctor.setSpecialization(resultSet.getString("SPECIALIZATION"));
        doctor.setOfficeNumber(resultSet.getString("OFFICE_NUMBER"));
        doctor.setPassword(resultSet.getString("PASSWORD"));
        
        System.out.println("Doctor ID: " + doctor.getDoctorId());
        System.out.println("Last Name: " + doctor.getLastName());
        System.out.println("First Name: " + doctor.getFirstName());
        System.out.println("Email: " + doctor.getEmail());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Office Number: " + doctor.getOfficeNumber());
        System.out.println("Password: " + doctor.getPassword());
      }
      
      resultSet.close();
      preparedStatement.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return doctor;
  }
  
  
  /**
   * Retrieves patient details associated with the doctor.
   * 
   * @return A ResultSet containing patient details.
   * @throws SQLException If an SQL exception occurs.
   */
  public ResultSet getPatientDetails(String patientId) throws SQLException {
   String query =  "SELECT " +
                    "p.PATIENT_ID, " +
                    "p.LAST, " +
                    "p.FIRST, " +
                    "p.EMAIL, " +
                    "p.PHONE_NUMBER, " +
                    "p.DIAGNOSIS " +
                    "FROM " +
                    "DoctorPatientDiagnosisView p " +
                    "WHERE " +
                    "p.DOCTOR_ID = ? AND " +
                    "p.PATIENT_ID = ?";

    Connection myConnection = openDBConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = myConnection.prepareStatement(query);
        stmt.setString(1, getDoctorId());
        stmt.setString(2, patientId);
        rs = stmt.executeQuery();
        return rs; // Caller must handle closing ResultSet and Connection
    } catch (SQLException e) {
        if (stmt != null) stmt.close();
        if (myConnection != null) myConnection.close();
        throw e;
    }
}


   
  public boolean addPrescription(String patientId, String prescriptionName, String dosage, String refillsRemaining, double price, String quantity) throws SQLException {
  
    
    String prescriptionId = generatePrescriptionId();
    
    String sql = "INSERT INTO HealthCareManagement_PRESCRIPTION " +
      "(PRESCRIPTION_ID, DATE_ISSUED, PRESCRIPTION_NAME, DOSAGE, REFILLS_REMAINING, PRICE, QUANTITY, DOCTOR_ID, PATIENT_ID, FILLED) " +
      "VALUES (?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, 'NO')";
    
    Connection myConnection = null;
    PreparedStatement stmt = null;
    try{
      myConnection = openDBConnection();
      stmt = myConnection.prepareStatement(sql);
      
      stmt.setString(1, prescriptionId);
      stmt.setString(2, prescriptionName);
      stmt.setString(3, dosage);
      stmt.setString(4, refillsRemaining);
      stmt.setDouble(5, price);
      stmt.setString(6, quantity);
      stmt.setString(7, this.doctorId); // Assume this.doctorId is set when the doctor logs in
      stmt.setString(8, patientId);
      
      int affectedRows = stmt.executeUpdate();
      return affectedRows > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e; // Rethrow the exception to allow further handling by the caller
  }
  }


   /**
   * Adds or updates an appointment note for a patient.
   * 
   * @param patientId The ID of the patient.
   * @param note The appointment note to add or update.
   * @param appointmentDate The date of the appointment.
   * @return True if the appointment note is added or updated successfully, otherwise false.
   * @throws SQLException If an SQL exception occurs.
   */
  public boolean addAppointmentNote(String patientId, String doctorId, String note, Date appointmentDate) throws SQLException {
    
    
    java.sql.Date sqlDate = new java.sql.Date(appointmentDate.getTime());  // Convert java.util.Date to java.sql.Date
   String sql = "INSERT INTO HealthCareManagement_APPOINTMENT (PATIENT_ID, DOCTOR_ID, NOTE, APPOINTMENT_DATE) VALUES (?, ?, ?, ?)";
    

    try(Connection myConnection = openDBConnection();
        PreparedStatement stmt = myConnection.prepareStatement(sql)){
          stmt.setString(1, patientId);
          stmt.setString(2, doctorId);  // Set the SQL date directly        
          stmt.setString(3, note);
          stmt.setDate(4, sqlDate); // Use the doctor ID from the class field
          
         int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;  // Rethrow the exception to allow further handling
    }
}
  /** Method that Allows doctors to edit diagnosis for a patient. 
    * 
    */
  public boolean editPatientDiagnosis(String patientId, String newDiagnosis) throws SQLException {
    

    String sql = "UPDATE HealthCareManagement_DIAGNOSES SET " +
                 "DIAGNOSES = ? " +
                 "WHERE PATIENT_ID = ?";

    try (Connection myConnection = openDBConnection();
         PreparedStatement stmt = myConnection.prepareStatement(sql)) {

        stmt.setString(1, newDiagnosis);
        stmt.setString(2, patientId);

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Rethrow the exception to allow further handling by the caller
    }
}
  
 public int getCountOfAppointments(String doctorId, String appointmentDate) {
        int appointmentCount = 0;
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            // Establish a connection
            conn = openDBConnection();

            // Prepare the call to the SQL function
            String sql = "{ ? = call DoctorAppointmentCount(?, ?) }";
            cstmt = conn.prepareCall(sql);

            // Register the return value as an OUT parameter
            cstmt.registerOutParameter(1, Types.INTEGER);

            // Set the input parameters for the doctor ID and appointment date
            cstmt.setString(2, doctorId);
            cstmt.setString(3, appointmentDate);

            // Execute the function call
            cstmt.execute();

            // Retrieve the result from the OUT parameter
            appointmentCount = cstmt.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return appointmentCount;
    }

  
  /**
   * Main method to test functionalities.
   */
  public static void main(String[] args) {
    Doctor doctor = new Doctor();

    // Log in as a doctor using a doctor ID
    String doctorId = "DOC001";
    String doctorPassword = "password";
    boolean loggedIn = doctor.doctorLogin(doctorId, doctorPassword);

    if (loggedIn) {
        System.out.println("Doctor logged in successfully.");

        // Fetch and display doctor information after login
        Doctor displayedDoctor = doctor.displayDoctorInfo(doctorId);
        System.out.println("Doctor Information:");
        System.out.println("Doctor ID: " + displayedDoctor.getDoctorId());
        System.out.println("Last Name: " + displayedDoctor.getLastName());
        System.out.println("First Name: " + displayedDoctor.getFirstName());
        System.out.println("Email: " + displayedDoctor.getEmail());
        System.out.println("Specialization: " + displayedDoctor.getSpecialization());
        System.out.println("Office Number: " + displayedDoctor.getOfficeNumber());

        // Set the Doctor object's doctorId to the logged-in doctor's ID for further use
        doctor.setDoctorId(displayedDoctor.getDoctorId());

        try {
            // Get patient details for a specific patient ID using the set doctorId
            String specificPatientId = "PAT001";
            System.out.println("\nPatient Details for ID: " + specificPatientId + ":");
            System.out.println("Using Doctor ID: " + doctor.getDoctorId());
            try (ResultSet patientResultSet = doctor.getPatientDetails(specificPatientId)) {
                if (patientResultSet.next()) {
                    System.out.println("Patient ID: " + patientResultSet.getString("PATIENT_ID"));
                    System.out.println("Last Name: " + patientResultSet.getString("LAST"));
                    System.out.println("First Name: " + patientResultSet.getString("FIRST"));
                    System.out.println("Email: " + patientResultSet.getString("EMAIL"));
                    System.out.println("Phone Number: " + patientResultSet.getString("PHONE_NUMBER"));
                    System.out.println("Diagnosis: " + patientResultSet.getString("DIAGNOSIS"));
                } else {
                    System.out.println("No details found for patient ID: " + specificPatientId);
                }
            }

            // Example of adding a prescription
            System.out.println("\nAdding Prescription...");
            boolean prescriptionAdded = doctor.addPrescription("PAT002", "Ibuprofen", "200mg", "5", 45.00, "20");
            if (prescriptionAdded) {
                System.out.println("Prescription added successfully.");
            } else {
                System.out.println("Failed to add prescription.");
            }

            // Adding an appointment note
            System.out.println("\nAdding Appointment Note...");
            Date appointmentDate = new Date();
            boolean noteAdded = doctor.addAppointmentNote("PAT003", doctorId, "Follow-up visit required", appointmentDate);
            if (noteAdded) {
                System.out.println("Appointment note added successfully.");
            } else {
                System.out.println("Failed to add appointment note.");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        }
    } else {
        System.out.println("Doctor login failed. Please check doctor ID and password.");
    }


// Log in as a doctor using a doctor ID
// Log in as a doctor using a doctor ID
    /**
    String doctorId = "DOC001";
    String doctorPassword = "password";
    boolean loggedIn = doctor.doctorLogin(doctorId, doctorPassword);
    
    if (loggedIn) {
      System.out.println("Doctor logged in successfully.");
      
      // Test getCountOfAppointments method
      String specificDate = "01-JUN-23";
      int appointmentCount = doctor.getCountOfAppointments(doctorId, specificDate);
      System.out.println("Total Appointments for Doctor " + doctorId + ": " + appointmentCount);
      
    } else {
      System.out.println("Doctor login failed. Please check doctor ID and password.");
      }*/
  }
}

      

