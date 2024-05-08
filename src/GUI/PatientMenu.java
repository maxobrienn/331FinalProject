package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

/**
 * Represents a graphical user interface for the patient menu.
 */
public class PatientMenu extends JFrame {
    private Patient patient;

    /**
     * Constructs a PatientMenu object.
     * @param patient The patient object associated with this menu.
     */
    public PatientMenu(Patient patient) {
        this.patient = patient;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("Patient Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));

        JButton viewProfileButton = new JButton("View Profile");
        JButton updateInfoButton = new JButton("Update Info");
        JButton viewPrescriptionButton = new JButton("View Prescriptions");
        JButton viewDiagnosesButton = new JButton("View Diagnoses");
        JButton viewDoctorsButton = new JButton("View Doctors");
        JButton choosePreferredDoctorButton = new JButton("Choose Preferred Doctor");
        JButton viewAppointmentsButton = new JButton("View Past Appointments");
        JButton makePaymentButton = new JButton("Make Payment");

        viewProfileButton.addActionListener(e -> displayProfile());
        updateInfoButton.addActionListener(e -> updateInfo());
        viewPrescriptionButton.addActionListener(e -> viewPrescriptions());
        viewDiagnosesButton.addActionListener(e -> viewDiagnoses());
        viewDoctorsButton.addActionListener(e -> viewDoctors());
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
        makePaymentButton.addActionListener(e -> makePayment());
        choosePreferredDoctorButton.addActionListener(e -> updatePatientPreferredDoctor());

        panel.add(viewProfileButton);
        panel.add(updateInfoButton);
        panel.add(viewPrescriptionButton);
        panel.add(viewDiagnosesButton);
        panel.add(choosePreferredDoctorButton);
        panel.add(viewDoctorsButton);
        panel.add(viewAppointmentsButton);
        panel.add(makePaymentButton);

        add(panel);
    }

    /**
     * Displays the patient's profile.
     */
    private void displayProfile() {
        SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
    }

    /**
     * Opens the interface for updating patient information.
     */
    private void updateInfo() {
        SwingUtilities.invokeLater(() -> new PatientEditGUI(patient));
    }
    
    /**
     * Displays the list of prescriptions associated with the patient.
     */
    private void viewPrescriptions() {
      PatientViewPrescriptionsGUI prescriptionsGUI = new PatientViewPrescriptionsGUI(patient);
      prescriptionsGUI.setVisible(true);
    }
    
    
    private void updatePatientPreferredDoctor() {
      PreferredDoctorGUI pdoctorGUI = new PreferredDoctorGUI(patient);
      pdoctorGUI.setVisible(true);
    }
    

    private void viewDiagnoses() {
      SwingUtilities.invokeLater(() -> new ViewDiagnosisGUI(patient));
    }
    
    /**
     * Displays the list of doctors associated with the patient.
     */
    private void viewDoctors() {

      ViewDoctorListGUI doctorsListGUI = new ViewDoctorListGUI();
      doctorsListGUI.setVisible(true);

      // Implement logic to display the list of doctors associated with the patient
      // Example: patient.viewDoctorList();
    }
    
    /**
     * Opens the interface for viewing past appointments of the patient.
     */
    private void viewAppointments() {
      SwingUtilities.invokeLater(() -> new PatientViewPastAppointmentGUI(patient));
    }
    
    /**
     * Opens the interface for making a payment.
     */
    private void makePayment() {
      PatientPaymentGUI paymentGUI = new PatientPaymentGUI(patient);
      paymentGUI.setVisible(true);
    }
    
    /**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
      // Example usage:
      // Patient patient = new Patient();
      // new PatientMenu(patient).setVisible(true);
    }
}
