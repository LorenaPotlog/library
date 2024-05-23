package myLibrary.repositories;

import myLibrary.Conexiune;
import myLibrary.models.Bibliotecar;
import myLibrary.models.Gen;
import myLibrary.models.Sectiune;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarDAO {


    public void adauga(String nume, String prenume, List<Integer> idSectiuniGestionate, String username, String password) {
        String insertBibliotecarSQL = "INSERT INTO Bibliotecar (nume, prenume) VALUES (?, ?)";
        String insertBibliotecarSectiuneSQL = "INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (?, ?)";
        String insertAuthSQL = "INSERT INTO auth (username, password, idBibliotecar) VALUES (?, ?, ?)";

        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmtBibliotecar = conn.prepareStatement(insertBibliotecarSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtBibliotecarSectiune = conn.prepareStatement(insertBibliotecarSectiuneSQL);
             PreparedStatement stmtAuth = conn.prepareStatement(insertAuthSQL)) {

            // Start transaction
            conn.setAutoCommit(false);

            // Insert into Bibliotecar table
            stmtBibliotecar.setString(1, nume);
            stmtBibliotecar.setString(2, prenume);
            stmtBibliotecar.executeUpdate();

            // Get the generated id for the Bibliotecar
            try (var generatedKeys = stmtBibliotecar.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int bibliotecarId = generatedKeys.getInt(1);

                    // Insert into BibliotecarSectiune table
                    for (int sectiuneId : idSectiuniGestionate) {
                        stmtBibliotecarSectiune.setInt(1, bibliotecarId);
                        stmtBibliotecarSectiune.setInt(2, sectiuneId);
                        stmtBibliotecarSectiune.executeUpdate();
                    }

                    // Insert into auth table
                    stmtAuth.setString(1, username);
                    stmtAuth.setString(2, password);
                    stmtAuth.setInt(3, bibliotecarId);
                    stmtAuth.executeUpdate();
                } else {
                    throw new SQLException("Eroare la adaugare");
                }
            }

            // Commit transaction
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la adaugare", e);
        }
    }

    public List<Bibliotecar> afiseazaToti() {
        List<Bibliotecar> bibliotecari = new ArrayList<>();
        String sql = "SELECT b.id AS bibliotecar_id, b.nume, b.prenume, s.id AS sectiune_id, s.gen " +
                "FROM Bibliotecar b " +
                "LEFT JOIN BibliotecarSectiune bs ON b.id = bs.bibliotecar_id " +
                "LEFT JOIN Sectiune s ON bs.sectiune_id = s.id " +
                "ORDER BY b.id";

        try (Connection conn = Conexiune.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {

            int currentBibliotecarId = -1;
            Bibliotecar currentBibliotecar = null;

            while (rs.next()) {
                int bibliotecarId = rs.getInt("bibliotecar_id");

                if (bibliotecarId != currentBibliotecarId) {
                    currentBibliotecarId = bibliotecarId;

                    if (currentBibliotecar != null) {
                        bibliotecari.add(currentBibliotecar);
                    }

                    currentBibliotecar = new Bibliotecar();
                    currentBibliotecar.setId(bibliotecarId);
                    currentBibliotecar.setNume(rs.getString("nume"));
                    currentBibliotecar.setPrenume(rs.getString("prenume"));
                    currentBibliotecar.setSectiuniGestionate(new ArrayList<>());
                }

                int sectiuneId = rs.getInt("sectiune_id");
                if (sectiuneId > 0) {
                    Sectiune sectiune = new Sectiune();
                    sectiune.setId(sectiuneId);
                    sectiune.setGen(Gen.valueOf(rs.getString("gen")));
                    currentBibliotecar.getSectiuniGestionate().add(sectiune);
                }
            }

            if (currentBibliotecar != null) {
                bibliotecari.add(currentBibliotecar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare", e);
        }

        return bibliotecari;
    }

    public Bibliotecar afiseaza(int id) {
        String sql = "SELECT b.id AS bibliotecar_id, b.nume, b.prenume, s.id AS sectiune_id, s.gen " +
                "FROM Bibliotecar b " +
                "LEFT JOIN BibliotecarSectiune bs ON b.id = bs.bibliotecar_id " +
                "LEFT JOIN Sectiune s ON bs.sectiune_id = s.id " +
                "WHERE b.id = ?";
        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            Bibliotecar bibliotecar = null;
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (bibliotecar == null) {
                        bibliotecar = new Bibliotecar();
                        bibliotecar.setId(rs.getInt("bibliotecar_id"));
                        bibliotecar.setNume(rs.getString("nume"));
                        bibliotecar.setPrenume(rs.getString("prenume"));
                        bibliotecar.setSectiuniGestionate(new ArrayList<>());
                    }

                    int sectiuneId = rs.getInt("sectiune_id");
                    if (sectiuneId > 0) {
                        Sectiune sectiune = new Sectiune();
                        sectiune.setId(sectiuneId);
                        sectiune.setGen(Gen.valueOf(rs.getString("gen")));
                        bibliotecar.getSectiuniGestionate().add(sectiune);
                    }
                }
            }
            return bibliotecar;
        } catch (SQLException e) {
            throw new RuntimeException("Eroare", e);
        }
    }

    public Integer autentificare(String username, String password) {
        String sql = "SELECT idBibliotecar FROM auth WHERE username = ? AND password = ?";

        try (Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idBibliotecar");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare", e);
        }

        return null;
    }

}
