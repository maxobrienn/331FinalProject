package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PatientMenu extends JFrame {
    private Patient patient;

    public PatientMenu(Patient patient) {
        this.patient = patient;
        initializeUI();
    }

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
        JButton viewAppointmentsButton = new JButton("View Past Appointments");
        JButton makePaymentButton = new JButton("Make Payment");

        viewProfileButton.addActionListener(e -> displayProfile());
        updateInfoButton.addActionListener(e -> updateInfo());
        viewPrescriptionButton.addActionListener(e -> viewPrescriptions());
        viewDiagnosesButton.addActionListener(e -> viewDiagnoses());
        viewDoctorsButton.addActionListener(e -> viewDoctors());
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
        makePaymentButton.addActionListener(e -> makePayment());

        panel.add(viewProfileButton);
        panel.add(updateInfoButton);
        panel.add(viewPrescriptionButton);
        panel.add(viewDiagnosesButton);
        panel.add(viewDoctorsButton);
        panel.add(viewAppointmentsButton);
        panel.add(makePaymentButton);

        add(panel);
    }

    private void displayProfile() {
        SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
    }

    private void updateInfo() {
        SwingUtilities.invokeLater(() -> new PatientEditGUI(patient));
    }
    
    private void viewPrescriptions() {
      PatientViewPrescriptionsGUI prescprtionsGUI = new PatientViewPrescriptionsGUI(patient);
      prescprtionsGUI.setVisible(true);
    }

    private void viewDiagnoses() {
      SwingUtilities.invokeLater(() -> new ViewDiagnosisGUI(patient));
    }
    
    private void viewDoctors() {
      // Display list of doctors
      // Example: patient.viewDoctorList();
    }
    
    private void viewAppointments() {
      SwingUtilities.invokeLater(() -> new PatientViewPastAppointmentGUI(patient));
    }
    
    private void makePayment() {
      // Create a new instance of PatientPaymentGUI
      PatientPaymentGUI paymentGUI = new PatientPaymentGUI(patient);
      // Make the PatientPaymentGUI window visible
      paymentGUI.setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
      // Example usage:
      // Patient patient = new Patient();
      // new PatientMenu(patient).setVisible(true);
    }
}
