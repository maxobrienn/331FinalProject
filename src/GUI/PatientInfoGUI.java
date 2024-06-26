package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

/**
 * Represents a graphical user interface for displaying patient information.
 */
public class PatientInfoGUI extends JFrame {

    private Patient patient;

    /**
     * Constructs a PatientInfoGUI object with the specified Patient.
     * @param patient The Patient object to display information.
     */
    public PatientInfoGUI(Patient patient) {
        this.patient = patient;
        initializeUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        setTitle("Patient Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Increased height to accommodate the new button

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add patient information labels and values to the panel
        panel.add(new JLabel("Patient ID:"));
        panel.add(new JLabel(patient.getPatientId()));
        panel.add(new JLabel("First Name:"));
        panel.add(new JLabel(patient.getFirstName()));
        panel.add(new JLabel("Last Name:"));
        panel.add(new JLabel(patient.getLastName()));
        panel.add(new JLabel("Date of Birth:"));
        panel.add(new JLabel(formatDate(patient.getDob())));
        panel.add(new JLabel("Street:"));
        panel.add(new JLabel(patient.getStreet()));
        panel.add(new JLabel("City:"));
        panel.add(new JLabel(patient.getCity()));
        panel.add(new JLabel("State:"));
        panel.add(new JLabel(patient.getState()));
        panel.add(new JLabel("ZIP Code:"));
        panel.add(new JLabel(patient.getZipCode()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(patient.getEmail()));
        panel.add(new JLabel("Phone Number:"));
        panel.add(new JLabel(patient.getPhoneNumber()));
        panel.add(new JLabel("Sex:"));
        panel.add(new JLabel(patient.getSex()));
        panel.add(new JLabel("Insurance ID:"));
        panel.add(new JLabel(patient.getInsuranceId()));
        panel.add(new JLabel("Preferred Doctor:"));
        panel.add(new JLabel(patient.getPreferredDoctor()));

        // Add a button to return to the PatientMenu
        JButton returnButton = new JButton("Return to PatientMenu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToPatientMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Formats the given date to a string in MM/dd/yyyy format.
     * @param date The date to format.
     * @return The formatted date string.
     */
    private String formatDate(java.util.Date date) {
        if (date == null) {
            return "N/A";
        }
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    /**
     * Closes the current window and returns to the PatientMenu.
     */
    private void returnToPatientMenu() {
        // Close this window and return to the PatientMenu
        dispose();
    }

    /**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a sample patient object
        Patient patient = new Patient("PAT001", new java.util.Date(90, 0, 1),
                "1234 Life St", "Anytown", "NY", "12345",
                "patient1@email.com", "123-456-7890",
                "Doe", "Jane", "Female", "INS001", "thsbaibniincd58n", "Test");

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
    }
}
