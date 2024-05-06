package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewPatientInfoGUI extends JFrame {
    private JTextField patientIdField;
    private JButton viewButton;
    private JTextArea infoArea;
    private Doctor doctor;  // Assuming the Doctor class is accessible

    public ViewPatientInfoGUI(Doctor doctor) {
        this.doctor = doctor;
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("View Patient Information");
        setSize(500, 400);  // Set frame size
        setLocationRelativeTo(null);  // Center on screen

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField(15);
        inputPanel.add(patientIdField);
        viewButton = new JButton("View Info");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPatientInfo();
            }
        });
        inputPanel.add(viewButton);

        // Text area for displaying patient info
        infoArea = new JTextArea(15, 40);
        infoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        // Adding components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Styling components
        inputPanel.setBackground(new Color(230, 230, 250));  // Lavender background
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(new Color(100, 149, 237));  // Cornflower blue
        viewButton.setForeground(Color.WHITE);
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        infoArea.setBackground(new Color(245, 245, 245));  // White smoke

        setVisible(true);
    }

    private void viewPatientInfo() {
        String patientId = patientIdField.getText().trim();
        try {
            ResultSet rs = doctor.getPatientDetails();  // Fetches details for all patients
            boolean found = false;
            while (rs.next()) {
                if (rs.getString("PATIENT_ID").equals(patientId)) {
                    String info = "Patient ID: " + rs.getString("PATIENT_ID") + "\n" +
                                  "Name: " + rs.getString("FIRST") + " " + rs.getString("LAST") + "\n" +
                                  "Email: " + rs.getString("EMAIL") + "\n" +
                                  "Phone: " + rs.getString("PHONE_NUMBER") + "\n" +
                                  "Diagnosis: " + rs.getString("DIAGNOSIS");
                    infoArea.setText(info);
                    found = true;
                    break;
                }
            }
            if (!found) {
                infoArea.setText("No information found for Patient ID: " + patientId);
            }
            rs.close(); // Don't forget to close ResultSet
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving patient information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Assume doctor is logged in and passed to the GUI
        Doctor doctor = new Doctor();  // This should be your logged-in doctor object
        new ViewPatientInfoGUI(doctor);
    }
}