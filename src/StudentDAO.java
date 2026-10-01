import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDAO {

    public boolean addStudent(Student student) {

        String sql = """
                INSERT INTO students
                (id, name, age, address, course)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getCourse());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("address"),
                        resultSet.getString("course")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }

        return students;
    }
    public Student findStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("address"),
                            resultSet.getString("course")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }

        return null;
    }
    public ArrayList<Student> findStudentsByName(String name) {

        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("address"),
                            resultSet.getString("course")
                    );

                    students.add(student);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching students: " + e.getMessage());
        }

        return students;
    }
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
    public boolean updateStudent(Student student) {

        String sql = """
            UPDATE students
            SET name = ?, age = ?, address = ?, course = ?
            WHERE id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getId());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
}