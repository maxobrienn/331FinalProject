package tableConstructors;
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

    // Getters and setters
    
}
