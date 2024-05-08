package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;

public class NumberOfAppointmentsGUI {
    private JFrame frame;
    private JTextField dateTextField;
    private Doctor doctor;

    public NumberOfAppointmentsGUI(Doctor doctor) {
        this.doctor = doctor;
        frame = new JFrame("Number of Appointments");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel dateLabel = new JLabel("Enter Date (DD-MON-YY):");
        dateTextField = new JTextField(10);
        JButton countButton = new JButton("Get Count");
        JButton returnButton = new JButton("Return to Main Page");

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDate = dateTextField.getText();
                int appointmentCount = getCountOfAppointments(selectedDate);
                JOptionPane.showMessageDialog(frame, "Appointments on " + selectedDate + ": " + appointmentCount);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                new DoctorMenu(doctor).setVisible(true); // Open DoctorMenu with the current doctor
            }
        });

        JPanel panel = new JPanel();
        panel.add(dateLabel);
        panel.add(dateTextField);
        panel.add(countButton);
        panel.add(returnButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    // Your method to get the count of appointments based on the selected date
    private int getCountOfAppointments(String selectedDate) {
    int appointmentCount = doctor.getCountOfAppointments(doctor.getDoctorId(), selectedDate);
    return appointmentCount;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a Doctor object and pass it to the constructor
                Doctor doctor = new Doctor();
                new NumberOfAppointmentsGUI(doctor);
            }
        });
    }
}
