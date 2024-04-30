package tableConstructors;
/**
 * @author Matt DeROsa
 */
public class Diagnosis {
    private String patientId;
    private String diagnoses;

    public Diagnosis() {
    }

    public Diagnosis(String patientId, String diagnoses) {
        this.patientId = patientId;
        this.diagnoses = diagnoses;
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

	/**
	 * @return the diagnoses
	 */
	public String getDiagnoses() {
		return diagnoses;
	}

	/**
	 * @param diagnoses the diagnoses to set
	 */
	public void setDiagnoses(String diagnoses) {
		this.diagnoses = diagnoses;
	}

	@Override
	public String toString() {
		return "Diagnosis [patientId=" + patientId + ", diagnoses=" + diagnoses + "]";
	}

    
    
    
}
