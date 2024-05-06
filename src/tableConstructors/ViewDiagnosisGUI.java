package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewDiagnosisGUI extends JFrame {
    private JTextArea resultArea;
    private Patient patient;

    public ViewDiagnosisGUI(Patient patient) {
        this.patient = patient;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Frame initial setup
        setTitle("Patient Diagnosis Viewer");
        setSize(800, 400); // Larger size
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        resultArea = new JTextArea(10, 60);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.BOLD, 16)); // Set font to large and bold

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Adding components to frame
        add(scrollPane, BorderLayout.CENTER);

        // Display the window
        setVisible(true);

        // View the diagnoses when the GUI is created
        viewDiagnoses();
    }

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
            String sql = "SELECT * FROM DOCTOR_PATIENT_DIAGNOSES WHERE PATIENT_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, patient.getPatientId());

            // Execute Query
            rs = pstmt.executeQuery();

            // Process the results
            resultArea.append(String.format("%-25s | %s\n", "Patient_ID", "Diagnoses")); // Header
            resultArea.append("---------------------------------------------------------------\n"); // Separator
            while (rs.next()) {
                String pId = rs.getString("PATIENT_ID");
                String diagnoses = rs.getString("DIAGNOSES");
                resultArea.append(String.format("%-25s | %s\n", pId, diagnoses));
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
}
