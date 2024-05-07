package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PharmacyEmployeeRefillMedGUI extends JFrame {
    private JTextField supplierIdField;
    private JTextField amountField;
    private JButton refillButton;
    private JButton returnButton;
    private PharmacyEmployee employee;

    public PharmacyEmployeeRefillMedGUI(PharmacyEmployee employee) {
        this.employee = employee;
        setTitle("Pharmacy Refill System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2)); // Adjusted layout

        supplierIdField = new JTextField();
        amountField = new JTextField();
        refillButton = new JButton("Refill");
        returnButton = new JButton("Return to Menu");

        refillButton.addActionListener(new RefillButtonListener());
        returnButton.addActionListener(new ReturnButtonListener());

        panel.add(new JLabel("Supplier ID:"));
        panel.add(supplierIdField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(refillButton);
        panel.add(returnButton); // Add return button to the panel

        add(panel, BorderLayout.NORTH);

        // Center the window on the screen
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private class RefillButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String supplierId = supplierIdField.getText();
            String amount = amountField.getText();
            String message = employee.updateMedicationSupply(supplierId, amount);
            if (message.startsWith("Medication quantity")) {
                JOptionPane.showMessageDialog(PharmacyEmployeeRefillMedGUI.this,
                        "Medication quantity for " + supplierId + " updated to " + amount);
            } else {
                JOptionPane.showMessageDialog(PharmacyEmployeeRefillMedGUI.this,
                        message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public static void main(String[] args) {
        PharmacyEmployee employee = new PharmacyEmployee();
        new PharmacyEmployeeRefillMedGUI(employee);
    }
}
