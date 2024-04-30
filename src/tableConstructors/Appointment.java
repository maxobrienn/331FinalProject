package tableConstructors;
/**
 * @author Matt DeROsa
 */

import java.util.Date;

public class Appointment {
    private String patientId;
    private String doctorId;
    private String note;
    private Date appointmentDate;

    public Appointment() {
    }

    public Appointment(String patientId, String doctorId, String note, Date appointmentDate) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.note = note;
        this.appointmentDate = appointmentDate;
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

	@Override
	public String toString() {
		return "Appointment [patientId=" + patientId + ", doctorId=" + doctorId + ", note=" + note
				+ ", appointmentDate=" + appointmentDate + "]";
	}

   
    
}
