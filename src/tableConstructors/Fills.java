package tableConstructors;
/**
 * @author Matt DeROsa
 */
public class Fills {
    private String pharmacyId;
    private String prescriptionId;

    public Fills() {
    }

    public Fills(String pharmacyId, String prescriptionId) {
        this.pharmacyId = pharmacyId;
        this.prescriptionId = prescriptionId;
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

	@Override
	public String toString() {
		return "Fills [pharmacyId=" + pharmacyId + ", prescriptionId=" + prescriptionId + "]";
	}

    
    
}
