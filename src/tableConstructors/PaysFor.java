package tableConstructors;

public class PaysFor {
    private String prescriptionId;
    private String insuranceId;

    public PaysFor() {
    }

    public PaysFor(String prescriptionId, String insuranceId) {
        this.prescriptionId = prescriptionId;
        this.insuranceId = insuranceId;
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
	 * @return the insuranceId
	 */
	public String getInsuranceId() {
		return insuranceId;
	}

	/**
	 * @param insuranceId the insuranceId to set
	 */
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

    // Getters and setters
    
}
