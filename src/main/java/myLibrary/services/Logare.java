package myLibrary.services;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logare {

    private static final String FILE_PATH = "logs.csv";

    public static void logare(String actiune) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String record = actiune + "," + getCurrentDateTime() + "\n";
            writer.write(record);
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
    private static String getCurrentDateTime () {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}