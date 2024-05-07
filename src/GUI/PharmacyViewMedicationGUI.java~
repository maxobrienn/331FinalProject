package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class PharmacyViewMedicationGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;

    public PharmacyViewMedicationGUI(PharmacyEmployee pharmacyEmployee) {
        setTitle("Pharmacy View Medication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        returnToMenuButton = new JButton("Return to Menu");
        dataTable = new JTable();

        // Create a table model with column headers
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Medication Name");
        model.addColumn("Supplier ID");
        model.addColumn("Quantity");
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

    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    private void populateTable(PharmacyEmployee pharmacyEmployee) {
        // Call the viewAvailableMedication method and populate the table with the results
        String[][] data = pharmacyEmployee.viewAvailableMedication();
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        // Create an instance of PharmacyEmployee
        PharmacyEmployee pharmacyEmployee = new PharmacyEmployee();
        pharmacyEmployee.setPharmacyId("PHRM001");

        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PharmacyViewMedicationGUI gui = new PharmacyViewMedicationGUI(pharmacyEmployee);
                gui.setVisible(true);
            }
        });
    }
}
