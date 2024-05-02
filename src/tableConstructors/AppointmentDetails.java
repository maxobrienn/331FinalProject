package tableConstructors;
import java.util.Date;


public class AppointmentDetails {
    private String doctorName;
    private Date appointmentDate;
    private String note;
    private String patientId;

    public AppointmentDetails(String doctorName, Date appointmentDate, String note, String patientId) {
        this.setDoctorName(doctorName);
        this.setAppointmentDate(appointmentDate);
        this.setNote(note);
        this.setPatientId(patientId);
    }

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the appointmentDate
	 */
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
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
		return "AppointmentDetails [doctorName=" + doctorName + ", appointmentDate=" + appointmentDate + ", note="
				+ note + ", patientId=" + patientId + "]";
	}
	
	
}