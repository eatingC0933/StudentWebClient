package se.yitingchang.studentwebclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Component
public class StudentClient implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8090/api/students";

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMENU:");
            System.out.println("1. Show all students");
            System.out.println("2. Add a student");
            System.out.println("3. Update a student");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    Student[] students = restTemplate.getForObject(baseUrl, Student[].class);
                    if (students != null) {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Student newStudent = new Student(name, age, email);
                    Student saved = restTemplate.postForObject(baseUrl, newStudent, Student.class);
                    System.out.println("Saved: " + saved);
                }
                case 3 -> {
                    System.out.print("Enter student ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New name: ");
                    String name = scanner.nextLine();
                    System.out.print("New age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New email: ");
                    String email = scanner.nextLine();

                    Student updatedStudent = new Student(id, name, age, email);
                    restTemplate.put(baseUrl + "/" + id, updatedStudent);
                    System.out.println("Updated.");
                }
                case 0 -> System.out.println("Bye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
