package myLibrary.services;

import myLibrary.models.Bibliotecar;
import myLibrary.repositories.BibliotecarDAO;

import static myLibrary.services.Logare.logare;

public class Autentificare {

    private final BibliotecarDAO bibliotecarDao = new BibliotecarDAO();

    public void autentificare(String username, String password) {
        logare("autentificare");
        Integer bibliotecarId = bibliotecarDao.autentificare(username, password);
        if (bibliotecarId != null) {
            Bibliotecar bibliotecar = bibliotecarDao.afiseaza(bibliotecarId);
            if (bibliotecar != null) {
                System.out.println("Urmatorul bibliotecare a fost autentificat:");
                System.out.println(bibliotecar);
            } else {
                System.out.println("Bibliotecar cu id-ul " + bibliotecarId + " nu a fost gasit.");
            }
        } else {
            System.out.println("Invalid username sau parola.");
        }
    }
}