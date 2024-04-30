package tableConstructors;
/**
 * @author Matt DeROsa
 */
import java.util.Date;

public class Prescription {
    private String prescriptionId;
    private Date dateIssued;
    private String prescriptionName;
    private String dosage;
    private String refillsRemaining;
    private double price;
    private String quantity;
    private String doctorId;
    private String patientId;

    public Prescription() {
    }

    public Prescription(String prescriptionId, Date dateIssued, String prescriptionName, String dosage, String refillsRemaining, double price, String quantity, String doctorId, String patientId) {
        this.prescriptionId = prescriptionId;
        this.dateIssued = dateIssued;
        this.prescriptionName = prescriptionName;
        this.dosage = dosage;
        this.refillsRemaining = refillsRemaining;
        this.price = price;
        this.quantity = quantity;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

	/**
	 * @return the prescriptionId
	 */
	public String getPrescriptionId() {
		return prescriptionId;
	}

	/**
	 * @param prescriptionId the prescriptionId to set
	 */
	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	/**
	 * @return the dateIssued
	 */
	public Date getDateIssued() {
		return dateIssued;
	}

	/**
	 * @param dateIssued the dateIssued to set
	 */
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	/**
	 * @return the prescriptionName
	 */
	public String getPrescriptionName() {
		return prescriptionName;
	}

	/**
	 * @param prescriptionName the prescriptionName to set
	 */
	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	/**
	 * @return the dosage
	 */
	public String getDosage() {
		return dosage;
	}

	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	/**
	 * @return the refillsRemaining
	 */
	public String getRefillsRemaining() {
		return refillsRemaining;
	}

	/**
	 * @param refillsRemaining the refillsRemaining to set
	 */
	public void setRefillsRemaining(String refillsRemaining) {
		this.refillsRemaining = refillsRemaining;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", dateIssued=" + dateIssued + ", prescriptionName="
				+ prescriptionName + ", dosage=" + dosage + ", refillsRemaining=" + refillsRemaining + ", price="
				+ price + ", quantity=" + quantity + ", doctorId=" + doctorId + ", patientId=" + patientId + "]";
	}
	
	

}
