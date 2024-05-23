package myLibrary.helpers;

import myLibrary.Conexiune;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class helpers {

    public static void h2Initialization() {
        String sqlFilePath1 = "src/main/resources/create_tables.sql";
        String sqlFilePath2 = "src/main/resources/insert_test.sql"; // Path to your second SQL file
        try (Connection conn = Conexiune.getConnection(); Statement stmt = conn.createStatement()) {

            executeSqlFile(sqlFilePath1, stmt);
            executeSqlFile(sqlFilePath2, stmt);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidSectionId(int idSectiune) {
        Set<Integer> validSectionIds = new HashSet<>(Set.of(1, 2, 3, 4));
        return validSectionIds.contains(idSectiune);
    }

    public static void executeSqlFile(String sqlFilePath, Statement stmt) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
        String line;
        StringBuilder sql = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sql.append(line);
            if (line.trim().endsWith(";")) {
                System.out.println("Executing SQL: " + sql);
                stmt.execute(sql.toString());
                sql.setLength(0);
            }
        }
        reader.close();
    }
}
