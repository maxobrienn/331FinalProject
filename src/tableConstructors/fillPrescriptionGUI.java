package tableConstructors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class fillPrescriptionGUI extends JFrame {
    private JButton fillButton;
    private JButton menuButton;
    private JTextField prescriptionIdField;

    public fillPrescriptionGUI() {
        setTitle("Prescription Filling");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel prescriptionIdLabel = new JLabel("Prescription ID:");
        prescriptionIdField = new JTextField();
        fillButton = new JButton("Fill Prescription");
        menuButton = new JButton("Return to Menu");

        panel.add(prescriptionIdLabel);
        panel.add(prescriptionIdField);
        panel.add(fillButton);
        panel.add(menuButton);

        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String prescriptionId = prescriptionIdField.getText();
                fillPrescription(prescriptionId);
            }
        });

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Return to menu action goes here
                // For now, simply close the window
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    public void fillPrescription(String prescriptionId) {
        // Your method implementation here
        // For simplicity, printing to console
        System.out.println("Prescription filled with ID: " + prescriptionId);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new fillPrescriptionGUI();
            }
        });
    }
}
