package myLibrary.models;

import java.time.LocalDate;

public class Imprumut {
    private int idArticol;
    private int idCititor;
    private LocalDate dataImprumut;
    private int durataImprumutZile;

    public int getIdArticol() {
        return idArticol;
    }

    public void setIdArticol(int idArticol) {
        this.idArticol = idArticol;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public void setIdCititor(int idCititor) {
        this.idCititor = idCititor;
    }

    public LocalDate getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(LocalDate dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public int getDurataImprumutZile() {
        return durataImprumutZile;
    }

    public void setDurataImprumutZile(int durataImprumutZile) {
        this.durataImprumutZile = durataImprumutZile;
    }

    @Override
    public String toString() {
        String formatZile = durataImprumutZile > 1 ? "zile" : "zi";
        return String.format("Articolul cu id-ul %s a fost imprumutata de catre clientul cu id-ul %s, in data de %s, pentru perioada de %s %s." , idArticol,
                idCititor, dataImprumut, durataImprumutZile, formatZile);
    }
}
