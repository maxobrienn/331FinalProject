package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import tableConstructors.*;

public class DoctorViewInfoGUI extends JFrame {

    private JTextField doctorIdField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField emailField;
    private JTextField specializationField;
    private JTextField officeNumberField;
    private JButton closeButton;

    public DoctorViewInfoGUI(Doctor doctor) {
        setTitle("Doctor Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add labels and text fields for doctor information
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Doctor ID:"), gbc);
        doctorIdField = new JTextField(20);
        doctorIdField.setEditable(false);
        doctorIdField.setText(doctor.getDoctorId());
        gbc.gridx = 1;
        panel.add(doctorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(20);
        lastNameField.setEditable(false);
        lastNameField.setText(doctor.getLastName());
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(20);
        firstNameField.setEditable(false);
        firstNameField.setText(doctor.getFirstName());
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        emailField.setEditable(false);
        emailField.setText(doctor.getEmail());
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Specialization:"), gbc);
        specializationField = new JTextField(20);
        specializationField.setEditable(false);
        specializationField.setText(doctor.getSpecialization());
        gbc.gridx = 1;
        panel.add(specializationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Office Number:"), gbc);
        officeNumberField = new JTextField(20);
        officeNumberField.setEditable(false);
        officeNumberField.setText(doctor.getOfficeNumber());
        gbc.gridx = 1;
        panel.add(officeNumberField, gbc);

        // Add a close button
        closeButton = new JButton("Close");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(closeButton, gbc);

        closeButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Sample Doctor object for testing
        Doctor doctor = new Doctor("DOC001", "Smith", "John", "john.smith@hospital.com", "password", "Cardiology", "101");

        SwingUtilities.invokeLater(() -> new DoctorViewInfoGUI(doctor));
    }
}
