package tableConstructors;

import java.sql.*;
/**
 * @author Matt DeROsa
 */
public class PharmacyEmployee {
    private String employeeId;
    private String lastName;
    private String firstName;
    private String ssn;
    private String phoneNumber;
    private String email;
    private String position;
    private String pharmacyId;
    private String password;
    
    private Boolean loggedIn = false;

    public PharmacyEmployee() {
    }

    public PharmacyEmployee(String employeeId, String lastName, String firstName, String ssn, String phoneNumber, String email, String position, String pharmacyId, String password) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.ssn = ssn;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.pharmacyId = pharmacyId;
        this.password = password;
    }

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the pharmacyId
	 */
	public String getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId the pharmacyId to set
	 */
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
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

	public Connection openDBConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
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
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM PHARMACY_EMPLOYEE WHERE EMAIL = ? AND PASSWORD = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next() && rs.getString(1) != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateEmployeeInfo(String employeeId, String lastName, String firstName, String ssn, String phoneNumber, String email, String position, String pharmacyId, String password) {
        try {
            Connection connection = openDBConnection();
            CallableStatement callableStatement = connection.prepareCall("{call Edit_Pharmacy_Employee_Info(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, employeeId);
            callableStatement.setString(2, lastName);
            callableStatement.setString(3, firstName);
            callableStatement.setString(4, ssn);
            callableStatement.setString(5, phoneNumber);
            callableStatement.setString(6, email);
            callableStatement.setString(7, position);
            callableStatement.setString(8, pharmacyId);
            callableStatement.setString(9, password);

            callableStatement.execute();

            System.out.println("Pharmacy employee information updated successfully.");

            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PharmacyEmployee displayEmployeeInfo(String employeeId) {
        PharmacyEmployee employee = new PharmacyEmployee();
        Connection con = openDBConnection();
        try {
            String sql = "SELECT * FROM HealthCareManagement_PHARMACYEMPLOYEE WHERE EMPLOYEE_ID = ?";
           
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getString("EMPLOYEE_ID"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setSsn(resultSet.getString("SSN"));
                employee.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                employee.setEmail(resultSet.getString("EMAIL"));
                employee.setPosition(resultSet.getString("POSITION"));
                employee.setPharmacyId(resultSet.getString("PHARMACY_ID"));
                employee.setPassword(resultSet.getString("PASSWORD"));

                System.out.println("Employee ID: " + employee.getEmployeeId());
                System.out.println("Last Name: " + employee.getLastName());
                System.out.println("First Name: " + employee.getFirstName());
                System.out.println("SSN: " + employee.getSsn());
                System.out.println("Phone Number: " + employee.getPhoneNumber());
                System.out.println("Email: " + employee.getEmail());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Pharmacy ID: " + employee.getPharmacyId());
                System.out.println("Password: " + employee.getPassword());
            }

            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
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
    
}
