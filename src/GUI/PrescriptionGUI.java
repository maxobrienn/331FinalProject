/**
 * Author: Mason Meyer
 * 
 * This class represents a graphical user interface (GUI) for creating prescriptions by a doctor.
 * It allows the doctor to enter prescription details such as patient ID, prescription name, dosage, etc.,
 * and submit the prescription to the database.
 */
package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import tableConstructors.*;

public class PrescriptionGUI extends JFrame {
     private JTextField patientIdField, prescriptionNameField, dosageField, refillsField, priceField, quantityField;
    private JButton submitButton, returnToMenuButton;
    private Doctor doctor;  // Assuming the Doctor class is accessible and has the required methods.

    /**
     * Constructs a PrescriptionGUI object.
     * @param doctor The doctor creating the prescription.
     */
    public PrescriptionGUI(Doctor doctor) {
        this.doctor = doctor;
        createUI();
    }

    /**
     * Creates the user interface components.
     */
    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Prescription");
        setLayout(new GridLayout(8, 2));  // Adjusted for an additional button

        // Create text fields and labels
        add(new JLabel("Patient ID:"));
        patientIdField = new JTextField(20);
        add(patientIdField);

        add(new JLabel("Prescription Name:"));
        prescriptionNameField = new JTextField(20);
        add(prescriptionNameField);

        add(new JLabel("Dosage:"));
        dosageField = new JTextField(20);
        add(dosageField);

        add(new JLabel("Refills Remaining:"));
        refillsField = new JTextField(20);
        add(refillsField);

        add(new JLabel("Price:"));
        priceField = new JTextField(20);
        add(priceField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField(20);
        add(quantityField);

        // Submit button
        submitButton = new JButton("Create Prescription");
        submitButton.addActionListener(e -> submitPrescription());
        add(submitButton);

        // Return to Doctor Menu button
        returnToMenuButton = new JButton("Return to Doctor Menu");
        returnToMenuButton.addActionListener(e -> returnToMenu());
        add(returnToMenuButton);

        pack();  // Size the frame
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }

    /**
     * Submits the prescription details to the database.
     */
    private void submitPrescription() {
        String patientId = patientIdField.getText();
        String prescriptionName = prescriptionNameField.getText();
        String dosage = dosageField.getText();
        String refillsRemaining = refillsField.getText();
        double price = Double.parseDouble(priceField.getText());
        String quantity = quantityField.getText();

        try {
            boolean result = doctor.addPrescription(patientId, prescriptionName, dosage, refillsRemaining, price, quantity);
            if (result) {
                JOptionPane.showMessageDialog(this, "Prescription added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add prescription.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    /**
     * Returns to the Doctor Menu when the corresponding button is clicked.
     */
    private void returnToMenu() {
        this.dispose();  // Close the current window
        new DoctorMenu(doctor).setVisible(true);  // Open the Doctor Menu
    }

    /**
     * The entry point of the application.
     * Example usage:
     * // Assume doctor is logged in and passed to the GUI
     * // Doctor doctor = new Doctor();  // This should be your logged-in doctor object
     * // new PrescriptionGUI(doctor);
     */
    public static void main(String[] args) {
        // Assume doctor is logged in and passed to the GUI
        Doctor doctor = new Doctor();  // This should be your logged-in doctor object
        new PrescriptionGUI(doctor);
    }
}
