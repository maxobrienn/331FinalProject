/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for viewing medication information in a pharmacy.
 * It displays medication details such as name, supplier ID, and quantity, and provides an option to return to the pharmacy menu.
 */
package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.PharmacyEmployee;

public class PharmacyViewMedicationGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;

    /**
     * Constructs a PharmacyViewMedicationGUI object.
     * @param pharmacyEmployee The pharmacy employee accessing the medication information.
     */
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

    /**
     * Handles the action to return to the pharmacy menu.
     */
    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    /**
     * Populates the table with medication data.
     * @param pharmacyEmployee The pharmacy employee accessing the medication information.
     */
    private void populateTable(PharmacyEmployee pharmacyEmployee) {
        // Call the viewAvailableMedication method and populate the table with the results
        String[][] data = pharmacyEmployee.viewAvailableMedication();
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    /**
     * The entry point of the application.
     * Example usage:
     * // Create an instance of PharmacyEmployee
     * // PharmacyEmployee pharmacyEmployee = new PharmacyEmployee();
     * // pharmacyEmployee.setPharmacyId("PHRM001");
     * // Create and display the GUI
     * // SwingUtilities.invokeLater(new Runnable() {
     * //     @Override
     * //     public void run() {
     * //         PharmacyViewMedicationGUI gui = new PharmacyViewMedicationGUI(pharmacyEmployee);
     * //         gui.setVisible(true);
     * //     }
     * // });
     */
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
