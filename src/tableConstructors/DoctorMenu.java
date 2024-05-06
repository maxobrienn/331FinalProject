import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Doctor;

public class DoctorMenu extends JFrame {
    private Doctor doctor;

    public DoctorMenu(Doctor doctor) {
        this.doctor = doctor;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Doctor Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton viewProfileButton = new JButton("View Profile");
        JButton updateInfoButton = new JButton("Update Info");
        JButton viewPatientsButton = new JButton("View Patients");

        viewProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayProfile();
            }
        });

        updateInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateInfo();
            }
        });

        viewPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPatients();
            }
        });

        panel.add(viewProfileButton);
        panel.add(updateInfoButton);
        panel.add(viewPatientsButton);

        add(panel);
    }

    private void displayProfile() {
        //SwingUtilities.invokeLater(() -> new DoctorInfoGUI(doctor));
    }

    private void updateInfo() {
        // Open a dialog to update doctor information
        // Example: new UpdateInfoDialog(doctor).setVisible(true);
    }

    private void viewPatients() {
        // Display the list of patients associated with this doctor
        // Example: doctor.viewPatients();
    }

    public static void main(String[] args) {
        // Example usage:
        // Doctor doctor = new Doctor();
        // new DoctorMenu(doctor).setVisible(true);
    }
}
