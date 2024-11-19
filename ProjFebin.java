import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjFebin {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel studentPanel, staffPanel, parentPanel, workerPanel, retrievePanel;
    private JTextField studentNameField, studentAgeField, studentUIDField;
    private JTextField staffNameField, staffDesignationField, staffUIDField;
    private JTextField parentNameField, parentContactField, parentUIDField;
    private JTextField workerNameField, workerDepartmentField, workerUIDField;
    private JButton studentSubmitButton, staffSubmitButton, parentSubmitButton, workerSubmitButton, retrieveButton;
    private JRadioButton studentRadioButton, parentRadioButton, workerRadioButton, staffRadioButton;
    private ButtonGroup radioButtonGroup;
    private JTextField uidField;
    private Connection conn;
    private Statement stmt;

    public ProjFebin() {
        frame = new JFrame("Personal Info Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(4, 2, 10, 70));
        studentPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField(20);
        studentPanel.add(studentNameField);
        studentPanel.add(new JLabel("Student Age:"));
        studentAgeField = new JTextField(20);
        studentPanel.add(studentAgeField);
        studentPanel.add(new JLabel("Student UID:"));
        studentUIDField = new JTextField(20);
        studentPanel.add(studentUIDField);
        studentSubmitButton = new JButton("Submit");
        studentSubmitButton.addActionListener(new StudentSubmitButtonListener());
        studentPanel.add(studentSubmitButton);
        tabbedPane.addTab("Students", studentPanel);

        staffPanel = new JPanel();
        staffPanel.setLayout(new GridLayout(4, 2, 10, 70));
        staffPanel.add(new JLabel("Staff Name:"));
        staffNameField = new JTextField(20);
        staffPanel.add(staffNameField);
        staffPanel.add(new JLabel("Staff Designation:"));
        staffDesignationField = new JTextField(20);
        staffPanel.add(staffDesignationField);
        staffPanel.add(new JLabel("Staff UID:"));
        staffUIDField = new JTextField(20);
        staffPanel.add(staffUIDField);
        staffSubmitButton = new JButton("Submit");
        staffSubmitButton.addActionListener(new StaffSubmitButtonListener());
        staffPanel.add(staffSubmitButton);
        tabbedPane.addTab("Staff", staffPanel);

        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayout(4, 2, 10, 70));
        parentPanel.add(new JLabel("Parent Name:"));
        parentNameField = new JTextField(20);
        parentPanel.add(parentNameField);
        parentPanel.add(new JLabel("Parent Contact:"));
        parentContactField = new JTextField(20);
        parentPanel.add(parentContactField);
        parentPanel.add(new JLabel("Parent UID:"));
        parentUIDField = new JTextField(20);
        parentPanel.add(parentUIDField);
        parentSubmitButton = new JButton("Submit");
        parentSubmitButton.addActionListener(new ParentSubmitButtonListener());
        parentPanel.add(parentSubmitButton);
        tabbedPane.addTab("Parents", parentPanel);

        workerPanel = new JPanel();
        workerPanel.setLayout(new GridLayout(4, 2, 10, 70));
        workerPanel.add(new JLabel("Worker Name:"));
        workerNameField = new JTextField(20);
        workerPanel.add(workerNameField);
        workerPanel.add(new JLabel("Worker Department:"));
        workerDepartmentField = new JTextField(20);
        workerPanel.add(workerDepartmentField);
        workerPanel.add(new JLabel("Worker UID:"));
        workerUIDField = new JTextField(20);
        workerPanel.add(workerUIDField);
        workerSubmitButton = new JButton("Submit");
        workerSubmitButton.addActionListener(new WorkerSubmitButtonListener());
        workerPanel.add(workerSubmitButton);
        tabbedPane.addTab("Workers", workerPanel);

        retrievePanel = new JPanel();
        retrievePanel.setLayout(new GridLayout(5, 1, 10, 10));
        studentRadioButton = new JRadioButton("Student");
        parentRadioButton = new JRadioButton("Parent");
        workerRadioButton = new JRadioButton("Worker");
        staffRadioButton = new JRadioButton("Staff");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(studentRadioButton);
        radioButtonGroup.add(parentRadioButton);
        radioButtonGroup.add(workerRadioButton);
        radioButtonGroup.add(staffRadioButton);
        retrievePanel.add(studentRadioButton);
        retrievePanel.add(parentRadioButton);
        retrievePanel.add(workerRadioButton);
        retrievePanel.add(staffRadioButton);
        retrievePanel.add(new JLabel("Enter UID:"));
        uidField = new JTextField(20);
        retrievePanel.add(uidField);
        retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(new RetrieveButtonListener());
        retrievePanel.add(retrieveButton);
        tabbedPane.addTab("Retrieve", retrievePanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        connectToDatabase();
        frame.setVisible(true);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "adithye@123");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

private class StudentSubmitButtonListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
    try {
        stmt.executeUpdate("INSERT INTO students (uid, name, age) VALUES (" + studentUIDField.getText() + ", '" + studentNameField.getText() + "', " + studentAgeField.getText() + ")");
        JOptionPane.showMessageDialog(frame, "Student data submitted successfully!");
    } catch (SQLException ex) {
        System.err.println("Error submitting student data: " + ex.getMessage());
        JOptionPane.showMessageDialog(frame, "Error submitting student data: " + ex.getMessage());
    }
    studentNameField.setText("");
    studentAgeField.setText("");
    studentUIDField.setText("");
}
}

