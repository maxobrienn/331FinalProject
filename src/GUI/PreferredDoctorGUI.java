package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class PreferredDoctorGUI extends JFrame {
    private JComboBox<String> doctorComboBox;
    private JButton updateButton;
    private JButton returnButton; // New button for returning to the main page
    private String selectedDoctor;
    private Patient patient;

    public PreferredDoctorGUI(Patient patient) {
        this.patient = patient; // Store the Patient object
        setTitle("Update Preferred Doctor");
        setSize(600, 200); // Increased height to accommodate the button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        doctorComboBox = new JComboBox<>();
        updateButton = new JButton("Update");
        updateButton.setEnabled(false); // Button initially disabled
        
        // Initialize return button
        returnButton = new JButton("Return to Main Page");

        // Set preferred size for buttons
        updateButton.setPreferredSize(new Dimension(100, 30)); // Set smaller size for update button
        returnButton.setPreferredSize(new Dimension(150, 30)); // Set smaller size for return button

        // Add components to content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(doctorComboBox, BorderLayout.NORTH); // Changed to NORTH to accommodate return button
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // Panel for buttons
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);
        container.add(buttonPanel, BorderLayout.CENTER); // Added button panel
        

        // Populate doctor names in the combo box
        populateDoctorComboBox();

        // Position the GUI in the middle of the screen
        setLocationRelativeTo(null);

        // Add action listener to the combo box
        doctorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected item
                String selected = (String) doctorComboBox.getSelectedItem();
                
                // Check if it's the default option
                if (!selected.equals("Choose a doctor")) {
                    // Enable the update button when a doctor is selected
                    selectedDoctor = selected;
                    updateButton.setEnabled(true);
                } else {
                    // Disable the update button if "Choose a doctor" is selected
                    updateButton.setEnabled(false);
                }
            }
        });

        // Add action listener to the update button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePatientPreferredDoctor();
            }
        });
        
        // Add action listener to the return button
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close this GUI and open the main menu
                dispose(); // Close this window
                PatientMenu.main(new String[]{}); // Open main menu
            }
        });
    }

    private void populateDoctorComboBox() {
        // Add default option
        doctorComboBox.addItem("Choose a doctor");

        // Retrieve doctor names from the database
        ArrayList<String> doctorNames = getDoctorNamesFromDatabase();

        // Add doctor names to the combo box
        for (String name : doctorNames) {
            doctorComboBox.addItem(name);
        }
    }

    private ArrayList<String> getDoctorNamesFromDatabase() {
        ArrayList<String> doctorNames = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                                "csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LAST, FIRST FROM HealthCareManagement_DOCTOR");

            // Add doctor names to the list
            while (resultSet.next()) {
                String lastName = resultSet.getString("LAST");
                String firstName = resultSet.getString("FIRST");
                String fullName = lastName + ", " + firstName;
                doctorNames.add(fullName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorNames;
    }

    private void updatePatientPreferredDoctor() {
        // Split the full name into first and last name
        String[] nameParts = selectedDoctor.split(", ");
        String lastName = nameParts[0];
        String firstName = nameParts[1];

        // Call the updatePatientPreferredDoctor method of the Patient object
        String preferredDoctorFullName = lastName + ", " + firstName;
        patient.updatePatientPreferredDoctor(preferredDoctorFullName);


        // Display a message indicating that the preferred doctor has been updated
        JOptionPane.showMessageDialog(this, "Preferred doctor updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);

        // Reset combo box selection and disable the update button
        doctorComboBox.setSelectedIndex(-1);
        updateButton.setEnabled(false);
    }

    public static void main(String[] args) {
        // Create a Patient object
        Patient patient = new Patient();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Pass the Patient object to the constructor of PreferredDoctorGUI
                PreferredDoctorGUI gui = new PreferredDoctorGUI(patient);
                gui.setVisible(true);
            }
        });
    }
}


