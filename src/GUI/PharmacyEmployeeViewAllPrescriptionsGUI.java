/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for pharmacy employees to view all prescriptions.
 * It displays prescription information such as prescription ID, patient ID, insurance ID, and amount owed.
 */
package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class PharmacyEmployeeViewAllPrescriptionsGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;
    private PharmacyEmployee pharmacyEmployee;
    
    /**
     * Constructs a PharmacyEmployeeViewAllPrescriptionsGUI object.
     * @param pharmacyEmployee The pharmacy employee viewing all prescriptions.
     */
    public PharmacyEmployeeViewAllPrescriptionsGUI(PharmacyEmployee pharmacyEmployee) {
      
      this.pharmacyEmployee = pharmacyEmployee;
      
      setTitle("Pharmacy View All Prescriptions");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      
      // Initialize components
      returnToMenuButton = new JButton("Return to Menu");
      dataTable = new JTable();

        // Create a table model with column headers
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Prescription ID");
        model.addColumn("Patient ID");
        model.addColumn("Insurance ID");
        model.addColumn("Amount Owed");
        dataTable.setModel(model);

        // Add components to the layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnToMenuButton);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Attach event listeners
        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click event
                returnToMenu();
            }
        });

        // Populate the table with data
        populateTable(pharmacyEmployee);

        // Set the size of the window
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    /**
     * ActionListener implementation for the return to menu button.
     * Closes the current window.
     */
    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    /**
     * Populates the table with prescription data.
     * @param pharmacyEmployee The pharmacy employee.
     */
    private void populateTable(PharmacyEmployee pharmacyEmployee) {
        // Call the viewAvailableMedication method and populate the table with the results
        String[][] data = pharmacyEmployee.viewPrescriptions();
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    /**
     * The entry point of the application. Creates an instance of PharmacyEmployee and displays the view all prescriptions GUI.
     */
    public static void main(String[] args) {
        // Create an instance of PharmacyEmployee
        PharmacyEmployee pharmacyEmployee = new PharmacyEmployee();
        pharmacyEmployee.setPharmacyId("PHRM001");

        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PharmacyEmployeeViewAllPrescriptionsGUI gui = new PharmacyEmployeeViewAllPrescriptionsGUI(pharmacyEmployee);
                gui.setVisible(true);
            }
        });
    }
    
}
