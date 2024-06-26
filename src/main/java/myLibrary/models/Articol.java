package myLibrary.models;

public class Articol {
    private int id;
    private String titlu;
    private String autor;
    private int anPublicare;
    private Boolean esteDisponibilaPentruImprumut = true;
    private int idSectiune;

    public Articol(int id, String titlu, String autor, int an, boolean esteDisponibilaPentruImprumut, int idSectiune) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.anPublicare = an;
        this.esteDisponibilaPentruImprumut = esteDisponibilaPentruImprumut;
        this.idSectiune = idSectiune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAnPublicare() {
        return anPublicare;
    }

    public void setAnPublicare(int anPublicare) {
        this.anPublicare = anPublicare;
    }

    public Boolean getEsteDisponibilaPentruImprumut() {
        return esteDisponibilaPentruImprumut;
    }

    public void setEsteDisponibilaPentruImprumut(Boolean esteDisponibilaPentruImprumut) {
        this.esteDisponibilaPentruImprumut = esteDisponibilaPentruImprumut;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdSectiune() {
        return idSectiune;
    }

    public void setIdSectiune(int idSectiune) {
        this.idSectiune = idSectiune;
    }

    @Override
    public String toString() {
        return "Articol{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", anPublicare=" + anPublicare +
                ", esteDisponibilaPentruImprumut=" + esteDisponibilaPentruImprumut +
                ", idSectiune=" + idSectiune +
                '}';
    }
}
