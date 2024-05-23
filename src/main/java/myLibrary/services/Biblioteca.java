package myLibrary.services;

import myLibrary.models.AudioBook;
import myLibrary.models.Bibliotecar;
import myLibrary.models.Carte;
import myLibrary.models.Imprumut;
import myLibrary.repositories.BibliotecarDAO;
import myLibrary.repositories.CarteDAO;
import myLibrary.repositories.AudioBookDAO;
import myLibrary.repositories.ImprumutDAO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import static myLibrary.services.Logare.logare;


public class Biblioteca {
    private ImprumutDAO imprumutDao;
    private AudioBookDAO audioBookDao;
    private CarteDAO carteDao;
    private BibliotecarDAO bibliotecarDao;


    public Biblioteca() {
        this.bibliotecarDao = new BibliotecarDAO();
        this.imprumutDao = new ImprumutDAO();
        this.audioBookDao = new AudioBookDAO();
        this.carteDao = new CarteDAO();
    }

    public void adaugaCarte(String titlu, String autor, int an, int idSectiune, int volum) {
        logare("adaugaCarte");
        carteDao.adauga(titlu, autor, an, idSectiune, volum);
    }

    public Carte afiseazaCarte(int id) {
        logare("afiseazaCarte");
        if( carteDao.afiseaza(id) == null){
            System.out.println("Nu exista cartea cu id-ul: " + id);
        }
        return carteDao.afiseaza(id);
    }

    public List<Carte> afiseazaToateCartile() {
        logare("afiseazaToateCartile");
        return carteDao.afiseazaToate();
    }

    public void modificaCarte(int id, String titlu, String autor) {
        logare("modificaCarte");
        if (titlu != null && !titlu.isEmpty()) {
            carteDao.actualizeazaTitlu(id, titlu);
        }
        if (autor != null && !autor.isEmpty()) {
            carteDao.actualizeazaAutor(id, autor);
        }
    }

    public void stergeCarte(int id) {
        logare("stergeCarte");
        carteDao.sterge(id);
    }

    public void imprumutaCarte(int idCarte, int idCititor, int durataImprumutZile) {
        logare("imprumutaCarte");

        Carte carte = carteDao.afiseaza(idCarte);
        if (carte == null) {
            throw new IllegalArgumentException("Cartea cu id-ul " + idCarte + " nu există.");
        }

        if (!carte.getEsteDisponibilaPentruImprumut()) {
            throw new IllegalStateException("Cartea cu id-ul " + idCarte + " nu este disponibil pentru împrumut.");
        }

        String numeleTabelei = "ImprumutCarte";
        LocalDate dataImprumut = LocalDate.now();
        imprumutDao.adauga(idCarte, idCititor, dataImprumut, durataImprumutZile, numeleTabelei);
        carteDao.actualizeazaDisponibilitatea(idCarte, false);
    }

    public void returneazaCarte(int idImprumut) {
        logare("returneazaCarte");
        Imprumut imprumut = imprumutDao.afiseaza(idImprumut, "ImprumutCarte");
        int idCarte = imprumut.getIdArticol();
        carteDao.actualizeazaDisponibilitatea(idCarte, true);
    }

//    AudioBook

    public void adaugaAudioBook(String titlu, String autor, int an, int idSectiune, int durata) {
        logare("adaugaAudioBook");
        audioBookDao.adauga(titlu, autor, an, idSectiune, durata);
    }

    public AudioBook afiseazaAudioBook(int id) {
        logare("afiseazaAudioBook");
        return audioBookDao.afiseaza(id);
    }

    public List<AudioBook> afiseazaToateAudioBooks() {
        logare("afiseazaToateAudioBooks");
        return audioBookDao.afiseazaToate();
    }

    public void stergeAudioBook(int id) {
        logare("stergeAudioBook");
        audioBookDao.sterge(id);
    }

    public void imprumutaAudioBook(int idAudioBook, int idCititor, int durataImprumutZile) {
        logare("imprumutaAudioBook");

        AudioBook audioBook = audioBookDao.afiseaza(idAudioBook);
        if (audioBook == null) {
            System.out.println("Audiobook-ul cu id-ul " + idAudioBook + " nu există.");
        }

        if (!audioBook.getEsteDisponibilaPentruImprumut()) {
            System.out.println("Audiobook-ul cu id-ul " + idAudioBook + " nu este disponibil pentru împrumut.");
        }

        String numeleTabelei = "ImprumutAudioBook";
        LocalDate dataImprumut = LocalDate.now();
        imprumutDao.adauga(idAudioBook, idCititor, dataImprumut, durataImprumutZile, numeleTabelei);
        audioBookDao.actualizeazaDisponibilitatea(idAudioBook, false);
    }

    public void returneazaAudioBook(int idImprumut) {
        logare("returneazaAudioBook");
        Imprumut imprumut = imprumutDao.afiseaza(idImprumut, "ImprumutAudioBook");
        int idAudioBook = imprumut.getIdArticol();

        audioBookDao.actualizeazaDisponibilitatea(idAudioBook, true);
    }

    //Bibliotecar

    public void adaugaBibliotecar(String nume, String prenume, List<Integer> sectiuni,String username, String password) {
        logare("adaugaBibliotecar");
        bibliotecarDao.adauga(nume, prenume, sectiuni,username,password);
    }

    public List<Bibliotecar> afiseazaBibliotecari() {
        logare("afiseazaBibliotecari");
        return bibliotecarDao.afiseazaToti();
    }


    public List<Imprumut> afiseazaImprumuturiCarti() {
        logare("afiseazaImprumuturiCarti");
        return imprumutDao.afiseazaToate("ImprumutCarte");
    }
}
