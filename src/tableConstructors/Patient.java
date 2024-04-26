package tableConstructors;
/**
* User Patient class constructor 
* as well as all the geters and setter
*/
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

    // Constructor without parameters
    public Patient() {
    }

    // Constructor with all fields
    public Patient(String patientId, Date dob, String street, String city, String state, String zipCode, String email, String phoneNumber, String lastName, String firstName, String sex, String insuranceId, String password) {
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
}
