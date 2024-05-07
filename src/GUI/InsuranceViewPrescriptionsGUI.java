package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

/**
 * This class represents a graphical user interface for insurance companies to view prescriptions of covered patients.
 */
public class InsuranceViewPrescriptionsGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;

    /**
     * Constructs a new InsuranceViewPrescriptionsGUI with the specified InsuranceCompany object.
     * @param insuranceCompany The InsuranceCompany object associated with this GUI.
     */
    public InsuranceViewPrescriptionsGUI(InsuranceCompany insuranceCompany) {
        setTitle("Insurance View Prescriptions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        returnToMenuButton = new JButton("Return to Menu");
        dataTable = new JTable();

        // Create a table model with column headers
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("PATIENT_ID");
        model.addColumn("PATIENT_NAME");
        model.addColumn("PRESCRIPTION_ID"); // New column for prescription ID
        model.addColumn("INSURANCE_ID");
        model.addColumn("AMOUNT_OWED");
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
                returnToMenu();
            }
        });

        // Populate the table with data
        populateTable(insuranceCompany);

        // Set the size of the window
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    /**
     * Closes the current window and returns to the InsuranceMenu.
     */
    private void returnToMenu() {
        dispose();
    }

    /**
     * Populates the table with data from the InsuranceCompany object.
     * @param insuranceCompany The InsuranceCompany object containing the data to populate the table.
     */
    private void populateTable(InsuranceCompany insuranceCompany) {
        String[][] data = insuranceCompany.viewCoveredPatientsInformation();
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    /**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of InsuranceCompany
        InsuranceCompany insuranceCompany = new InsuranceCompany();

        // Set insurance company ID
        insuranceCompany.setInsuranceId("INS001");

        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InsuranceViewPrescriptionsGUI gui = new InsuranceViewPrescriptionsGUI(insuranceCompany);
                gui.setVisible(true);
            }
        });
    }
}
