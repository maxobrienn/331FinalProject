package tableConstructors;

import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * @author Matt DeROsa
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
    
    public Doctor() {
    }

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
  * @return the doctorId
  */
 public String getDoctorId() {
  return doctorId;
 }

 /**
  * @param doctorId the doctorId to set
  */
 public void setDoctorId(String doctorId) {
  this.doctorId = doctorId;
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

 /**
  * @return the specialization
  */
 public String getSpecialization() {
  return specialization;
 }

 /**
  * @param specialization the specialization to set
  */
 public void setSpecialization(String specialization) {
  this.specialization = specialization;
 }

 /**
  * @return the officeNumber
  */
 public String getOfficeNumber() {
  return officeNumber;
 }

 /**
  * @param officeNumber the officeNumber to set
  */
 public void setOfficeNumber(String officeNumber) {
  this.officeNumber = officeNumber;
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
 
 @Override
 public String toString() {
  return "Doctor [doctorId=" + doctorId + ", lastName=" + lastName + ", firstName=" + firstName + ", email="
    + email + ", password=" + password + ", specialization=" + specialization + ", officeNumber="
    + officeNumber + ", loggedIn=" + loggedIn + "]";
 }

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
 
 public boolean doctorLogin(String email, String password) {
     Connection con = openDBConnection();
     try {
         PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_DOCTOR WHERE EMAIL = ? AND PASSWORD = ?");
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

    public void updateDoctorInfo(String doctorId, String lastName, String firstName, String email, String password, String specialization, String officeNumber) {
        try {
            Connection connection = openDBConnection();
            CallableStatement callableStatement = connection.prepareCall("{call Edit_Doctor_Info(?,?,?,?,?,?,?)}");
            callableStatement.setString(1, doctorId);
            callableStatement.setString(2, lastName);
            callableStatement.setString(3, firstName);
            callableStatement.setString(4, email);
            callableStatement.setString(5, specialization);
            callableStatement.setString(6, officeNumber);
            callableStatement.setString(7, password);

            callableStatement.execute();

            System.out.println("Doctor information updated successfully.");

            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
     * Method that allow doctor users to view certain information from their patients’ profiles and view their diagnoses. 
     * GET PATIENT INFO
    */
    public ResultSet getPatientDetails() throws SQLException {
    if (!getLoggedIn()) {
        throw new IllegalStateException("Doctor must be logged in to access patient information.");
    }

    String query = "SELECT p.PATIENT_ID, p.FIRST, p.LAST, p.EMAIL, p.PHONE_NUMBER, p.DIAGNOSES " +
                   "FROM HealthCareManagement_PATIENT p " +
                   "JOIN HealthCareManagement_APPOINTMENT a ON p.PATIENT_ID = a.PATIENT_ID " +
                   "WHERE a.DOCTOR_ID = ? " +
                   "ORDER BY p.LAST, p.FIRST";

    Connection myConnection = openDBConnection(); // Use 'myConnection' as the connection variable
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        stmt = myConnection.prepareStatement(query);
        stmt.setString(1, this.getDoctorId()); // Set doctorId for logged-in doctor

        rs = stmt.executeQuery();
        return rs; // The caller must handle closing the ResultSet and Connection
    } catch (SQLException e) {
        if (stmt != null) stmt.close();
        if (myConnection != null) myConnection.close(); // Ensure the connection is closed here
        throw e; // Rethrow the exception to handle it in the calling method
    }
}
    /** Method that allows doctors to create prescriptions for their patients. 
      * ADD A PERSCRIPTION
      */
    public boolean addPrescription(String prescriptionId, String patientId, String prescriptionName, String dosage, String refillsRemaining, double price, String quantity) throws SQLException {
      if (!getLoggedIn()) {
        throw new IllegalStateException("Doctor must be logged in to create a prescription.");
    }

    String sql = "INSERT INTO HealthCareManagement_PRESCRIPTION " +
                 "(PRESCRIPTION_ID, DATE_ISSUED, PRESCRIPTION_NAME, DOSAGE, REFILLS_REMAINING, PRICE, QUANTITY, DOCTOR_ID, PATIENT_ID, FILLED) " +
                 "VALUES (?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, 'NO')";

    Connection myConnection = null;
    PreparedStatement stmt = null;
    try {
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
    } finally {
        if (stmt != null) stmt.close();
        if (myConnection != null) myConnection.close();
    }
}
    /**Method that allows doctors to leave appointment note and date after seeing a patient. 
      * AddNote
      */
    public boolean addAppointmentNote(String patientId, String note, Date appointmentDate) throws SQLException {
    if (!getLoggedIn()) {
        throw new IllegalStateException("Doctor must be logged in to add or update an appointment note.");
    }

    String sql = "UPDATE HealthCareManagement_APPOINTMENT SET " +
                 "NOTE = ?, APPOINTMENT_DATE = ? " +
                 "WHERE PATIENT_ID = ? AND DOCTOR_ID = ?";

    java.sql.Date sqlDate = new java.sql.Date(appointmentDate.getTime());  // Convert java.util.Date to java.sql.Date

    Connection myConnection = null;
    PreparedStatement stmt = null;
    try {
        myConnection = openDBConnection();
        stmt = myConnection.prepareStatement(sql);
        stmt.setString(1, note);
        stmt.setDate(2, sqlDate);  // Set the SQL date directly
        stmt.setString(3, patientId);
        stmt.setString(4, this.doctorId); // Use the doctor ID from the class field

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Rethrow to allow further handling
    } finally {
        if (stmt != null) stmt.close();
        if (myConnection != null) myConnection.close();
    }
}
    public static void main(String[] args) {
        Doctor doctor = new Doctor("DOC001", "Smith", "John", "john.smith@example.com", "password123", "Cardiology", "100A");
        doctor.setLoggedIn(true); // Simulate the doctor being logged in

        // Test adding a prescription
        try {
            boolean prescriptionAdded = doctor.addPrescription(
                "PRSC007", "PAT001", "Meth", "500mg", "10", 19.99, "30");
            if (prescriptionAdded) {
                System.out.println("Prescription added successfully.");
            } else {
                System.out.println("Failed to add prescription.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding prescription: " + e.getMessage());
        }

        // Test adding an appointment note
        try {
            boolean noteAdded = doctor.addAppointmentNote(
                "PAT001", "Please follow up in two weeks.", new Date() // Current date as appointment date
            );
            if (noteAdded) {
                System.out.println("Appointment note added successfully.");
            } else {
                System.out.println("Failed to add appointment note.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding appointment note: " + e.getMessage());
        }

        // Test retrieving patient details
        try (ResultSet rs = doctor.getPatientDetails()) {
            if (rs != null) {
                while (rs.next()) {
                    System.out.println("Patient ID: " + rs.getString("PATIENT_ID"));
                    System.out.println("Name: " + rs.getString("FIRST") + " " + rs.getString("LAST"));
                    System.out.println("Email: " + rs.getString("EMAIL"));
                    System.out.println("Phone Number: " + rs.getString("PHONE_NUMBER"));
                    System.out.println("Diagnoses: " + rs.getString("DIAGNOSES"));
                    System.out.println("--------------------------------------------");
                }
            } else {
                System.out.println("No patient information available.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving patient details: " + e.getMessage());
        }
    }
}