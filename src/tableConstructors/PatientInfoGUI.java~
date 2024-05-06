import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import tableConstructors.Patient;

public class PatientInfoGUI extends JFrame {
  
  private Patient patient;
  
  public PatientInfoGUI(Patient patient) {
    this.patient = patient;
    
    setTitle("Patient Information");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    
    JPanel panel = new JPanel(new GridLayout(0, 2));
    
    // Add patient information labels and values to the panel
    panel.add(new JLabel("Patient ID:"));
    panel.add(new JLabel(patient.getPatientId()));
    panel.add(new JLabel("First Name:"));
    panel.add(new JLabel(patient.getFirstName()));
    panel.add(new JLabel("Last Name:"));
    panel.add(new JLabel(patient.getLastName()));
    panel.add(new JLabel("Date of Birth:"));
    panel.add(new JLabel(formatDate(patient.getDob())));
    panel.add(new JLabel("Street:"));
    panel.add(new JLabel(patient.getStreet()));
    panel.add(new JLabel("City:"));
    panel.add(new JLabel(patient.getCity()));
    panel.add(new JLabel("State:"));
    panel.add(new JLabel(patient.getState()));
    panel.add(new JLabel("ZIP Code:"));
    panel.add(new JLabel(patient.getZipCode()));
    panel.add(new JLabel("Email:"));
    panel.add(new JLabel(patient.getEmail()));
    panel.add(new JLabel("Phone Number:"));
    panel.add(new JLabel(patient.getPhoneNumber()));
    panel.add(new JLabel("Sex:"));
    panel.add(new JLabel(patient.getSex()));
    panel.add(new JLabel("Insurance ID:"));
    panel.add(new JLabel(patient.getInsuranceId()));
    
    add(panel);
    setVisible(true);
  }
  
  private String formatDate(java.util.Date date) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    return dateFormat.format(date);
  }
  
  @SuppressWarnings("deprecation")
    public static void main(String[] args) {
    // Create a sample patient object
    Patient patient = new Patient("PAT001", new java.util.Date(90, 0, 1),
                                  "1234 Life St", "Anytown", "NY", "12345",
                                  "patient1@email.com", "123-456-7890",
                                  "Doe", "Jane", "Female", "INS001", "thsbaibniincd58n");
    
    // Create and display the GUI
    SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
  }
}
