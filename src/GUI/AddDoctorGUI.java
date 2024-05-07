package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import tableConstructors.*;

// Author: Matt
public class AddDoctorGUI extends JFrame {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField specializationField;
    private JTextField officeNumberField;

    public AddDoctorGUI() {
        setTitle("Add New Doctor"); // Setting the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when the window is closed
        setSize(400, 500); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
        gbc.anchor = GridBagConstraints.WEST; // Set the anchor to the west

        // Set margins between components
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add labels and text fields for doctor information

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

        // Specialization
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Specialization:"), gbc); // Add label for specialization
        specializationField = new JTextField(20); // Create text field for specialization
        gbc.gridx = 1;
        panel.add(specializationField, gbc); // Add text field for specialization

        // Office Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Office Number:"), gbc); // Add label for office number
        officeNumberField = new JTextField(20); // Create text field for office number
        gbc.gridx = 1;
        panel.add(officeNumberField, gbc); // Add text field for office number

        // Add a button to add the doctor
        JButton addButton = new JButton("Add Doctor");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc); // Add button to panel

        // ActionListener for the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewDoctor(); // Call method to add new doctor
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddDoctorGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel); // Add panel to JFrame
        setVisible(true); // Make JFrame visible
    }

    // Method to add a new doctor
    private void addNewDoctor() throws SQLException {
        // Get data from text fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String specialization = specializationField.getText();
        String officeNumber = officeNumberField.getText();

        // Create a new Doctor object
        Doctor newDoctor = new Doctor();
        newDoctor.setFirstName(firstName);
        newDoctor.setLastName(lastName);
        newDoctor.setEmail(email);
        newDoctor.setPassword(password);
        newDoctor.setSpecialization(specialization);
        newDoctor.setOfficeNumber(officeNumber);

        // Call the addDoctor method directly from the Doctor class
        newDoctor.addDoctor(newDoctor);

        // Display a success message
        JOptionPane.showMessageDialog(this, "Doctor added successfully!");
    }

    // Main method to start the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddDoctorGUI());
    }
}
