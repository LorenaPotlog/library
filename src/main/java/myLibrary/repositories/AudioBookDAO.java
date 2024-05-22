package myLibrary.repositories;

import myLibrary.Conexiune;
import myLibrary.models.AudioBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AudioBookDAO {

    public void adauga(String titlu, String autor, int an, int idSectiune, int durata) {
        String sql = "INSERT INTO AudioBook (titlu, autor, an, idSectiune, durata,esteDisponibilaPentruImprumut) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titlu);
            stmt.setString(2, autor);
            stmt.setInt(3, an);
            stmt.setInt(4, idSectiune);
            stmt.setInt(5, durata);
            stmt.setBoolean(6, true);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la adaugare", e);
        }
    }

    public void sterge(int id) {
        String sql = "DELETE FROM AudioBook WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergere", e);
        }
    }

    public AudioBook afiseaza(int id) {
        String sql = "SELECT * FROM AudioBook WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la afisare pentru" + id, e);
        }
        return null;
    }

    public List<AudioBook> afiseazaToate() {
        String sql = "SELECT * FROM AudioBook";
        List<AudioBook> audioBooks = new ArrayList<>();
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                audioBooks.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la afisarea tuturor audio book-urilor", e);
        }
        return audioBooks;
    }

    private AudioBook mapper(ResultSet rs) throws SQLException {
        return new AudioBook(
                rs.getInt("id"),
                rs.getString("titlu"),
                rs.getString("autor"),
                rs.getInt("an"),
                rs.getBoolean("esteDisponibilaPentruImprumut"),
                rs.getInt("idSectiune"),
                rs.getInt("durata")
        );
    }

    public void actualizeazaDisponibilitatea(int idAudioBook, boolean disponibil) {
        String sql = "UPDATE AudioBook SET esteDisponibilaPentruImprumut = ? WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, disponibil);
            stmt.setInt(2, idAudioBook);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizare", e);
        }
    }
}
