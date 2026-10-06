import java.sql.Connection;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/student_management";

    private static final String USER =
            System.getenv("DB_USER");

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    public static Connection getConnection() {

        try {
            return java.sql.DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        Connection connection = getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");
        }
    }
}