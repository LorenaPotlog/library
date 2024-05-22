package myLibrary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DatabaseInitializer {
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
            System.out.println("Tables created successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
