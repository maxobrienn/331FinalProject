package tableConstructors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class PharmacyEmployeeViewGUI extends JFrame {

    private PharmacyEmployee employee;

    public PharmacyEmployeeViewGUI(PharmacyEmployee employee) {
        this.employee = employee;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Pharmacy Employee Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Adjusted height to accommodate the new button

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add employee information labels and values to the panel
        panel.add(new JLabel("Employee ID:"));
        panel.add(new JLabel(employee.getEmployeeId()));
        panel.add(new JLabel("First Name:"));
        panel.add(new JLabel(employee.getFirstName()));
        panel.add(new JLabel("Last Name:"));
        panel.add(new JLabel(employee.getLastName()));
        panel.add(new JLabel("SSN:"));
        panel.add(new JLabel(employee.getSsn()));
        panel.add(new JLabel("Phone Number:"));
        panel.add(new JLabel(employee.getPhoneNumber()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(employee.getEmail()));
        panel.add(new JLabel("Position:"));
        panel.add(new JLabel(employee.getPosition()));
        panel.add(new JLabel("Pharmacy ID:"));
        panel.add(new JLabel(employee.getPharmacyId()));

        // Add a button to return to the PharmacyMenu
        JButton returnButton = new JButton("Return to PharmacyMenu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToPharmacyMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    private void returnToPharmacyMenu() {
        // Close this window and return to the PharmacyMenu
        dispose();
    }

    public static void main(String[] args) {
        // Create a sample employee object
        PharmacyEmployee employee = new PharmacyEmployee("EMP001", "Doe", "John", "123-45-6789", "555-123-4567", "john.doe@example.com", "Pharmacist", "PH001", "password123");

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PharmacyEmployeeViewGUI(employee));
    }
}
