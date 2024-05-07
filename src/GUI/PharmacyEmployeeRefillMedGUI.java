/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for the pharmacy employee to refill medication.
 * It allows the pharmacy employee to refill medication by specifying the supplier ID and the amount to be refilled.
 */
package GUI;

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

    /**
     * Constructs a PharmacyEmployeeRefillMedGUI object.
     * @param employee The pharmacy employee who is refilling medication.
     */
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

    /**
     * ActionListener implementation for the refill button.
     * This listener handles the refill action initiated by the user.
     */
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

    /**
     * ActionListener implementation for the return button.
     * This listener handles the action to return to the main menu.
     */
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * The entry point of the application. Creates a sample employee object and displays the refill medication GUI.
     */
    public static void main(String[] args) {
        PharmacyEmployee employee = new PharmacyEmployee();
        new PharmacyEmployeeRefillMedGUI(employee);
    }
}
