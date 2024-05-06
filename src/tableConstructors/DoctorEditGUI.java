package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;


public class DoctorEditGUI extends JFrame {

    private Doctor doctor;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField emailField;
    private JTextField specializationField;
    private JTextField officeNumberField;

    public DoctorEditGUI(Doctor doctor) {
        this.doctor = doctor;

        setTitle("Update Doctor Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adjust window size to show all fields
        setSize(500, 300);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.BLUE;

        addRow(panel, gbc, "Doctor ID:", doctor.getDoctorId(), labelFont, labelColor);
        lastNameField = addEditableRow(panel, gbc, "Last Name:", doctor.getLastName(), labelFont, labelColor);
        firstNameField = addEditableRow(panel, gbc, "First Name:", doctor.getFirstName(), labelFont, labelColor);
        emailField = addEditableRow(panel, gbc, "Email:", doctor.getEmail(), labelFont, labelColor);
        specializationField = addEditableRow(panel, gbc, "Specialization:", doctor.getSpecialization(), labelFont, labelColor);
        officeNumberField = addEditableRow(panel, gbc, "Office Number:", doctor.getOfficeNumber(), labelFont, labelColor);

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

        // Add "Return to Doctor Menu" button
        JButton returnButton = new JButton("Return to Doctor Menu");
        gbc.gridy++;
        panel.add(returnButton, gbc);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action to return to Doctor Menu
                dispose(); // Close the current GUI
                // Assuming you have a method to open the Doctor Menu GUI
                openDoctorMenu();
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to open the Doctor Menu GUI
    private void openDoctorMenu() {
        // Code to open the Doctor Menu GUI
        // Replace this with your implementation to open the Doctor Menu
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
        // Update doctor information based on text field values
        doctor.setLastName(lastNameField.getText());
        doctor.setFirstName(firstNameField.getText());
        doctor.setEmail(emailField.getText());
        doctor.setSpecialization(specializationField.getText());
        doctor.setOfficeNumber(officeNumberField.getText());

        // Call the method to update doctor information
        doctor.updateDoctorInfo(
                lastNameField.getText(),
                firstNameField.getText(),
                emailField.getText(),
                specializationField.getText(),
                officeNumberField.getText()
        );

        // Display updated doctor information
        System.out.println("Updated Doctor Information:");
        System.out.println(doctor.toString());
    }

    public static void main(String[] args) {
        // Create a sample doctor object
        Doctor doctor = new Doctor("DOC001", "Smith", "John", "john.smith@hospital.com", "password", "Cardiology", "101");

        // Set look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new DoctorEditGUI(doctor));
    }
}
