package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;

public class PatientPaymentGUI extends JFrame {
    private JTextField amountField;
    private JTextField prescriptionIdField;
    private JLabel statusLabel;
    private Patient patient;
    
    public PatientPaymentGUI(Patient patient) {
      this.patient = patient;
      
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
      JButton returnButton = new JButton("Return to Patient Menu"); // New button
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
            returnToPatientMenu(); // Open the patient menu window here
          }
        });
        
        add(panel);
    }
    
    private void returnToPatientMenu() {
        // Close this window and return to the PatientMenu
        dispose();
    }
    
    /**
     * Open a database connection.
     * 
     * @return The database connection.
     */
    public Connection openDBConnection() {
      try {
        // Load driver and link to driver manager
        Class.forName("oracle.jdbc.OracleDriver");
        // Create a connection to the specified database
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                              "csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");
        return myConnection;
      } catch (Exception E) {
        E.printStackTrace();
      }
      return null;
    }
    
    private void makePayment() {
      String amount = amountField.getText();
      String prescriptionId = prescriptionIdField.getText();
      
      if (isPrescriptionIdValid(prescriptionId)) {
        // Call the makePayment method of Patient class using the patient object
        patient.makePayment(amount, prescriptionId);
        
        // Display success message
        statusLabel.setText("Payment made successfully.");
      } else {
        // Prescription ID is not valid, show a pop-up message
        JOptionPane.showMessageDialog(null, "The entered prescription ID does not exist.", "Invalid Prescription ID", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public boolean isPrescriptionIdValid(String prescriptionId) {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      
      try {
        // Open a database connection.
        connection = openDBConnection();
        
        // Prepare the SQL query to check if the prescription ID exists
        String queryString = "SELECT COUNT(*) FROM HealthCareManagement_PRESCRIPTION WHERE PRESCRIPTION_ID = ?";
        preparedStatement = connection.prepareStatement(queryString);
        preparedStatement.setString(1, prescriptionId);
        
        // Execute the query
        resultSet = preparedStatement.executeQuery();
        
        // Get the count of rows returned by the query
        resultSet.next();
        int count = resultSet.getInt(1);
        
        // If count is greater than 0, the prescription ID exists
        return count > 0;
        
      } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false in case of any exception
      } finally {
        // Close JDBC objects in the finally block
        try {
          if (resultSet != null) resultSet.close();
          if (preparedStatement != null) preparedStatement.close();
          if (connection != null) connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a dummy patient object for testing
                Patient patient = new Patient();
                patient.setPatientId("PAT001");

                PatientPaymentGUI paymentGUI = new PatientPaymentGUI(patient);
                paymentGUI.setVisible(true);
            }
        });
    }
}