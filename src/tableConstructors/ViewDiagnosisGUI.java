
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Patient;

public class ViewDiagnosisGUI extends JFrame {
    private JTextField patientIdField;
    private JTextArea resultArea;
    private JButton viewButton;
    private JButton returnButton; // New button for returning to PatientMenu
    private Patient patient;

    public ViewDiagnosisGUI(Patient patient) {
        this.patient = patient;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Frame initial setup
        setTitle("Patient Diagnosis Viewer");
        setSize(600, 400); // Larger size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        patientIdField = new JTextField(10);
        patientIdField.setText(patient.getPatientId());
        patientIdField.setEditable(false);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        viewButton = new JButton("View Diagnoses");
        viewButton.addActionListener(e -> viewDiagnoses());

        returnButton = new JButton("Return to Patient Menu");
        returnButton.addActionListener(e -> returnToMenu());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Patient ID:"));
        topPanel.add(patientIdField);
        topPanel.add(viewButton);
        topPanel.add(returnButton); // Add return button to the top panel

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Adding components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Display the window
        setVisible(true);
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
            resultArea.append("PATIENT_ID\tDIAGNOSES\n");
            while (rs.next()) {
                String pId = rs.getString("PATIENT_ID");
                String diagnoses = rs.getString("DIAGNOSES");
                resultArea.append(pId + "\t" + diagnoses + "\n");
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

    // Method to return to the Patient Menu
    private void returnToMenu() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(() -> new PatientMenu(patient)); // Open PatientMenu
    }

    public static void main(String[] args) {
        // Assuming Patient is logged in and Patient instance is created accordingly
        Patient patient = new Patient("123456", new java.util.Date(), "123 Elm St", "Anytown", "Anystate", "12345", "email@example.com",
                                      "555-1234", "Doe", "John", "M", "12345-67890", "password123");
        SwingUtilities.invokeLater(() -> new ViewDiagnosisGUI(patient));
    }
}
