import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PatientInfoGUI extends JFrame {

    private Patient patient;

    public PatientInfoGUI(Patient patient) {
        this.patient = patient;

        setTitle("Patient Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.BLUE;

        // Add patient information labels and values to the panel
        addRow(panel, gbc, "Patient ID:", patient.getPatientId(), labelFont, labelColor);
        addRow(panel, gbc, "First Name:", patient.getFirstName(), labelFont, labelColor);
        addRow(panel, gbc, "Last Name:", patient.getLastName(), labelFont, labelColor);
        addRow(panel, gbc, "Date of Birth:", formatDate(patient.getDob()), labelFont, labelColor);
        addRow(panel, gbc, "Street:", patient.getStreet(), labelFont, labelColor);
        addRow(panel, gbc, "City:", patient.getCity(), labelFont, labelColor);
        addRow(panel, gbc, "State:", patient.getState(), labelFont, labelColor);
        addRow(panel, gbc, "ZIP Code:", patient.getZipCode(), labelFont, labelColor);
        addRow(panel, gbc, "Email:", patient.getEmail(), labelFont, labelColor);
        addRow(panel, gbc, "Phone Number:", patient.getPhoneNumber(), labelFont, labelColor);
        addRow(panel, gbc, "Sex:", patient.getSex(), labelFont, labelColor);
        addRow(panel, gbc, "Insurance ID:", patient.getInsuranceId(), labelFont, labelColor);

        add(panel);
        setVisible(true);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        panel.add(label, gbc);

        JLabel value = new JLabel(valueText);
        value.setFont(labelFont);
        gbc.gridx = 1;
        panel.add(value, gbc);

        gbc.gridy++;
    }

    private String formatDate(java.util.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        // Create a sample patient object
        Patient patient = new Patient("PAT001", new java.util.Date(90, 0, 1),
                "1234 Life St", "Anytown", "NY", "12345",
                "patient1@email.com", "123-456-7890",
                "Doe", "Jane", "Female", "INS001", "thsbaibniincd58n");

        // Set look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
    }
}
