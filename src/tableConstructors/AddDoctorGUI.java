package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tableConstructors.*;

public class AddDoctorGUI extends JFrame {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField specializationField;
    private JTextField officeNumberField;

    public AddDoctorGUI() {
        setTitle("Add New Doctor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add labels and text fields for doctor information
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
        panel.add(new JLabel("Specialization:"), gbc);
        specializationField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(specializationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Office Number:"), gbc);
        officeNumberField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(officeNumberField, gbc);

        // Add a button to add the doctor
        JButton addButton = new JButton("Add Doctor");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewDoctor();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(AddDoctorGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
        setVisible(true);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddDoctorGUI());
    }
}
