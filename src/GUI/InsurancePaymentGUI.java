package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

/**
 * This class represents a graphical user interface for making insurance payments.
 */
public class InsurancePaymentGUI extends JFrame {
    private JTextField amountField;
    private JTextField prescriptionIdField;
    private JLabel statusLabel;
    private InsuranceCompany insuranceCompany;

    /**
     * Constructs a new InsurancePaymentGUI with the specified InsuranceCompany object.
     * @param insuranceCompany The InsuranceCompany object associated with this GUI.
     */
    public InsurancePaymentGUI(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;

        setTitle("Make Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // Increased grid layout to accommodate the new button

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        JLabel prescriptionIdLabel = new JLabel("Prescription ID:");
        prescriptionIdField = new JTextField();
        JButton makePaymentButton = new JButton("Make Payment");
        JButton returnButton = new JButton("Return to Menu"); // New button
        statusLabel = new JLabel();

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(prescriptionIdLabel);
        panel.add(prescriptionIdField);
        panel.add(makePaymentButton);
        panel.add(statusLabel);
        panel.add(returnButton);

        makePaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makePayment();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToInsuranceMenu();
            }
        });

        add(panel);
    }

    /**
     * Closes the current window and returns to the InsuranceMenu.
     */
    private void returnToInsuranceMenu() {
        dispose();
    }

    /**
     * Attempts to make a payment based on the entered amount and prescription ID.
     */
    private void makePayment() {
        String amount = amountField.getText();
        String prescriptionId = prescriptionIdField.getText();

        if (isPrescriptionIdValid(prescriptionId)) {
            insuranceCompany.makePayment(amount, prescriptionId);
            statusLabel.setText("Payment made successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "The entered prescription ID does not exist.", "Invalid Prescription ID", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks if the given prescription ID is valid by querying the database.
     * @param prescriptionId The prescription ID to validate.
     * @return true if the prescription ID exists in the database, false otherwise.
     */
    public boolean isPrescriptionIdValid(String prescriptionId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = insuranceCompany.openDBConnection();
            String queryString = "SELECT COUNT(*) FROM HealthCareManagement_PRESCRIPTION WHERE PRESCRIPTION_ID = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, prescriptionId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InsuranceCompany insuranceCompany = new InsuranceCompany();
                insuranceCompany.setInsuranceId("INS001");

                InsurancePaymentGUI paymentGUI = new InsurancePaymentGUI(insuranceCompany);
                paymentGUI.setVisible(true);
            }
        });
    }
}
