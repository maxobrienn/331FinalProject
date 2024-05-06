package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditDiagnosisGUI extends JFrame {
    private JTextField patientIdField, diagnosisField;
    private JButton submitButton;
    private Doctor doctor;  // Assuming the Doctor class is accessible and has the required methods.

    public EditDiagnosisGUI(Doctor doctor) {
        this.doctor = doctor;
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Edit Patient Diagnosis");
        setLayout(new GridLayout(3, 2, 10, 10));  // Layout with rows, cols, hgap, vgap

        // Adding labels and fields to the form
        add(new JLabel("Patient ID:"));
        patientIdField = new JTextField(20);
        add(patientIdField);

        add(new JLabel("New Diagnosis:"));
        diagnosisField = new JTextField(200);
        add(diagnosisField);

        // Submit button
        submitButton = new JButton("Submit Diagnosis");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitDiagnosis();
            }
        });
        add(submitButton);

        pack();
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }

    private void submitDiagnosis() {
        String patientId = patientIdField.getText();
        String newDiagnosis = diagnosisField.getText();

        try {
            boolean result = doctor.editPatientDiagnosis(patientId, newDiagnosis);
            if (result) {
                JOptionPane.showMessageDialog(this, "Diagnosis updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update diagnosis.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Assume doctor is logged in and passed to the GUI
        Doctor doctor = new Doctor();  // This should be your logged-in doctor object
        new EditDiagnosisGUI(doctor);
    }
}
