/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for viewing past appointments of a patient.
 * It displays appointment details including doctor name, date, and purpose.
 * The GUI also provides a button to return to the patient menu.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import tableConstructors.*;

public class PatientViewPastAppointmentGUI extends JFrame {

    private Patient patient;

    /**
     * Constructs a PatientViewPastAppointmentGUI object.
     * @param patient The patient whose past appointments are being viewed.
     */
    public PatientViewPastAppointmentGUI(Patient patient) {
        this.patient = patient;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("View Past Appointments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Increased height to accommodate the new button

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Retrieve appointment details for the patient
        List<AppointmentDetails> appointmentDetailsList = patient.getAppointmentDetails();

        // Add appointment details labels and values to the panel
        for (AppointmentDetails details : appointmentDetailsList) {
            panel.add(new JLabel("Doctor:"));
            panel.add(new JLabel(details.getDoctorName()));
            panel.add(new JLabel("Date:"));
            panel.add(new JLabel(formatDate(details.getAppointmentDate())));
            panel.add(new JLabel("Purpose:"));
            panel.add(new JLabel(details.getNote()));
        }

        // Add a button to return to the PatientMenu
        JButton returnButton = new JButton("Return to Patient Menu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToPatientMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    private String formatDate(java.util.Date date) {
        if (date == null) {
            return "N/A";
        }
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    private void returnToPatientMenu() {
        // Close this window and return to the PatientMenu
        dispose();
    }
}
