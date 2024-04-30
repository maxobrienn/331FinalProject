package tableConstructors;
/**
 * @author Matt DeROsa
 */
public class Supplies {
	
    private String supplierId;
    private String pharmacyId;

    public Supplies() {
    }

    public Supplies(String supplierId, String pharmacyId) {
        this.supplierId = supplierId;
        this.pharmacyId = pharmacyId;
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

	@Override
	public String toString() {
		return "Supplies [supplierId=" + supplierId + ", pharmacyId=" + pharmacyId + "]";
	}

    
}
