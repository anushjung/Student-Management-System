import javax.swing.*;
import java.awt.*;

public class StudentManagementGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField courseField;
    private JTable studentTable;
    private JScrollPane tableScrollPane;

    private StudentDAO studentDAO;


    public StudentManagementGUI() {

        studentDAO = new StudentDAO();

        setTitle("Student Management System");
        setSize(1000, 700);        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel(
                "Student Management System",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 100, 20, 100
                )
        );

        formPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        add(formPanel, BorderLayout.CENTER);

// Student table
        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Address",
                "Course"
        };

        studentTable = new JTable(
                new Object[0][5],
                columns
        );

        tableScrollPane = new JScrollPane(studentTable);

        add(tableScrollPane, BorderLayout.SOUTH);
        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        viewButton.addActionListener(e -> {

            var students = studentDAO.getAllStudents();

            String[] column = {
                    "ID",
                    "Name",
                    "Age",
                    "Address",
                    "Course"
            };

            Object[][] data = new Object[students.size()][5];

            for (int i = 0; i < students.size(); i++) {

                Student student = students.get(i);

                data[i][0] = student.getId();
                data[i][1] = student.getName();
                data[i][2] = student.getAge();
                data[i][3] = student.getAddress();
                data[i][4] = student.getCourse();
            }

            studentTable.setModel(
                    new javax.swing.table.DefaultTableModel(
                            data,
                            column
                    )
            );

        });

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(tableScrollPane, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
        // Add Student button
        addButton.addActionListener(e -> {

            try {
                int id = Integer.parseInt(
                        idField.getText().trim()
                );

                String name = nameField.getText().trim();

                int age = Integer.parseInt(
                        ageField.getText().trim()
                );

                String address = addressField.getText().trim();
                String course = courseField.getText().trim();

                if (name.isEmpty()
                        || address.isEmpty()
                        || course.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill in all fields."
                    );

                    return;
                }

                Student student = new Student(
                        id,
                        name,
                        age,
                        address,
                        course
                );

                if (studentDAO.addStudent(student)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student added successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to add student."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ID and Age must be numbers."
                );
            }
            studentTable.getSelectionModel().addListSelectionListener(f -> {

                int selectedRow = studentTable.getSelectedRow();

                if (selectedRow >= 0) {

                    idField.setText(
                            studentTable.getValueAt(selectedRow, 0).toString()
                    );

                    nameField.setText(
                            studentTable.getValueAt(selectedRow, 1).toString()
                    );

                    ageField.setText(
                            studentTable.getValueAt(selectedRow, 2).toString()
                    );

                    addressField.setText(
                            studentTable.getValueAt(selectedRow, 3).toString()
                    );

                    courseField.setText(
                            studentTable.getValueAt(selectedRow, 4).toString()
                    );
                }
            });
        });

        // Clear button
        clearButton.addActionListener(e -> clearFields());
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        courseField.setText("");

        idField.requestFocus();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentManagementGUI gui =
                    new StudentManagementGUI();

            gui.setVisible(true);
        });
    }
}