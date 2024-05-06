import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import tableConstructors.Patient;

public class PatientEditGUI extends JFrame {

    private Patient patient;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField insuranceIdField;
    private JTextField sexField;

    public PatientEditGUI(Patient patient) {
        this.patient = patient;

        setTitle("Update Patient Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.BLUE;

        addRow(panel, gbc, "Patient ID:", patient.getPatientId(), labelFont, labelColor);
        phoneNumberField = addEditableRow(panel, gbc, "Phone Number:", patient.getPhoneNumber(), labelFont, labelColor);
        emailField = addEditableRow(panel, gbc, "Email:", patient.getEmail(), labelFont, labelColor);
        streetField = addEditableRow(panel, gbc, "Street:", patient.getStreet(), labelFont, labelColor);
        cityField = addEditableRow(panel, gbc, "City:", patient.getCity(), labelFont, labelColor);
        stateField = addEditableRow(panel, gbc, "State:", patient.getState(), labelFont, labelColor);
        zipCodeField = addEditableRow(panel, gbc, "ZIP Code:", patient.getZipCode(), labelFont, labelColor);
        insuranceIdField = addEditableRow(panel, gbc, "Insurance ID:", patient.getInsuranceId(), labelFont, labelColor);
        sexField = addEditableRow(panel, gbc, "Sex:", patient.getSex(), labelFont, labelColor);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label, gbc);

        JLabel value = new JLabel(valueText);
        value.setFont(labelFont);
        gbc.gridx = 1;
        panel.add(value, gbc);
    }

    private JTextField addEditableRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label, gbc);

        JTextField textField = new JTextField(valueText, 15);
        textField.setFont(labelFont);
        gbc.gridx = 1;
        panel.add(textField, gbc);

        return textField;
    }

    private void saveChanges() {
        // Update patient information based on text field values
        patient.setPhoneNumber(phoneNumberField.getText());
        patient.setEmail(emailField.getText());
        patient.setStreet(streetField.getText());
        patient.setCity(cityField.getText());
        patient.setState(stateField.getText());
        patient.setZipCode(zipCodeField.getText());
        patient.setInsuranceId(insuranceIdField.getText());
        patient.setSex(sexField.getText());

        // Call the method to update patient information
        patient.updatePatientInfo(
                phoneNumberField.getText(),
                emailField.getText(),
                streetField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipCodeField.getText(),
                insuranceIdField.getText(),
                sexField.getText()
        );

        // Display updated patient information
        System.out.println("Updated Patient Information:");
        System.out.println(patient.toString());
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
        SwingUtilities.invokeLater(() -> new PatientEditGUI(patient));
    }
}
