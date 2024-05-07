package tableConstructors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class InsuranceViewPrescriptionsGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;

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
                // Handle button click event
                returnToMenu();
            }
        });

        // Populate the table with data
        populateTable(insuranceCompany);

        // Set the size of the window
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    private void populateTable(InsuranceCompany insuranceCompany) {
        // Call the viewCoveredPatientsInformation method and populate the table with the results
        String[][] data = insuranceCompany.viewCoveredPatientsInformation();
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        // Create an instance of InsuranceCompany
        InsuranceCompany insuranceCompany = new InsuranceCompany();

        // Call the makePayment method with the given parameters
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
