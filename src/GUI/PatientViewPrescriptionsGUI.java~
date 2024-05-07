package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class PatientViewPrescriptionsGUI extends JFrame {
    private JButton returnToMenuButton;
    private JTable dataTable;
    private Patient patient;

    public PatientViewPrescriptionsGUI(Patient patient) {
        this.patient = patient;
        setTitle("View Prescription Balances");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        returnToMenuButton = new JButton("Return to Menu");
        dataTable = new JTable();

        // Create a table model with column headers
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("PRESCRIPTION_ID");
        model.addColumn("DATE_ISSUED");
        model.addColumn("PRESCRIPTION_NAME");
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
        populateTable(patient.viewPrescriptionBalances());

        // Set the size of the window
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    private void populateTable(String[][] data) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        for (String[] row : data) {
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        // Create an instance of Patient
        Patient patient = new Patient();

        // Call the necessary methods to set patient data, if needed
        // For example:
        // patient.setPatientId("PAT001");

        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PatientViewPrescriptionsGUI gui = new PatientViewPrescriptionsGUI(patient);
                gui.setVisible(true);
            }
        });
    }
}
