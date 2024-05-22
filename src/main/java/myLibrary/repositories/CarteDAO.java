package myLibrary.repositories;

import myLibrary.Conexiune;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarteDAO {

    public void adauga(String titlu, String autor, int an){
        String sql = "INSERT INTO Carte (titlu, autor, an) VALUES (?, ?, ?)";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titlu);
            stmt.setString(2, autor);
            stmt.setInt(3, an);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
