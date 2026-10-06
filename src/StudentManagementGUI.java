import javax.swing.*;
import java.awt.*;

public class StudentManagementGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField courseField;
    private JTextField searchField;


    private JTable studentTable;
    private JScrollPane tableScrollPane;

    private StudentDAO studentDAO;


    public StudentManagementGUI() {

        studentDAO = new StudentDAO();

        // Window settings
        setTitle("Student Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "Student Management System",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 15, 10
                )
        );
        add(titleLabel, BorderLayout.NORTH);

        // =========================
        // FORM
        // =========================

        JPanel formPanel = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        formPanel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 100, 15, 100
                )
        );

        // Student ID
        formPanel.add(new JLabel("Student ID:"));

        idField = new JTextField();
        formPanel.add(idField);

        // Name
        formPanel.add(new JLabel("Name:"));

        nameField = new JTextField();
        formPanel.add(nameField);

        // Age
        formPanel.add(new JLabel("Age:"));

        ageField = new JTextField();
        formPanel.add(ageField);

        // Address
        formPanel.add(new JLabel("Address:"));

        addressField = new JTextField();
        formPanel.add(addressField);

        // Course
        formPanel.add(new JLabel("Course:"));

        courseField = new JTextField();
        formPanel.add(courseField);

        add(formPanel, BorderLayout.CENTER);

        // =========================
        // TABLE
        // =========================

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

        studentTable.setRowHeight(28);

        studentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        studentTable.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        studentTable.getTableHeader().setReorderingAllowed(false);

        tableScrollPane = new JScrollPane(studentTable);
        tableScrollPane.setPreferredSize(
                new Dimension(900, 220)
        );
        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        8
                )
        );
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

        Font buttonFont = new Font(
                "Arial",
                Font.BOLD,
                13
        );

        addButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        addButton.setFocusPainted(false);
        viewButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);



        // =========================
// SEARCH PANEL
// =========================

        JPanel searchPanel = new JPanel();

        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        JButton clearSearchButton = new JButton("Clear Search");

        searchPanel.add(
                new JLabel("Search Name:")
        );

        searchPanel.add(searchField);

        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);


// =========================
// BOTTOM PANEL
// =========================

        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // ADD STUDENT
        // =========================

        addButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        idField.getText().trim()
                );


                String name = nameField.getText().trim();

                int age = Integer.parseInt(
                        ageField.getText().trim()
                );
                if (id <= 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student ID must be greater than 0."
                    );

                    return;
                }

                if (age < 1 || age > 100) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Age must be between 1 and 100."
                    );

                    return;
                }

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
                if (!isValidName(name)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Name can contain only letters and spaces."
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

                    loadStudents();

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
        });

        // =========================
        // VIEW STUDENTS
        // =========================

        viewButton.addActionListener(e -> loadStudents());
        // =========================
// SEARCH STUDENTS
// =========================

        searchButton.addActionListener(e -> {

            String name = searchField.getText().trim();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a name to search."
                );

                return;
            }

            var students =
                    studentDAO.findStudentsByName(name);

            String[] column = {
                    "ID",
                    "Name",
                    "Age",
                    "Address",
                    "Course"
            };

            Object[][] data =
                    new Object[students.size()][5];

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
                            columns
                    )
            );

            if (students.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No students found."
                );
            }
        });

        clearSearchButton.addActionListener(e -> {

            searchField.setText("");

            loadStudents();
        });

        // =========================
        // UPDATE STUDENT
        // =========================

        updateButton.addActionListener(e -> {

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

                if (studentDAO.updateStudent(student)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student updated successfully!"
                    );

                    clearFields();

                    loadStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student not found or update failed."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "ID and Age must be numbers."
                );
            }
        });

        // =========================
        // DELETE STUDENT
        // =========================

        deleteButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        idField.getText().trim()
                );

                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                if (studentDAO.deleteStudent(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deleted successfully!"
                    );

                    clearFields();

                    loadStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student not found or delete failed."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student first."
                );
            }
        });

        // =========================
        // CLEAR BUTTON
        // =========================

        clearButton.addActionListener(e -> clearFields());

        // =========================
        // TABLE ROW SELECTION
        // =========================

        studentTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int selectedRow =
                                studentTable.getSelectedRow();

                        if (selectedRow >= 0) {

                            idField.setText(
                                    studentTable
                                            .getValueAt(
                                                    selectedRow,
                                                    0
                                            )
                                            .toString()
                            );

                            nameField.setText(
                                    studentTable
                                            .getValueAt(
                                                    selectedRow,
                                                    1
                                            )
                                            .toString()
                            );

                            ageField.setText(
                                    studentTable
                                            .getValueAt(
                                                    selectedRow,
                                                    2
                                            )
                                            .toString()
                            );

                            addressField.setText(
                                    studentTable
                                            .getValueAt(
                                                    selectedRow,
                                                    3
                                            )
                                            .toString()
                            );

                            courseField.setText(
                                    studentTable
                                            .getValueAt(
                                                    selectedRow,
                                                    4
                                            )
                                            .toString()
                            );
                        }
                    }
                }
        );
    }

    // =========================
    // LOAD STUDENTS INTO TABLE
    // =========================

    private void loadStudents() {

        var students = studentDAO.getAllStudents();

        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Address",
                "Course"
        };

        Object[][] data =
                new Object[students.size()][5];

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
                        columns
                )
        );
    }

    private boolean isValidName(String name) {

        return name.matches("[a-zA-Z ]+");
    }

    // =========================
    // CLEAR FORM
    // =========================

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        courseField.setText("");

        idField.requestFocus();
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentManagementGUI gui =
                    new StudentManagementGUI();

            gui.setVisible(true);
        });
    }
}