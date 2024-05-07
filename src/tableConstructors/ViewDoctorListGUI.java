package tableConstructors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewDoctorListGUI extends JFrame {
    private JTable doctorTable;
    private JButton returnButton;

    public ViewDoctorListGUI() {
        setTitle("View Doctor List");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model with column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Last Name");
        model.addColumn("First Name");
        model.addColumn("Email");
        model.addColumn("Specialization");

        // Populate table model with data
        populateTableModel(model);

        // Create table with the populated model
        doctorTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(doctorTable);

        // Create return button
        returnButton = new JButton("Return to Main Menu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });

        // Add components to content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(returnButton, BorderLayout.SOUTH);

        // Position the GUI in the middle of the screen
        setLocationRelativeTo(null);
    }

    private void populateTableModel(DefaultTableModel model) {
        try {
            // Open a database connection.
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");

            // Prepare the SQL statement.
            String queryString = "SELECT LAST, FIRST, EMAIL, SPECIALIZATION FROM HealthCareManagement_DOCTOR";

            // Create a PreparedStatement for executing the query.
            PreparedStatement preparedStmt = connection.prepareStatement(queryString);

            // Execute the query
            ResultSet rs = preparedStmt.executeQuery();

            // Iterate through the result set and add each row to the table model
            while (rs.next()) {
                String lastName = rs.getString("LAST");
                String firstName = rs.getString("FIRST");
                String email = rs.getString("EMAIL");
                String specialization = rs.getString("SPECIALIZATION");

                model.addRow(new Object[]{lastName, firstName, email, specialization});
            }

            // Close the resources
            rs.close();
            preparedStmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void returnToMainMenu() {
        dispose(); // Close the current window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewDoctorListGUI gui = new ViewDoctorListGUI();
                gui.setVisible(true);
            }
        });
    }
}

