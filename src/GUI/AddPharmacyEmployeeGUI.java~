package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddPharmacyEmployeeGUI extends JFrame {

    private JTextField employeeIdField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField ssnField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField positionField;
    private JTextField pharmacyIdField;
    private JTextField passwordField;

    public AddPharmacyEmployeeGUI() {
        setTitle("Add New Pharmacy Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);


        // Last Name
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        // First Name
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        // SSN
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("SSN:"), gbc);
        ssnField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(ssnField, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone Number:"), gbc);
        phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Position
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Position:"), gbc);
        positionField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        // Pharmacy ID
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Pharmacy ID:"), gbc);
        pharmacyIdField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(pharmacyIdField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Add Button
        JButton addButton = new JButton("Add Pharmacy Employee");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Action Listener for Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPharmacyEmployee();
            }
        });

        // Add panel to frame
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to add a new pharmacy employee
    private void addPharmacyEmployee() {
        PharmacyEmployee pharmacyEmployee = new PharmacyEmployee();
        pharmacyEmployee.setLastName(lastNameField.getText());
        pharmacyEmployee.setFirstName(firstNameField.getText());
        pharmacyEmployee.setSsn(ssnField.getText());
        pharmacyEmployee.setPhoneNumber(phoneNumberField.getText());
        pharmacyEmployee.setEmail(emailField.getText());
        pharmacyEmployee.setPosition(positionField.getText());
        pharmacyEmployee.setPharmacyId(pharmacyIdField.getText());
        pharmacyEmployee.setPassword(passwordField.getText());

        pharmacyEmployee.addPharmacyEmployee(pharmacyEmployee);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPharmacyEmployeeGUI());
    }
}
