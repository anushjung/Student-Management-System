import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    private final StudentDAO studentDAO;
    private ArrayList<Student> students;
    // Constructor
    public StudentManager() {
        studentDAO = new StudentDAO();
        students = studentDAO.getAllStudents();
    }

    // Check if student ID already exists
    public boolean studentIdExists(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // Add Student
    public void addStudent(Student student) {

        if (studentDAO.addStudent(student)) {
            students.add(student);
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    // Get total number of students
    public int getStudentCount() {
        return students.size();
    }

    public void viewStudents() {

        students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n====================== STUDENT LIST ======================");
        System.out.printf("%-6s %-20s %-6s %-15s %-20s%n",
                "ID", "Name", "Age", "Course", "Address");

        System.out.println("--------------------------------------------------------------------------");

        for (Student student : students) {
            System.out.printf("%-6d %-20s %-6d %-15s %-20s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getAddress());
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Students: " + students.size());
    }

    // Search Student
    public Student searchStudent(int id) {
        return studentDAO.findStudentById(id);
    }
    // Search students by name
    public ArrayList<Student> searchStudentsByName(String name) {
        return studentDAO.findStudentsByName(name);
    }

    // Display one student
    public void displayStudent(Student student) {

        System.out.println("ID       : " + student.getId());
        System.out.println("Name     : " + student.getName());
        System.out.println("Age      : " + student.getAge());
        System.out.println("Address  : " + student.getAddress());
        System.out.println("Course   : " + student.getCourse());
    }

    // Update Student
    public boolean updateStudent(
            int id,
            String name,
            int age,
            String address,
            String course) {

        Student student = new Student(
                id,
                name,
                age,
                address,
                course
        );

        if (studentDAO.updateStudent(student)) {

            // Update the in-memory list
            for (int i = 0; i < students.size(); i++) {

                if (students.get(i).getId() == id) {
                    students.set(i, student);
                    break;
                }
            }

            return true;
        }

        return false;
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student != null && studentDAO.deleteStudent(id)) {
            students.remove(student);
            return true;
        }

        return false;
    }
    // Sort students by ID
    public void sortById() {

        students.sort(
                Comparator.comparingInt(Student::getId)
        );

        System.out.println("Students sorted by ID.");
    }
    // Sort students by name
    public void sortByName() {

        students.sort(
                Comparator.comparing(
                        Student::getName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("Students sorted by name.");
    }
    // Show course statistics
    public void showCourseStatistics() {

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        Map<String, Integer> courseCounts = new HashMap<>();

        for (Student student : students) {

            String course = student.getCourse();

            courseCounts.put(
                    course,
                    courseCounts.getOrDefault(course, 0) + 1
            );
        }

        System.out.println("\n========== COURSE STATISTICS ==========");

        for (Map.Entry<String, Integer> entry : courseCounts.entrySet()) {

            System.out.println(
                    entry.getKey() + " : " + entry.getValue() + " student(s)"
            );
        }
    }
    // Show student statistics
    public void showStatistics() {

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        int totalStudents = students.size();
        int totalAge = 0;

        Student youngest = students.get(0);
        Student oldest = students.get(0);

        for (Student student : students) {

            totalAge += student.getAge();

            if (student.getAge() < youngest.getAge()) {
                youngest = student;
            }

            if (student.getAge() > oldest.getAge()) {
                oldest = student;
            }
        }

        double averageAge = (double) totalAge / totalStudents;

        System.out.println("\n========== STUDENT STATISTICS ==========");
        System.out.println("Total Students : " + totalStudents);
        System.out.printf("Average Age    : %.2f%n", averageAge);

        System.out.println("\nYoungest Student:");
        displayStudent(youngest);

        System.out.println("\nOldest Student:");
        displayStudent(oldest);
    }
    public void displayStudents(ArrayList<Student> students) {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n====================== STUDENT LIST ======================");
        System.out.printf("%-6s %-20s %-6s %-15s %-20s%n",
                "ID", "Name", "Age", "Course", "Address");

        System.out.println("--------------------------------------------------------------------------");

        for (Student student : students) {
            System.out.printf("%-6d %-20s %-6d %-15s %-20s%n",
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getAddress());
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Students: " + students.size());
    }
}