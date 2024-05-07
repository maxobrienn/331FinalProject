package tableConstructors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import tableConstructors.*;

public class PatientViewPastAppointmentGUI extends JFrame {
  
  public PatientViewPastAppointmentGUI(List<AppointmentDetails> appointmentDetailsList) {
    setTitle("Past Appointments");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    
    // Create a table to display past appointments
    String[] columnNames = {"Doctor Name", "Appointment Date", "Note"};
    Object[][] data = new Object[appointmentDetailsList.size()][3];
    
    for (int i = 0; i < appointmentDetailsList.size(); i++) {
      AppointmentDetails appointmentDetails = appointmentDetailsList.get(i);
      data[i][0] = appointmentDetails.getDoctorName();
      data[i][1] = appointmentDetails.getAppointmentDate();
      data[i][2] = appointmentDetails.getNote();
    }
    
    JTable table = new JTable(new DefaultTableModel(data, columnNames));
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
    
    // Create a "Return to Menu" button
    JButton returnButton = new JButton("Return to Menu");
    returnButton.addActionListener(e -> {
      dispose(); // Close the current window
      // Call a method to display the main menu GUI
      // For example: displayMainMenuGUI();
    });
    add(returnButton, BorderLayout.SOUTH);
    
    // Set frame visibility
    setVisible(true);
  }
}
