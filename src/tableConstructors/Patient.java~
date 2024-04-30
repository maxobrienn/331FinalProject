package tableConstructors;

import java.sql.*;
import java.util.Date;

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
     * Constructor with parameters
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

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        // Creating a sample patient
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

        // Testing login functionality
        System.out.println("Logging in...");
        System.out.println(patient.login("patient1@email.com", "thsbaibniincd58n"));

        // Testing editing patient info
        System.out.println("\nEditing patient info before...");
        System.out.println(patient.toString());

        System.out.println("\nEdit patient info after....");
        patient.updatePatientInfo("987-654-3210", "updatedemail1@example.com", "789 Health Ave",
                "Carecity", "CA", "34567", "INS003", "Male");
        System.out.println(patient.toString());

        // Testing viewing patient profile
        System.out.println("\nViewing patient profile...");
        Patient retrievedPatient = patient.displayPatientInfo(patient.getPatientId());
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
        retrievedPatient = patient.displayPatientInfo(patient.getPatientId());
        if (retrievedPatient != null) {
            System.out.println(retrievedPatient.toString());
        } else {
            System.out.println("Patient not found!");
        }
    }
}
