package myLibrary.repositories;

import java.sql.*;
import java.time.LocalDate;

import myLibrary.Conexiune;
import myLibrary.models.Imprumut;


public class ImprumutDAO {

    public void adaugaImprumut(int idArticol, int idCititor, LocalDate dataImprumut, int durataImprumutZile, String tableName) {
        String sql = "INSERT INTO " + tableName + " (idArticol, idCititor, dataImprumut, durataImprumutZile) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArticol);
            stmt.setInt(2, idCititor);
            stmt.setDate(3, Date.valueOf(dataImprumut));
            stmt.setInt(4, durataImprumutZile);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la imprumutare", e);
        }
    }

    public Imprumut getImprumut(int idImprumut, String tableName) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idImprumut);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la afisare pentru " + idImprumut, e);
        }
        return null;
    }

    private Imprumut mapper(ResultSet rs) throws SQLException {
        return new Imprumut(
                rs.getInt("idArticol"),
                rs.getInt("idCititor"),
                rs.getDate("dataImprumut").toLocalDate(),
                rs.getInt("durataImprumutZile")
        );
    }

}
