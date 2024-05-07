/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for viewing patient information.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import tableConstructors.*;

public class ViewPatientInfoGUI extends JFrame {
    private JTextField patientIdField;
    private JButton viewButton, returnToMenuButton;
    private JTextArea infoArea;
    private Doctor doctor;

    /**
     * Constructs a ViewPatientInfoGUI object.
     * @param doctor The Doctor object associated with this GUI.
     */
    public ViewPatientInfoGUI(Doctor doctor) {
        this.doctor = doctor;
        createUI();
    }

    /**
     * Creates the user interface.
     */
    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("View Patient Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField(15);
        inputPanel.add(patientIdField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        viewButton = new JButton("View Info");
        viewButton.addActionListener(e -> viewPatientInfo());
        buttonPanel.add(viewButton);

        returnToMenuButton = new JButton("Return to Doctor Menu");
        returnToMenuButton.addActionListener(e -> returnToMenu());
        buttonPanel.add(returnToMenuButton);

        // Text area for displaying patient info
        infoArea = new JTextArea(15, 40);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        // Adding components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Styling components
        inputPanel.setBackground(new Color(230, 230, 250));  // Lavender background
        buttonPanel.setBackground(new Color(230, 230, 250)); // Consistent background with input panel
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(new Color(100, 149, 237));  // Cornflower blue
        viewButton.setForeground(Color.WHITE);
        returnToMenuButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnToMenuButton.setBackground(new Color(100, 149, 237));  // Matching style with view button
        returnToMenuButton.setForeground(Color.WHITE);
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        infoArea.setBackground(new Color(245, 245, 245));  // White smoke

        setVisible(true);
    }

    /**
     * Retrieves and displays patient information based on the entered patient ID.
     */
    private void viewPatientInfo() {
        String patientId = patientIdField.getText().trim();
        if (patientId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Patient ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (ResultSet rs = doctor.getPatientDetails(patientId)) {
            if (rs.next()) {
                String info = "Patient ID: " + rs.getString("PATIENT_ID") + "\n" +
                              "Name: " + rs.getString("FIRST") + " " + rs.getString("LAST") + "\n" +
                              "Email: " + rs.getString("EMAIL") + "\n" +
                              "Phone: " + rs.getString("PHONE_NUMBER") + "\n" +
                              "Diagnosis: " + rs.getString("DIAGNOSIS");
                infoArea.setText(info);
            } else {
                infoArea.setText("No information found for Patient ID: " + patientId);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error retrieving patient information: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Returns to the Doctor Menu.
     */
    private void returnToMenu() {
        dispose();
        new DoctorMenu(doctor).setVisible(true);
    }

    /**
     * The main method for testing purposes.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Doctor doctor = new Doctor(); // This should be your logged-in doctor object
        doctor.setDoctorId("DOC001"); // Normally you would set this after a successful login
        new ViewPatientInfoGUI(doctor);
    }
}
