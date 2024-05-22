package myLibrary.models;

public class Carte extends Articol implements Comparable<Carte> {
    private int volum;

    public Carte(int id, String titlu, String autor, int an, boolean esteDisponibilaPentruImprumut, int idSectiune, int volum) {
        super(id, titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune);
        this.volum = volum;
    }

    public int getVolum() {
        return volum;
    }

    public void setVolum(int volum) {
        this.volum = volum;

    }

    @Override
    public int compareTo(Carte o) {
        return Integer.compare(this.getAnPublicare(), o.getAnPublicare());
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + getId() +
                ", titlu='" + getTitlu() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", anPublicare=" + getAnPublicare() +
                ", esteDisponibilaPentruImprumut=" + getEsteDisponibilaPentruImprumut() +
                ", idSectiune=" + getIdSectiune() +
                ", volum=" + volum +
                '}';
    }
}
