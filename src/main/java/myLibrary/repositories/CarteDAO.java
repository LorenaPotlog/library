package myLibrary.repositories;

import myLibrary.Conexiune;
import myLibrary.models.Carte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;

public class CarteDAO {

    public void adauga(String titlu, String autor, int an, int idSectiune, int volum) {
        String sql = "INSERT INTO Carte (titlu, autor, an, idSectiune, volum) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titlu);
            stmt.setString(2, autor);
            stmt.setInt(3, an);
            stmt.setInt(4, idSectiune);
            stmt.setInt(5, volum);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la adaugare", e);
        }
    }

    public void actualizeazaAutor(int id, String autor) {
        String sql = "UPDATE Carte SET autor = ? WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizare", e);
        }
    }

    public void actualizeazaTitlu(int id, String titlu) {
        String sql = "UPDATE Carte SET titlu = ? WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titlu);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizare", e);
        }
    }

    public void sterge(int id) {
        String sql = "DELETE FROM Carte WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la stergere", e);
        }
    }
    public Carte afiseaza(int id) {
        String sql = "SELECT * FROM Carte WHERE id = ?";

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

    public SortedSet<Carte> afiseazaToate() {
        String sql = "SELECT * FROM Carte";
        SortedSet<Carte> carti = new TreeSet<>();

        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                carti.add(mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la afisare " + e);
        }

        return carti;
    }

    private Carte mapper(ResultSet rs) throws SQLException {
        return new Carte(
                rs.getInt("id"),
                rs.getString("titlu"),
                rs.getString("autor"),
                rs.getInt("an"),
                rs.getBoolean("esteDisponibilaPentruImprumut"),
                rs.getInt("idSectiune"),
                rs.getInt("volum")
        );
    }

    public void actualizeazaDisponibilitatea(int idCarte, boolean disponibil) {
        String sql = "UPDATE Carte SET esteDisponibilaPentruImprumut = ? WHERE id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, disponibil);
            stmt.setInt(2, idCarte);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la actualizare", e);
        }
    }


}
