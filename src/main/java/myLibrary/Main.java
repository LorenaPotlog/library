package myLibrary;

import myLibrary.repositories.CarteDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sqlFilePath = "src/main/resources/create_tables.sql";
        try (Connection conn = Conexiune.getConnection();
             Statement stmt = conn.createStatement()) {

            BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    System.out.println("Executing SQL: " + sql.toString());
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
            reader.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        CarteDAO carteDAO = new CarteDAO();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book year: ");
                    int year = scanner.nextInt();
//                    carteDAO.adauga(title, author, year);
                    System.out.println("Book added successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    }
