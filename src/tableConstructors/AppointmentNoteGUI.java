package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class AppointmentNoteGUI extends JFrame {
    private JTextField patientIdField, doctorIdField, noteField;
    private JSpinner dateSpinner;
    private JButton submitButton, returnToMenuButton;
    private Doctor doctor;  // Assuming the Doctor class is accessible and has the required methods.

    public AppointmentNoteGUI(Doctor doctor) {
        this.doctor = doctor;
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Add Appointment Note");
        setLayout(new GridLayout(6, 2, 10, 10));  // Updated grid layout for additional button

        // Adding labels and fields to the form
        add(new JLabel("Patient ID:"));
        patientIdField = new JTextField(20);
        add(patientIdField);

        add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField(20);
        add(doctorIdField);

        add(new JLabel("Note:"));
        noteField = new JTextField(200);
        add(noteField);

        add(new JLabel("Appointment Date:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        // Submit button
        submitButton = new JButton("Submit Note");
        submitButton.addActionListener(e -> submitNote());
        add(submitButton);

        // Return to Doctor Menu button
        returnToMenuButton = new JButton("Return to Doctor Menu");
        returnToMenuButton.addActionListener(e -> returnToMenu());
        add(returnToMenuButton);

        pack();  // Size the frame
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }

    private void submitNote() {
        String patientId = patientIdField.getText();
        String doctorId = doctorIdField.getText();
        String note = noteField.getText();
        Date appointmentDate = (Date) dateSpinner.getValue();

        try {
            boolean result = doctor.addAppointmentNote(patientId, doctorId, note, appointmentDate);
            if (result) {
                JOptionPane.showMessageDialog(this, "Appointment note added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add appointment note.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void returnToMenu() {
        this.dispose();  // Close the current window
        new DoctorMenu(doctor).setVisible(true);  // Open the Doctor Menu
    }

    public static void main(String[] args) {
        // Assume doctor is logged in and passed to the GUI
        Doctor doctor = new Doctor();  // This should be your logged-in doctor object
        new AppointmentNoteGUI(doctor);
    }
}