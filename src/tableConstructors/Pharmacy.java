package tableConstructors;

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
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}

	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
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

    // Getters and setters
    
}
