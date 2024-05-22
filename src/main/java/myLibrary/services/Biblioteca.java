package myLibrary.services;

import myLibrary.models.Imprumut;
import myLibrary.repositories.CarteDAO;
import myLibrary.repositories.AudioBookDAO;
import myLibrary.repositories.ImprumutDAO;

import java.time.LocalDate;


public class Biblioteca {
    private ImprumutDAO imprumutDao;
    private AudioBookDAO audioBookDao;
    private CarteDAO    carteDAO;

    public Biblioteca() {
        this.imprumutDao = new ImprumutDAO();
        this.audioBookDao = new AudioBookDAO();
        this.carteDAO = new CarteDAO();
    }

    public void adaugaCarte(){
    }

    public void afiseazaCarte(){

    }

    public void modificaCarte(){

    }

    public void stergeCarte(){

    }
    public void imprumutaCarte(int idCarte, int idCititor, int durataImprumutZile) {
        String numeleTabelei = "ImprumutCarte";
        LocalDate dataImprumut = LocalDate.now();
        imprumutDao.adaugaImprumut(idCarte, idCititor, dataImprumut, durataImprumutZile,numeleTabelei);
        audioBookDao.actualizeazaDisponibilitatea(idCarte, false);
    }

    public void returneazaCarte(int idImprumut) {
            Imprumut imprumut = imprumutDao.getImprumut(idImprumut, "ImprumutCarte");
            int idCarte = imprumut.getIdArticol();
            carteDAO.actualizeazaDisponibilitatea(idCarte, true);
        }

//    AudioBook


    public void adaugaAudioBook(){

    }

    public void afiseazaAudioBook(){

    }

    public void modificaAudioBook(){

    }

    public void stergeAudioBook(){
    }

    public void imprumutaAudioBook(int idAudioBook, int idCititor, int durataImprumutZile) {
        String numeleTabelei = "ImprumutAudioBook";
        LocalDate dataImprumut = LocalDate.now();
        imprumutDao.adaugaImprumut(idAudioBook, idCititor, dataImprumut, durataImprumutZile,numeleTabelei);
        audioBookDao.actualizeazaDisponibilitatea(idAudioBook, false);
    }

    public void returneazaAudioBook(int idImprumut) {
        Imprumut imprumut = imprumutDao.getImprumut(idImprumut, "ImprumutAudioBook");
        int idAudioBook = imprumut.getIdArticol();

        audioBookDao.actualizeazaDisponibilitatea(idAudioBook, true);
    }
}
