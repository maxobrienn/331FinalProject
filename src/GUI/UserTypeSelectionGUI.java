import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLoginGUI extends JFrame implements ActionListener {
    private JButton updateButton;
    private Object user;

    public UserLoginGUI(Object user) {
        this.user = user;

        setTitle("User Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 rows, 1 column, with 10px horizontal and vertical gap

        // Create buttons
        updateButton = new JButton("Update Preferred Doctor");
        updateButton.addActionListener(this);

        // Add buttons to panel
        panel.add(updateButton);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Here, perform the update operation for the preferred doctor
            // For example, you can update the database and then close this window
            updatePreferredDoctor();
            setVisible(false); // Hide the current window

            // Open the PatientMenu window
            openPatientMenu();
        }
    }

    private void updatePreferredDoctor() {
        // Your code to update the patient's preferred doctor in the database goes here
        // This method should perform the necessary database update operations
    }

    private void openPatientMenu() {
        // Assuming PatientMenu is your main menu for patients
        PatientMenu patientMenu = new PatientMenu();
        patientMenu.setVisible(true);
    }
}
