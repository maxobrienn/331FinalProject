package tableConstructors;

import java.sql.*;
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
	
	public void addDoctor(Doctor doctor) {
	    try (Connection connection = openDBConnection()) {
	        // Generate a new doctor ID using stored procedure
	        CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_Doctor_ID}");
	        callableStatement.registerOutParameter(1, Types.CHAR);
	        callableStatement.execute();

	        String generatedId = callableStatement.getString(1);
	        callableStatement.close();

	        // Insert doctor data into the database
	        String sql = "INSERT INTO HealthCareManagement_DOCTOR (DOCTOR_ID, FIRST, LAST, EMAIL, PASSWORD, SPECIALIZATION, OFFICE_NUMBER) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, generatedId);
	        preparedStatement.setString(2, doctor.getFirstName());
	        preparedStatement.setString(3, doctor.getLastName());
	        preparedStatement.setString(4, doctor.getEmail());
	        preparedStatement.setString(5, doctor.getPassword());
	        preparedStatement.setString(6, doctor.getSpecialization());
	        preparedStatement.setString(7, doctor.getOfficeNumber());

	        preparedStatement.executeUpdate();
	        System.out.println("Doctor added successfully with ID: " + generatedId);

	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
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

    
}
