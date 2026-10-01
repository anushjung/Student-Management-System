import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n====================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Student Statistics");
            System.out.println("8. Course Statistics");
            System.out.println("9. Exit");
            System.out.println("====================================");

            int choice = readInt(scanner, "Enter your choice: ");

            // ADD STUDENT
            if (choice == 1) {

                int id = readPositiveInt(scanner, "Enter Student ID: ");

                if (manager.studentIdExists(id)) {

                    System.out.println("Student ID already exists!");

                } else {

                    String name = readText(scanner, "Enter Student Name: ");

                    int age = readAge(scanner);

                    String address = readText(scanner, "Enter Address: ");

                    String course = readText(scanner, "Enter Course: ");

                    Student student = new Student(
                            id,
                            name,
                            age,
                            address,
                            course
                    );

                    manager.addStudent(student);
                }
            }

            // VIEW STUDENTS
            else if (choice == 2) {

                manager.viewStudents();
            }

            // SEARCH STUDENT
            else if (choice == 3) {

                System.out.println("\n========== SEARCH STUDENT ==========");
                System.out.println("1. Search by ID");
                System.out.println("2. Search by Name");

                int searchChoice = readInt(
                        scanner,
                        "Enter your choice: "
                );

                if (searchChoice == 1) {

                    int id = readPositiveInt(
                            scanner,
                            "Enter Student ID: "
                    );

                    Student student = manager.searchStudent(id);

                    if (student != null) {

                        System.out.println("\n========== STUDENT FOUND ==========");
                        manager.displayStudent(student);

                    } else {

                        System.out.println("Student not found!");
                    }

                } else if (searchChoice == 2) {

                    String name = readText(
                            scanner,
                            "Enter Student Name: "
                    );

                    var results = manager.searchStudentsByName(name);

                    if (results.isEmpty()) {

                        System.out.println("No students found!");

                    } else {

                        System.out.println(
                                "\n========== SEARCH RESULTS =========="
                        );

                        for (Student student : results) {

                            manager.displayStudent(student);

                            System.out.println("----------------------------------");
                        }

                        System.out.println(
                                "Students found: " + results.size()
                        );
                    }

                } else {

                    System.out.println("Invalid search option!");
                }
            }

            // UPDATE STUDENT
            else if (choice == 4) {

                int id = readPositiveInt(
                        scanner,
                        "Enter Student ID to update: "
                );

                Student student = manager.searchStudent(id);

                if (student != null) {

                    String name = readText(
                            scanner,
                            "Enter new name: "
                    );

                    int age = readAge(scanner);

                    String address = readText(
                            scanner,
                            "Enter new address: "
                    );

                    String course = readText(
                            scanner,
                            "Enter new course: "
                    );

                    manager.updateStudent(
                            id,
                            name,
                            age,
                            address,
                            course
                    );

                    System.out.println(
                            "Student updated successfully!"
                    );

                } else {

                    System.out.println("Student not found!");
                }
            }

            // DELETE STUDENT
            else if (choice == 5) {

                int id = readPositiveInt(
                        scanner,
                        "Enter Student ID to delete: "
                );

                Student student = manager.searchStudent(id);

                if (student != null) {

                    System.out.println("\nStudent found:");
                    manager.displayStudent(student);

                    System.out.print("\nAre you sure you want to delete this student? (yes/no): ");
                    String confirmation = scanner.nextLine().trim();

                    if (confirmation.equalsIgnoreCase("yes")) {

                        boolean deleted = manager.deleteStudent(id);

                        if (deleted) {
                            System.out.println("Student deleted successfully!");
                        }

                    } else {

                        System.out.println("Delete operation cancelled.");
                    }

                } else {

                    System.out.println("Student not found!");
                }
            }
            // SORT STUDENTS
            else if (choice == 6) {

                System.out.println("\n========== SORT STUDENTS ==========");
                System.out.println("1. Sort by ID");
                System.out.println("2. Sort by Name");

                int sortChoice = readInt(
                        scanner,
                        "Enter your choice: "
                );

                if (sortChoice == 1) {

                    manager.sortById();

                } else if (sortChoice == 2) {

                    manager.sortByName();

                } else {

                    System.out.println("Invalid sorting option!");
                }
            }
            // STUDENT STATISTICS
            else if (choice == 7) {

                manager.showStatistics();
            }

// COURSE STATISTICS
            else if (choice == 8) {

                manager.showCourseStatistics();
            }



            // EXIT
            else if (choice == 9) {

                System.out.println(
                        "\nThank you for using Student Management System!"
                );

                break;

            } else {

                System.out.println(
                        "Invalid choice! Please enter 1-6."
                );
            }
        }

        scanner.close();
    }

    // Read integer safely
    public static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a number."
                );
            }
        }
    }

    // Read positive integer
    public static int readPositiveInt(
            Scanner scanner,
            String message) {

        while (true) {

            int number = readInt(scanner, message);

            if (number > 0) {
                return number;
            }

            System.out.println(
                    "Please enter a positive number."
            );
        }
    }

    // Read non-empty text
    public static String readText(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String text = scanner.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println(
                    "This field cannot be empty."
            );
        }
    }

    // Read valid age
    public static int readAge(Scanner scanner) {

        while (true) {

            int age = readInt(
                    scanner,
                    "Enter Age: "
            );

            if (age >= 5 && age <= 100) {
                return age;
            }

            System.out.println(
                    "Age must be between 5 and 100."
            );
        }
    }
}