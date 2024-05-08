
package GUI;
import tableConstructors.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class PreferredDoctorGUI extends JFrame {
    private JComboBox<String> doctorComboBox;
    private JButton updateButton;
    private JButton returnButton;
    private String selectedDoctor;
    private Patient patient;

    public PreferredDoctorGUI(Patient patient) {
        this.patient = patient;
        setTitle("Update Preferred Doctor");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        doctorComboBox = new JComboBox<>();
        updateButton = new JButton("Update");
        updateButton.setEnabled(false);

        returnButton = new JButton("Return to Main Page");
        updateButton.setPreferredSize(new Dimension(30, 30));
        returnButton.setPreferredSize(new Dimension(30, 30));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(doctorComboBox, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);
        container.add(buttonPanel, BorderLayout.CENTER);

        populateDoctorComboBox();
        setLocationRelativeTo(null);

        doctorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) doctorComboBox.getSelectedItem();
                if (!selected.equals("Choose a doctor")) {
                    selectedDoctor = selected;
                    updateButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePatientPreferredDoctor();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                PatientMenu.main(new String[]{});
            }
        });
    }

    private void populateDoctorComboBox() {
        doctorComboBox.addItem("Choose a doctor");
        ArrayList<String> doctorLastNames = getDoctorLastNamesFromDatabase();
        for (String lastName : doctorLastNames) {
            doctorComboBox.addItem(lastName);
        }
    }

    private ArrayList<String> getDoctorLastNamesFromDatabase() {
        ArrayList<String> doctorLastNames = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//cscioraclerh7srv.ad.csbsju.edu:1521/" +
                                                                "csci.cscioraclerh7srv.ad.csbsju.edu","TEAM05", "TEAM05");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LAST FROM HealthCareManagement_DOCTOR");

            while (resultSet.next()) {
                String lastName = resultSet.getString("LAST");
                doctorLastNames.add(lastName);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorLastNames;
    }

    private void updatePatientPreferredDoctor() {
        String lastName = selectedDoctor;
        patient.updatePatientPreferredDoctor(lastName);
        JOptionPane.showMessageDialog(this, "Preferred doctor updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        doctorComboBox.setSelectedIndex(0);
        updateButton.setEnabled(false);
    }

    public static void main(String[] args) {
        Patient patient = new Patient();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreferredDoctorGUI gui = new PreferredDoctorGUI(patient);
                gui.setVisible(true);
            }
        });
    }
}
