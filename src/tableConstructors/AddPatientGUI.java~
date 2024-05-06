import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tableConstructors.Patient;

public class AddPatientGUI extends JFrame {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField dobField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField phoneNumberField;
    private JTextField sexField;
    private JTextField insuranceIdField;

    public AddPatientGUI() {
        setTitle("Add New Patient");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add labels and text fields for patient information
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Date of Birth (MM/dd/yyyy):"), gbc);
        dobField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Street:"), gbc);
        streetField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("City:"), gbc);
        cityField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("State:"), gbc);
        stateField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(stateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ZIP Code:"), gbc);
        zipCodeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(zipCodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone Number:"), gbc);
        phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Sex:"), gbc);
        sexField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(sexField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Insurance ID:"), gbc);
        insuranceIdField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(insuranceIdField, gbc);

        // Add a button to add the patient
        JButton addButton = new JButton("Add Patient");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewPatient();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddPatientGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void addNewPatient() throws SQLException {
        // Get data from text fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        Date dob = parseDate(dobField.getText()); // Convert string to Date
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zipCode = zipCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        String sex = sexField.getText();
        String insuranceId = insuranceIdField.getText();

        // Create a new Patient object
        Patient newPatient = new Patient();
        newPatient.setFirstName(firstName);
        newPatient.setLastName(lastName);
        newPatient.setEmail(email);
        newPatient.setPassword(password);
        newPatient.setDob(dob);
        newPatient.setStreet(street);
        newPatient.setCity(city);
        newPatient.setState(state);
        newPatient.setZipCode(zipCode);
        newPatient.setPhoneNumber(phoneNumber);
        newPatient.setSex(sex);
        newPatient.setInsuranceId(insuranceId);

        // Call the addPatient method directly from the Patient class
        newPatient.addPatient(newPatient);

        // Display a success message
        JOptionPane.showMessageDialog(this, "Patient added successfully!");
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPatientGUI());
    }
}
