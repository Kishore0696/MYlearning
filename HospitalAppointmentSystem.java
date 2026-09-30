import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// ================= MODEL CLASSES =================
class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + name + " (" + specialization + ")";
    }
}

class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phone;

    public Patient(int id, String name, int age, String gender, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + name + " (" + gender + ", " + age + "y)";
    }
}

class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String timeSlot;

    public Appointment(int appointmentId, Patient patient, Doctor doctor, String date, String timeSlot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public int getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDate() { return date; }
    public String getTimeSlot() { return timeSlot; }
}

// ================= MAIN GUI APPLICATION =================
public class HospitalAppointmentSystem extends JFrame {

    // In-memory data structures
    private static final ArrayList<Doctor> doctors = new ArrayList<>();
    private static final ArrayList<Patient> patients = new ArrayList<>();
    private static final ArrayList<Appointment> appointments = new ArrayList<>();

    private static int nextPatientId = 101;
    private static int nextAppointmentId = 1001;

    // GUI Components
    private JTabbedPane tabbedPane;
    private DefaultComboBoxModel<Patient> patientComboModel;
    private DefaultComboBoxModel<Doctor> doctorComboModel;
    private DefaultTableModel doctorTableModel;
    private DefaultTableModel appointmentTableModel;

    public HospitalAppointmentSystem() {
        setTitle("Hospital Appointment Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Preload default doctors
        initializeDoctorData();

        // Build Navigation & Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Register Patient", createPatientPanel());
        tabbedPane.addTab("Doctor List & Search", createDoctorPanel());
        tabbedPane.addTab("Book Appointment", createBookingPanel());
        tabbedPane.addTab("Manage Appointments", createManageAppointmentsPanel());

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 115, 70));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel titleLabel = new JLabel("City Care Hospital Portal");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBackground(new Color(220, 53, 69));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.addActionListener(e -> exitApplication());

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(exitBtn, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void initializeDoctorData() {
        doctors.add(new Doctor(1, "Dr. Priya Sharma", "Cardiologist"));
        doctors.add(new Doctor(2, "Dr. Rajesh Kumar", "Neurologist"));
        doctors.add(new Doctor(3, "Dr. Ananya Iyer", "Dermatologist"));
        doctors.add(new Doctor(4, "Dr. Vikram Patel", "Orthopedic"));
        doctors.add(new Doctor(5, "Dr. Sneha Reddy", "Pediatrician"));
    }

    // Tab 1: Patient Registration
    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField phoneField = new JTextField(20);
        JButton registerBtn = new JButton("Register Patient");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(genderBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(phoneField, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(registerBtn, gbc);

        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                if (age <= 0 || age > 125) throw new NumberFormatException();

                Patient patient = new Patient(nextPatientId++, name, age, gender, phone);
                patients.add(patient);
                patientComboModel.addElement(patient);

                JOptionPane.showMessageDialog(this, "Patient Registered Successfully!\nAssigned ID: " + patient.getId());
                nameField.setText("");
                ageField.setText("");
                phoneField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a valid positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Tab 2: Doctor List & Search
    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Search bar
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search by Name / Spec");
        JButton resetBtn = new JButton("Reset");

        searchBar.add(new JLabel("Search: "));
        searchBar.add(searchField);
        searchBar.add(searchBtn);
        searchBar.add(resetBtn);

        // Table
        String[] cols = {"Doctor ID", "Doctor Name", "Specialization"};
        doctorTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable doctorTable = new JTable(doctorTableModel);
        populateDoctorTable("");

        searchBtn.addActionListener(e -> populateDoctorTable(searchField.getText().trim()));
        resetBtn.addActionListener(e -> {
            searchField.setText("");
            populateDoctorTable("");
        });

        panel.add(searchBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(doctorTable), BorderLayout.CENTER);
        return panel;
    }

    private void populateDoctorTable(String filter) {
        doctorTableModel.setRowCount(0);
        for (Doctor doc : doctors) {
            if (filter.isEmpty() ||
                doc.getName().toLowerCase().contains(filter.toLowerCase()) ||
                doc.getSpecialization().toLowerCase().contains(filter.toLowerCase())) {
                doctorTableModel.addRow(new Object[]{doc.getId(), doc.getName(), doc.getSpecialization()});
            }
        }
    }

    // Tab 3: Book Appointment
    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        patientComboModel = new DefaultComboBoxModel<>();
        doctorComboModel = new DefaultComboBoxModel<>();

        for (Doctor d : doctors) doctorComboModel.addElement(d);

        JComboBox<Patient> patientBox = new JComboBox<>(patientComboModel);
        JComboBox<Doctor> doctorBox = new JComboBox<>(doctorComboModel);
        JTextField dateField = new JTextField("YYYY-MM-DD", 15);
        JComboBox<String> slotBox = new JComboBox<>(new String[]{
            "09:00 AM - 10:00 AM",
            "10:30 AM - 11:30 AM",
            "02:00 PM - 03:00 PM",
            "04:00 PM - 05:00 PM"
        });

        JButton bookBtn = new JButton("Confirm Booking");

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Select Patient:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(patientBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Select Doctor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(doctorBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Time Slot:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(slotBox, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(bookBtn, gbc);

        bookBtn.addActionListener(e -> {
            Patient selectedPatient = (Patient) patientBox.getSelectedItem();
            Doctor selectedDoctor = (Doctor) doctorBox.getSelectedItem();
            String date = dateField.getText().trim();
            String slot = (String) slotBox.getSelectedItem();

            if (selectedPatient == null) {
                JOptionPane.showMessageDialog(this, "No patient selected. Please register a patient first.", "Notice", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (date.isEmpty() || date.equals("YYYY-MM-DD")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid appointment date.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Conflict check: doctor slot availability
            for (Appointment app : appointments) {
                if (app.getDoctor().getId() == selectedDoctor.getId() &&
                    app.getDate().equalsIgnoreCase(date) &&
                    app.getTimeSlot().equalsIgnoreCase(slot)) {
                    JOptionPane.showMessageDialog(this, "This doctor is already booked for " + slot + " on " + date + ".", "Slot Unavailable", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Appointment newAppointment = new Appointment(nextAppointmentId++, selectedPatient, selectedDoctor, date, slot);
            appointments.add(newAppointment);
            refreshAppointmentsTable();

            JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!\nAppointment ID: " + newAppointment.getAppointmentId());
        });

        return panel;
    }

    // Tab 4: View & Cancel Appointments
    private JPanel createManageAppointmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] cols = {"Appt ID", "Patient Name", "Doctor Name", "Specialization", "Date", "Slot"};
        appointmentTableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable appointmentTable = new JTable(appointmentTableModel);

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelBtn = new JButton("Cancel Selected Appointment");
        cancelBtn.setBackground(new Color(220, 53, 69));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);

        bottomBar.add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            int selectedRow = appointmentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment from the table to cancel.", "Notice", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int apptId = (int) appointmentTableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Cancel Appointment #" + apptId + "?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                appointments.removeIf(a -> a.getAppointmentId() == apptId);
                refreshAppointmentsTable();
                JOptionPane.showMessageDialog(this, "Appointment #" + apptId + " cancelled.");
            }
        });

        panel.add(new JScrollPane(appointmentTable), BorderLayout.CENTER);
        panel.add(bottomBar, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshAppointmentsTable() {
        appointmentTableModel.setRowCount(0);
        for (Appointment a : appointments) {
            appointmentTableModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatient().getName(),
                a.getDoctor().getName(),
                a.getDoctor().getSpecialization(),
                a.getDate(),
                a.getTimeSlot()
            });
        }
    }

    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit System", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Hospital Appointment System closed.");
            System.exit(0);
        }
    }

    // ================= CONSOLE ENTRY POINT =================
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Starting Hospital Appointment System...");
        System.out.println("Opening Desktop Interface...");
        System.out.println("==================================================");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            HospitalAppointmentSystem app = new HospitalAppointmentSystem();
            app.setVisible(true);
        });
    }
}