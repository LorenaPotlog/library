package myLibrary.services;

import myLibrary.models.Bibliotecar;
import myLibrary.repositories.BibliotecarDAO;

import static myLibrary.services.Logare.logare;

public class Autentificare {

    private final BibliotecarDAO bibliotecarDao = new BibliotecarDAO();

    public void autentificare(int idBibliotecar, String username, String password) {
        logare("autentificare");
        boolean isAuthenticated = bibliotecarDao.autentificare(idBibliotecar, username, password);
        if (isAuthenticated) {
            Bibliotecar bibliotecar = bibliotecarDao.afiseaza(idBibliotecar);
            if (bibliotecar != null) {
                System.out.println("Urmatorul bibliotecar a fost autentificat:");
                System.out.println(bibliotecar);
            } else {
                System.out.println("Bibliotecar cu id-ul " + idBibliotecar + " nu a fost gasit.");
            }
        } else {
            System.out.println("Invalid username sau parola.");
        }
    }
}