private class StaffSubmitButtonListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
    try {
        stmt.executeUpdate("INSERT INTO staff (uid, name, designation) VALUES (" + staffUIDField.getText() + ", '" + staffNameField.getText() + "', '" + staffDesignationField.getText() + "')");
        JOptionPane.showMessageDialog(frame, "Staff data submitted successfully!");
    } catch (SQLException ex) {
        System.err.println("Error submitting staff data: " + ex.getMessage());
        JOptionPane.showMessageDialog(frame, "Error submitting staff data: " + ex.getMessage());
    }
    staffNameField.setText("");
    staffDesignationField.setText("");
    staffUIDField.setText("");
}
}

private class ParentSubmitButtonListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
    try {
        stmt.executeUpdate("INSERT INTO parents (uid, name, contact) VALUES (" + parentUIDField.getText() + ", '" + parentNameField.getText() + "', '" + parentContactField.getText() + "')");
        JOptionPane.showMessageDialog(frame, "Parent data submitted successfully!");
    } catch (SQLException ex) {
        System.err.println("Error submitting parent data: " + ex.getMessage());
        JOptionPane.showMessageDialog(frame, "Error submitting parent data: " + ex.getMessage());
    }
    parentNameField.setText("");
    parentContactField.setText("");
    parentUIDField.setText("");
}
}

private class WorkerSubmitButtonListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
    try {
        stmt.executeUpdate("INSERT INTO workers (uid, name, department) VALUES (" + workerUIDField.getText() + ", '" + workerNameField.getText() + "', '" + workerDepartmentField.getText() + "')");
        JOptionPane.showMessageDialog(frame, "Worker data submitted successfully!");
    } catch (SQLException ex) {
        System.err.println("Error submitting worker data: " + ex.getMessage());
        JOptionPane.showMessageDialog(frame, "Error submitting worker data: " + ex.getMessage());
    }
    workerNameField.setText("");
    workerDepartmentField.setText("");
    workerUIDField.setText("");
}
}

private class RetrieveButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            int uid = Integer.parseInt(uidField.getText());
            String query = "";
            if (studentRadioButton.isSelected()) {
                query = "SELECT * FROM students WHERE uid = " + uid;
            } else if (parentRadioButton.isSelected()) {
                query = "SELECT * FROM parents WHERE uid = " + uid;
            } else if (workerRadioButton.isSelected()) {
                query = "SELECT * FROM workers WHERE uid = " + uid;
            } else if (staffRadioButton.isSelected()) {
                query = "SELECT * FROM staff WHERE uid = " + uid;
            }

            ResultSet resultSet = stmt.executeQuery(query);
            if (resultSet.next()) {
                JFrame resultFrame = new JFrame("Retrieve Result");
                resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultFrame.setSize(400, 200);
                resultFrame.setLayout(new BorderLayout());

                String resultText = "";
                if (studentRadioButton.isSelected()) {
                    resultText = "Name: " + resultSet.getString("name") + "\nAge: " + resultSet.getInt("age");
                } else if (parentRadioButton.isSelected()) {
                    resultText = "Name: " + resultSet.getString("name") + "\nContact: " + resultSet.getString("contact");
                } else if (workerRadioButton.isSelected()) {
                    resultText = "Name: " + resultSet.getString("name") + "\nDepartment: " + resultSet.getString("department");
                } else if (staffRadioButton.isSelected()) {
                    resultText = "Name: " + resultSet.getString("name") + "\nDesignation: " + resultSet.getString("designation");
                }

                JTextArea resultArea = new JTextArea(resultText, 10, 30);
                resultArea.setEditable(false);
                resultFrame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
                resultFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "No data found for UID " + uid);
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving data: " + ex.getMessage());
            JOptionPane.showMessageDialog(frame, "Error retrieving data: " + ex.getMessage());
        }
    }
}

public void createTables() {
try {
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (uid INT PRIMARY KEY, name VARCHAR(255), age INT)");
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS staff (uid INT PRIMARY KEY, name VARCHAR(255), designation VARCHAR(255))");
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS parents (uid INT PRIMARY KEY, name VARCHAR(255), contact VARCHAR(255))");
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS workers (uid INT PRIMARY KEY, name VARCHAR(255), department VARCHAR(255))");
} catch (SQLException e) {
    System.err.println("Error creating tables: " + e.getMessage());
}
}

public void closeConnection() {
try {
    if (stmt != null) {
        stmt.close();
    }
    if (conn != null) {
        conn.close();
    }
} catch (SQLException e) {
    System.err.println("Error closing connection: " + e.getMessage());
}
}

public static void main(String[] args) {
    ProjFebin gui = new ProjFebin();
gui.createTables();
gui.frame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        gui.closeConnection();
    }
});
}
}