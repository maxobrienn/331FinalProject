package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import tableConstructors.*;

public class ViewDiagnosisGUI extends JFrame {
    private JTextArea resultArea;

    private JButton viewButton;
    private JButton returnButton; // New button for returning to PatientMenu
    private Patient patient;

    /**
     * Constructs a ViewDiagnosisGUI object.
     * @param patient The patient whose diagnoses are being viewed.
     */
    public ViewDiagnosisGUI(Patient patient) {
        this.patient = patient;
        createAndShowGUI();
    }

    /**
     * Creates and displays the GUI.
     */
    private void createAndShowGUI() {
        // Frame initial setup
        setTitle("Patient Diagnosis Viewer");
        setSize(800, 400); // Larger size
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        resultArea = new JTextArea(10, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font to large and bold
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        viewButton = new JButton("View Diagnoses");
        viewButton.addActionListener(e -> viewDiagnoses());
        viewButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size

        returnButton = new JButton("Return to Patient Menu");
        returnButton.addActionListener(e -> returnToMenu());
        returnButton.setPreferredSize(new Dimension(200, 30)); // Set preferred size

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("Patient ID:"));
        topPanel.add(viewButton);
        topPanel.add(returnButton); // Add return button to the top panel

        // Adding components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Display the window
        setVisible(true);

        // View the diagnoses when the GUI is created
        viewDiagnoses();
    }

    /**
     * Retrieves and displays the patient's diagnoses along with diagnosis date.
     */
    private void viewDiagnoses() {
        // Clear previous results
        resultArea.setText("");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection
            conn = patient.openDBConnection();

            // SQL Query
            String sql = "SELECT PATIENT_ID, DIAGNOSES, DIAGNOSIS_DATE FROM HealthCareManagement_SEEDIAGNOSIS WHERE PATIENT_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patient.getPatientId());

            // Execute Query
            rs = pstmt.executeQuery();

            // Process the results
            resultArea.append(String.format("%-25s | %-15s | %s\n", "Patient_ID", "Diagnoses", "Diagnosis_Date")); // Header
            resultArea.append("---------------------------------------------------------------------\n"); // Separator
            while (rs.next()) {
                String pId = rs.getString("PATIENT_ID");
                String diagnoses = rs.getString("DIAGNOSES");
                String diagnosisDate = rs.getString("DIAGNOSIS_DATE"); // Assuming the date is stored as a string
                resultArea.append(String.format("%-25s | %-15s | %s\n", pId, diagnoses, diagnosisDate));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error accessing database: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Returns to the Patient Menu.
     */
    private void returnToMenu() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(() -> new PatientMenu(patient)); // Open PatientMenu
    }
}

    /**
     * The main method for testing purposes.
     * @param args The command-line arguments.
     
    public static void main(String[] args) {
        // Assuming Patient is logged in and Patient instance is created accordingly
        Patient patient = new Patient("123456", new java.util.Date(), "123 Elm St", "Anytown", "Anystate", "12345", "email@example.com",
                                      "555-1234", "Doe", "John", "M", "12345-67890", "password123", "test");
        SwingUtilities.invokeLater(() -> new ViewDiagnosisGUI(patient));
    }
    */

