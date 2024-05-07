package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class DoctorMenu extends JFrame {
    private Doctor doctor;

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Doctor Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton viewProfileButton = new JButton("View Profile");
        JButton updateInfoButton = new JButton("Update Info");
        JButton viewPatientsInfoButton = new JButton("View Patients Information");
        JButton createPrescriptionButton = new JButton("Create Prescription");
        JButton addAppointmentNoteButton = new JButton("Add Appointment Note");
        JButton editPatientDiagnosesButton = new JButton("Edit Patient Diagnoses");

        viewProfileButton.addActionListener(e -> displayProfile());
        updateInfoButton.addActionListener(e -> updateInfo());
        viewPatientsInfoButton.addActionListener(e -> viewPatientsInfo());
        createPrescriptionButton.addActionListener(e -> createPrescription());
        addAppointmentNoteButton.addActionListener(e -> addAppointmentNote());
        editPatientDiagnosesButton.addActionListener(e -> editPatientDiagnoses());

        panel.add(viewProfileButton);
        panel.add(updateInfoButton);
        panel.add(viewPatientsInfoButton);
        panel.add(createPrescriptionButton);
        panel.add(addAppointmentNoteButton);
        panel.add(editPatientDiagnosesButton);

        add(panel);
    }

    private void displayProfile() {
        // Open doctor profile window
    }

    private void updateInfo() {
        // Open update info dialog
    }

    private void viewPatientsInfo() {
        // View patients information
    }

    private void createPrescription() {
     SwingUtilities.invokeLater(() -> new PrescriptionGUI(doctor));
    }

    private void addAppointmentNote() {
     SwingUtilities.invokeLater(() -> new AppointmentNoteGUI(doctor));
    }

    private void editPatientDiagnoses() {
     SwingUtilities.invokeLater(() -> new EditDiagnosisGUI(doctor));
    }

    public static void main(String[] args) {
        // Example usage:
        // Doctor doctor = new Doctor();
        // new DoctorMenu(doctor).setVisible(true);
    }
}
