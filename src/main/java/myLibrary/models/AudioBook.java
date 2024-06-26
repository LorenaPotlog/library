package myLibrary.models;

public class AudioBook extends Articol{
    private int durata;

    public AudioBook(int id, String titlu, String autor, int an, boolean esteDisponibilaPentruImprumut, int idSectiune, int durata) {
        super(id, titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune);
        this.durata = durata;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "id=" + getId() +
                ", titlu='" + getTitlu() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", anPublicare=" + getAnPublicare() +
                ", esteDisponibilaPentruImprumut=" + getEsteDisponibilaPentruImprumut() +
                ", idSectiune=" + getIdSectiune() +
                ", durata=" + durata +
                '}';
    }
}
