package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tableConstructors.*;

// Authors: Matt
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
        setTitle("Add New Patient"); // Setting the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when the window is closed
        setSize(400, 600); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
        gbc.anchor = GridBagConstraints.WEST; // Set the anchor to the west

        // Set margins between components
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add labels and text fields for patient information
        
        // First Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("First Name:"), gbc); // Add label for first name
        firstNameField = new JTextField(20); // Create text field for first name
        gbc.gridx = 1;
        panel.add(firstNameField, gbc); // Add text field for first name

        // Last Name
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Last Name:"), gbc); // Add label for last name
        lastNameField = new JTextField(20); // Create text field for last name
        gbc.gridx = 1;
        panel.add(lastNameField, gbc); // Add text field for last name

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc); // Add label for email
        emailField = new JTextField(20); // Create text field for email
        gbc.gridx = 1;
        panel.add(emailField, gbc); // Add text field for email

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc); // Add label for password
        passwordField = new JPasswordField(20); // Create password field
        gbc.gridx = 1;
        panel.add(passwordField, gbc); // Add password field

        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Date of Birth (MM/dd/yyyy):"), gbc); // Add label for date of birth
        dobField = new JTextField(20); // Create text field for date of birth
        gbc.gridx = 1;
        panel.add(dobField, gbc); // Add text field for date of birth

        // Street
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Street:"), gbc); // Add label for street
        streetField = new JTextField(20); // Create text field for street
        gbc.gridx = 1;
        panel.add(streetField, gbc); // Add text field for street

        // City
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("City:"), gbc); // Add label for city
        cityField = new JTextField(20); // Create text field for city
        gbc.gridx = 1;
        panel.add(cityField, gbc); // Add text field for city

        // State
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("State:"), gbc); // Add label for state
        stateField = new JTextField(20); // Create text field for state
        gbc.gridx = 1;
        panel.add(stateField, gbc); // Add text field for state

        // ZIP Code
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ZIP Code:"), gbc); // Add label for ZIP code
        zipCodeField = new JTextField(20); // Create text field for ZIP code
        gbc.gridx = 1;
        panel.add(zipCodeField, gbc); // Add text field for ZIP code

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone Number:"), gbc); // Add label for phone number
        phoneNumberField = new JTextField(20); // Create text field for phone number
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc); // Add text field for phone number

        // Sex
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Sex:"), gbc); // Add label for sex
        sexField = new JTextField(20); // Create text field for sex
        gbc.gridx = 1;
        panel.add(sexField, gbc); // Add text field for sex

        // Insurance ID
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Insurance ID:"), gbc); // Add label for insurance ID
        insuranceIdField = new JTextField(20); // Create text field for insurance ID
        gbc.gridx = 1;
        panel.add(insuranceIdField, gbc); // Add text field for insurance ID

        // Add a button to add the patient
        JButton addButton = new JButton("Add Patient");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc); // Add button to panel

        // ActionListener for the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewPatient(); // Call method to add new patient
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddPatientGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel); // Add panel to JFrame
        setVisible(true); // Make JFrame visible
    }

    // Method to add a new patient
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

    // Method to parse date from string
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Main method to start the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPatientGUI());
    }
}
