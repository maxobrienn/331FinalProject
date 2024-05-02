package tableConstructors;

import java.sql.*;

public class Pharmacy {
    private String pharmacyId;
    private String pharmacyName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String password;
    private String email;
    
    private boolean loggedIn = false;

    public Pharmacy() {
    }

    public Pharmacy(String pharmacyId, String pharmacyName, String street, String city, String state, String zipCode, String phoneNumber, String password, String email) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
	public String toString() {
		return "Pharmacy [pharmacyId=" + pharmacyId + ", pharmacyName=" + pharmacyName + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", email=" + email + ", loggedIn=" + loggedIn + "]";
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

	public boolean pharmacyLogin(String email, String password) {
	    Connection con = openDBConnection();
	    try {
	        PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM HealthCareManagement_PHARMACY WHERE EMAIL = ? AND PASSWORD = ?");
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
	
	public void addPharmacy(Pharmacy pharmacy) {
	    try (Connection connection = openDBConnection()) {
	        // Generate a new pharmacy ID using stored procedure
	        CallableStatement callableStatement = connection.prepareCall("{? = call Generate_Random_Pharmacy_ID}");
	        callableStatement.registerOutParameter(1, Types.CHAR);
	        callableStatement.execute();

	        String generatedId = callableStatement.getString(1);
	        callableStatement.close();

	        // Insert pharmacy data into the database
	        String sql = "INSERT INTO HealthCareManagement_PHARMACY (PHARMACY_ID, PHARMACY_NAME, STREET, CITY, STATE, ZIP_CODE, PHONE_NUMBER, PASSWORD, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, generatedId);
	        preparedStatement.setString(2, pharmacy.getPharmacyName());
	        preparedStatement.setString(3, pharmacy.getStreet());
	        preparedStatement.setString(4, pharmacy.getCity());
	        preparedStatement.setString(5, pharmacy.getState());
	        preparedStatement.setString(6, pharmacy.getZipCode());
	        preparedStatement.setString(7, pharmacy.getPhoneNumber());
	        preparedStatement.setString(8, pharmacy.getPassword());
	        preparedStatement.setString(9, pharmacy.getEmail());

	        preparedStatement.executeUpdate();
	        System.out.println("Pharmacy added successfully with ID: " + generatedId);

	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



    public void updatePharmacyInfo(String pharmacyId, String pharmacyName, String street, String city, String state, String zipCode, String phoneNumber, String password, String email) {
        try {
            Connection connection = openDBConnection();
            CallableStatement callableStatement = connection.prepareCall("{call Edit_Pharmacy_Info(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setString(1, pharmacyId);
            callableStatement.setString(2, phoneNumber);
            callableStatement.setString(3, email);
            callableStatement.setString(4, street);
            callableStatement.setString(5, city);
            callableStatement.setString(6, state);
            callableStatement.setString(7, zipCode);
            callableStatement.setString(8, password);
            callableStatement.setString(9, email);

            callableStatement.execute();

            System.out.println("Pharmacy information updated successfully.");

            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pharmacy displayPharmacyInfo(String pharmacyId) {
        Pharmacy pharmacy = new Pharmacy();
        Connection con = openDBConnection();
        try {
            String sql = "SELECT * FROM HealthCareManagement_PHARMACY WHERE PHARMACY_ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, pharmacyId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pharmacy.setPharmacyId(resultSet.getString("PHARMACY_ID"));
                pharmacy.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                pharmacy.setEmail(resultSet.getString("EMAIL"));
                pharmacy.setStreet(resultSet.getString("STREET"));
                pharmacy.setCity(resultSet.getString("CITY"));
                pharmacy.setState(resultSet.getString("STATE"));
                pharmacy.setZipCode(resultSet.getString("ZIP_CODE"));
                pharmacy.setPassword(resultSet.getString("PASSWORD"));
                pharmacy.setEmail(resultSet.getString("EMAIL"));

                System.out.println("Pharmacy ID: " + pharmacy.getPharmacyId());
                System.out.println("Phone Number: " + pharmacy.getPhoneNumber());
                System.out.println("Email: " + pharmacy.getEmail());
                System.out.println("Street: " + pharmacy.getStreet());
                System.out.println("City: " + pharmacy.getCity());
                System.out.println("State: " + pharmacy.getState());
                System.out.println("Zip Code: " + pharmacy.getZipCode());
                System.out.println("Password: " + pharmacy.getPassword());
                System.out.println("Email: " + pharmacy.getEmail());
            }

            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
